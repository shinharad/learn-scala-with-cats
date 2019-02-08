package com.github.shinharad.cats.ch04.state

import cats.data.State

object ComposingAndTransformingState extends App {

  val step1 = State[Int, String] { num =>
    val ans = num + 1
    (ans, s"Result of step1: $ans")
  }

  val step2 = State[Int, String] { num =>
    val ans = num * 2
    (ans, s"Result of step2: $ans")
  }

  val both = for {
    a <- step1
    b <- step2
  } yield (a, b)

  val (state, result) = both.run(20).value
  println(state)  // => 42
  println(result) // => (Result of step1: 21,Result of step2: 42)

}
