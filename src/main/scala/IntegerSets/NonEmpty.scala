package IntegerSets

/**
 * Created by alex on 18/08/14.
 */
class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {

  //search left or right sub-tree until x is found, or we come to an empty sub-tree
  def contains(x: Int): Boolean = {
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
  }

  //To include x in the tree, create new partial tree structure where changes need to occur.
  //Don't mutate anything.  Use persistent data structure.
  def incl(x: Int): IntSet = {
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this
  }

  //Every call to union is on a smaller set, so recursion would end eventually
  def union(other: IntSet): IntSet = {
    ((left union right) union other) incl elem
  }

  override def toString = "{" + left + elem + right + "}"
}
