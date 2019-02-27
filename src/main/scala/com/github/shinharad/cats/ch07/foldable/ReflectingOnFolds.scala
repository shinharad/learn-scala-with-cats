package com.github.shinharad.cats.ch07.foldable

object ReflectingOnFolds extends App {

  val r1 = List(1, 2, 3).foldLeft(List.empty[Int])((a, i) => i :: a)
  println(r1) // => List(3, 2, 1)

  val r2 = List(1, 2, 3).foldRight(List.empty[Int])((i, a) => i :: a)
  println(r2) // => List(1, 2, 3)

}
