package com.github.shinharad.cats.ch06.validated

import cats.syntax.either._

object FormValidationFailFast extends App {

  case class User(name: String, age: Int)

  // Form Validation Part 1
  type FormData    = Map[String, String]
  type FailFast[A] = Either[List[String], A]

  def getValue(name: String)(data: FormData): FailFast[String] =
    data
      .get(name)
      .toRight(List(s"$name field not specified"))

  val getName = getValue("name") _

  println(getName(Map("name" -> "Dade Murphy")))
  // => Right(Dade Murphy)

  println(getName(Map()))
  // => Left(List(name field not specified))

  // Form Validation Part 2
  type NumFmtExn = NumberFormatException

  def parseInt(name: String)(data: String): FailFast[Int] =
    Either
      .catchOnly[NumFmtExn](data.toInt)
      .leftMap(_ => List(s"$name must be an integer"))

  println(parseInt("age")("11"))
  // => Right(11)

  println(parseInt("age")("foo"))
  // => Left(List(age must be an integer))

  // Form Validation Part 3
  def nonBlank(name: String)(data: String): FailFast[String] = {
    data.asRight[List[String]]
      .ensure(List(s"$name cannot be blank"))(_.nonEmpty)

    // IntelliJ IDEAのScala Pluginで型推論に失敗する
//    Right(data)
//      .ensure(List(s"$name cannot be blank"))(_.nonEmpty)
  }

  def nonNegative(name: String)(data: Int): FailFast[Int] =
    data.asRight[List[String]]
      .ensure(List(s"$name must be non-negative"))(_ >= 0)

  println(nonBlank("name")("Dade Murphy"))
  // => Right(Dade Murphy)

  println(nonBlank("name")(""))
  // => Left(List(name cannot be blank))

  println(nonNegative("age")(11))
  // => Right(11)

  println(nonNegative("age")(-1))
  // => Left(List(age must be non-negative))

  // Form Validation Part 4
  def readName(data: FormData): FailFast[String] =
    getValue("name")(data)
      .flatMap(nonBlank("name"))

  def readAge(data: FormData): FailFast[Int] =
    getValue("age")(data)
      .flatMap(nonBlank("age"))
      .flatMap(parseInt("age"))
      .flatMap(nonNegative("age"))

  println(readName(Map("name" -> "Dade Murphy")))
  // => Right(Dade Murphy)

  println(readName(Map("name" -> "")))
  // => Left(List(name cannot be blank))

  println(readName(Map()))
  // => Left(List(name field not specified))

  println(readAge(Map("age" -> "11")))
  // => Right(11)

  println(readAge(Map("age" -> "-1")))
  // => Left(List(age must be non-negative))

  println(readAge(Map()))
  // => Left(List(age field not specified))

}
