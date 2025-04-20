# Asignación Big Data Processing Ulises González

actualización 20 de abril

Resumen de los Cambios Realizados
He transformado un notebook Jupyter (.ipynb) en una estructura de proyecto Scala/SBT estandarizada para cumplir con los requisitos académicos del curso de Big Data Processing. El proceso implicó:

Creación de la estructura de directorios estándar SBT:

<img width="757" alt="image" src="https://github.com/user-attachments/assets/3d9433b9-8182-44fa-8582-e3b306d5a598" />

Extracción y refactorización del código:

Convertí las celdas de código del notebook en funciones modulares dentro de un objeto Scala
Estructuré las funciones por tipo de ejercicio (DataFrames, UDFs, joins, RDDs, análisis de ventas)
Añadí documentación detallada para explicar la funcionalidad de cada componente


Creación de tests unitarios:

Implementé pruebas para cada una de las funciones
Definí datos de prueba representativos de los conjuntos originales
Incluí validaciones que comparan los resultados con valores esperados calculados manualmente


Configuración del entorno:

Creé un archivo build.sbt con las dependencias necesarias
Configuré una sesión de Spark específica para pruebas en TestInit.scala
Incluí un conjunto de datos CSV para las pruebas relacionadas con ventas



Funcionalidades Implementadas
El proyecto está organizado alrededor de cinco ejercicios principales:

Operaciones básicas con DataFrames: Visualización de esquemas, filtrado y ordenamiento
Funciones definidas por usuario (UDFs): Clasificación de valores según umbrales
Joins y agregaciones: Combinación de datos y cálculo de promedios por país
Procesamiento con RDDs: Conteo de ocurrencias de indicadores educativos
Análisis de ventas: Cálculo de ingresos y estadísticas de productos










## Introducción y Contexto Personal

Durante el curso anterior de Big Data Architecture, me fascinó particularmente el estudio de la integración entre Hadoop y Elasticsearch como parte de una solución completa para el procesamiento y análisis de grandes volúmenes de datos. Me pareció especialmente interesante la práctica donde conectábamos estos sistemas a través de configuraciones específicas, utilizando Hive para consultar datos en Hadoop y visualizándolos con Kibana.

Inspirado por estos conocimientos, quise adaptar el ejercicio actual para profundizar más en el ecosistema de Google Cloud y poner en práctica lo aprendido en la materia anterior. En mi adaptación, decidí utilizar un volumen de datos significativamente mayor al requerido en los ejemplos básicos, específicamente datos educativos del Banco Mundial alojados en Google Cloud Storage, para hacer la experiencia más cercana a un escenario real de Big Data.

## Mi Enfoque para la Práctica Actual

En esta práctica con Spark y Scala, aproveché los conceptos fundamentales sobre arquitecturas de Big Data que adquirí previamente. Aunque Scala y Spark son requisitos de la materia actual, la elección de trabajar con conjuntos de datos voluminosos y reales fue mi decisión para enriquecer la experiencia de aprendizaje.

A través de los 5 ejercicios propuestos, he explorado tanto las APIs de alto nivel (DataFrame) como las de bajo nivel (RDD) que ofrece Spark, implementando:

1. Operaciones básicas de manipulación de DataFrames
2. Funciones personalizadas (UDFs) para extender la funcionalidad de Spark
3. Joins y agregaciones para combinar y analizar diferentes fuentes de datos
4. Procesamiento con RDDs para operaciones más específicas
5. Integración con fuentes externas mediante la carga y transformación de archivos CSV

Esta implementación complementa perfectamente mis aprendizajes previos, ya que representa la capa de procesamiento que alimentaría los sistemas de almacenamiento y visualización estudiados en Big Data Architecture. Los resultados de este procesamiento podrían indexarse directamente en Elasticsearch utilizando las configuraciones que aprendí anteriormente.

Mi objetivo con esta adaptación ha sido crear un flujo completo de datos que integre las tecnologías estudiadas en ambos cursos, demostrando cómo el procesamiento con Spark puede manejar volúmenes de datos realistas en un entorno cloud empresarial como Google Cloud Platform.

