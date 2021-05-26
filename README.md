Credit Suisse - Developer assignment for Server Log Reviews

**Project is designed using below technologies**
1. Spring Boot v 2.5.0
2. JDK v11 (but should be compatible using v8 also)
3. JPA Repositories
4. HSQLDb
5. Mockito (JUnit)
6. Maven

**Future extensions** 
1. TDD (Test Driven Development) libraries like Cucumber/WirMock
2. Enhance test case.

**How to execute**
Project is setup under maven so all related libraries and dependency added pom.xml.
Run project using command line (pre-requisite Maven and Git) installed.
1. Set JAVA_HOME to jdk v11.
2. mvn clean install
3. mvn spring-boot:run

Once application is up and running, below spring boot endpoints exposed and accessible via HTTP URLs.
1. GET http://localhost:8080/logs/loadDefaultFile -- This will process and load default data set in HSQL DB 
2. GET http://localhost:8080/logs/getFailedEvents -- This will list down all event took beyond 4 seconds.
3. GET http://localhost:8080/logs/get/EVENT_ID (sample value scsmbstgra) -- This endpoint will list all events happened against specific event ID.
4. POST http://localhost:8080/logs/loadCustomData -- Provide JSON data in request, and will load in HSQL DB. 