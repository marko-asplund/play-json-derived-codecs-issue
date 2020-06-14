name := "play-json-2"

version := "0.1"

scalaVersion := "2.12.11"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.8.1",
  "ai.x" %% "play-json-extensions" % "0.42.0",
  "com.typesafe.play" %% "play-json-joda" % "2.8.1",
  "org.julienrf" %% "play-json-derived-codecs" % "7.0.0"
)
