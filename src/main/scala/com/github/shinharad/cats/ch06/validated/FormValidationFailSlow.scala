package com.github.shinharad.cats.ch06.validated

import cats.data.Validated
import cats.instances.list._
import cats.syntax.apply._
import cats.syntax.either._

object FormValidationFailSlow extends App {
  import FormValidationFailFast._

  // Form Validation Part 5
  type FailSlow[A] = Validated[List[String], A]

  def readUser(data: FormData): FailSlow[User] =
    (
      readName(data).toValidated,
      readAge(data).toValidated
    ).mapN(User.apply)

  println(readUser(Map("name" -> "Dave", "age" -> "37")))
  // => Valid(User(Dave,37))

  println(readUser(Map("age" -> "-1")))
  // => Invalid(List(name field not specified, age must be non-negative))

}
