import sbt._

object Dependencies {

  val scalaV = "2.12.8"

  val catsV = "1.6.0"

  val scalatestV  = "3.0.5"

  val scalacheckV = "1.14.0"

  lazy val testingDependencies = Seq(
    "org.scalatest"     %% "scalatest"  % scalatestV,
    "org.scalacheck"    %% "scalacheck" % scalacheckV
  ).map(_ % Test)

  lazy val catsDependencies = Seq(
    "org.typelevel" %% "cats-core"
  ).map(_ % catsV)

  // Project Dependencies

  lazy val coreDependencies =
    testingDependencies ++
    catsDependencies

}
