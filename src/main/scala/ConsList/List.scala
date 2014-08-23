package ConsList

/**
 * Created by alex on 22/08/14.
 */
trait List[T] {
  def isEmpty: Boolean

  def head: T

  def tail: List[T]
}

object List {
  // allows creation of List(1,2)
  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil))
}
