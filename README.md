## Microservicio de Questions

### Introducción

El microservicio que se plantea busca resolver la funcionalidad de permitir a los usuarios del ecommerce, realizar consultas sobre un artículo en particular. La lectura de esta pregunta estaría disponible para cualquier usuario que quisiera obtener más información de un artículo en cuestión. Pero sólo el usuario que posteó el artículo estará habilitado para responder esa pregunta. De esta forma, un artículo tendrá diferentes preguntas de diferentes usuarios con la correspondiente respuesta del usuario que posteó el artículo.

### Casos de uso

## CU: Crear Question

- Se valida que exista el artículo y se crea la question
- cualquier usuario que este logeado puede hacer una question sobre un artículo

## CU: Responder Question

- solo los usuarios 'admin' pueden responder ya que ellos son los habilitados a subir artículos

## CU: Notificar consulta

- se emite un paquete con información de las preguntas a través de un canal rabbitmq

## CU: Consultar Questions

- se accede a las preguntas de un artículo a través del article id
- se muestran las preguntas disponibles con su respuesta

### Modelo de datos

**Question**
id: integer
articleId: integer
customerName: String
questionInfo: String
creationDate: Date

**Response**
id: integer
questionId: integer
ownerName: String
responseInfo: String
creationDate: Date

**Notification**
id: integer
articleId: integer
questionInfo: String
responseInfo: String
creationDate: Date

### Interfaz REST

**Creación de question de un artículo**
`POST /v1/question/{articleId}`

_Body_

```json
{
	"userName": {"nombre del usuario que genera la pregunta"},
	"description": {"la pregunta"}
}
```

_Response_
`200 OK` si la question fue creada con éxito | `400 BAD REQUEST`

**Consulta de question de un artículo**
_Desde usuario cliente_
`GET /v1/question/{articleId}/userData`

_Response_
`200 OK` si existe por lo menos una question en ese artículo | `404 N0T FOUND`

```json
[
      {
            "customerName": {"customerName"},
            "description": {"pregunta"},
            "creationDate": {"creationDate"},
            "response": {"responseInfo"},
            "responseDate": {"responseDate"}
      }
]
```

_Desde usuario vendedor_
`GET /v1/question/{articleId}/ownerData`

_Response_"
`200 OK` si existe por lo menos una question en ese artículo | `404 NOT FOUND`

```json
[
      {
            "id": {"id"},
            "articleId": {"articleId"},
            "questionId": {"questionId"},
            "customerName": {"customerName"},
            "description": {"pregunta"},
            "creationDate": {"creationDate"},
            "response": {"responseInfo"},
            "responseDate":{"responseDate"}
      }
]
```

**Creación de response de la question de un artículo**
`POST /v1/question/{questionId}/response`

_Body_

```json
{
	"ownerName": {"nombre del usuario admin que posteo el articulo"},
	"description": {"respuesta"}
}
```

_Response_
`200 OK` si la question fue creada con éxito | `400 BAD REQUEST`

### Interfaz asincronica (rabbit)

**Validación de artículo**
Le mandamos articleId y nos devuelve si existe o no el article.

- GET - Escucha de mensajes article-exist desde `questions`

```json
{
      "type": "article-exist",
      "message": {
            "referenceId": "{cartId}",
            "articleId": "{articleId}",
            "valid": true|false
      }
}
```

- POST - Se envía mensaje a catalog para comprobar que existe un artículo

```json
{
  "type": "article-exist",
  "queue": "questions",
  "exchange": "questions",
  "message": {
    "referenceId": "{cartId}",
    "articleId": "{articleId}"
  }
}
```

**Notificación de questions**

- POST - Envía notificación de creación de question

```json
{
  "type": "notification",
  "queue": "questions",
  "exchange": "questions",
  "message": {
    "articleId": "{articleId}",
    "questionId": "{questionId}",
    "questionInfo": "{questionInfo}",
    "customerName": "{customerName}",
    "questionDate": "{questionDate}"
  }
}
```

- POST - Envía notificación de response

```json
{
  "type": "notification",
  "queue": "questions",
  "exchange": "questions",
  "message": {
    "questionId": "{questionId}",
    "responseInfo": "{responseInfo}",
    "ownerName": "{ownerName}",
    "responseDate": "{responseDate}"
  }
}
```
