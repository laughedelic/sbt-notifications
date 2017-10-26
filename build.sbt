name         := "sbt-notifications"
organization := "laughedelic"
version      := "0.2.0"

licenses := Seq("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0"))

sbtPlugin := true
sbtVersion := "1.0.2"
scalaVersion := "2.12.3"
scalacOptions ++= Seq(
  "-deprecation",
  "-feature"
)

scriptedBufferLog := false
scriptedLaunchOpts ++= Seq(
  "-Xmx1024M",
  s"-Dplugin.version=${version.value}"
)

bintrayReleaseOnPublish := !isSnapshot.value
bintrayPackageLabels := Seq("sbt", "notifications")
publishTo := (publishTo in bintray).value
