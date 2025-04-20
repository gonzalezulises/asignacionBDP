/**
 * Suite de pruebas para las funciones de análisis de Big Data
 * 
 * Este archivo contiene pruebas unitarias que verifican el correcto funcionamiento
 * de cada una de las funciones definidas en el objeto ExamenFunctions. Las pruebas:
 * 
 * - Crean conjuntos de datos de ejemplo basados en datos educativos y de ventas
 * - Aplican las funciones a estos datos
 * - Comparan los resultados obtenidos con los resultados esperados calculados manualmente
 * - Verifican tanto la funcionalidad con datos sintéticos como con datos reales (CSV)
 * 
 * Cada test está diseñado para validar un aspecto específico del procesamiento
 * de datos con Spark, asegurando la correcta implementación de los ejercicios.
 */

package com.bdp.examen

import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.BeforeAndAfterAll

class ExamenTest extends AnyFunSuite with BeforeAndAfterAll {
  
  val spark: SparkSession = TestInit.spark
  import spark.implicits._
  
  val worldBankData = Seq(
    ("Chad", "TCD", "Enrolment in lower secondary education, all programmes, both sexes (number)", "UIS.E.2", 321921.0, 2012L),
    ("Chad", "TCD", "Enrolment in upper secondary education, all programmes, both sexes (number)", "UIS.E.3", 68809.0, 2006L),
    ("Chad", "TCD", "Enrolment in upper secondary education, all programmes, both sexes (number)", "UIS.E.3", 30551.0, 1999L),
    ("Chad", "TCD", "Enrolment in upper secondary education, all programmes, both sexes (number)", "UIS.E.3", 79784.0, 2007L),
    ("Chad", "TCD", "Repeaters in primary education, all grades, both sexes (number)", "UIS.R.1", 282699.0, 2006L),
    ("Chad", "TCD", "School enrollment, primary (% gross)", "SE.PRM.ENRR", 82.61, 2010L)
  )
  
  val worldBankSchema = StructType(Array(
    StructField("country_name", StringType, true),
    StructField("country_code", StringType, true),
    StructField("indicator_name", StringType, true),
    StructField("indicator_code", StringType, true),
    StructField("value", DoubleType, true),
    StructField("year", LongType, true)
  ))
  
  val worldBankDF: DataFrame = spark.createDataFrame(
    spark.sparkContext.parallelize(worldBankData.map(Row.fromTuple)),
    worldBankSchema
  )
  
  val ventasData = Seq(
    (1, 101, 5, 20.0),
    (2, 102, 3, 15.0),
    (3, 101, 2, 20.0),
    (4, 103, 7, 10.0),
    (5, 102, 4, 15.0)
  )
  
  val ventasSchema = StructType(Array(
    StructField("id_venta", IntegerType, true),
    StructField("id_producto", IntegerType, true),
    StructField("cantidad", IntegerType, true),
    StructField("precio_unitario", DoubleType, true)
  ))
  
  val ventasDF: DataFrame = spark.createDataFrame(
    spark.sparkContext.parallelize(ventasData.map(Row.fromTuple)),
    ventasSchema
  )
  
  val ventasCSVDF: DataFrame = spark.read
    .option("header", "true")
    .option("inferSchema", "true")
    .csv("src/test/resources/ventas.csv")
  

  test("Test mostrarEsquema") {
    ExamenFunctions.mostrarEsquema(worldBankDF)
    assert(true)
  }
  

  test("Test filtrarPorValor") {
    val resultDF = ExamenFunctions.filtrarPorValor(worldBankDF, 80.0)
    val expectedCount = worldBankData.count(_._5 > 80.0)
    
    assert(resultDF.count() == expectedCount)
    assert(resultDF.filter(col("value") <= 80.0).count() == 0)
  }
  

  test("Test ordenarPorValor") {
    val resultDF = ExamenFunctions.ordenarPorValor(worldBankDF)
    val maxValue = worldBankDF.select(max("value")).first().getDouble(0)
    val topRow = resultDF.first()
    
    assert(topRow.getDouble(2) == maxValue)
  }
  

  test("Test crearEvaluadorValor") {
    val evaluador = ExamenFunctions.crearEvaluadorValor(50000.0)
    val resultDF = evaluador(worldBankDF)
    
    val altoCount = resultDF.filter(col("nivel_valor") === "Alto").count()
    val bajoCount = resultDF.filter(col("nivel_valor") === "Bajo").count()
    val expectedAltoCount = worldBankData.count(_._5 > 50000.0)
    val expectedBajoCount = worldBankData.count(_._5 <= 50000.0)
    
    assert(altoCount == expectedAltoCount)
    assert(bajoCount == expectedBajoCount)
  }
  

  test("Test promedioIndicadoresPorPais") {
    val resultDF = ExamenFunctions.promedioIndicadoresPorPais(worldBankDF)

    assert(resultDF.count() == 1)
    
    val row = resultDF.first()
    val expectedPromedio = worldBankData.map(_._5).sum / worldBankData.length
    

    assert(Math.abs(row.getDouble(2) - expectedPromedio) < 0.001)
  }
  

  test("Test contarOcurrenciasIndicadores") {
    val resultDF = ExamenFunctions.contarOcurrenciasIndicadores(worldBankDF)
    

    val uisE3Count = resultDF.filter(col("indicator_name") === "Enrolment in upper secondary education, all programmes, both sexes (number)").first().getInt(1)
    assert(uisE3Count == 3) // Debería haber 3 ocurrencias
  }
  

  test("Test calcularIngresoVentas") {
    val resultDF = ExamenFunctions.calcularIngresoVentas(ventasDF)
    
  
    val firstRowIngreso = resultDF.first().getDouble(4) // La columna ingreso_total está en la posición 4
    val expectedIngreso = ventasData(0)._3 * ventasData(0)._4 // cantidad * precio_unitario
    
    assert(firstRowIngreso == expectedIngreso)
  }
  

  test("Test agregarIngresosPorProducto") {
    val resultDF = ExamenFunctions.agregarIngresosPorProducto(ventasDF)
   
    val distinctProductCount = ventasData.map(_._2).distinct.length
    assert(resultDF.count() == distinctProductCount)
    
 
    val producto101Row = resultDF.filter(col("id_producto") === 101).first()
    val expectedIngreso101 = ventasData.filter(_._2 == 101).map(t => t._3 * t._4).sum
    
    assert(producto101Row.getDouble(1) == expectedIngreso101)
  }
  

  test("Test agregarIngresosPorProducto con CSV") {
    if (ventasCSVDF != null) {
      val resultDF = ExamenFunctions.agregarIngresosPorProducto(ventasCSVDF)
      
 
      assert(resultDF.count() > 0)
      
      val ingresos = resultDF.select("ingreso_total").collect().map(_.getDouble(0))
      assert(ingresos.zip(ingresos.tail).forall { case (a, b) => a >= b })
    }
  }
  
  override protected def afterAll(): Unit = {
    spark.stop()
  }
}
