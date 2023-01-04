GET http://localhost:8080/api/responses
HTTP/1.1 200

[Asserts]
jsonpath "$[2].idQuestion" equals "nbPC"
jsonpath "$[2].userResponse" equals "96"
