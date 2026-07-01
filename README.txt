 Pré-requisitos: Docker Desktop,Java 21+, Maven
cd C:\Users\Eu\Desktop\biblioteca-microservicos"(seu local do arquivo)"
Gerar os JARs dos serviços
1.C:\Users\Eu\Desktop\biblioteca-microservicos
cd autor-ms
.\mvnw.cmd clean package -DskipTests
2.C:\Users\Eu\Desktop\biblioteca-microservicos
cd livro-ms
.\mvnw.cmd clean package -DskipTests
3.C:\Users\Eu\Desktop\biblioteca-microservicos
cd front-ms
.\mvnw.cmd clean package -DskipTests
4.Subir todos os containers
docker-compose up --build
5.Acessar o sistema
http://localhost
6.Para teste de quebra no microsserviço
#Parar o autor-ms
docker stop autor-ms
#Iniciar novamente
docker start autor-ms


Funcionalidade	 URL
Página inicial	 http://localhost
Lista de Autores http://localhost/autores
Novo Autor	 http://localhost/autores/novo
Lista de Livros	 http://localhost/livros
Novo Livro	 http://localhost/livros/novo

## Arquitetura:
- Nginx (API Gateway)
- autor-ms (REST + PostgreSQL)
- livro-ms (REST + PostgreSQL)
- front-ms (Thymeleaf + RestClient)