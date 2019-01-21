package com.github.shinharad.cats.ch03

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._
import scala.util.Random

object FuturesAndReferentialTransparency extends App {

  val future: Future[String] =
    Future(123)
      .map(n => n + 1)
      .map(n => n * 2)
      .map(n => n + "!")

  println(Await.result(future, 1.second))

  val future1 = {
    val r = new Random(0L)

    val x = Future(r.nextInt)

    for {
      a <- x
      b <- x
    } yield (a, b)
  }

  val future2 = {
    val r = new Random(0L)

    for {
      a <- Future(r.nextInt)
      b <- Future(r.nextInt)
    } yield (a, b)
  }

  val result1 = Await.result(future1, 1.second)
  println(result1)

  val result2 = Await.result(future2, 1.second)
  println(result2)

}

object FunctionExample extends App {

  val func1: Int => Double =
    (x: Int) => x.toDouble

  val func2: Double => Double =
    (y: Double) => y * 2

  // "-Ypartial-unification"
  val r1 = (func1 map func2)(1)
  println(r1)

  val r2 = (func1 andThen func2)(1)
  println(r2)

  val r3 = func2(func1(1))
  println(r3)

  // "-Ypartial-unification"
  val func =
    ((x: Int) => x.toDouble)
      .map(x => x + 1)
      .map(x => x * 2)
      .map(x => x + "!")

  println(func(123))

}
