package com.github.shinharad.cats.ch04.identity_monad

import cats.Id

trait MonadicSecretIdentities {

  def pure[A](value: A): Id[A] = value

  def map[A, B](initial: Id[A])(func: A => B): Id[B] =
    func(initial)
  map(123)(_ * 2)

  def flatMap[A, B](initial: Id[A])(func: A => Id[B]) =
    func(initial)
  flatMap(123)(_ * 2)
  flatMap("abc")(_ + "!")

}
