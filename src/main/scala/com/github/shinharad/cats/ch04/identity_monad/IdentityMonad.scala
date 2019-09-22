package com.github.shinharad.cats.ch04.identity_monad

import cats.{Id, Monad}
import cats.syntax.flatMap._
import cats.syntax.functor._

object IdentityMonad extends App {

  def sumSquare[F[_]: Monad](a: F[Int], b: F[Int]): F[Int] =
    for {
      x <- a
      y <- b
    } yield x * x + y * y

  // Id allows us to call our monadic method using plain values.
  val r1 = sumSquare(3: Id[Int], 4: Id[Int])

  val r2 = "Date": Id[String]

  val r3 = 123: Id[Int]

  val r4 = List(1, 2, 3): Id[List[Int]]

  println(r1)
  println(r2)
  println(r3)
  println(r4)

  val a = Monad[Id].pure(3)

  val b = Monad[Id].flatMap(a)(_ + 1)

  val r5 = for {
    x <- a
    y <- b
  } yield x + y
  println(r5)

}