El notebook completo lo puedes encontrar aca: [ver el notebook directamente en GitHub](https://github.com/gonzalezulises/asignacionBDP/blob/main/Asignacion-Ulises-Gonzalez.ipynb).

El desarrollo paso a paso a continuación:

Lo primero ha sido iniciar por la creación de un proyecto en google cloud que me permita resolver los ejercicios
Para ello he creado el proyecto big-data-processing y he investigado que necesito validar que los siguientes APIS estén disponibles:
Dataproc API (no instalado, y lo he instalado y habilidado)
Cloud Storage API (ya disponible de la materia anterior)
Compute Engine API (ya disponible de la materia anterior)
Luego voy a preparar un contenedor para el proyecto que me permita gestionar los datos:

<img width="2018" alt="image" src="https://github.com/user-attachments/assets/fa2936d4-143b-4dcb-9955-36ca0f58c588" />

Luego me fui a la configuración de dataproc

<img width="2018" alt="image" src="https://github.com/user-attachments/assets/4350b12b-4fd0-4d90-9a8d-7f594ab74cfc" />

Creando un Clúster en Compute Engine

<img width="2018" alt="image" src="https://github.com/user-attachments/assets/906d88ae-67d1-4590-afed-670975118449" />


Luego para operar necesito crear un notebook en vertex AI

<img width="2018" alt="image" src="https://github.com/user-attachments/assets/ee0207fa-0ea0-4cc8-8ef3-a42c10bc011f" />

debo seleccionar una imagen que me permita usar spark entonces voy a habilitar artifac registry para buscar una imagen de docker con scala y spark

<img width="1624" alt="image" src="https://github.com/user-attachments/assets/6c46eab4-dae1-46e1-8b36-b862b973e478" />

Una vez configurada la instancia ya puedo utilizar JupyterLab

<img width="1772" alt="image" src="https://github.com/user-attachments/assets/df083350-220f-468a-b93d-7daefe8b3b8e" />

<img width="1772" alt="image" src="https://github.com/user-attachments/assets/eb4d7eb4-9f45-4df7-948e-c4bd5afc665d" />

Pruebo si spark esta funcionando:
<img width="1566" alt="image" src="https://github.com/user-attachments/assets/30ce7fdc-67c3-4ff4-85a5-2e56e368d579" />

Para obtener un set de datos, voy a utilizar la información pública del banco mundial y hacer una exportación a un bucket que me servirá para realizar los ejercicios solicitados:
<img width="1916" alt="image" src="https://github.com/user-attachments/assets/d6d8b81b-5a68-41e2-824e-e0fac60b6e0f" />

El último ejercicio me pide unos datos de ventas, como es un .csv lo voy a subir al bucket que tengo dispuesto para poder disponibilizar estos datos:
<img width="1916" alt="image" src="https://github.com/user-attachments/assets/7a5e645a-3a7a-4c55-a637-c5bb3853157e" />


Ya tengo disponible el notebook para trabajar los ejercicios de la asignación:

<img width="1916" alt="image" src="https://github.com/user-attachments/assets/93033d09-668b-4c15-9e6d-9ab4e91a4e53" />

A continuación capturas con los ejercicios:

Ejercicio 1:

<img width="1916" alt="image" src="https://github.com/user-attachments/assets/763224b5-3665-4282-b646-051dd25d07b5" />

<img width="1916" alt="image" src="https://github.com/user-attachments/assets/8fcf8bd8-a418-450d-bb6a-dd4b5841c229" />


Ejercicio 2:

<img width="1916" alt="image" src="https://github.com/user-attachments/assets/1af4f6aa-0fad-409a-bffc-7653992276da" />

Ejercicio 3: 

<img width="1916" alt="image" src="https://github.com/user-attachments/assets/b1156b1d-36ca-4902-8139-903a6ae7543b" />

Ejercicio 4:

<img width="1916" alt="image" src="https://github.com/user-attachments/assets/2f1bc61f-b88b-4968-bde9-b8294b479b78" />

Ejercicio 5: 

<img width="1916" alt="image" src="https://github.com/user-attachments/assets/262b4ba4-315a-460a-86c2-821a4c1e6f74" />
<img width="1916" alt="image" src="https://github.com/user-attachments/assets/0720782a-cbd0-44b4-b198-3f8f38b3c6d1" />





