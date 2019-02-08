package com.github.shinharad.cats.ch04.eval

import cats.Eval

object EvalAsAMonad extends App {

  val greeting = Eval
    .always { println("Step 1"); "Hello" }
    .map { str =>
      println("Step 2"); s"$str world"
    }
//  println(greeting)
  // cats.Eval$$anon$9@6a472554

//  println(greeting.value)
  // Step 1
  // Step 2
  // Hello world

  // Note that, while the semantics of the originating Eval instances are maintained,
  // mapping functions are always called lazily on demand (def semantics).
  val ans = for {
    a <- Eval.now { println("Calculating A"); 40 }
    b <- Eval.always { println("Calculating B"); 2 }
  } yield {
    println("Adding A and B")
    a + b
  }
//  println(ans)
  // cats.Eval$$anon$9@525b461a

//  println(ans.value)
  // Calculating B
  // Adding A and B
  // 42

//  println(ans.value)
  // Calculating B
  // Adding A and B
  // 42

  // Eval has a memoize method that allows us to memoize a chain of computations.
  // The result of the chain up to the call to memoize is cached,
  // whereas calculations after the call retain their original semantics.
  val saving = Eval
    .always { println("Step 1"); "The cat" }
    .map { str =>
      println("Step 2"); s"$str sat on"
    }
    .memoize
    .map { str =>
      println("Step 3"); s"$str the mat"
    }
  println(saving)
  // cats.Eval$$anon$9@61d47554

  println(saving.value)
  // Step 1
  // Step 2
  // Step 3
  // The cat sat on the mat

  println(saving.value)
  // Step 3
  // The cat sat on the mat

}
