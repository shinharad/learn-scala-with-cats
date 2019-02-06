package com.github.shinharad.cats.ch04.monaderror

import scala.util.Try
import cats.instances.try_._
import cats.syntax.applicativeError._

object InstancesOfMonadError {

  val exn: Throwable =
    new RuntimeException("It's all gone wrong")

  exn.raiseError[Try, Int]

}
