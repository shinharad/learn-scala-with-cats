package com.github.shinharad.cats.ch04.either

import cats.syntax.either._

object EitherExtensionMethods extends App {

  val r1 = Either.catchOnly[NumberFormatException]("foo".toInt)

  val r2 = Either.catchNonFatal(sys.error("Badness"))

  println(r1)
  println(r2)

  val r3 = "Error".asLeft[Int].getOrElse(0)
  println(r3)

  val r4 = "Error".asLeft[Int].orElse(2.asRight[String])
  println(r4)

  // ensure
  val r5 = -1.asRight[String].ensure("Must be non-negative!")(_ > 0)
  println(r5)

  // recover and recoverWith
  val r6 = "error".asLeft[Int].recover {
    case str: String => -1
  }
  println(r6)

  val r7 = "error".asLeft[Int].recoverWith {
    case str: String => Right(-1)
  }
  println(r7)

  // leftMap and bimap
  val r8 = "foo".asLeft[Int].leftMap(_.reverse)
  println(r8)

  val r9 = 6.asRight[String].bimap(_.reverse, _ * 7)
  println(r9)

  val r10 = "bar".asLeft[Int].bimap(_.reverse, _ * 7)
  println(r10)

  // swap
  val r11 = 123.asRight[String].swap
  println(r11)

}
