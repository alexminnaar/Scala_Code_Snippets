package ListSumMax

/**
 * Created by alex on 27/07/14.
 */
object ListSumMax extends App {

  def listMax(input: List[Double]): Double = {

    val inputLength = input.length

    def getMax(curIndx: Int, curMax: Double): Double = {
      if (curIndx == inputLength) curMax
      else {
        if (input(curIndx) > curMax) getMax(curIndx + 1, input(curIndx))
        else getMax(curIndx + 1, curMax)
      }
    }

    getMax(0, -10000.0)
  }

  def listSum(input: List[Double]): Double = {

    val inputLength = input.length

    def getSum(curIndx: Int, curSum: Double): Double = {
      if (curIndx == inputLength) curSum
      else getSum(curIndx + 1, curSum + input(curIndx))
    }

    getSum(0, 0.0)
  }

}
