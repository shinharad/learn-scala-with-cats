package com.github.shinharad.cats.ch01.anatomy

final case class Cat(name: String, age: Int, color: String)

object PrintableExample extends App {
  import PrintableInstances._
  import PrintableSyntax._

  implicit val catPrintable = new Printable[Cat] {
    def format(cat: Cat) = {
      val name  = Printable.format(cat.name)
      val age   = Printable.format(cat.age)
      val color = Printable.format(cat.color)
      s"$name is a $age year-old $color cat."
    }
  }

  Cat("Garfield", 38, "ginger and black").print

}
