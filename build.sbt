name := "CSYE7200_FinalProject_Team5_Winter2017"

version := "0.1"

scalaVersion := "2.11.8"

organization := "edu.neu.CSYE7200.winter2017.team5"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.0.0" % "provided",
  "org.apache.spark" % "spark-sql_2.11" % "2.0.0" % "provided")