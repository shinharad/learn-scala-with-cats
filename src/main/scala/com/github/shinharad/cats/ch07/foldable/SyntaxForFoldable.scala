package com.github.shinharad.cats.ch07.foldable

import cats.instances.int._
import cats.instances.list._
import cats.instances.string._
import cats.syntax.foldable._ // for combineAll and foldMap

import cats.implicits._

object SyntaxForFoldable extends App {

  val r1 = List(1, 2, 3).combineAll
  println(r1) // => 6

  val r2 = List(1, 2, 3).foldMap(_.toString)
  println(r2) // => 123

}
