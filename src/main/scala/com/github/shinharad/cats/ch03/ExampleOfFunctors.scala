package com.github.shinharad.cats.ch03

object ExampleOfFunctors extends App {

  val r1 = List(1, 2, 3).map(n => n + 1)
  println(r1)

  val r2 = List(1, 2, 3)
    .map(n => n + 1)
    .map(n => n * 2)
    .map(n => n + "!")
  println(r2)

  val f: Int => Int    = n => n + 1
  val g: Int => Int    = n => n * 2
  val h: Int => String = n => n + "!"

  val r3 = List(1, 2, 3) map (h compose g compose f)
  println(r3)

}
