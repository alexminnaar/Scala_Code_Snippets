package TwoSum

/**
 * Solution to the 2-sum problem using sort and two pointers. O(NlogN)
 */

object TwoSumWithSort extends App {

  def twoSum(l: List[Double], s: Double): Boolean = {

    var p1 = 0
    var p2 = l.size-1

    val sortedL = l.sorted

    while (p1 < p2) {

      val curSum = sortedL(p1) + sortedL(p2)

      if (curSum == s) {
        return true
      }
      else if (curSum > s) {
        p2 -= 1
      }
      else {
        p1 += 1
      }

    }

    false

  }


  val testList=List(2.1,-1.2,3.8,22.3)

  println(twoSum(testList,5.9))

}
