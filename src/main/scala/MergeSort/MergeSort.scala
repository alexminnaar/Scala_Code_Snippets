package MergeSort

/**
 * Merge sort works by
 *
 * Separating list into two sub-lists, each containing around half of the elements of the original list
 * Sort the two sublists
 * Merge the two sorted sublists into single sorted list
 *
 * If the list consists of zero or one elements, it is already sorted.
 *
 * complexity O(NlogN)
 */

object MergeSort extends App {

  def msort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2

    if (n == 0) xs

    else {
      val (fst, snd) = xs.splitAt(n)
      merge(msort(fst), msort(snd))
    }
  }

  def merge(xs: List[Int], ys: List[Int]):List[Int] =
    (xs,ys) match {
      case (Nil,ys) => ys
      case (xs,Nil) => xs
      case (x::xs1,y::ys1) =>
        if(x<y) x::merge(xs1,ys)
        else y::merge(xs,ys1)
    }

  val nums=List(2,-48,3,6,-2)

  println(msort(nums))

}
