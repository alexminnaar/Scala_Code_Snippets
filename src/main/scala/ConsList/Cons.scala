package ConsList

/**
 * Created by alex on 22/08/14.
 */
//val parameters can implement methods in trait. Wow I never knew that!
class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}
