# Getting Started

### Application overview
This application manages to create Favourite food recipes.
There are several pre-defined ingredients using which recipe can be created using the APIs provided to
manage database.

### Technology stack
* Java 8 - *For development of API*
* Spring Boot 2.7.3 - *Spring MVC framework and Spring boot liberaries to develop and deploy application* 
* Swagger API - *For Presentation and API documentation*
* Lombok -  *For Cleaner Code *
* Spring Data JPA - *For Database Interactions*
* H2 Database - *In Memory database to save records*


### Steps to bring application up
* Execute this command to build the artifact  : *gradle bootRun*
* On Startup three main tables will be created 
    * **ingredients**
    * **recipe**
    * **recipe_ingredients**
* Predefined Ingredients data will be inserted into table **ingredients**
* Open url http://localhost:8080/swagger-ui.html to check application status.