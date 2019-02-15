package com.github.shinharad.cats.ch05

import cats.data.{EitherT, OptionT}
import cats.instances.either._
import cats.instances.future._
import cats.syntax.applicative._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object BuildingMonadStacks extends App {

  type ErrorOr[A] = Either[String, A]

  type ErrorOrOption[A] = OptionT[ErrorOr, A]

  val a = 10.pure[ErrorOrOption]

  val b = 32.pure[ErrorOrOption]

  val c = a.flatMap(x => b.map(y => x + y))
  //...

  type FutureEither[A] = EitherT[Future, String, A]

  type FutureEitherOption[A] = OptionT[FutureEither, A]

  val futureEitherOf: FutureEitherOption[Int] =
    for {
      a <- 10.pure[FutureEitherOption]
      b <- 32.pure[FutureEitherOption]
    } yield a + b

  // Constructing and Unpacking Instances
  val errorStack1 = OptionT[ErrorOr, Int](Right(Some(10)))

  val errorStack2 = 32.pure[ErrorOrOption]

  var r1 = errorStack1.value
  println(r1) // => Right(Some(10))

  val r2 = errorStack2.value.map(_.getOrElse(-1))
  println(r2) // => Right(32)

  val intermediate = futureEitherOf.value
  println(intermediate) // => EitherT(Future(Success(Right(Some(42)))))

  val stack = intermediate.value
  println(stack) // => Future(Success(Right(Some(42))))

  val r3 = Await.result(stack, 1.second)
  println(r3) // => Right(Some(42))

}
