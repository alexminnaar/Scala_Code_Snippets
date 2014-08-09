package BankAccount

import akka.actor.Actor
import akka.actor.Props
import akka.event.LoggingReceive

/**
 * Created by alex on 09/08/14.
 */

//class creating actors to test WireTransfer action
class TransferMain extends Actor {

  //First create two BankAccount actors
  val accountA = context.actorOf(Props[BankAccount], "accountA")
  val accountB = context.actorOf(Props[BankAccount], "accountB")

  //send a deposit message to accountA
  accountA ! BankAccount.Deposit(100)

  //If a 'Done' message is received back, call a transfer function
  def receive = LoggingReceive {
    case BankAccount.Done => transfer(70)
  }


  //transfer function creates a transacton actor and sends a 'Transfer' message to it between
  //accountA and accountB for the specified amount.
  def transfer(amount: BigInt): Unit = {

    val transaction = context.actorOf(Props[WireTransfer], "transfer")

    transaction ! WireTransfer.Transfer(accountA, accountB, amount)

    context.become(LoggingReceive {
      case WireTransfer.Done =>
        println("successs")
        context.stop(self)
      case WireTransfer.Failed =>
        println("failed")
        context.stop(self)
    })

  }
}
