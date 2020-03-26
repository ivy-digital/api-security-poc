# Prova de conceito para autenticação de API para requisições de clientes externo

## Exigências

Para executar o aplicativo, você precisa:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Executando o aplicativo localmente

```shell
mvn spring-boot:run
```

## Chamando a API

```shell
curl --location --request GET 'http://localhost:8080/api/beneficiario/' \
--header 'client_id: 06ddb46f-e04d-479a-941c-ce6c2d488afb' \
--header 'user_id: 222.100.888-44' \
--header 'Content-Type: application/json'
```

