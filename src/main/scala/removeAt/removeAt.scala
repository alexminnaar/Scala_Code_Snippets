package removeAt

/**
 * Created by alex on 01/09/14.
 */
object removeAt extends App{


  def removeAt(n:Int,xs:List[Int])= (xs take n):::(xs drop n+1)

  val testList=List(1,2,3)

  println(removeAt(1,testList))
}
