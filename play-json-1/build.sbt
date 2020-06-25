name := "play-json-1"

version := "0.1"

scalaVersion := "2.12.11"

scalacOptions ++= Seq(
  "-P:splain:implicits:true"
)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.6.13",
  "ai.x" %% "play-json-extensions" % "0.40.2",
  "com.typesafe.play" %% "play-json-joda" % "2.6.13",
  "org.julienrf" %% "play-json-derived-codecs" % "6.0.0"
)

addCompilerPlugin("io.tryp" % "splain" % "0.5.6" cross CrossVersion.patch)
