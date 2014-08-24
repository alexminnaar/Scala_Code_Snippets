package NaturalNumbers

/**
 * Created by alex on 24/08/14.
 */
object Zero extends Nat {

  def isZero = true

  def predecessor = throw new Error("0.predecessor")

  def +(that: Nat) = that

  def -(that: Nat) = if (that.isZero) this else throw new Error("negative number")
}
