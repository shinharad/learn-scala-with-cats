package com.github.shinharad.cats.ch06

import cats.syntax.either._

object StopOnTheFirstErrorEncounter extends App {

  def parseInt(str: String): Either[String, Int] =
    Either
      .catchOnly[NumberFormatException](str.toInt)
      .leftMap(_ => s"Couldn't read $str")

  val result = for {
    a <- parseInt("a")
    b <- parseInt("b")
    c <- parseInt("c")
  } yield a + b + c

  println(result)
  // => Left(Couldn't read a)

}
