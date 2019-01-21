package com.github.shinharad.cats.ch02.monoid

trait Monoid[A] {
  def combine(x: A, y: A): A
  def emplty: A
}

object MonoidLaw {
  def associativeLaw[A](x: A, y: A, z: A)(implicit m: Monoid[A]): Boolean = {
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)
  }

  def identityLaw[A](x: A)(implicit m: Monoid[A]): Boolean = {
    (m.combine(x, m.emplty) == x) &&
    (m.combine(m.emplty, x) == x)
  }

//  (1 - 2) - 3
//
//  1 - (2 - 3)

}
