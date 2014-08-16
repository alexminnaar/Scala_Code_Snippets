package Rationals

/**
 * Created by alex on 16/08/14.
 */

//class implementing rational numbers i.e. fractions where the numerator and denominator are integers.
class Rationals(x: Int, y: Int) {

  //Make sure we are not dividing by zero
  require(y != 0, "Cannot divide by zero!!!!")

  //Want to keep rational number in fully reduced form.  Do this immediately after it is created.
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  private val g = gcd(x, y)

  def numer = x / g

  def denom = y / g

  //negative of rational
  def neg: Rationals = {
    new Rationals(-numer, denom)
  }

  //method adding two rationals
  def addRational(s: Rationals): Rationals = {
    new Rationals(numer * s.denom + s.numer * denom, denom * s.denom)
  }

  //subtraction method using additiona and negation
  def subtractRational(r: Rationals): Rationals = {
    addRational(r.neg)
  }

  //Check if less than
  def less(r: Rationals): Boolean = numer * r.denom < r.numer * denom

  //Find maximum of two rationals
  def max(r: Rationals): Rationals = if (this.less(r)) r else this

  //print the rational in a pretty way
  override def toString = {
    numer + "/" + denom
  }

}

//Let's test it!
object Rationals extends App {

  val x = new Rationals(1, 3)
  val y = new Rationals(5, 7)
  val z = new Rationals(3, 2)

  println(x.subtractRational(y).subtractRational(z))

  println(y.addRational(y))
}
