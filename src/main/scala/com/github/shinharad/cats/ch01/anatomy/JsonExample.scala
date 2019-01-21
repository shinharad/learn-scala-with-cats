package com.github.shinharad.cats.ch01.anatomy

object Example1 extends App {
  import JsonWriterInstances._

  Json.toJson("Dave")

  Json.toJson(Person("Dave", "dave@example.com"))
  Json.toJson(Person("Dave", "dave@example.com"))(personWriter)

}

// enrich my libraryでtoJsonを生やす
object Example2 extends App {
  import JsonWriterInstances._
  import JsonSyntax._

  Person("Dave", "dave@example.com").toJson
  Person("Dave", "dave@example.com").toJson(personWriter)

}

// implicitlyでimplicit scopeに定義があるか検査ができる
object Example3 extends App {
  import JsonWriterInstances._

  implicitly[JsonWriter[String]]
//  implicitly[JsonWriter[Int]]

}

object Example4 extends App {
  import JsonWriterInstances._

  implicit val optionIntWriter: JsonWriter[Option[Int]] = ???
  implicit val optionPersonWriter: JsonWriter[Option[Person]] = ???

  implicit def optionWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]] =
    new JsonWriter[Option[A]] {
      def write(option: Option[A]): Json =
        option match {
          case Some(aValue) => writer.write(aValue)
          case None => JsNull
        }
    }

  Json.toJson(Option("A string"))
  Json.toJson(Option("A string"))(optionWriter[String])

  Json.toJson(Option(123))
//  Json.toJson(Option(123l))

}


