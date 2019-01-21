package com.github.shinharad.cats.ch02.semigroup.exercise

import com.github.shinharad.cats.ch02.semigroup.Monoid

object AllSetForMonoids extends App {

//  implicit def setUnionMonoid[A]: Monoid[Set[A]] =
//    new Monoid[Set[A]] {
//      def combine(a: Set[A], b: Set[A]) = a union b
//      def empty = Set.empty[A]
//    }

//  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
//    def combine(a: Int, b: Int) = a + b
//    def empty = 0
//  }

//  implicit def setIntersectionSemigroup[A]: Semigroup[Set[A]] =
//    new Semigroup[Set[A]] {
//      def combine(a: Set[A], b: Set[A]) =
//        a intersect b
//    }

  implicit def symDiffMonoid[A]: Monoid[Set[A]] =
    new Monoid[Set[A]] {
      def combine(a: Set[A], b: Set[A]): Set[A] =
        (a diff b) union (b diff a)
      def empty: Set[A] = Set.empty
    }

  val intSetMonoid = Monoid[Set[Int]]

  println(intSetMonoid.combine(Set(1, 2), Set(2, 3)))

}
