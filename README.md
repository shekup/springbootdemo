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
More dependencies can be added later, such as: Jackson for parsing, H2 for in-memory database, data-jpa for managing entities, 
