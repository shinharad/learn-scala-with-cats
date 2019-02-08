package com.github.shinharad.cats.ch04.state

import cats.data.State

object CreatingAndUnpackingState extends App {

  val a = State[Int, String] { state =>
    (state, s"The state is $state")
  }
  println(a)

  val (state, result) = a.run(10).value
  println(state) // => 10
  println(result) // => The state is 10

  // runS
  val state2 = a.runS(10).value
  println(state2) // => 10

  // runA
  val result2 = a.runA(10).value
  println(result2) // => The state is 10

}
