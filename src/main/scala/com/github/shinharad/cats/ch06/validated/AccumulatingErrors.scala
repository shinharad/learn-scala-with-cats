package com.github.shinharad.cats.ch06.validated

import cats.Semigroupal
import cats.data.Validated
import cats.instances.list._

object AccumulatingErrors extends App {

  type AllErrorsOr[A] = Validated[List[String], A]

  val r = Semigroupal[AllErrorsOr].product(
    Validated.invalid(List("Error 1")),
    Validated.invalid(List("Error 2"))
  )
  println(r)

}
