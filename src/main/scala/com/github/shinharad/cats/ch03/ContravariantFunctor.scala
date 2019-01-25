package com.github.shinharad.cats.ch03

trait Printable[A] { self =>

  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] =
    new Printable[B] {
      def format(value: B): String =
        self.format(func(value))
    }
}

object ContravariantFunctor extends App {

  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }

  implicit val booleanPrintable: Printable[Boolean] =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if (value) "yes" else "no"
    }

  implicit def boxPrintable[A](implicit p: Printable[A]) =
    new Printable[Box[A]] {
      def format(box: Box[A]): String =
        p.format(box.value)
    }

  def format[A](value: A)(implicit p: Printable[A]): String =
    p.format(value)

  val r1 = format("hello")

  val r2 = format(true)

  val r3 = format(Box("hello world"))

  val r4 = format(Box(true))

  println(r1)
  println(r2)
  println(r3)
  println(r4)

}
