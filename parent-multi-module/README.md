### Spring Boot Multimodule project example
https://www.javatpoint.com/spring-boot-multi-module-project
https://dzone.com/articles/ddd-spring-boot-multi-module-maven-project

Proofs of concept that shows how to generate Spring Boot multimodule
applications.

### Description

This project is an Spring Boot multimodule application.

Every module match with some application layer (service layer, repository layer, web layer, model, etc..).

#### Parent - Pom Aggregator

This module is a maven aggregator that contains all application modules. Also, it includes all common dependencies needed by more than one module. Dependencies are defined without version.

#### Project Module - Model/Domain

Module that contains all Entities and Visual Objects to be used in the project. It doesn't have any dependencies.

#### Project Module - Repository

Module that contains all repositories to be used in the project. Depends of Model/Domain Module.

#### Project Module - Service API

Module that contains API of all project services. Depends of Model/Domain Module.

#### Project Module - Service Implementation

Module that contains services implementation defined on Service API module. Depends of Repository Module and Service API Module.

#### Project Module - Application

This is the main module of the project. It contains Application.java class,
that contains main method, necessary to run Spring Boot applications. It
contains all necessary application configuration properties. It contains all
web controllers, views and web resources. It include Model/Domain Module and Service 
Implementation module as dependency that contains Model/Domain Module, Repository
Module and Service API module.

### Test

Follow these instructions to execute application using embedded tomcat server:

* Generate project distribution using *mvn clean install* command on Parent
  module.
* Execute *mvn spring-boot:run* command on Application module
* Open browser on *http://localhost:8080*
* Test *http://localhost:8080/*. 

