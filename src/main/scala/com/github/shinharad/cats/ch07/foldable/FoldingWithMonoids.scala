package com.github.shinharad.cats.ch07.foldable

import cats.Foldable
import cats.instances.option._
import cats.instances.list._
import cats.instances.int._
import cats.instances.string._
import cats.instances.vector._

object FoldingWithMonoids extends App {

  val r1 = Foldable[Option].nonEmpty(Option(42))
  println(r1) // => true

  val r2 = Foldable[List].find(List(1, 2, 3))(_ % 2 == 0)
  println(r2) // => Some(2)

  val r3 = Foldable[List].combineAll(List(1, 2, 3))
  println(r3) // => 6

  val r4 = Foldable[List].foldMap(List(1, 2, 3))(_.toString)
  println(r4) // => 123

  val ints = List(Vector(1, 2, 3), Vector(4, 5, 6))
  val r5   = (Foldable[List] compose Foldable[Vector]).combineAll(ints)
  println(r5) // => 21

}
