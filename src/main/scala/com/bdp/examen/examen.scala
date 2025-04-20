package com.bdp.examen

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.rdd.RDD

object ExamenFunctions {
  
  def mostrarEsquema(df: DataFrame): Unit = {
    println("Esquema del DataFrame:")
    df.printSchema()
  }
  
  def filtrarPorValor(df: DataFrame, umbral: Double): DataFrame = {
    df.filter(col("value") > umbral)
  }
  
  def ordenarPorValor(df: DataFrame): DataFrame = {
    df.select("country_name", "indicator_name", "value", "year")
      .orderBy(desc("value"))
  }
  
  def crearEvaluadorValor(umbral: Double): DataFrame => DataFrame = {
    df => {
      val evaluateValueUDF = udf((value: Double) => {
        if (value > umbral) "Alto" else "Bajo"
      })
      df.withColumn("nivel_valor", evaluateValueUDF(col("value")))
    }
  }
  
  def promedioIndicadoresPorPais(df: DataFrame): DataFrame = {
    val countriesDF = df
      .select("country_name", "country_code")
      .distinct()
      
    val indicatorsDF = df
      .select("country_code", "indicator_name", "value", "year")
      .filter(col("value").isNotNull)
      
    val joinedDF = countriesDF.join(indicatorsDF, Seq("country_code"), "inner")
    
    joinedDF
      .groupBy("country_name", "country_code")
      .agg(
        avg("value").alias("promedio_indicadores"),
        count("indicator_name").alias("total_indicadores")
      )
      .orderBy(desc("promedio_indicadores"))
  }
  
  def contarOcurrenciasIndicadores(df: DataFrame): DataFrame = {
    val indicatorsRDD = df
      .select("indicator_name")
      .rdd
      .map(row => row.getString(0))
      
    val indicatorCountsRDD = indicatorsRDD
      .map(indicator => (indicator, 1))
      .reduceByKey(_ + _)
      .sortBy(_._2, ascending = false)
      
    indicatorCountsRDD.toDF("indicator_name", "count")
  }
  
  def calcularIngresoVentas(ventasDF: DataFrame): DataFrame = {
    ventasDF.withColumn("ingreso_total", col("cantidad") * col("precio_unitario"))
  }
  
  def agregarIngresosPorProducto(ventasDF: DataFrame): DataFrame = {
    val ventasConIngresoDF = calcularIngresoVentas(ventasDF)
    
    ventasConIngresoDF
      .groupBy("id_producto")
      .agg(
        sum("ingreso_total").alias("ingreso_total"),
        sum("cantidad").alias("cantidad_total"),
        avg("precio_unitario").alias("precio_promedio"),
        count("id_venta").alias("num_ventas")
      )
      .orderBy(desc("ingreso_total"))
  }
}
