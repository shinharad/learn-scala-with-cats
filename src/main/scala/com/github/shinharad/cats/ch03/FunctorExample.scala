package com.github.shinharad.cats.ch03

import cats.Functor

object FunctorExample1 extends App {

  import cats.Functor
  import cats.instances.list._
  import cats.instances.option._

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

object FunctorExample2 extends App {

  import cats.syntax.functor._
  import cats.instances.option._
  import cats.instances.list._
  import scala.language.higherKinds

  def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] =
    start.map(n => n + 1 * 2)

  println(doMath(Option(20)))

  println(doMath(List(1, 2, 3)))

}
