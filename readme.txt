Instalação e Execução do Projeto Parking
Este é um projeto Spring Boot que utiliza Maven para gerenciar as dependências. Para instalar e executar este projeto, siga as instruções abaixo.

Pré-requisitos
Java 8 ou superior
Maven 3.6.0 ou superior

Instalação:
Clone o repositório para a sua máquina local usando git:
git clone https://github.com/cuscuscxs/parking-example.git

Navegue até o diretório do projeto:
'cd parking-example'
Use o Maven para instalar as dependências e compilar o projeto:
'mvn clean install'

Execução:
Para executar o projeto, use o plugin Spring Boot Maven:

Disponível em http://localhost:8080/parking.

Acessando o Console H2
Este projeto utiliza o banco de dados H2 em memória. Para acessar o console H2, vá para http://localhost:8080/parking/h2-console em seu navegador.

No console H2, insira a seguinte URL do JDBC: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE. O nome de usuário é sa e a senha está em branco.

IMPORTANTE:
Dados no banco de dados H2 em memória serão perdidos quando a aplicação for encerrada.