package IntegerSets

/**
 * Created by alex on 18/08/14.
 */

//The class of a tree for the empty set
object Empty extends IntSet {
  //Empty tree cannot contain any value
  def contains(x: Int): Boolean = false

  //Add x to the tree which results in its own non-empty tree with two empty children
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)

  def union(other: IntSet): IntSet = other

  override def toString = "."
}
