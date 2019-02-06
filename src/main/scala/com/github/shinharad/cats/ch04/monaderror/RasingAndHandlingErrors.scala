package com.github.shinharad.cats.ch04.monaderror

import cats.MonadError
import cats.instances.either._ // for MonadError
import cats.syntax.applicative._ // for pure
import cats.syntax.applicativeError._ // for raiseError
import cats.syntax.monadError._ // for ensure

object RasingAndHandlingErrors extends App {

  type ErrorOr[A] = Either[String, A]

  val monadError = MonadError[ErrorOr, String]

  val success = monadError.pure(42)
  println(success) // => Right(42)

  val failure = monadError.raiseError("Badness")
  println(failure) // => Left(Badness)

  val r1 = monadError.handleError(failure) {
    case "Badness" =>
      monadError.pure("It's ok")

    case other =>
      monadError.raiseError("It's not ok")
  }
  println(r1) // => Right(Right(It's ok))

  val r2 = monadError.ensure(success)("Number too low!")(_ > 1000)
  println(r2) // => Left(Number too low!)


  // MonadError syntax
  val success2 = 42.pure[ErrorOr]
  println(success2) // => Right(42)

  val failure2 = "Badness".raiseError[ErrorOr, Int]
  println(failure2) // => Left(Badness)

  val r3 = success2.ensure("Number to low!")(_ > 1000)
  println(r3) // => Left(Number to low!)

}
