name := "scala-workshop-day2"

version := "0.1"

scalaVersion := "2.12.6"

// inform sbt-assembly to use Main in MANIFEST.MF
mainClass in assembly := Some("Main")

libraryDependencies += "com.github.scopt" %% "scopt" % "3.7.0"