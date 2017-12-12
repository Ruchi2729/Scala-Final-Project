name := "ExamFinal"

version := "0.1"

scalaVersion := "2.12.4"

val scalaTestVersion="3.0.1"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.9.2",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6",
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
)
        