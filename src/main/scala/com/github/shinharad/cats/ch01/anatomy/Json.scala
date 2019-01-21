package com.github.shinharad.cats.ch01.anatomy

sealed trait Json
final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String)            extends Json
final case class JsNumber(get: Double)            extends Json
case object JsNull                                extends Json

object Json {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
    w.write(value)
}

trait JsonWriter[A] {
  def write(value: A): Json
}

final case class Person(name: String, email: String)

object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String] =
    new JsonWriter[String] {
      def write(value: String): Json =
        JsString(value)
    }
//  implicit val stringWriter: JsonWriter[String] =
//    JsString(_)

  implicit val personWriter: JsonWriter[Person] =
    new JsonWriter[Person] {
      def write(value: Person): Json =
        JsObject(
          Map(
            "name"  -> JsString(value.name),
            "email" -> JsString(value.email)
          ))
    }
//  implicit val personWriter: JsonWriter[Person] =
//    value => JsObject(Map(
//      "name" -> JsString(value.name),
//      "email" -> JsString(value.email)
//    ))

}

object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonWriter[A]): Json =
      w.write(value)
  }
}
