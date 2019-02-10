package com.github.shinharad.cats.ch04.state

import cats.data.State

object ComposingAndTransformingState2 extends App {

  // get extracts the state as the result
  val getDemo = State.get[Int]
  val r1      = getDemo.run(10).value
  println(r1) // => (10,10)

  // set updates the state and returns unit as the result
  val setDemo = State.set[Int](30)
  val r2      = setDemo.run(10).value
  println(r2) // => (30,())

  // pure ignores the state and returns a supplied result
  val pureDemo = State.pure[Int, String]("Result")
  val r3       = pureDemo.run(10).value
  println(r3) // => (10,Result)

  // inspect extracts the state via a transformation function
  val inspectDemo = State.inspect[Int, String](_ + "!")
  val r4          = inspectDemo.run(10).value
  println(r4) // => (10,10!)

  // modify updates the state using an update function
  val modifyDemo = State.modify[Int](_ + 1)
  val r5         = modifyDemo.run(10).value
  println(r5) // => (11,())

  import State._
  val program: State[Int, (Int, Int, Int)] = for {
    a <- get[Int]
    _ <- set[Int](a + 1)
    b <- get[Int]
    _ <- modify[Int](_ + 1)
    c <- inspect[Int, Int](_ * 1000)
  } yield (a, b, c)

  val (state, result) = program.run(1).value
  println(state)  // => 3
  println(result) // => (1,2,3000)

}
