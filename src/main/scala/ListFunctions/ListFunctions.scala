package ListFunctions

/**
 * This is how map works in Scala too!
 */
object ListFunctions extends App {

  def scaleList(xs: List[Double], factor: Double): List[Double] = xs match {
    case Nil => xs
    case y :: ys => y * factor :: scaleList(ys, factor)
  }

  println(scaleList(List(3.2,3.4,5.5,1,3),5.5))


  def squareList(xs:List[Int]):List[Int]=xs match {
    case Nil => xs
    case y::ys => y*y::squareList(ys)
  }

  println(squareList(List(3,3,5,1)))

  def posElems(xs:List[Int]):List[Int]=xs match {
    case Nil => xs
    case y::ys => if(y>0) y::posElems(ys) else posElems(ys)
  }

  println(posElems(List(3,-3,5,-1)))


}
