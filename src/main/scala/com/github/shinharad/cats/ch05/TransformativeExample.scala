package com.github.shinharad.cats.ch05

import cats.instances.list._     // for Monad
import cats.syntax.applicative._ // for pure
import cats.data.OptionT

object TransformativeExample extends App {

  // List[Option[A]]
  type ListOption[A] = OptionT[List, A]

  val result1: ListOption[Int] = OptionT(List(Option(10)))

  val result2: ListOption[Int] = 32.pure[ListOption]

  val r = result1.flatMap { x =>
    result2.map { y =>
      x + y
    }
  }
  println(r.value) // => List(Some(42))

}
