package com.github.shinharad.cats.ch07.foldable

import cats.Eval
import cats.Foldable
import cats.instances.stream._ // for Foldable

// Streamは無くなるのだけれども
object FoldingRightInCats extends App {

  def bigData = (1 to 100000).toStream

//  bigData.foldRight(0L)(_ + _)
  // java.lang.StackOverflowError ...

  val eval: Eval[Long] =
    Foldable[Stream]
      .foldRight(bigData, Eval.now(0L)) { (num, eval) =>
        eval.map(_ + num)
      }

  println(eval.value)
  // => 5000050000

}
