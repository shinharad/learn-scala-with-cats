
lazy val root = (project in file("."))
  .settings(
    name := "learn-scala-with-cats",
    organization := "com.github.shinharad",
    scalaVersion := Dependencies.scalaV,
    libraryDependencies ++= Dependencies.coreDependencies,
    scalacOptions ++= Seq(
        "-Xfatal-warnings",
        "-Ypartial-unification",
        "-unchecked",
        "-deprecation",
        "-feature",
        "-Xlint"
    ),
    scalafmtOnCompile in ThisBuild := true
  )
