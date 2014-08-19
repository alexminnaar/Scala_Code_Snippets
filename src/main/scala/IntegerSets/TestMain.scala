package IntegerSets

/**
 * Created by alex on 18/08/14.
 */

//test out our IntSet c
object TestMain extends App {

  val t1 = new NonEmpty(3, Empty, Empty)

  println(t1)

  val t2 = t1 incl 4

  println(t2)

}
