name := "Hotel Recommend"
 
version := "1.0" 
      
lazy val `hotelrecommend` = (project in file(".")).enablePlugins(PlayScala)

val scalaTestVersion = "3.0.1"

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.11.8"



 // .map(_.excludeAll(ExclusionRule(organization = "com.google.guava")))

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "com.fasterxml.jackson.core" % "jackson-core" % "2.8.7",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.7",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.8.7",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.7",
  "org.apache.spark" %% "spark-core" % "2.1.0",
  "org.webjars" %% "webjars-play" % "2.5.0-1",
  "org.webjars" % "bootstrap" % "3.3.6",
  "org.apache.spark" %% "spark-mllib" % "2.1.0",
  "org.apache.spark" %% "spark-sql" % "2.1.0",
  "com.adrianhurt" %% "play-bootstrap" % "1.0-P25-B3"
).map(_.excludeAll(ExclusionRule(organization = "com.google.guava")))

// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind

