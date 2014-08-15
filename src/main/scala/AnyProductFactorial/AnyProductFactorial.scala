package AnyProductFactorial

/**
 * Created by alex on 14/08/14.
 */
//The product of the input function over the input interval
//Also the factorial function in terms of the product function
object AnyProductFactorial extends App {

  def product(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1
    else f(a) * product(f)(a + 1, b)
  }

  def factorial(n: Int) = product(x => x)(1, n)

  val testProduct = product(x => x * x)(2, 5)
  println(testProduct)

  val testFactorial = factorial(7)
  println(testFactorial)
}
