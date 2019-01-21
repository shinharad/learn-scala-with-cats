package com.github.shinharad.cats.ch02.cats.exercise

import cats.Monoid
import cats.instances.int._
import cats.syntax.semigroup._

case class Order(totalCost: Double, quantity: Double)

object AddingAllTheThings extends App {

  def add(items: List[Int]): Int =
    items.foldLeft(Monoid[Int].empty)(_ |+| _)

  implicit val monoid: Monoid[Order] = new Monoid[Order] {
    def combine(o1: Order, o2: Order) =
      Order(
        o1.totalCost + o2.totalCost,
        o1.quantity + o2.quantity
      )
    def empty = Order(0, 0)
  }

  println(Order(100.0, 200.0) |+| Order(300.0, 400.0))

}

