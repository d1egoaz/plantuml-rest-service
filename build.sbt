name := """plantuml-rest-service"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "net.sourceforge.plantuml" %  "plantuml"           % "8046",
  "org.scalatestplus.play"   %% "scalatestplus-play" % "1.5.1" % Test
)
