package com.github.shinharad.cats.ch07.traverse

import cats.instances.option._

object TraversingWithOptions extends App {
  import TraversingWithApplicatives._

  def process(inputs: List[Int]): Option[List[Int]] =
    listTraverse(inputs)(n => if (n % 2 == 0) Some(n) else None)

  println(process(List(2, 4, 6))) // => Some(List(2, 4, 6))
  println(process(List(1, 2, 3))) // => None

}
