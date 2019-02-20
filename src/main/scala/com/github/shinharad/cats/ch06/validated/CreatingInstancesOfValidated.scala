package com.github.shinharad.cats.ch06.validated

import cats.data.Validated
import cats.syntax.validated._
import cats.syntax.applicative._
import cats.syntax.applicativeError._
import cats.instances.list._

object CreatingInstancesOfValidated extends App {

  val v = Validated.Valid(123)
  println(v) // => Valid(123)

  val i = Validated.Invalid(List("Badness"))
  println(i) // => Invalid(List(Badness))

  // smart constructors
  val v2 = Validated.valid[List[String], Int](123)
  println(v2) // => Valid(123)

  val i2 = Validated.invalid[List[String], Int](List("Badness"))
  println(i2) // => Invalid(List(Badness))

  // syntax
  val v3 = 123.valid[List[String]]
  println(v3) // => Valid(123)

  val i3 = List("Badness").invalid[Int]
  println(i3) // => Invalid(List(Badness))

  type ErrorsOr[A] = Validated[List[String], A]

  // applicative and applicativeError syntax
  val v4 = 123.pure[ErrorsOr]
  println(v4) // => Valid(123)

  val i4 = List("Badness").raiseError[ErrorsOr, Int]
  println(i4) // => Invalid(List(Badness))

  // from Exceptions, Try, Either and Option
  val r1 = Validated.catchOnly[NumberFormatException]("foo".toInt)
  println(r1) // => Invalid(java.lang.NumberFormatException: For input string: "foo")

  val r2 = Validated.catchNonFatal(sys.error("Badness"))
  println(r2) // => Invalid(java.lang.RuntimeException: Badness)

  val r3 = Validated.fromTry(scala.util.Try("foo".toInt))
  println(r3) // => Invalid(java.lang.NumberFormatException: For input string: "foo")

  val r4 = Validated.fromEither[String, Int](Left("Badness"))
  println(r4) // => Invalid(Badness)

  val r5 = Validated.fromOption[String, Int](None, "Badness")
  println(r5) // => Invalid(Badness)

}
