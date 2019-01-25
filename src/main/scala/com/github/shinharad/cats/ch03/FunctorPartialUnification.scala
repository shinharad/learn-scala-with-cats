package com.github.shinharad.cats.ch03

import cats.instances.function._
import cats.syntax.functor._

object FunctorPartialUnification extends App {

  val func1: Int => Double =
    (x: Int) => x.toDouble

  val func2: Double => Double =
    (y: Double) => y * 2

  val r1 = (func1 map func2)(1)
  println(r1)

  val r2 = (func1 andThen func2)(1)
  println(r2)

  val r3 = func2(func1(1))
  println(r3)

  val func =
    ((x: Int) => x.toDouble)
      .map(x => x + 1)
      .map(x => x * 2)
      .map(x => x + "!")
  println(func(123))

}
