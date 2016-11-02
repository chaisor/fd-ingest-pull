logLevel := Level.Warn

resolvers ++= Seq(
  Classpaths.sbtPluginReleases
)

// pulls in: sbt-pgp, sbt-release, sbt-mima-plugin, sbt-dependency-graph, sbt-buildinfo, sbt-sonatype
addSbtPlugin("org.typelevel" % "sbt-typelevel" % "0.3.1")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")
addSbtPlugin("org.brianmckenna" % "sbt-wartremover" % "0.14")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.6.0")
addSbtPlugin("io.gatling" % "gatling-sbt" % "2.1.7")
addSbtPlugin("io.spray" % "sbt-revolver" % "0.7.2")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.0.0-M4")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.3.3")
addSbtPlugin("com.typesafe.sbt" %% "sbt-osgi" % "0.7.0")
addSbtPlugin("com.orrsella" % "sbt-stats" % "1.0.5")
addSbtPlugin("com.typesafe.sbt" % "sbt-license-report" % "1.1.0")