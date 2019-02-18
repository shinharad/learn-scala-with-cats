package com.github.shinharad.cats.ch06

import cats.instances.option._
import cats.syntax.apply._

object ApplySyntax extends App {

  val r1 = (Option(123), Option("abc")).tupled
  println(r1) // => Some((123,abc))

  val r2 = (Option(123), Option("abc"), Option(true)).tupled
  println(r2) // => Some((123,abc,true))

  case class Cat(name: String, born: Int, color: String)

  val r3 = (
    Option("Garfield"),
    Option(1978),
    Option("Orange & Black")
  ).mapN(Cat)
  println(r3) // => Some(Cat(Garfield,1978,Orange & Black))

  val add: (Int, Int) => Int = (a, b) => a + b

//  (Option(1), Option(2), Option(3)).mapN(add)
  (Option(1), Option(2)).mapN(add)

}
