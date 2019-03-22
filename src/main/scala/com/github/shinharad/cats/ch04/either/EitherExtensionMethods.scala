package com.github.shinharad.cats.ch04.either

import cats.syntax.either._

object EitherExtensionMethods extends App {

  val r1 = Either.catchOnly[NumberFormatException]("foo".toInt)
  println(r1) // => Left(java.lang.NumberFormatException: For input string: "foo")

  val r2 = Either.catchNonFatal(sys.error("Badness"))
  println(r2) // => Left(java.lang.RuntimeException: Badness)

  val r3 = "Error".asLeft[Int].getOrElse(0)
  println(r3) // => 0

  val r4 = "Error".asLeft[Int].orElse(2.asRight[String])
  println(r4) // => Right(2)

  // ensure
  val r5 = -1.asRight[String].ensure("Must be non-negative!")(_ > 0)
  println(r5) // => Left(Must be non-negative!)

  // recover and recoverWith
  val r6 = "error".asLeft[Int].recover {
    case str: String => -1
  }
  println(r6) // => Right(-1)

  val r7 = "error".asLeft[Int].recoverWith {
    case str: String => Right(-1)
  }
  println(r7) // => Right(-1)

  // leftMap and bimap
  val r8 = "foo".asLeft[Int].leftMap(_.reverse)
  println(r8) // => Left(oof)

  val r9 = 6.asRight[String].bimap(_.reverse, _ * 7)
  println(r9) // => Right(42)

  val r10 = "bar".asLeft[Int].bimap(_.reverse, _ * 7)
  println(r10) // => Left(rab)

  // swap
  val r11 = 123.asRight[String].swap
  println(r11) // => Left(123)

}
