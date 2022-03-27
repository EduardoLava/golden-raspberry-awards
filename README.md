# Golden Raspberry Awards

Projeto contém uma API RESTful para consulta dos indicados e vencedores da categoria de pior filme do Golden Raspberry Awards.
Todos os dados do sistema são importados por meio de um arquivo CSV e salvos em memória.

## Pré-requisitos para executar o projeto

Ter instalado em sua máquina:
- **Java 11**.
- **Apache Maven 3.8.5**.

## Tecnologias utilizadas no projeto

- **Spring Boot 2.6.4** (Data, JPA, Web, Devtools, Open-API UI, Test e Validation).
- **Banco de dados H2**.
- **Open CSV V5.3**.

## Requisitos do sistema

1. Ler o arquivo CSV dos filmes e inserir os dados em uma base de dados ao iniciar a aplicação.

## Requisitos da API

1. Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido.

## Requisitos não funcionais do sistema:

1. O web service RESTful deve ser implementado com base no nível 2 de maturidade de Richardson.
2. Devem ser implementados somente testes de integração. Eles devem garantir que os dados obtidos estão de acordo com os dados fornecidos na proposta.
3. O banco de dados deve estar em memória utilizando um SGBD embarcado (por exemplo, H2). Nenhuma instalação externa deve ser necessária.
4. A aplicação deve conter um readme com instruções para rodar o projeto e os testes de integração.

## Instruções para o arquivo CSV

Para que a leitura do arquivo CSV funcione, ele deve seguir estas definições:
- O nome do arquivo deve ser: **movielist.csv**.
- Ele deve ser salvo no diretório **src\main\resources\csv** do porjeto.
- O arquivo deve conter as seguintes colunas delimitados por **;** (Ponto e vírgula) seguindo esta ordem: year, title, studios, producers, winner.
- Os campos **studios** e **producers** tem suporte a inserir mais de uma informação, para separá-las utilize os delimitadores: **,** (Vírgula) ou **and** possibilitando desta forma que um filme tenha mais de um produtor e mais do que um estúdio.

## Executando os testes da aplicação

Para execução dos testes utilize o seguinte comando:
```
mvn test
```
Ao executar o comando os seguintes testes devem ser executados:
- Validações para verificar se os dados foram importados na inicialização da aplicação de acordo com os requisitos do sistema.
- Validações para execução da consulta de vencedores com maior e menor intervalo entre os prêmios de acordo com os requisitos da API.

## Executando a aplicação

Para construir e executar a aplicação execute o seguinte comando:
```
./mvnw spring-boot:run
```

## Documentação da API

Para visualizar a documentação da API, execute a aplicação e acesse em seu navegador a seguinte URL:

[swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Acesso ao banco de dados

Para ter acesso ao banco de dados, execute a apliação e acesse o seguinte o link [H2 Console](http://localhost:8080/h2-console)

Utilize as seguintes informações para o acesso:
- Driver: **org.h2.Driver**
- URL JDBC: **jdbc:h2:mem:movies**
- Usuário: **cine**
- Senha: **cine**
