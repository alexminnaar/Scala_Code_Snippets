package IntegerSets

/**
 * Created by alex on 18/08/14.
 */

//We are implementing sets of integers as binary trees.
abstract class IntSet {

  def incl(x: Int): IntSet

  def contains(x: Int): Boolean

  def union(other: IntSet): IntSet
}
