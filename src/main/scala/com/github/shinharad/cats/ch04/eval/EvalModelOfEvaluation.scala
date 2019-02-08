package com.github.shinharad.cats.ch04.eval

import cats.Eval

object EvalModelOfEvaluation extends App {

  val now = Eval.now(math.random + 1000)
  println(now) // => Now(1000.4090122927167)

  val later = Eval.later(math.random + 2000)
  println(later) // => cats.Later@1dd92fe2

  val always = Eval.always(math.random + 3000)
  println(always) // => cats.Always@1b68b9a4

  val r1 = now.value
  println(r1) // => 1000.2144483674313

  val r2 = later.value
  println(r2) // => 2000.5566056540997

  val r3 = always.value
  println(r3) // => 3000.230588282586

  // Eval.now captures a value right now. Its semantics are similar to a val
  // eager and memoized
  val x = Eval.now {
    println("Computing X")
    math.random
  }
  println(x)
  // Computing X
  // Now(0.007074451170749008)

  println(x.value)
  // 0.007074451170749008

  println(x.value)
  // 0.007074451170749008

  // Eval.always captures a lazy computation, similar to a def
  val y = Eval.always {
    println("Computing Y")
    math.random
  }
  println(y)
  // cats.Always@3b2c72c2

  println(y.value)
  // Computing Y
  // 0.43512907738168927

  println(y.value)
  // Computing Y
  // 0.6069255237758898

  // Eval.later captures a lazy, memoized computation, similar to a lazy val
  val z = Eval.later {
    println("Computing Z")
    math.random
  }
  println(z)
  // cats.Later@176d53b2

  println(z.value)
  // Computing Z
  // 0.8937286637675295

  println(z.value)
  // 0.8937286637675295

}
