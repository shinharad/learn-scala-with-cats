package com.github.shinharad.cats.ch07.foldable

import cats.Foldable
import cats.syntax.foldable._

import scala.language.higherKinds

object ExplicitsOverImplicits extends App {

  def sum[F[_]: Foldable](values: F[Int]): Int =
    values.foldLeft(0)(_ + _)

}
