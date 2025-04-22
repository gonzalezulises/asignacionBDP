package com.bdp.examen

import org.apache.spark.sql.DataFrame
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

class examenTest extends FlatSpec with Matchers with BeforeAndAfterAll with TestInit {
  import spark.implicits._

  override def afterAll(): Unit = {
    stopSpark()
  }

  "Ejercicio 1" should "seleccionar los nombres de los estudiantes y ordenarlos por calificación de forma descendente" in {
    val estudiantes = Seq(
      ("Ana", 23, 9.0),
      ("Luis", 21, 7.5),
      ("Pedro", 22, 8.5),
      ("Maria", 20, 9.5)
    ).toDF("nombre", "edad", "calificacion")
    
    val resultado = examen.ejercicio1(estudiantes)
    
    val nombres = resultado.collect().map(_.getString(0))
    nombres shouldBe Array("Maria", "Ana", "Pedro", "Luis")
  }

  "Ejercicio 2" should "aplicar una UDF para determinar si un número es par o impar" in {
    val numeros = Seq(
      (1),
      (2),
      (3),
      (4),
      (5)
    ).toDF("numero")
    
    val resultado = examen.ejercicio2(numeros)
    
    val paridades = resultado.select("paridad").collect().map(_.getString(0))
    paridades shouldBe Array("Impar", "Par", "Impar", "Par", "Impar")
  }

  "Ejercicio 3" should "realizar un join entre estudiantes y calificaciones y calcular el promedio por estudiante" in {
    val estudiantes = Seq(
      (1, "Ana"),
      (2, "Luis"),
      (3, "Pedro")
    ).toDF("id", "nombre")
    
    val calificaciones = Seq(
      (1, "Matemáticas", 9.0),
      (1, "Física", 8.5),
      (2, "Matemáticas", 7.0),
      (2, "Física", 8.0),
      (3, "Matemáticas", 8.5),
      (3, "Física", 9.0)
    ).toDF("id_estudiante", "asignatura", "calificacion")
    
    val resultado = examen.ejercicio3(estudiantes, calificaciones)
    
    val promedios = resultado.select("id", "promedio_calificaciones").collect().map(row => 
      (row.getInt(0), row.getDouble(1))
    )
    
    promedios.length shouldBe 3
    promedios(0)._2 shouldBe 8.75 +- 0.01
    promedios(1)._2 shouldBe 7.5 +- 0.01
    promedios(2)._2 shouldBe 8.75 +- 0.01
  }

  "Ejercicio 4" should "contar la cantidad de ocurrencias de cada palabra" in {
    val palabras = List("hola", "mundo", "hola", "spark", "scala", "spark", "big", "data", "hola")
    
    val resultado = examen.ejercicio4(palabras)(spark)
    
    val resultadoArray = resultado.collect()
    
    resultadoArray(0) shouldBe ("hola", 3)
    resultadoArray(1) shouldBe ("spark", 2)
  }

 "Ejercicio 5" should "calcular el ingreso total por producto" in {
  val ventas = Seq(
    (1, 101, 5, 20.0),
    (2, 102, 3, 15.0),
    (3, 101, 2, 20.0),
    (4, 103, 7, 10.0),
    (5, 102, 4, 15.0)
  ).toDF("id_venta", "id_producto", "cantidad", "precio_unitario")
  
  val resultado = examen.ejercicio5(ventas).orderBy("id_producto")

  resultado.count() shouldBe 3

  val filas = resultado.collect()
  
  filas(0).getInt(0) shouldBe 101
  filas(0).getDouble(1) shouldBe 140.0 +- 0.01
  
  filas(1).getInt(0) shouldBe 102
  filas(1).getDouble(1) shouldBe 105.0 +- 0.01
  
  filas(2).getInt(0) shouldBe 103
  filas(2).getDouble(1) shouldBe 70.0 +- 0.01
 }
}
