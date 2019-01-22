package com.github.shinharad.cats.ch03

import cats.Functor

import scala.concurrent.{ExecutionContext, Future}

object FunctorCustomTypesExample extends App {

  implicit val optionFunctor: Functor[Option] =
    new Functor[Option] {
      def map[A, B](value: Option[A])(func: A => B): Option[B] =
        value.map(func)
    }

  implicit def futureFunctor(implicit ec: ExecutionContext): Functor[Future] =
    new Functor[Future] {
      def map[A, B](value: Future[A])(func: A => B): Future[B] =
        value.map(func)
    }

}
