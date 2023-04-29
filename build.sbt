name := "covid-api-extension"

version := "0.1"

scalaVersion := "3.2.2"

idePackagePrefix := Some("shgo.innowise.trainee.covidapi")

val Http4sVersion = "1.0.0-M39"
val CirceVersion = "0.14.5"
libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-ember-server" % Http4sVersion,
  "org.http4s"      %% "http4s-circe"        % Http4sVersion,
  "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
  "io.circe"        %% "circe-generic"       % CirceVersion,
  "com.softwaremill.sttp.client3" %% "core" % "3.8.15",
  "com.softwaremill.sttp.client3" %% "circe" % "3.8.15",
  "org.scalatest" %% "scalatest" % "3.2.15" % Test
)