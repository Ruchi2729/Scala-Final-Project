import sbt.ExclusionRule

name := "ScalaSparkFinalProject"

version := "0.1"

scalaVersion := "2.11.8"

val scalaTestVersion = "3.0.1"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.2.0"

)


libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.2.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.2.0"
libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % "test"

libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.5.0" % "test"

// https://mvnrepository.com/artifact/org.json4s/json4s-jackson
libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.2.9"


