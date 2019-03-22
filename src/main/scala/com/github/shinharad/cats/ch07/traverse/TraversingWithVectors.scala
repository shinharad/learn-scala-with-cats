package com.github.shinharad.cats.ch07.traverse

import cats.instances.vector._

object TraversingWithVectors extends App {
  import TraversingWithApplicatives._

  val r = listSequence(List(Vector(1, 2), Vector(3, 4)))
  println(r) // => Vector(List(1, 3), List(1, 4), List(2, 3), List(2, 4))

}
