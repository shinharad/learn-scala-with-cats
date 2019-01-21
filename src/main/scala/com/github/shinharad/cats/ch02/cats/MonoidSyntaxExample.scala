package com.github.shinharad.cats.ch02.cats

import cats.Monoid
import cats.instances.int._
import cats.instances.string._
import cats.syntax.semigroup._

object MonoidSyntaxExample extends App {

  val stringResult = "Hi " |+| "there" |+| Monoid[String].empty
  println(stringResult)

  val intResult = 1 |+| 2 |+| Monoid[Int].empty
  println(intResult)

}
