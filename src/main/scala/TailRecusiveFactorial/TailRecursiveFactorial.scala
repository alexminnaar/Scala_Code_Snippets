package TailRecusiveFactorial

/**
 * Created by alex on 19/07/14.
 */
object TailRecursiveFactorial extends App {

  def factorial(n: Int): Int = {

    //Recursive call is last so tail recursion occurs
    def loop(accumulator: Int, n: Int): Int = {
      if (n == 0) accumulator
      else loop(accumulator * n, n - 1)
    }

    //Initialize loop
    loop(1, n)
  }

  //test
  println(factorial(4))

}
