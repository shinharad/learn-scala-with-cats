package com.github.shinharad.cats.ch03

import scala.concurrent.{Await, Future}
import scala.util.Random
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

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
