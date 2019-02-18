package com.github.shinharad.cats.ch06

import cats.Semigroupal
import cats.instances.option._

object JoiningThreeOrMoreContexts extends App {

  val r1 = Semigroupal.tuple3(Option(1), Option(2), Option(3))
  println(r1) // => Some((1,2,3))

  val r2 = Semigroupal.tuple3(Option(1), Option(2), Option.empty[Int])
  println(r2) // => None

  val r3 = Semigroupal.map3(Option(1), Option(2), Option(3))(_ + _ + _)
  println(r3) // => Some(6)

  val r4 = Semigroupal.map2(Option(1), Option.empty[Int])(_ + _)
  println(r4) // => None

}
