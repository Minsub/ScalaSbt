name := "ScalaSbt"

version := "1.0"

scalaVersion := "2.11.12"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.9"
libraryDependencies += "org.apache.commons" % "commons-email" % "1.4"
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.0.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.0.1"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.0.1"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.8.4"
libraryDependencies += "net.sf.opencsv" % "opencsv" % "2.3"
libraryDependencies += "org.postgresql" % "postgresql" % "9.4.1212"
libraryDependencies += "net.sf.jt400" % "jt400" % "8.7"
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.40"
libraryDependencies += "org.scalikejdbc" %% "scalikejdbc" % "2.5.0"
libraryDependencies += "org.elasticsearch.client" % "transport" % "5.1.1"
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.7"
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.7"
libraryDependencies += "io.netty" % "netty-all" % "4.1.5.Final"
