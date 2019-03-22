package com.github.shinharad.cats.ch04.writer

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

import cats.data.Writer
import cats.instances.vector._
import cats.syntax.applicative._
import cats.syntax.writer._

object ShowYourWorking extends App {

  def slowly[A](body: => A) =
    try body
    finally Thread.sleep(100)

  def factorial(n: Int): Int = {
    val ans = slowly(if (n == 0) 1 else n * factorial(n - 1))
    println(s"fact $n $ans")
    ans
  }

  val r1 = factorial(5)
//  println(r1)

  // If we start several factorials in parallel, the log messages can becom interleaved on standard out.
  // This makes it difficult to see which messages come from which computation.
  val r2 = Await.result(
    Future.sequence(
      Vector(
        Future(factorial(3)),
        Future(factorial(3))
      )
    ),
    5.seconds
  )
//  println(r2)

  type Logged[A] = Writer[Vector[String], A]

  def factorial2(n: Int): Logged[Int] =
    for {
      ans <- if (n == 0) {
        1.pure[Logged]
      } else {
        slowly(factorial2(n - 1).map(_ * n))
      }
      _ <- Vector(s"fact $n $ans").tell
    } yield ans

  val (log, res) = factorial2(5).run
//  println(log) // => Vector(fact 0 1, fact 1 1, fact 2 2, fact 3 6, fact 4 24, fact 5 120)
//  println(res) // => 120

  val Vector((logA, ansA), (logB, ansB)) = Await.result(
    Future.sequence(
      Vector(
        Future(factorial2(3).run),
        Future(factorial2(5).run)
      )
    ),
    5.seconds
  )
  println(logA) // => Vector(fact 0 1, fact 1 1, fact 2 2, fact 3 6)
  println(ansA) // => 6
  println(logB) // => Vector(fact 0 1, fact 1 1, fact 2 2, fact 3 6, fact 4 24, fact 5 120)
  println(ansB) // => 120

}
