package com.github.shinharad.cats.ch06.validated

import cats.Semigroupal
import cats.data.{NonEmptyList, Validated}
import cats.syntax.validated._
import cats.instances.string._
import cats.instances.vector._
import cats.syntax.apply._ // for tupled

object CombiningInstancesOfValidated extends App {

  type AllErrorsOr[A] = Validated[String, A]

  Semigroupal[AllErrorsOr]

  val r1 = (
    "Error 1".invalid[Int],
    "Error 2".invalid[Int]
  ).tupled
  println(r1) // => Invalid(Error 1Error 2)

  val r2 = (
    Vector(404).invalid[Int],
    Vector(500).invalid[Int]
  ).tupled
  println(r2) // => Invalid(Vector(404, 500))

  val r3 = (
    NonEmptyList.of("Error 1").invalid[Int],
    NonEmptyList.of("Error 2").invalid[Int]
  ).tupled
  println(r3) // => Invalid(NonEmptyList(Error 1, Error 2))

}
