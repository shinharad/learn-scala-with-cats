package com.github.shinharad.cats.ch06

import cats.Semigroupal
import cats.instances.option._

object JoiningTwoContexts extends App {

  val r1 = Semigroupal[Option].product(Some(123), Some("abc"))
  println(r1) // => Some((123,abc))

  val r2 = Semigroupal[Option].product(None, Some("abc"))
  println(r2) // => None

  val r3 = Semigroupal[Option].product(Some(123), None)
  println(r3) // => None

}
