package com.github.shinharad.cats.ch04.monaderror

import scala.util.Try
import cats.instances.try_._
import cats.syntax.applicativeError._

object InstancesOfMonadError extends App {

  val exn: Throwable =
    new RuntimeException("It's all gone wrong")

  println(exn.raiseError[Try, Int])
  // => Failure(java.lang.RuntimeException: It's all gone wrong)

}
