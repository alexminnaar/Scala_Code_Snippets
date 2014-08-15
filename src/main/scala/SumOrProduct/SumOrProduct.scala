package SumOrProduct

/**
 * Created by alex on 14/08/14.
 */
//Very general function that applies a function over the specified interval and combines the subsequent
//values in a way defined by the combine function
object SumOrProduct extends App {

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
    if (a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
  }

  //For example, sum of squares
  val mapReduceTest = mapReduce(x => x * x, (u, v) => u + v, 1)(1, 6)

  println(mapReduceTest)
}
