package BankAccount

import akka.actor.{ActorRef, Actor}
import akka.event.LoggingReceive

/**
 * Created by alex on 09/08/14.
 */

//possible messages WireTransfer actor can receive
object WireTransfer {

  case class Transfer(from: ActorRef, to: ActorRef, amount: BigInt)

  case object Done

  case object Failed

}

//actor implementing the actions of a wire transfer between two bank account actors
class WireTransfer extends Actor {

  import WireTransfer._

  def receive = LoggingReceive {
    //If Transfer message is received, send withdraw message to 'from' and wait for reply
    case Transfer(from, to, amount) =>
      from ! BankAccount.Withdraw(amount)
      context.become(awaitFrom(to, amount, sender))
  }

  //If Withdraw was successful, send deposit to other bank account actor, or else give them a failure message
  def awaitFrom(to: ActorRef, amount: BigInt, customer: ActorRef): Receive = LoggingReceive {
    case BankAccount.Done =>
      to ! BankAccount.Deposit(amount)
      context.become(awaitTo(customer))
    case BankAccount.Failed =>
      customer ! Failed
      context.stop(self)
  }

  //If deposit was successful, send 'Done' to original actor that sent Transfer message
  def awaitTo(customer: ActorRef): Receive = LoggingReceive {
    case BankAccount.Done =>
      customer ! Done
      context.stop(self)
  }
}
