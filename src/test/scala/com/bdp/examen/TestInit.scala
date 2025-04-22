package com.bdp.examen

import org.apache.spark.sql.SparkSession

trait TestInit {
  lazy val spark: SparkSession = SparkSession.builder()
    .appName("BDP Examen Test")
    .master("local[*]")
    .config("spark.driver.bindAddress", "127.0.0.1")
    .getOrCreate()

  /**
   * Detener la sesión de Spark después de todas las pruebas
   */
  def stopSpark(): Unit = {
    if (spark != null) {
      spark.stop()
    }
  }
}
