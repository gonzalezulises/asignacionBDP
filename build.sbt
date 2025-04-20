// Configuración del proyecto para Big Data Processing
//
// Este archivo define:
// - Información básica del proyecto (nombre, versión, versión de Scala)
// - Dependencias necesarias para Apache Spark (core y SQL)
// - Dependencias para testing con ScalaTest
//
// La configuración está optimizada para asegurar compatibilidad entre
// los componentes y permitir tanto el desarrollo como las pruebas
// del proyecto en un entorno local.


name := "examen-bdp"
version := "1.0"
scalaVersion := "2.12.15"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.3.0",
  "org.apache.spark" %% "spark-sql" % "3.3.0",
  "org.scalatest" %% "scalatest" % "3.2.12" % "test"
)
