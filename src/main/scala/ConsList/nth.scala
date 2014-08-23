package ConsList

/**
 * Created by alex on 22/08/14.
 */
object nth extends App {

  //Function recursively finding the element in the nth index of the list with just head and tail functions
  def nth[T](n: Int, xs: List[T]): T = {
    if (xs.isEmpty) throw new IndexOutOfBoundsException
    else if (n == 0) xs.head
    else nth(n - 1, xs.tail)
  }

  val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))

  println(nth(2, list))

  println(nth(-1, list))
}
