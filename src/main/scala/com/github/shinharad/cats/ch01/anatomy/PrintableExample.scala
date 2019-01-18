package com.github.shinharad.cats.ch01.anatomy

final case class Cat(name: String, age: Int, color: String)

object PrintableExample extends App {
  import PrintableSyntax._

  implicit val catPrintable = new Printable[Cat] {
    def format(input: Cat) = input.toString
  }

  Cat("Garfield", 38, "ginger and black").print

}
