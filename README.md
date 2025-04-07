# asignacionBDP

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






