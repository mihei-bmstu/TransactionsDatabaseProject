ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "2.13.10"

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

val sparkVersion = "3.3.1"
val slickVersion = "3.4.1"
val kafkaVersion = "3.3.1"

val sparkCore = "org.apache.spark" %% "spark-core" % sparkVersion
val sparkSql = "org.apache.spark" %% "spark-sql" % sparkVersion
val slick = "com.typesafe.slick" %% "slick" % slickVersion
val slickHikari = "com.typesafe.slick" %% "slick-hikaricp" % slickVersion
val postgres = "org.postgresql" % "postgresql" % "42.3.6"
val kafka = "org.apache.kafka" % "kafka-clients" % kafkaVersion
val sparkSQLKafka = "org.apache.spark" %% "spark-sql-kafka-0-10" % sparkVersion
val flyway = "org.flywaydb" % "flyway-core" % "9.7.0"

lazy val root = (project in file("."))
  .settings(
    name := "TransactionsDatabaseProject",
    libraryDependencies := Seq(postgres, slick, slickHikari)
  )

lazy val producer = (project in file("./moduleProducer"))
  .settings(
    name := "KafkaProducer",
    libraryDependencies ++= Seq(kafka)
  ).dependsOn(root)

lazy val consumer = (project in file("./moduleConsumer"))
  .settings(
    name := "KafkaConsumer",
    libraryDependencies ++= Seq(sparkSQLKafka, kafka)
  ).dependsOn(root)

lazy val migration = (project in file("./moduleMigration"))
  .settings(
    name := "DBMigration",
    libraryDependencies ++= Seq(flyway)
  ).dependsOn(root)
