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

  def msort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
    val n = xs.length / 2

    if (n == 0) xs

    else {

      def merge(xs: List[T], ys: List[T]): List[T] =
        (xs, ys) match {
          case (Nil, ys) => ys
          case (xs, Nil) => xs
          case (x :: xs1, y :: ys1) =>
            if (lt(x, y)) x :: merge(xs1, ys)
            else y :: merge(xs, ys1)
        }

      val (fst, snd) = xs.splitAt(n)
      merge(msort(fst)(lt), msort(snd)(lt))
    }
  }

  val nums = List(2, -48, 3, 6, -2)
  val fruits = List("apple", "pineapple", "orange", "banana")

  //Could also leave types out and let the compiler infer them.
  println(msort(nums)((x: Int, y: Int) => x < y))
  println(msort(fruits)((x: String, y: String) => x.compareTo(y) < 0))

}
