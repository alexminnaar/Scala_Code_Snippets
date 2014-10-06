package BinarySearch


object BinarySearch extends App {

  def binarySearch(l: List[Int], num: Int): Boolean = {

    //sort list
    val sortedL = l.sorted

    var low = 0

    var high = sortedL.size - 1

    while (high >= low) {

      var mid = (high + low) / 2

      if (sortedL(mid) == num) {
        return true
      }
      else if (num > sortedL(mid)) {
        low = mid + 1
      }
      else {
        high = mid - 1
      }
    }
    false
  }

  val testList = List(4, 3, 7, 1, -3)

  println(binarySearch(testList, 7))
  println(binarySearch(testList, 2))

}
