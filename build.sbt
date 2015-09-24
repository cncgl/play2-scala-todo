name := """play2-scala-todo"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "com.typesafe.play" %% "anorm" % "2.4.0",
//  "com.typesafe.slick" %% "slick" % "3.0.3",
//  "com.typesafe.slick" %% "slick-codegen" % "3.0.3",
//  "com.typesafe.play" %% "play-slick" % "1.0.1",
//  "com.typesafe.play" %% "play-slick-evolutions" % "1.0.1",
  "org.postgresql"  %  "postgresql"  %  "9.3-1102-jdbc41"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
