organization  := "io.github.peel"

version := "0.1.0"

scalaVersion := "2.11.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaHttpV =  "1.0-M2"
  val akkaV = "2.3.8"
  val specsV = "2.3.11"
  val logbackV = "1.1.2"
  Seq(
    "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpV,
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-slf4j" % akkaV,
    "ch.qos.logback" %% "logback-classic" % logbackV,
    "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
    "org.specs2" %% "specs2-core" % specsV % "test"
  )
}

Revolver.settings

