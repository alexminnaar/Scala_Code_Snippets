package CounterActor

import akka.actor.Actor
import akka.actor.Props

/**
 * Created by alex on 09/08/14.
 */

//To run in IntelliJ, set Main Class to akka.Main and Program Arguments to CounterActor.CounterMain

//Actor that sends messages to CounterActor
class CounterMain extends Actor {

  //create the counter actor from the CounterActor class
  val counter = context.actorOf(Props[CounterActor], "counter")

  //send it incr message 3 times then send get message
  counter ! "incr"
  counter ! "incr"
  counter ! "incr"
  counter ! "get"

  //If this actor receive count, print it
  def receive = {
    case count: Int =>
      println(s"count was $count")
      context.stop(self)
  }


}
