package LinkChecker

import akka.actor.Actor
import akka.pattern.pipe
import java.util.concurrent.Executor
import akka.actor.Status
import scala.concurrent.ExecutionContext

/**
 * Created by alex on 09/08/14.
 */

//possible messages getter can receive
object Getter {

  case object Done

  case object Abort

}

//Actor that receives webpage bodies as message, finds links in them and sends them to its parent
class Getter(url: String, depth: Int) extends Actor {

  import Getter._

  //need this for next line
  implicit val executor = context.dispatcher.asInstanceOf[Executor with ExecutionContext]

  //ask WebClient to get url.  It will return a future.  When it is complete, pipe it to self.
  // So this actor sends a message to itself which is kind of weird
  WebClient get url pipeTo self

  //If receive body message, find its links and send result to parent
  def receive = {
    case body: String =>
      for (link <- findLinks(body))
        context.parent ! Controller.Check(link, depth)
      stop()
    case _: Status.Failure => stop()
    case Abort => stop()
  }

  def stop(): Unit = {
    context.parent ! Done
    context.stop(self)
  }

  //functionality to find links in string using regex
  val A_TAG = "(?i)<a ([^>]+)>.+?</a>".r
  val HREF_ATTR = """\s*(?i)href\s*=\s*(?:"([^"]*)"|'([^']*)'|([^'">\s]+))\s*""".r

  def findLinks(body: String): Iterator[String] = {
    for {
      anchor <- A_TAG.findAllMatchIn(body)
      HREF_ATTR(dquot, quot, bare) <- anchor.subgroups
    } yield if (dquot != null) dquot
    else if (quot != null) quot
    else bare
  }


}
