package com.github.shinharad.cats.ch03.invariant

import com.github.shinharad.cats.ch03.Box

trait Codec[A] { self =>
  def encode(value: A): String
  def decode(value: String): A

  def imap[B](dec: A => B, enc: B => A): Codec[B] = {
//    val self = this
    new Codec[B] {
      def encode(value: B): String =
        self.encode(enc(value))

      def decode(value: String): B =
        dec(self.decode(value))
    }
  }

}

object InvariantFunctor extends App {

  implicit val stringCodec: Codec[String] =
    new Codec[String] {
      def encode(value: String): String = value
      def decode(value: String): String = value
    }

  // decodeは例外エラーを投げる可能性がある
  implicit val intCodec: Codec[Int] =
    stringCodec.imap(_.toInt, _.toString)

  implicit val booleanCodec: Codec[Boolean] =
    stringCodec.imap(_.toBoolean, _.toString)

  implicit val doubleCodec: Codec[Double] =
    stringCodec.imap(_.toDouble, _.toString)

  implicit def boxCodec[A](implicit c: Codec[A]): Codec[Box[A]] =
    c.imap[Box[A]](a => Box(a), _.value)

  def encode[A](value: A)(implicit c: Codec[A]): String =
    c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): A =
    c.decode(value)

  val r1 = encode(123.4)
  println(r1)

  val r2 = decode[Double]("123.4")
  println(r2)

  val r3 = encode(Box(123.4))
  println(r3)

  val r4 = decode[Box[Double]]("123.4")
  println(r4)

}
