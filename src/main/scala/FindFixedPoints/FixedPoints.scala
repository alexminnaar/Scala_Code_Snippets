package FindFixedPoints

import math.abs
/**
 * Created by alex on 15/08/14.
 */

//Fixed points of a function f are points x such that f(x)=x
//Can find fixed points by repeatedly apply function to point to get an approximation
object FixedPoints extends App{

  val tol=0.001

  def closeEnough(x:Double,y:Double)={
    abs((x-y)/x)/x < tol
  }

  def fixedPoint(f:Double => Double)(initialGuess:Double)={

    def loop(guess: Double):Double ={
      val next=f(guess)
      if(closeEnough(guess,next)) next
      else loop(next)
    }

    loop(initialGuess)

  }

  val testFixedPoint=fixedPoint(x=>1+x/2)(1)

  println(testFixedPoint)
}
