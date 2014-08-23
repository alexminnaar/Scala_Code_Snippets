package ConsList

/**
 * Created by alex on 22/08/14.
 */
trait List[T] {
  def isEmpty: Boolean

  def head: T

  def tail: List[T]
}
