package LinkChecker

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorLogging
import akka.actor.ReceiveTimeout
import scala.concurrent.duration._
import akka.actor.ActorRef

/**
 * Created by alex on 09/08/14.
 */

//Messages controller can receive
object Controller {

  case class Check(url: String, depth: Int)

  case class Result(links: Set[String])

}

class Controller extends Actor with ActorLogging {

  import Controller._

  //These collections must be immutable so they can be shared.
  //URLs that have already been seen
  var cache = Set.empty[String]
  //Getter actors that the controller has created
  var children = Set.empty[ActorRef]

  context.setReceiveTimeout(10.seconds)

  def receive = {

    //If Check message is received (from a Getter actor), create a Getter actor to explore it
    case Check(url, depth) =>
      log.debug("{} checking {}", depth, url)
      if (!cache(url) && depth > 0)
        children += context.actorOf(Props(new Getter(url, depth - 1)))
      cache += url

    //when Getter is finished, remove it from children set
    //if all children are finished, send result to parent
    case Getter.Done =>
      children -= sender
      if (children.isEmpty)
        context.parent ! Result(cache)

    //If it is taking to long, tell each Getter child actor to stop
    case ReceiveTimeout =>
      context.children foreach (_ ! Getter.Abort)
  }

}
