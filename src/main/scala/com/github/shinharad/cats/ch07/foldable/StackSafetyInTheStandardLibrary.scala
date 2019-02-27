package com.github.shinharad.cats.ch07.foldable

object StackSafetyInTheStandardLibrary extends App {

  println((1 to 100000).toList.foldRight(0L)(_ + _))
  // => 5000050000

  println((1 to 100000).toVector.foldRight(0L)(_ + _))
  // => 5000050000

  // We’ve called out Stream because it is an excep􀦞on to this rule.

}
