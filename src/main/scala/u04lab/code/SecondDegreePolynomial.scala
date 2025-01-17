package u04lab.code

// Express a second degree polynomial
// Structure: secondDegree * X^2 + firstDegree * X + constant
trait SecondDegreePolynomial:
  def constant: Double
  def firstDegree: Double
  def secondDegree: Double
  def +(polynomial: SecondDegreePolynomial): SecondDegreePolynomial
  def -(polynomial: SecondDegreePolynomial): SecondDegreePolynomial


object SecondDegreePolynomial:
  def apply(secondDegree: Double, firstDegree: Double, constant: Double): SecondDegreePolynomial =
    SecondDegreePolynomialImpl(constant, firstDegree, secondDegree)

  private class SecondDegreePolynomialImpl(
                                            override val constant: Double,
                                            override val firstDegree: Double,
                                            override val secondDegree: Double
                                          ) extends SecondDegreePolynomial:
    override def +(polynomial: SecondDegreePolynomial): SecondDegreePolynomial = doOperation(polynomial)(_ + _)
    override def -(polynomial: SecondDegreePolynomial): SecondDegreePolynomial = doOperation(polynomial)(_ - _)
    private def doOperation(polynomial: SecondDegreePolynomial)(o: (Double, Double) => Double) =
      SecondDegreePolynomialImpl(
        o(polynomial.constant, this.constant),
        o(polynomial.firstDegree, this.firstDegree),
        o(polynomial.secondDegree, this.secondDegree)
      )

    override def toString: String =
      "{" + this.secondDegree + "X^2 + " + this.firstDegree + "X + " + this.constant + "}"

@main def checkComplex(): Unit =
  val simplePolynomial = SecondDegreePolynomial(1.0, 0, 3)
  val anotherPolynomial = SecondDegreePolynomial(0.0, 1, 0.0)
  val fullPolynomial = SecondDegreePolynomial(3.0, 2.0, 5.0)
  val sum = simplePolynomial + anotherPolynomial
  println((sum, sum.secondDegree, sum.firstDegree, sum.constant)) // 1.0 * X^2 + 1.0 * X + 3.0
  val multipleOperations = fullPolynomial - (anotherPolynomial + simplePolynomial)
  println((multipleOperations, multipleOperations.secondDegree, multipleOperations.firstDegree, multipleOperations.constant)) // 2.0 * X^2 + 1.0 * X + 2.0

/** Hints:
  *   - implement SecondDegreePolynomial with a SecondDegreePolynomialImpl class, similar to PersonImpl in slides
  *   - check that equality and toString do not work
  *   - use a case class SecondDegreePolynomialImpl instead
  *   - check equality and toString now
  */
