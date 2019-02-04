package com.github.shinharad.cats.ch03.invariant_in_cats

import cats.Monoid
import cats.instances.string._ // for Monoid
import cats.syntax.invariant._ // for imap
import cats.syntax.semigroup._ // for |+|

object InvariantInCats extends App {

  implicit val symbolMonoid: Monoid[Symbol] =
    Monoid[String].imap(Symbol.apply)(_.name)

  Monoid[Symbol].empty

  val r = 'a |+| 'few |+| 'words

  println(r)

}
