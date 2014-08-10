package LinkChecker

import akka.actor.Actor
import akka.actor.Props
import scala.concurrent.duration._
import akka.actor.ReceiveTimeout

/**
 * Created by alex on 10/08/14.
 */
class Main extends Actor {

  import Receptionist._

  //create receptionist actor
  val receptionist = context.actorOf(Props[Receptionist], "receptionist")

  //send it message to get urls from specified root url
  receptionist ! Get("http://www.google.com")

  context.setReceiveTimeout(10.seconds)

  def receive = {
    //When all the actors are finished, ti will receive a result message or failure or timeout
    case Result(url, set) =>
      println(set.toVector.sorted.mkString(s"Results for '$url':\n", "\n", "\n"))
    case Failed(url) =>
      println(s"Failed to fetch '$url'\n")
    case ReceiveTimeout =>
      context.stop(self)

  }

  override def postStop(): Unit = {
    WebClient.shutdown()
  }
}
