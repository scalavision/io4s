name := "scalavision/io4s"

organization := "scalavision"

scalaVersion := "2.11.8"

libraryDependencies ++=
  Seq("org.specs2" %% "specs2-core" % "3.8.9" % "test")

scalacOptions in Test ++= Seq("-Yrangepos")
