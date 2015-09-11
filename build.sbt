import sbt._
import sbt.Keys._

val baseSettings: Seq[Def.Setting[_]] =
  Seq(
    name := "boldAir",
    version := "1.0.0",
    organization := "com.boldradius",
    scalaVersion := "2.11.7",
    ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) },
    scalacOptions in Compile ++= Seq(
      "-encoding", 
      "UTF-8", 
      "-target:jvm-1.7", 
      "-deprecation", 
      "-unchecked", 
      "-Xfatal-warnings", 
      "-feature", 
      "-language:postfixOps"
    ),
    scalacOptions in (Compile, doc) <++= (
      name in (Compile, doc), 
      version in (Compile, doc)
    ) map DefaultOptions.scaladoc,
    javacOptions in (Compile, compile) ++= Seq(
      "-source", 
      "1.7", 
      "-target", 
      "1.7", 
      "-Xlint:unchecked", 
      "-Xlint:deprecation", 
      "-Xlint:-options"
    ),
    javacOptions in doc := Seq(),
    javaOptions += "-Xmx2G",
    outputStrategy := Some(StdoutOutput),
    exportJars := true,
    fork := true,
    Keys.fork in run := true
  )


lazy val root =  project.in( file(".") )
  .settings( baseSettings ++ Defaults.itSettings :_*)
  .settings( libraryDependencies ++= {
        Seq(
          "org.slf4j"                  %   "slf4j-api"                             % "1.7.7",
          "com.typesafe.scala-logging" %% "scala-logging"                          % "3.0.0",
          "ch.qos.logback"             %   "logback-core"                          % "1.1.2",
          "ch.qos.logback"             %   "logback-classic"                       % "1.1.2",
          "org.scalatest"              %%  "scalatest"                             % "2.2.1"  % "test",
          "com.github.nscala-time"     %% "nscala-time"                            % "2.2.0",
          "com.squants"                %% "squants"                                % "0.5.3",
          "commons-io"                 %   "commons-io"                            % "2.4"    % "test"
        )
      }
  )





