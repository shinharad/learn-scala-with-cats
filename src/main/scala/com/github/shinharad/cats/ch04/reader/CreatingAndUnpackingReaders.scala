package com.github.shinharad.cats.ch04.reader

import cats.data.Reader

case class Cat(name: String, favoriteFood: String)

object CreatingAndUnpackingReaders extends App {

  val catName: Reader[Cat, String] = Reader(cat => cat.name)
  val r1                           = catName.run(Cat("Garfield", "lasagne"))
  println(r1) // => Garfield

  val greetKitty: Reader[Cat, String] = catName.map(name => s"Hello $name")
  val r2                              = greetKitty.run(Cat("Heathcliff", "junk food"))
  println(r2) // => Hello Heathcliff

  val feedKitty: Reader[Cat, String] =
    Reader(cat => s"Have a nice bowl of ${cat.favoriteFood}")

  val greetAndFeed: Reader[Cat, String] =
    for {
      greet <- greetKitty
      feed  <- feedKitty
    } yield s"$greet. $feed."

  val r3 = greetAndFeed(Cat("Garfield", "lasagne"))
  println(r3) // => Hello Garfield. Have a nice bowl of lasagne.

  val r4 = greetAndFeed(Cat("Heathcliff", "junk food"))
  println(r4) // => Hello Heathcliff. Have a nice bowl of junk food.

}
