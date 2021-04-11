# BCF_BackProject__Rest-Full-API

*Backend proyecto fin de grado - DAM - Juan de Colonia*

## Descripción

## Tecnologías Utilizadas

```
Lenguaje de programación: Java - Jdk 11
Framework e inicializador de proyecto: SpringBoot
Gestor de Proyecto: Maven
Librerías utilizadas: MapStruct, Lombok, Plexus, Javax Validations, Jackson (embebido), Slf4j(facade), etc. *A la espera de añadir Swagger*
Base de datos: Actualmente utilizando H2 database, ideal para pruebas o demos como este proyecto.
```

## Descargar y compilar

- Clonar el repositorio `git clone https://github.com/Javi3Code/BCF_BackProject__Rest-Full-API.git`.
- Una vez clonado, compilamos para generar el código faltante de los DTO's `mvn clean compile`. Añadir la carpeta `target/generate-source/annotations/org...` como recursos del proyecto, en el caso de utilizar Eclipse -> Click dcho sobre sobre la carpeta -> buildpath -> use as source folder (Todo esto si surgiera el problema con los Mappers generados por mapstruct al compilar)
