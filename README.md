# Asignación Big Data Processing Ulises González

# Implementación de Procesamiento de Big Data con Apache Spark

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

Ya tengo disponible el notebook para trabajar los ejercicios de la asignación:

<img width="1916" alt="image" src="https://github.com/user-attachments/assets/93033d09-668b-4c15-9e6d-9ab4e91a4e53" />





