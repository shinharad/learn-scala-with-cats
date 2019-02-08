package com.github.shinharad.cats.ch04.eval

import cats.Eval

object SaferFoldingUsingEval extends App {

  //  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
  //    as match {
  //      case head :: tail =>
  //        fn(head, foldRight(tail, acc)(fn))
  //      case Nil =>
  //        acc
  //    }

  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B =
    foldRightEval(as, Eval.now(acc)) { (a, b) =>
      b.map(fn(a, _))
    }.value

  def foldRightEval[A, B](as: List[A], acc: Eval[B])(fn: (A, Eval[B]) => Eval[B]): Eval[B] =
    as match {
      case head :: tail =>
        Eval.defer(fn(head, foldRightEval(tail, acc)(fn)))
      case Nil =>
        acc
    }

  val r = foldRight((1 to 100000).toList, 0L)(_ + _)
  println(r)

}
