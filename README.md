
# Employee Directory CRUD Application

This a MAVEN project where I practice building RESTful API using Spring Boot 3. The project also includes a simple MVC app built using Spring MVC containing the basic CRUD operations


# Database Connection

I'm using **MySQL** for the database and **JPA / Hibernate** for the ORM. I've included the SQL script I used to build the tables to facilitate recreation of the used structure. Username, password and URL for the JDBC connection should be set as environment variables.

## Security

The application is secured using **Spring Security** with custom table names for users, roles and authorities. Passwords as stored as plain-text pre-fixed with *{noop}* in the database. There are 3 main roles in the application: "EMPLOYEE", "MANAGER", and "ADMIN", each authorized access to different functionalities.

## Entities

The main entity in the project is the **Employee** entity, which describes a single employee's information such as first name, last name, email..etc.