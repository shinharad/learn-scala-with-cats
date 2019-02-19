package com.github.shinharad.cats.ch06

import cats.data.Validated
import cats.syntax.validated._

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

}
