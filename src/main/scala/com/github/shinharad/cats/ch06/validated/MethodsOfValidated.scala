package com.github.shinharad.cats.ch06.validated

import cats.syntax.either._
import cats.syntax.validated._

object MethodsOfValidated extends App {

  val r1 = 123.valid.map(_ * 100)
  println(r1) // => Valid(12300)

  val r2 = "?".invalid.leftMap(_.toString)
  println(r2) // => Invalid(?)

  val r3 = 123.valid[String].bimap(_ + "!", _ * 100)
  println(r3) // => Valid(12300)

  val r4 = "?".invalid[Int].bimap(_ + "!", _ * 100)
  println(r4) // => Invalid(?!)

  // andThen
  val r5 = 32.valid.andThen { a =>
    10.valid.map { b =>
      a + b
    }
  }
  println(r5) // => Valid(42)

  // toEither and toValidated
  val r6 = "Badness".invalid[Int]
  println(r6) // => Invalid(Badness)

  val r7 = "Badness".invalid[Int].toEither
  println(r7) // => Left(Badness)

  val r8 = "Badness".invalid[Int].toEither.toValidated
  println(r8) // => Invalid(Badness)

  val r9 = (-1).valid[String].ensure("Negative!")(_ > 0)
  println(r9) // => Invalid(Negative!)

  // getOrElse and fold
  val r10 = "fail".invalid[Int].getOrElse(0)
  println(r10) // => 0

  val r11 = "fail".invalid[Int].fold(_ + "!!!", _.toString)
  println(r11) // => fail!!!

}
