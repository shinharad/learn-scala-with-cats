
lazy val root = (project in file("."))
  .settings(
    name := "scala-with-cats",
    organization := "com.github.shinharad",
    scalaVersion := "2.13.1",
    // scalaVersion := "2.12.9",
    libraryDependencies ++= Dependencies.catsDependencies,
    scalacOptions ++= Seq(
//        "-Xfatal-warnings",
        // "-Ypartial-unification",
        "-unchecked",
        "-deprecation",
        "-feature",
        "-Xlint"
    ),
    ThisBuild / scalafmtOnCompile := true,
    ThisBuild / turbo := true
  )
