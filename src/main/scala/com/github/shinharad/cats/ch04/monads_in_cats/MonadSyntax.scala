package com.github.shinharad.cats.ch04.monads_in_cats

import cats.Monad
import cats.instances.option._
import cats.instances.list._
import cats.syntax.applicative._
import cats.syntax.flatMap._
import cats.syntax.functor._

import scala.language.higherKinds

object MonadSyntax extends App {

  1.pure[Option]

  1.pure[List]

  def sumSquare[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] =
    a.flatMap(x => b.map(y => x * x + y * y))

  def sumSquare2[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] =
    for {
      x <- a
      y <- b
    } yield x * x + y * y

  sumSquare(Option(3), Option(4))

  sumSquare(List(1, 2, 3), List(4, 5))

}
