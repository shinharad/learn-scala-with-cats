package com.github.shinharad.cats.ch07.traverse

import cats.data.Validated
import cats.instances.list._

import scala.language.higherKinds

object TraversingWithValidated extends App {
  import TraversingWithApplicatives._

  type ErrorOr[A] = Validated[List[String], A]

  def process(inputs: List[Int]): ErrorOr[List[Int]] =
    listTraverse(inputs) { n =>
      if (n % 2 == 0) {
        Validated.valid(n)
      } else {
        Validated.invalid(List(s"$n is not even"))
      }
    }

  println(process(List(2, 4, 6)))
  // => Valid(List(2, 4, 6))

  println(process(List(1, 2, 3)))
  // => Invalid(List(1 is not even, 3 is not even))

}
