package ConsList

import java.util.NoSuchElementException

/**
 * Created by alex on 22/08/14.
 */
class Nil[T] extends List[T] {
  def isEmpty = true

  //Throws are of type Nothing.  This is OK because nothing is a subtype of both T and List[T] (any type actually)
  def head: Nothing = throw new NoSuchElementException("No head element in Nil")

  def tail: Nothing = throw new NoSuchElementException("No tail in Nil")
}
