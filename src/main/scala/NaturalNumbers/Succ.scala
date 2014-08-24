package NaturalNumbers

/**
 * Created by alex on 24/08/14.
 */
class Succ(n: Nat) extends Nat {

  def isZero = false

  def predecessor = n

  def +(that: Nat) = new Succ(n + that)

  def -(that: Nat) = if (that.isZero) this else n - that.predecessor
}
