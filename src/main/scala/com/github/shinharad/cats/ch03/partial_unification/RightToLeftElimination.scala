package com.github.shinharad.cats.ch03.partial_unification

import cats.instances.function._
import cats.syntax.contravariant._
import cats.syntax.functor._

// Most multiparameter type constructors are designed to be right-biased,
// requiring the left ot right elimination that is supported by the compiler out of the box.
object RightToLeftElimination {

  val func1 = (x: Int) => x.toDouble
  val func2 = (y: Double) => y * 2

  val func3 = func1.map(func2)

  val func3a: Int => Double =
    a => func2(func1(a))

  val func3b: Int => Double =
    func2.compose(func1)

  // Compile Error
//  val func3c: Int => Double =
//    func2.contramap(func1)

  type <=[B, A] = A => B
  val func2b: Double <= Double = func2
  val func3c = func2b.contramap(func1)

}
