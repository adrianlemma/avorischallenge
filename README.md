# avorischallenge
Challenge tecnico para MinData/Avoris

> **Desarrollado por [Marcelo Adrian Lemma](https://ar.linkedin.com/in/marcelo-lemma-123b04122).**

> Backend API desarrollada para el challenge técnico de **[MinData](https://www.mindata.es/)** para proyecto de **[AvorisTech](https://www.avoristravel.com/about)**

## Descripción

Esta aplicación consiste en tres endpoints generados para crear, buscar y listar estudiantes y sus materias aprobadas.

Se basa en el patronde diseño de **inmutabilidad** para el diseño de las clases de **Estudiante**, **Fecha** y **Materia**
y en el patron **BFF** (Backend for Frontend).

Además de incluir los **Controllers** con sus respectivas validaciones, se incluyen tests unitarios con **JUnit** y **Mockito** para asegurar el correcto funcionamiento.

## EndPoints

- **Agregar Estudiante**: 
> - Metodo: **POST**
> - URL: [https://{servidor}:{puerto}/avoris/estudiante/save]()

request 
-     {
        "Nombre": "Lechuga",
        "Cantidad":80,
        "fechaFinalizacion": {
            "dia": 25,
            "mes": 11,
            "anio": 2032
        },
        "materiasCursadas": [
            {
                "nombre": "Matematicas",
                "calificacion": 8.9
            },
            ...
        ]
      }

- **Buscar Estudiante**:
> - Metodo: **GET**
> - URL: [https://{servidor}:{puerto}/avoris/estudiante/{estudiante-id}]()
> - Parámetro: **estudiante-id** (Id del estudiante en la base de datos)

response
-     {
        "Nombre": "Lechuga",
        "Cantidad":80,
        "fechaFinalizacion": {
            "fecha": "25 de Noviembre de 2032"
        },
        "materiasCursadas": [
            {
                "nombre": "Matematicas",
                "calificacion": 8.9
            },
            ...
        ]
      }

- **Listar Estudiantes**:
> - Metodo: **GET**
> - URL: [https://{servidor}:{puerto}/avoris/estudiante/list]()

response
-     [
        {
            "Nombre": "Lechuga",
            "Cantidad":80,
            "fechaFinalizacion": {
                "fecha": "25 de Noviembre de 2032"
            },
            "materiasCursadas": [
                {
                    "nombre": "Matematicas",
                    "calificacion": 8.9
                },
                ...
            ]
        },
        ...
      ]

## Tecnologías

#### El proyecto fue desarrollado con:

- Java 19
- Spring Boot
- JPA
- JUnit
- Mockito

## Validaciones

Los datos utilizados para el request al crear nuevos Estudiantes, deben cumplir los siguientes requisitos:

- **Nombre**: Se validará que no sea nulo, no sea una cadena vacía o que no solo contenga espacios en blanco y/o tabulaciones.


- **Edad**: Se validará que no sea nulo, no sea un numero negativo y que no exceda el rango valido (de 6 a 99 años).


- **Fecha**:
  - **Dia**: Se validará que no sea nulo y que se encuentre en el rango válido (de 1 a 12).
  - **Mes**: Se validará que no sea nulo y que se encuentre en el rango válido segun el mes y sea año viciesto o no (de 1 a 28, de 1 a 29, de 1 a 30 o de 1 a 31 según corresponda).
  - **Anio**: Se validará que no sea nulo y que se encuentre en el rango válido (de 1500 a 3000).


- **Materia**:
  - **Nombre**: Se validará que no sea nulo, no sea una cadena vacía o que no solo contenga espacios en blanco y/o tabulaciones.
  - **Calificacion**: Se validará que no sea nulo y que se encuentre en el rango válido (de 0 a 10). Acepta decimales.