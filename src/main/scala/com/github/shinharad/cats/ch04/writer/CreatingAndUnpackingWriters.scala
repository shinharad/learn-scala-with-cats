package com.github.shinharad.cats.ch04.writer

import cats.data.Writer
import cats.instances.vector._   // for Monoid
import cats.syntax.applicative._ // for pure
import cats.syntax.writer._      // for tell

object CreatingAndUnpackingWriters extends App {

  val writer = Writer(
    Vector(
      "It was the best of times",
      "it was the worst of times"
    ),
    1859
  )
  println(writer)
  // => WriterT((Vector(It was the best of times, it was the worst of times),1859))

  // Notice that the type reported on the console is actually WriterT[Id, Vector[String], Int] instead of Writer[Vector[String], Int] as we might expect.

  type Logged[A] = Writer[Vector[String], A]

  val r = 123.pure[Logged]
  println(r) // => WriterT((Vector(),123))

  val r2 = Vector("msg1", "msg2", "msg3").tell
  println(r2) // => WriterT((Vector(msg1, msg2, msg3),()))

  val a = Writer(Vector("msg1", "msg2", "msg3"), 123)
  println(a) // => WriterT((Vector(msg1, msg2, msg3),123))

  val b = 123.writer(Vector("msg1", "msg2", "msg3"))
  println(b) // => WriterT((Vector(msg1, msg2, msg3),123))

  val aResult: Int = a.value
  println(aResult) // => 123

  val aLog: Vector[String] = a.written
  println(aLog) // => Vector(msg1, msg2, msg3)

  val (log, result) = b.run
  println(log)    // => Vector(msg1, msg2, msg3)
  println(result) // => 123

}
