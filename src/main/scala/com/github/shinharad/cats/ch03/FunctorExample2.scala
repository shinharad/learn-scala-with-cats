package com.github.shinharad.cats.ch03

import cats.Functor
import cats.syntax.functor._
import cats.instances.option._
import cats.instances.list._
import scala.language.higherKinds

object FunctorExample2 extends App {

  def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] =
    start.map(n => n + 1 * 2)

  println(doMath(Option(20)))

  println(doMath(List(1, 2, 3)))

}
