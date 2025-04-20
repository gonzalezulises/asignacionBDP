/**
 * Inicialización del entorno de pruebas para Spark
 * 
 * Este archivo configura una sesión de Spark adecuada para ejecutar pruebas unitarias.
 * La configuración incluye:
 * 
 * - Creación de SparkSession en modo local para pruebas rápidas
 * - Configuración de parámetros específicos para el entorno de pruebas
 * - Exposición de la sesión para ser utilizada por los tests
 * 
 * Esta configuración permite que las pruebas se ejecuten de forma aislada
 * y eficiente sin necesidad de un clúster Spark completo.
 */

package com.bdp.examen

import org.apache.spark.sql.SparkSession

object TestInit {

  val spark: SparkSession = SparkSession.builder()
    .appName("BDP Examen Test")
    .master("local[*]")
    .config("spark.driver.bindAddress", "127.0.0.1")
    .getOrCreate()
}
