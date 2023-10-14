# ToDo List API

![Java](https://img.shields.io/badge/Java-11-brightgreen)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.4-brightgreen)

Este é um projeto Java que implementa uma aplicação de lista de tarefas (ToDo List) usando o framework Spring Boot. A aplicação permite que os usuários criem, gerenciem e atualizem tarefas em um sistema seguro de autenticação básica.

## Estrutura do Projeto

O projeto é organizado em várias classes e pacotes que desempenham funções específicas. Aqui está um resumo das partes principais do projeto:

### Modelos

#### `UserModel`

O modelo `UserModel` representa os usuários na aplicação. Ele possui campos como nome de usuário, nome, senha e data de criação. As senhas são armazenadas de forma segura usando o algoritmo BCrypt.

#### `TaskModel`

O modelo `TaskModel` representa as tarefas a serem realizadas pelos usuários. Ele inclui informações como nome da tarefa, data de início, data de término e informações adicionais da tarefa.

### Controladores

#### `UserController`

O controlador `UserController` é responsável por lidar com operações relacionadas a usuários. Ele permite que os usuários se registrem na aplicação por meio de um endpoint POST e verifica se os nomes de usuário são únicos. Além disso, ele usa BCrypt para armazenar senhas de forma segura.

#### `TaskController`

O controlador `TaskController` lida com as operações relacionadas a tarefas. Ele oferece funcionalidades para criar tarefas, listar tarefas associadas a um usuário e atualizar tarefas existentes. O filtro `FilterTaskAuth` garante que apenas usuários autenticados possam acessar endpoints relacionados a tarefas.

### Filtros

#### `FilterTaskAuth`

O filtro `FilterTaskAuth` intercepta as solicitações HTTP e verifica as credenciais de autenticação básica no cabeçalho "Authorization". Ele verifica as credenciais com base no modelo `UserModel` e garante que apenas usuários autenticados possam acessar endpoints relacionados a tarefas.

### Controlador de Exceções

#### `ExceptionHandlerController`

O controlador de exceções `ExceptionHandlerController` lida com exceções específicas relacionadas a solicitações HTTP. No exemplo fornecido, ele lida com a exceção `HttpMessageNotReadableException`, retornando uma resposta HTTP 400 Bad Request com detalhes da causa da exceção.

## Como Iniciar

Para executar o projeto, siga estas etapas:

1. Certifique-se de ter o Java (versão 11 ou superior) e o Maven instalados em seu sistema.

2. Clone o repositório:

   ```shell
   git clone https://github.com/raonicerqueira/todolist.git
3. Navegue até o diretório do projeto:

   ```shell
   cd todolist
4. Execute o aplicativo usando o Maven:

   ```shell
   mvn spring-boot:run
O aplicativo será iniciado e estará acessível em http://localhost:8080.


## Implantação com Docker

Se você deseja implantar este projeto usando Docker, siga as etapas abaixo:

1. Certifique-se de ter o Docker instalado em seu sistema. Se não o tiver, você pode baixá-lo em [Docker's Website](https://www.docker.com/get-started).

2. Clone o repositório:

   ```shell
   git clone https://github.com/raonicerqueira/todolist.git

3. Navegue até o diretório do projeto:

   ```shell
   cd todolist

4. Construa a imagem Docker usando o Dockerfile fornecido:

   ```shell
   docker build -t todolist .

5. Execute o contêiner Docker:

   ```shell
   docker run -p 8080:8080 todolist

## Requisitos

- Java 11 ou superior
- Maven

## Contribuições

Fique à vontade para contribuir para este projeto. Siga as diretrizes de contribuição e o código de conduta do projeto.


## Autor

- Raoni Cerqueira