package com.github.shinharad.cats.ch03.contravariant

import com.github.shinharad.cats.ch03.Box

trait Printable[A] { self =>

  def format(value: A): String

  def contramap[B](func: B => A): Printable[B] =
    b => self.format(func(b))

}

object ContravariantFunctor extends App {

  implicit val stringPrintable: Printable[String] =
    b => "\"" + b + "\""

  implicit val booleanPrintable: Printable[Boolean] =
    b => if (b) "yes" else "no"

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
