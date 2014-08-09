package CounterActor

import akka.actor.Actor


/**
 * Created by alex on 09/08/14.
 */

//Actor that actually implements the counting
class CounterActor extends Actor {

  //If incr message is received, increment state
  //If get message is received, send sender the current state
  def counter(n: Int): Receive = {
    case "incr" => context.become(counter(n + 1))
    case "get" => sender ! n
  }

  def receive = counter(0)

}
