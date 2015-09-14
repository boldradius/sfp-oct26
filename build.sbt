organization := "training.scala"

name := "air-scala"

scalaVersion := Version.scala

// The Typesafe repository
resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Dependencies.airScala


// Simplify console work in test and main by importing our package space
initialCommands in console := """
import training.scala.air_scala._
import training.scala.air_scala.domain._
"""

// Run and compilation settings
scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-target:jvm-1.6",
  "-encoding", "UTF-8",
  "-Xfatal-warnings",
  "-feature"
)

javacOptions in (Compile, compile) ++= List(
  "-source", "1.7",
  "-target", "1.7",
  "-Xlint:unchecked",
  "-Xlint:deprecation",
  "-Xlint:-options"
)

javacOptions in doc := Seq()

javaOptions += "-Xmx2G"

fork in run := true

parallelExecution in Test := false

parallelExecution in ThisBuild := false



// Eclipse settings to ease the pain
EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource
EclipseKeys.eclipseOutput := Some(".target")
EclipseKeys.withSource := true



