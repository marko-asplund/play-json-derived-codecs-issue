name := "play-json-2"

version := "0.1"

scalaVersion := "2.13.2"

scalacOptions ++= Seq(
  "-P:splain:implicits:true",
  "-Vimplicits"
)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.9.0",
  "ai.x" %% "play-json-extensions" % "0.42.0",
  "com.typesafe.play" %% "play-json-joda" % "2.9.0",
  "org.julienrf" %% "play-json-derived-codecs" % "7.0.0"
)

addCompilerPlugin("io.tryp" % "splain" % "0.5.6" cross CrossVersion.patch)
