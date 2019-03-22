package com.github.shinharad.cats.ch03.contravariant_in_cats

import cats.{Contravariant, Show}
import cats.instances.string._

object ContravariantInCats extends App {

  val showString = Show[String]

  val showSymbol = Contravariant[Show]
    .contramap(showString)((sym: Symbol) => s"'${sym.name}")

  val r1 = showSymbol.show('dave)
  println(r1)

  import cats.syntax.contravariant._
  showString.contramap[Symbol](_.name).show('dave)

  println(Show[String].contramap[Symbol](_.name).show('dave))

  case class Hoge(value: String)

  val f: Show[Hoge] = Show[String].contramap[Hoge](_.value)
  println(f.show(Hoge("abc")))

  import cats.instances.int._
  val g: Show[Hoge] = Show[Int].contramap[Hoge](_.value.toInt)
  println(g.show(Hoge("123")))

}
