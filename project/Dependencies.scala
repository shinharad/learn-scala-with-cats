import sbt._

object Dependencies {

  val catsV = "2.0.0"

  lazy val catsDependencies = Seq(
    "org.typelevel" %% "cats-core"
  ).map(_ % catsV)

}
