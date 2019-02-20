package com.github.shinharad.cats.ch06.semigroupal

import cats.Monoid
import cats.instances.int._
import cats.instances.invariant._
import cats.instances.list._
import cats.instances.string._
import cats.syntax.apply._
import cats.syntax.semigroup._

object FancyFunctorsAndApplySyntax extends App {

  case class Cat(
    name: String,
    yearOfBirth: Int,
    favoriteFoods: List[String]
  )

  val tupleToCat: (String, Int, List[String]) => Cat =
    Cat.apply
//  val tupleToCat: (String, Int, List[String]) => Cat = (x, y, z) => Cat(x, y, z)

  val catToTuple: Cat => (String, Int, List[String]) =
    cat => (cat.name, cat.yearOfBirth, cat.favoriteFoods)

  implicit val catMonoid: Monoid[Cat] = (
    Monoid[String],
    Monoid[Int],
    Monoid[List[String]]
  ).imapN(tupleToCat)(catToTuple)

  val garfield   = Cat("Garfield", 1978, List("Lasagne"))
  val heathcliff = Cat("Heathcliff", 1988, List("Junk Food"))

  val r = garfield |+| heathcliff
  println(r) // => Cat(GarfieldHeathcliff,3966,List(Lasagne, Junk Food))

}
