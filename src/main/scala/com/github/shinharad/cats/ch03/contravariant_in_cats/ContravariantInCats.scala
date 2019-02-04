package com.github.shinharad.cats.ch03.contravariant_in_cats

import cats.{Contravariant, Show}
import cats.instances.string._
import cats.syntax.contravariant._

object ContravariantInCats extends App {

  val showString = Show[String]

  val showSymbol = Contravariant[Show]
    .contramap(showString)((sym: Symbol) => s"'${sym.name}")

  val r1 = showSymbol.show('dave)
  println(r1)

  showString.contramap[Symbol](_.name).show('dave)
  showString.contramap[Symbol](x => x.name).show('dave)

}
