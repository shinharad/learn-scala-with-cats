package com.github.shinharad.cats.ch07.traverse

import cats.Traverse
import cats.instances.future._
import cats.instances.list._
import cats.syntax.traverse._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.language.{higherKinds, postfixOps}

object TraverseInCats extends App {

  val hostnames = List(
    "alpha.example.com",
    "beta.example.com",
    "gamma.demo.com"
  )

  def getUptime(hostname: String): Future[Int] =
    Future(hostname.length * 60)

  val totalUptime: Future[List[Int]] =
    Traverse[List].traverse(hostnames)(getUptime)

  val r1 = Await.result(totalUptime, 1 second)
  println(r1) // => List(1020, 960, 840)

  val numbers = List(Future(1), Future(2), Future(3))

  val numbers2: Future[List[Int]] =
    Traverse[List].sequence(numbers)

  val r2 = Await.result(hostnames.traverse(getUptime), 1 second)
  println(r2) // => List(1020, 960, 840)

  val r3 = Await.result(numbers.sequence, 1 second)
  println(r3) // => List(1, 2, 3)

}
