package com.github.shinharad.cats.ch04.eval

import cats.Eval

object TrampoliningAndEvalDefer extends App {

//  def factorial(n: BigInt): BigInt =
//    if (n == 1) n else n * factorial(n - 1)
//
//  factorial(50000)
  // java.lang.StackOverflowError


//  def factorial(n: BigInt): Eval[BigInt] =
//    if (n == 1) {
//      Eval.now(n)
//    } else {
//      factorial(n - 1).map(_ * n)
//    }
//  factorial(50000).value
  // java.lang.StackOverflowError


  // It avoids consuming stack by creating a chain of function objects on the heap.
  // There are still limits on how deeply we can nest computations,
  // but they are bounded by the size of the heap rather than the stack.
  def factorial(n: BigInt): Eval[BigInt] =
    if (n == 1) {
      Eval.now(n)
    } else {
      Eval.defer(factorial(n - 1).map(_ * n))
    }
  val r = factorial(50000).value
  println(r)

}
