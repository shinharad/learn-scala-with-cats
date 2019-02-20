package com.github.shinharad.cats.ch06.semigroupal

import cats.Monad
import cats.syntax.flatMap._
import cats.syntax.functor._
import cats.instances.either._
import cats.instances.list._

import scala.language.higherKinds

// fail-fast error handling
object ProductOfMonads extends App {

  def product[M[_]: Monad, A, B](x: M[A], y: M[B]): M[(A, B)] =
    for {
      a <- x
      b <- y
    } yield (a, b)

  val r1 = product(List(1, 2), List(3, 4))
  println(r1)
  // => List((1,3), (1,4), (2,3), (2,4))

  type ErrorOr[A] = Either[Vector[String], A]

  val r2 = product[ErrorOr, Int, Int](
    Left(Vector("Error 1")),
    Left(Vector("Error 2"))
  )
  println(r2)
  // => Left(Vector(Error 1))

}
