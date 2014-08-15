package FixedPoints

/**
 * Created by alex on 14/08/14.
 */
//Tail-recursive higher-order function that sums the input function over the input interval
object AnySum extends App {

  def sum(f: Int => Int, a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, acc + f(a))
    }
    loop(a, 0)
  }

  //Test with the cubic function
  val cubeSum = sum(x => x * x * x, 3, 7)

  println(cubeSum)
}
