package com.bdp.examen

import org.apache.spark.sql.SparkSession

object TestInit {

  val spark: SparkSession = SparkSession.builder()
    .appName("BDP Examen Test")
    .master("local[*]")
    .config("spark.driver.bindAddress", "127.0.0.1")
    .getOrCreate()
}
