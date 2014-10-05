package TwoSum

import scala.collection.immutable.HashMap

/**
 * 2-sum problem implemented with hashmap. O(N)
 */
object TwoSumWithHashMap extends App {

  def twoSum(l: List[Int], s: Int): Boolean = {

    var elHash: HashMap[Int, Integer] = new HashMap()

    for (el <- l) {
      elHash += (el -> 1)
    }

    for (el <- l) {
      if (elHash.contains(s - el)) {
        return true
      }
    }

    false
  }


  val testList = List(2, -1, 3, 22)

  println(twoSum(testList, 7))

}
