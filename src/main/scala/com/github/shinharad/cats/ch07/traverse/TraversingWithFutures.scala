package com.github.shinharad.cats.ch07.traverse

import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps

object TraversingWithFutures extends App {

  val hostnames = List(
    "alpha.example.com",
    "beta.example.com",
    "gamma.demo.com"
  )

  def getUptime(hostname: String): Future[Int] =
    Future(hostname.length * 60)

  val allUptimes: Future[List[Int]] =
    hostnames.foldLeft(Future(List.empty[Int])) {
      (accum, host) =>
        val uptime = getUptime(host)
        for {
          accum <- accum
          uptime <- uptime
        } yield accum :+ uptime
    }

  val r1 = Await.result(allUptimes, 1 second)
  println(r1) // => List(1020, 960, 840)

  // traverse
  val allUptimes2: Future[List[Int]] =
    Future.traverse(hostnames)(getUptime)

  val r2 = Await.result(allUptimes2, 1 second)
  println(r2) // => List(1020, 960, 840)

}
