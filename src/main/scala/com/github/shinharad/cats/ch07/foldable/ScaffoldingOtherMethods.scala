package com.github.shinharad.cats.ch07.foldable

object ScaffoldingOtherMethods extends App {

  def map[A, B](list: List[A])(func: A => B): List[B] =
    list.foldRight(List.empty[B]) { (item, accum) =>
      func(item) :: accum
    }

  println(map(List(1, 2, 3))(_ * 2))
  // => List(2, 4, 6)

  def flatMap[A, B](list: List[A])(func: A => List[B]): List[B] =
    list.foldRight(List.empty[B]) { (item, accum) =>
      func(item) ::: accum
    }

  println(flatMap(List(1, 2, 3))(a => List(a, a * 10, a * 1000)))
  // => List(1, 10, 1000, 2, 20, 2000, 3, 30, 3000)

  def filter[A](list: List[A])(func: A => Boolean): List[A] =
    list.foldRight(List.empty[A]) { (item, accum) =>
      if (func(item)) item :: accum else accum
    }

  println(filter(List(1, 2, 3))(_ % 2 == 1))
  // => List(1, 3)

  import scala.math.Numeric

  def sumWithNumeric[A](list: List[A])(implicit numeric: Numeric[A]): A =
    list.foldRight(numeric.zero)(numeric.plus)

  println(sumWithNumeric(List(1, 2, 3)))
  // => 6

  import cats.Monoid

  def sumWithMonoid[A](list: List[A])(implicit monoid: Monoid[A]): A =
    list.foldRight(monoid.empty)(monoid.combine)

  import cats.instances.int._ // for Monoid

  println(sumWithMonoid(List(1, 2, 3)))
  // => 6

}
