package NewtonsMethodScala

/**
 * Created by alex on 19/07/14.
 */
object NewtonMethodSquareRoot extends App {

  //Recursive functions need explicit return type in Scala
  def rootIter(estimate: Double, x: Double): Double = {
    if (convergeTest(estimate, x)) estimate
    else rootIter(improveEstimate(estimate, x), x) //improve estimate via Newton method
  }

  def convergeTest(estimate: Double, x: Double): Boolean = {
    math.abs(estimate * estimate - x) < 0.01
  }

  //Newton update
  def improveEstimate(estimate: Double, x: Double): Double = {
    (estimate + x / estimate) / 2
  }

  def sqrt(x: Double) = rootIter(1.0, x)

  //Run a test
  println(sqrt(64))

}
