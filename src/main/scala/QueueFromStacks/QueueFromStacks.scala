package QueueFromStacks

import scala.collection.mutable.Stack

class QueueFromStacks[T] {

  var stack1: Stack[T] = new Stack()
  var stack2: Stack[T] = new Stack()

  def enqueue(el: T) {
    stack1.push(el)
  }

  def dequeue:T = {
    if (stack2.isEmpty) {
      while (!stack1.isEmpty) {
        stack2.push(stack1.pop())
      }
    }
    stack2.pop()
  }
}

object QueueTest extends App{

  val testQueue=new QueueFromStacks[Double]

  testQueue.enqueue(2.2)
  testQueue.enqueue(3.1)
  testQueue.enqueue(4.6)

  println(testQueue.dequeue)
  println(testQueue.dequeue)
  println(testQueue.dequeue)
}


