package com.github.shinharad.cats.ch03

import cats.Functor
import cats.syntax.functor._
import cats.instances.option._
import cats.instances.list._
import scala.language.higherKinds

object FunctorInCats2 extends App {

  def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] =
    start.map(n => n + 1 * 2)

  val r1 = doMath(Option(20))
  println(r1)

  val r2 = doMath(List(1, 2, 3))
  println(r2)

}
