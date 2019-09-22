package com.github.shinharad.cats.ch01.meetcats

object EqExample01 extends App {
  import cats.Eq
  import cats.instances.int._

  val eqInt = Eq[Int]

  println(eqInt.eqv(123, 123))
  println(eqInt.eqv(123, 234))
//  eqInt.eqv(123, "234")

  import cats.syntax.eq._

  123 === 123

  123 =!= 234

  // compile error
//  123 === "123"

  // compile error
//  List(1, 2, 3).map(Option(_)).filter(x => x === 1)

}

// Comparing Options
object EqExample02 extends App {
  import cats.instances.int._
  import cats.instances.option._
  import cats.syntax.eq._

//  Some(1) === None
  println((Some(1): Option[Int]) === (None: Option[Int]))

  import cats.syntax.option._

  1.some === none[Int]
  1.some =!= none[Int]

}

// Comparing Custome Types
object EqExample03 extends App {
  import java.util.Date
  import cats.Eq
  import cats.instances.long._
  import cats.syntax.eq._

  // instance
  implicit val dateEq: Eq[Date] =
    Eq.instance[Date] { (date1, date2) =>
      date1.getTime === date2.getTime
    }

  val x = new Date()
  val y = new Date()

  println(x === x)

  println(x === y)

}
