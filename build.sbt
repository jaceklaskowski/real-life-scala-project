name := "scala-workshop-project"

version := "0.1"

scalaVersion := "2.12.6"

// inform sbt-assembly to use Main in MANIFEST.MF
mainClass in assembly := Some("Main")

libraryDependencies += "com.github.scopt" %% "scopt" % "3.7.0"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

logBuffered in Test := false