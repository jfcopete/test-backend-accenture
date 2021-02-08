# test-backend-accenture
Esta prueba se presenta para la posición de Java Backend con SpringBoot para Accenture.

Orientada a  SOA con estilo de arquitetura Representational State Transfer (REST).

Presentada y realizada por Juan Fernando Copete Mutis

¿Como correr?

Requisitos: IDE con Spring Boot 2.4.2, Java 8 , y Con gestor de paqeutes Maven.

Importar el proyecto y correrlo como Java Application o a través de Spring Boot 



y después en ejecutar.

## Ayudas/Docs

- Se ha generado documentación del código con comentarios explicando el porqué de algunas lineas de código.
- Se ha generado un Collection.json de Postman en la carpeta docs para que se puedan guiar en la ejecución de la aplicación. Para válidar el punto de eliminar o actualizar, cuando afectan los tiempos, se debe modificar la fecha de JSON en el objeto del pedido como se ve acontinuación.

```json
"price": 926000.0,
        "idCliente": 1,
        "date": "2021-02-08T01:21:57.635+00:00",
        "state": "ACCEPTED"
```

esto se debe modificar dependiendo el día, o el més para probar los criterios de calidad, por ejemplo, 24 horas antes para cancelar el pedido.

```json
"price": 926000.0,
        "idCliente": 1,
        "date": "2021-02-07T01:21:57.635+00:00",
        "state": "ACCEPTED"
```

- Por último, en la carpeta Docs, se puede encontrar el Diagrama UML que utilicé para modelar y conectar las clases.
