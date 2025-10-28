# auth-app

1. Instalation.
  To run this application you need to install Docker(https://www.docker.com/)  and Maven Apache (https://maven.apache.org/download.cgi).
  Make sure both are available in your terminal:

   docker --version
   mvn --version
   
   #You should see the version of Docker and Maven printed in the console.
   
2. Build 
  After installing dependencies, build both Java services and run them with Docker Compose:
   
   mvn -f auth-api/pom.xml clean package -DskipTests
   mvn -f data-api/pom.xml clean package -DskipTests

   docker compose up -d --build

   #This will start PostgresSQL, auth-api and data-api
   
3. Registration & Login
  After running app you need to register and login by using JWT token from responce.
  Resister a new user:

   curl -v -X POST http://localhost:8080/api/auth/register -H "Content-Type: application/json" -d "{"email":"a@a.com","password":"pass"}" 

  Login and get JWT token:
  
   curl -v -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d "{"email":"a@a.com","password":"pass"}"

   # Save token from responce.
   
4. Process text
 After logging in, you can process text by sending the token:

   curl -v -X POST http://localhost:8080/api/process -H "Authorization: Bearer <token>" -H "Content-Type: application/json" -d "{"text":"hello"}" 

5. Stop services
  To stop all services:

  docker compose stop
   

   

   
