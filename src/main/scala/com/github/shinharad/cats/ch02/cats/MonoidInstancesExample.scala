package com.github.shinharad.cats.ch02.cats

import cats.{Monoid, Semigroup}
import cats.instances.int._
import cats.instances.option._
import cats.instances.string._

object MonoidInstancesExample01 extends App {

  println(Monoid[String].combine("Hi ", "there"))

  println(Monoid[String].empty)

  println(Monoid.apply[String].combine("Hi ", "there"))

  println(Monoid.apply[String].empty)

  Semigroup[String].combine("Hi ", "there")

  Monoid[Int].combine(32, 10)

}

object MonoidInstancesExample02 extends App {

  val a = Option(22)

  val b = Option(20)

  println(Monoid[Option[Int]].combine(a, b))

}
