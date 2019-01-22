package com.github.shinharad.cats.ch03

import cats.Functor
import cats.instances.list._
import cats.instances.option._

object FunctorExample1 extends App {

  val list1 = List(1, 2, 3)

  val list2 = Functor[List].map(list1)(_ * 2)
  println(list2)

  val option1 = Option(123)

  val option2 = Functor[Option].map(option1)(_.toString)
  println(option2)

  val func = (x: Int) => x + 1

  val liftedFunc = Functor[Option].lift(func)
  println(liftedFunc)

  println(liftedFunc(Option(1)))

}
