package Histogram

import scala.collection.mutable.HashMap

/**
 * Makes a histogram given a list of points and number of bins.
 */

object Histogram extends App {

  def makeHistogram(data: List[Double], numBins: Int) = {

    val binSize = (data.max - data.min) / numBins

    var binCounts: HashMap[Int, Int] = HashMap.empty

    //val normData = zeroNormalized(data)

    for (el <- data) {
      val bin = ((el - data.min) / binSize).toInt
      if (binCounts.contains(bin)) {
        binCounts += (bin -> (binCounts(bin) + 1))

      }
      else {
        binCounts += (bin -> 1)
      }
    }

    println("Bin sizes are: " + binSize)
    println("Histogram is :" + binCounts)
  }


  val dataPoints = List(2.8, 3.0, 4.2, 5.4, 7.9)
  val numBins = 3
  makeHistogram(dataPoints, numBins)

}
