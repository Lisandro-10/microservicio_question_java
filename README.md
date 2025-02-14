## Microservicio de Questions

### Introducción

El microservicio que se plantea busca resolver la funcionalidad de permitir a los usuarios del ecommerce, realizar consultas sobre un artículo en particular. La lectura de esta pregunta estaría disponible para cualquier usuario que quisiera obtener más información de un artículo en cuestión. Pero sólo el usuario que posteó el artículo estará habilitado para responder esa pregunta. De esta forma, un artículo tendrá diferentes preguntas de diferentes usuarios con la correspondiente respuesta del usuario que posteó el artículo.

### Casos de uso

## CU: Crear Question

- Se valida que exista el artículo y se crea la question
- cualquier usuario que este logeado puede hacer una question sobre un artículo

## CU: Responder Question

- solo los usuarios 'admin' pueden responder ya que ellos son los habilitados a subir artículos


## CU: Consultar Questions

- se accede a las preguntas de un artículo a través del article id
- se muestran las preguntas disponibles con su respuesta

### Modelo de datos

**Question**
id: Long
customerName: String
questionDescription: String
creationDate: Date
articleId: String
status: QuestionStatus

**Answer**
id: Long
question: Question
ownerName: String
answerDescription: String
creationDate: Date


### Interfaz REST

**Creación de question de un artículo**
`POST /v1/{articleId}/questions`

_Body_

```json
{
	"customerName": {"nombre del usuario que genera la pregunta"},
	"questionDescription": {"la pregunta"}
}
```

_Response_
`200 OK` si la question fue creada con éxito | `400 BAD REQUEST`

**Consulta de question de un artículo**
_Desde usuario cliente_
`GET /v1/{articleId}/questions`

_Response_
`200 OK` si existe por lo menos una question en ese artículo | `404 N0T FOUND`

```json
[
      {
            "customerName": {"customerName"},
            "questionDescription": {"pregunta"},
            "answers": ["lista de respuestas"]
      }
]
```


**Creación de response de la question de un artículo**
`POST /v1/answer/{questionId}`

_Body_

```json
{
	"ownerName": {"nombre del usuario admin que posteo el articulo"},
	"answerDescription": {"respuesta"}
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
      "type": "question_article_exist",
      "message": {
            "referenceId": "{questionId}",
            "articleId": "{articleId}",
            "valid": true|false
      }
}
```

- POST - Se envía mensaje a catalog para comprobar que existe un artículo

```json
{
  "type": "article-exist",
  "queue": "catalog_article_exist",
  "exchange": "article_exist",
  "message": {
    "referenceId": "{questionId}",
    "articleId": "{articleId}"
  }
}
```
