package com.github.shinharad.cats.ch01.variance

import com.github.shinharad.cats.ch01.anatomy.Json

// Circle is a subtype of Shape
sealed trait Shape
case class Circle(radius: Double) extends Shape

object Covariance extends App {

  val circles: List[Circle] = ???
  val shapes: List[Shape] = circles

}

object Contravariance extends App {

  trait JsonWriter[-A] {
    def write(value: A): Json
  }

  val shape: Shape = ???
  val circle: Circle = ???

  // JsonWriter[Shape] is a subtype of JsonWriter[Circle]
  // We can use shapeWriter anywhere we expect to see a JsonWriter[Circle].
  val shapeWriter: JsonWriter[Shape] = ???
  val circleWriter: JsonWriter[Circle] = ???

  def format[A](value: A, writer: JsonWriter[A]): Json =
    writer.write(value)

}

object Invariance extends App {
  trait F[A]

  sealed trait A
  final case object B extends A
  final case object C extends A
}
