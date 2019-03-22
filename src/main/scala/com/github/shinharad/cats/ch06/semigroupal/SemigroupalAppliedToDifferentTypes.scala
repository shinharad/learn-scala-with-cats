package com.github.shinharad.cats.ch06.semigroupal

import cats.Semigroupal
import cats.instances.future._
import cats.instances.list._
import cats.instances.either._
import cats.syntax.apply._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._
import scala.language.postfixOps

object SemigroupalAppliedToDifferentTypes extends App {

  // Future
  val futurePair = Semigroupal[Future]
    .product(Future("Hello"), Future(123))

  println(Await.result(futurePair, 1 second))
  // => (Hello,123)

  case class Cat(
    name: String,
    yearOfBirth: Int,
    favoriteFood: List[String]
  )

  val futureCat = (
    Future("Garfield"),
    Future(1978),
    Future(List("Lasagne"))
  ).mapN(Cat.apply)

  println(Await.result(futureCat, 1 second))
  // => Cat(Garfield,1978,List(Lasagne))

  // List
  println(Semigroupal[List].product(List(1, 2), List(3, 4)))
  // => List((1,3), (1,4), (2,3), (2,4))

  // Either
  type ErrorOr[A] = Either[Vector[String], A]

  val r = Semigroupal[ErrorOr].product(
    Left(Vector("Error 1")),
    Left(Vector("Error 2"))
  )
  println(r)
  // => Left(Vector(Error 1))

}
