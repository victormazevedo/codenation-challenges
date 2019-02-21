## Detalhes técnicos

### Configuração do ambiente de Desenvolvimento

Primeiro faça o clone do projeto.

**Carga de dados**  
Para realizar a primeira carga de dados no MongoDB com os dados fornecidos nos CSVs, 
execute o seguinte comando na pasta raíz do projeto:
```aidl
docker-compose up
```
Este comando fará com que o MongoDB e a Aplicação MapFood sejam rodaos. Em seguida acesse:

[Carga](http://localhost:8080/mapfood/carga)

**Testar a aplicação**  
Em desenvolvimento, rode seguinte comando para testar a a aplicação:
```aidl
mvn clean install
```

### Swagger

Para acessar o Swagger certifique-se que a aplicação está rodando e em seguida acesse:

[Swagger](http://localhost:8080/mapfood/swagger-ui.html)