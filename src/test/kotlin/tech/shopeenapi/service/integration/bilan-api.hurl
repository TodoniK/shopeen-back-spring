GET http://localhost:8080/api/bilan
HTTP/1.1 200

[Asserts]
jsonpath "$[0].bilanEuro" equals "139.23"
jsonpath "$[0].bilanKwh" equals "280.84000000000003"
jsonpath "$[0].bilanKgeqCO2" equals "82.11"
