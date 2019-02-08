package com.github.shinharad.cats.ch04.writer

import cats.data.Writer
import cats.instances.vector._
import cats.syntax.applicative._
import cats.syntax.writer._

object ComposingAndTransformingWriters extends App {

  type Logged[A] = Writer[Vector[String], A]

  val writer1 = for {
    a <- 10.pure[Logged]
    _ <- Vector("a", "b", "c").tell
    b <- 32.writer(Vector("x", "y", "z"))
  } yield a + b

  val r1 = writer1.run
  println(r1) // => (Vector(a, b, c, x, y, z),42)


  // mapWritten
  val writer2 = writer1.mapWritten(_.map(_.toUpperCase))
  val r2 = writer2.run
  println(r2) // => (Vector(A, B, C, X, Y, Z),42)

  writer1.mapWritten { x => // Vector[String]
    x.map { y =>
      y.toUpperCase
    }
  }

  // bimap
  val writer3 = writer1.bimap(
    log => log.map(_.toUpperCase),
    res => res * 100
  )
  val r3 = writer3.run
  println(r3) // => (Vector(A, B, C, X, Y, Z),4200)

  // mapBoth
  val writer4 = writer1.mapBoth { (log, res) =>
    val log2 = log.map(_ + "!")
    val res2 = res * 1000
    (log2, res2)
  }
  val r4 = writer4.run
  println(r4) // => (Vector(a!, b!, c!, x!, y!, z!),42000)

  // reset
  val writer5 = writer1.reset
  val r5 = writer5.run
  println(r5) // => (Vector(),42)

  // swap
  val writer6 = writer1.swap
  val r6 = writer6.run
  println(r6) // => (42,Vector(a, b, c, x, y, z))

}
