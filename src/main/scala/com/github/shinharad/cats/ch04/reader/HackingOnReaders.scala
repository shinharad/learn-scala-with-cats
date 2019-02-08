package com.github.shinharad.cats.ch04.reader

import cats.data.Reader
import cats.syntax.applicative._ // for pure

case class Db(
  userNames: Map[Int, String],
  passwords: Map[String, String]
)

object HackingOnReaders extends App {

  type DbReader[A] = Reader[Db, A]

  def findUserName(userId: Int): DbReader[Option[String]] =
    Reader(db => db.userNames.get(userId))

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader(db => db.passwords.get(username).contains(password))

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    for {
      username <- findUserName(userId)
      passwordOk <- username
        .map { name =>
          checkPassword(name, password)
        }
        .getOrElse {
          false.pure[DbReader]
        }
    } yield passwordOk

  val users = Map(
    1 -> "dade",
    2 -> "kate",
    3 -> "margo"
  )

  val passwords = Map(
    "dade"  -> "zerocool",
    "kate"  -> "acidburn",
    "margo" -> "secret"
  )

  val db = Db(users, passwords)

  val r1 = checkLogin(1, "zerocool").run(db)
  println(r1) // => true

  val r2 = checkLogin(4, "davinci").run(db)
  println(r2) // => false

}
