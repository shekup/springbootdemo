# springbootdemo

Spring Boot Demo application - Loads data from csv file and exposes as RESTful microservice
The application is created using IntelliJ as IDE, Spring Boot as Microservice framework, and H2 as in memory database. 

One application startup:
1. Application loads the data from csv file (included in project) into in memory database
2. Exposes the data as RESTful API -> Get List of users, Get Individual user, update and delete a user 
3. Updates are committed to in-memory database
4. Test class is provided to perform integration test
This is just a basic application to understand developing microservice with spring boot. 

In IntelliJ, Spring boot application can be easily created using:
File -> New Project -> Spring Initializr and using default options. 
The created project has structure, Application class, pom.xml, etc. 
More dependencies can be added later, such as: Jackson for parsing, H2 for in-memory database, data-jpa for managing entities.

To package and run the application commands are simple - 
1. Download the source code. 
2. Install maven on your local machine. On CMD check "mvn -v" to confirm maven is installed. 
3. Go to the path ..springbootdemo (The folder that has all the code)
4. run command mvnw clean package (This command will create the package)
5. run command mvnw spring-boot:run (This command will run the application)
6. Go to browser and test - http://localhost:8080/

You can notice a target folder is created and inside is the generated jar (package). 
One can run application directly by running the jar file 
Go to path springbootdemo/target and run command ->
java -jar springbootdemo-0.0.1-SNAPSHOT.jar
(Indeed you might get errors while directly running the jar file)
