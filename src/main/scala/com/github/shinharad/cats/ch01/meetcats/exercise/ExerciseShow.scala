package com.github.shinharad.cats.ch01.meetcats.exercise

import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._

object ExerciseShow extends App {

  final case class Cat(name: String, age: Int, color: String)

  implicit val catShow = Show.show[Cat] { cat =>
    val name  = cat.name.show
    val age   = cat.age.show
    val color = cat.color.show
    s"$name is a $age year-old $color cat."
  }

  println(Cat("Garfield", 38, "ginger and black").show)

}
