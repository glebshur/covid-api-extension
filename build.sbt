name := "covid-api-extension"

version := "0.1"

scalaVersion := "3.2.2"

idePackagePrefix := Some("shgo.innowise.trainee.covidapi")

val Http4sVersion = "1.0.0-M39"
val CirceVersion = "0.14.5"
val SttpVersion = "3.8.15"
val ScalaTestVersion = "3.2.15"
val LogbackVersion = "1.4.6"
libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-ember-server" % Http4sVersion,
  "org.http4s"      %% "http4s-circe"        % Http4sVersion,
  "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
  "io.circe"        %% "circe-generic"       % CirceVersion,
  "com.softwaremill.sttp.client3" %% "core" % SttpVersion,
  "com.softwaremill.sttp.client3" %% "circe" % SttpVersion,
  "org.scalatest" %% "scalatest" % ScalaTestVersion % Test
)