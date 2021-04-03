## Live coding Bootcamp Inter

### Stack utilizada
* Java8
* spring webflux
* Spring data
* dynamodb
* junit
* sl4j
* reactor

Documentação gerada: swagger: http://localhost:8080/swagger-ui-heroes-reactive-api.html

## Passos para rodar o projeto

* Configurar credenciais no Amazon CLI

  * Instalar se não possuir ainda: https://docs.aws.amazon.com/pt_br/cli/latest/userguide/cli-chap-install.html

* Iniciar DynamoDB

  * Instalar se nao possuir: https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html

* Iniciar o projeto com o Maven

  * Comando: mvn spring-boot:run

* Enviar requisições para o endpoint da API: http://localhost:8080/heroes

  * Conferir Documentação do swagger citada anteriormente

  