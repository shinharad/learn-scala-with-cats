package com.github.shinharad.cats.ch07.foldable

import cats.Foldable
import cats.instances.list._ // for Foldable

object FoldableInCats extends App {

  val ints = List(1, 2, 3)

  val r1 = Foldable[List].foldLeft(ints, 0)(_ + _)
  println(r1) // => 6

  import cats.instances.option._ // for Foldable

  val maybeInt = Option(123)

  val r2 = Foldable[Option].foldLeft(maybeInt, 10)(_ * _)
  println(r2) // => 1230

}
