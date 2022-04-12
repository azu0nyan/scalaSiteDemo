name := "scala-site-demo"

version := "0.1"

scalaVersion := "2.13.8"


//json parsing
val circeVersion = "0.12.3"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

//databases
libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"       % "3.4.0",
  "com.h2database"  %  "h2"                % "1.4.200",
  "ch.qos.logback"  %  "logback-classic"   % "1.2.3"
)
//http server
libraryDependencies += "com.sparkjava"  % "spark-core" % "2.8.0"
//html generator
libraryDependencies += "com.lihaoyi" %% "scalatags" % "0.8.5"
