name := "fd-ingest-pull"

version := "1.0"

scalaVersion := "2.11.8"
crossScalaVersions := Seq("2.11.8")

description := "Fast Data - Pull Data Ingestion"
homepage := Some(url("https://bitbucket.org/ceth/fd-ingest-pull"))

licenses := Seq("GNU General Public License (GPL), Version 3.0"
  -> url("http://opensource.org/licenses/GPL-3.0"))

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.typesafeRepo("releases")
)

libraryDependencies ++= {
  val akkaV       = "2.4.7"
  val sparkV      = "1.6.1"
  val monocleV    = "1.2.2"
  val kafkaV      = "0.10.0.0"
  val phantomV    = "1.5.0"
  val gatlingV    = "2.2.2"
  val slickV      = "3.1.1"
  val sccV        = "1.6.0"
  val codahaleVersion = "3.0.2"
  Seq(
    "com.typesafe.akka"           %% "akka-actor"                        % akkaV,
    "com.typesafe.akka"           %% "akka-cluster"                      % akkaV,
    "com.typesafe.akka"           %% "akka-cluster-sharding"             % akkaV,
    "com.typesafe.akka"           %% "akka-cluster-metrics"              % akkaV,
    "com.typesafe.akka"           %% "akka-remote"                       % akkaV,
    "com.typesafe.akka"           %% "akka-slf4j"                        % akkaV,
    "com.typesafe.akka"           %% "akka-contrib"			 % akkaV,
    "com.typesafe.akka"           %% "akka-stream"                       % akkaV,
    "com.typesafe.akka"           %% "akka-http-core"                    % akkaV,
    "com.typesafe.akka"           %% "akka-http-experimental"            % akkaV,
    "com.typesafe.akka"           %% "akka-http-spray-json-experimental" % akkaV,
    "com.typesafe.akka"           %% "akka-http-testkit"                 % akkaV,
    "com.typesafe"                %  "config"                            % "1.3.0",

    "org.apache.spark"            %% "spark-streaming"                   % sparkV % "provided",
    "org.apache.spark"            %% "spark-mllib"                       % sparkV % "provided",
    "org.apache.spark"            %% "spark-catalyst"                    % sparkV % "provided",
    "org.apache.spark"            %% "spark-sql"                         % sparkV % "provided",
    "org.apache.spark"            %% "spark-graphx"                      % sparkV % "provided",
    "org.apache.spark"            %% "spark-bagel"                       % sparkV % "provided",
    "org.apache.spark"            %% "spark-streaming-kafka"             % sparkV % "provided",

    "org.apache.kafka"            %% "kafka"                             % kafkaV excludeAll(ExclusionRule("org.slf4j"), ExclusionRule("log4j")),

    "com.typesafe.slick"          %% "slick"                             % slickV,
    "com.typesafe.slick"          %% "slick-codegen"                     % slickV,
    "com.github.tminglei"         %% "slick-pg"                          % "0.14.1" excludeAll(ExclusionRule("com.typesafe.slick")),
    "org.reactivestreams"         %  "reactive-streams"                  % "1.0.0",
    "org.postgresql"              %  "postgresql"                        % "9.4.1208",

    "com.datastax.spark"          %% "spark-cassandra-connector"          % sccV,
    "com.datastax.spark" 	  %% "spark-cassandra-connector-embedded" % sccV,
    "com.datastax.cassandra" 	  %  "cassandra-driver-core" 	          % "3.0.2" excludeAll(ExclusionRule("org.slf4j"), ExclusionRule("log4j")),

    "org.reactivemongo"           %% "reactivemongo"                     % "0.11.13",
    "io.scalac"                   %% "reactive-rabbit"                   % "1.1.1" excludeAll(ExclusionRule("org.reactivestreams")),

    "com.github.julien-truffaut"  %% "monocle-core"                      % monocleV,
    "com.github.julien-truffaut"  %% "monocle-macro"                     % monocleV,

    "com.codahale.metrics"        %  "metrics-core"                      % codahaleVersion,
    "com.codahale.metrics" 	      %  "metrics-graphite"                  % codahaleVersion,

    "org.slf4j" 		              %  "slf4j-api" 	                 % "1.7.21",
    "ch.qos.logback"              %  "logback-classic"                   % "1.1.7"  % "compile,runtime,test",
    "org.log4s"                   %% "log4s"                             % "1.3.0",
    "org.scalatest"               %% "scalatest"                         % "2.2.6"  % "test",
    "org.scalacheck"              %% "scalacheck"                        % "1.13.1" % "test",
    "com.typesafe.akka"           %% "akka-testkit"                      % akkaV    % "test",
    "io.gatling.highcharts"       %  "gatling-charts-highcharts"         % gatlingV % "test",
    "io.gatling"                  %  "gatling-test-framework"            % gatlingV % "test"
  )
}

scalacOptions ++= Seq(
  "-deprecation",
  "-target:jvm-1.8",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Ywarn-unused-import"
)

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

wartremover.wartremoverSettings

wartremover.wartremoverErrors in (Compile, compile) ++= Seq(
  wartremover.Wart.Any,
  wartremover.Wart.Any2StringAdd,
  wartremover.Wart.EitherProjectionPartial,
  wartremover.Wart.OptionPartial,
  wartremover.Wart.Product,
  wartremover.Wart.Serializable,
  wartremover.Wart.ListOps
)

enablePlugins(GatlingPlugin)

incOptions := incOptions.value.withNameHashing(true)



import com.typesafe.sbt.license.{LicenseInfo, DepModuleInfo}

// Attach notes to modules
licenseReportNotes := {
  case DepModuleInfo(group, id, version) if group == "example" => "Made up artifact"
}

// Override the license information from ivy, if it's non-existent or
// wrong
licenseOverrides := {
  case DepModuleInfo("org.apache.spark", _, _) =>
    LicenseInfo(LicenseCategory.Apache, "Apache 2.0", "http://opensource.org/licenses/apache-2.0")
}