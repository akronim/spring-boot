### https://www.baeldung.com/spring-data-mongodb-tutorial
### https://javatodev.com/spring-boot-mongodb-crud-api/
### https://www.journaldev.com/18156/spring-boot-mongodb
### https://www.mongodb.com/compatibility/spring-boot
### https://hevodata.com/learn/spring-boot-mongodb-config/
### https://www.bezkoder.com/spring-boot-mongodb-crud/
### https://medium.com/javarevisited/building-a-rest-service-with-spring-boot-and-mongodb-part-1-2de01e4f434d


### tree -f

### to check if you are using BASH or ZSH, run the command
```
$ echo $SHELL
```

### banners
https://devops.datenkollektiv.de/banner.txt/index.html

### Embedded MongoDB - MongoClient

# spring-boot and mongodb
- https://start.spring.io/ => Spring Web, Embedded MongoDB Database

### pom.xml - you will have this
```xml
<dependency>
	<groupId>de.flapdoodle.embed</groupId>
	<artifactId>de.flapdoodle.embed.mongo</artifactId>
	<!-- <scope>test</scope> -->
</dependency>
```

### https://mvnrepository.com/artifact/org.mongodb/mongo-java-driver
### pom.xml - add
```xml
<dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>mongo-java-driver</artifactId>
    <version>3.12.11</version>
</dependency>
```
### application.properties
```
# to instruct flapdoodle mongo to run on specific port
spring.data.mongodb.port=28018

# specified version will be automatically downloaded - for available versions see:
# https://mvnrepository.com/artifact/de.flapdoodle.embed/de.flapdoodle.embed.mongo
spring.mongodb.embedded.version=3.4.6
```
### DemoApplication.java
```java
package com.example.demo;

import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	MongoClient mongoClient;

	public MongoClient getClient() {
		if (mongoClient == null) {
			return new MongoClient("localhost", 28018);
		}

		return mongoClient;
	}

	@Override
	public void run(String... args) throws Exception {
		MongoClient mongoClient = getClient();
		MongoDatabase database = mongoClient.getDatabase("mongoTestDB");
		MongoCollection<Document> employeCollection = database.getCollection("employees");

		Document employee1 = new Document();
		employee1.append("firstName", "John");
		employee1.append("lastName", "Doe");
		employee1.append("address", "Some City");

		employeCollection.insertOne(employee1);

	}

}
```
### run
```
mvn spring-boot:run
```
### MongoDB shell
```
mongo --port 28018
```
### show all connections
```
db.serverStatus().connections
```
### if you need to shut down mongo server manually:
```
mongo --port 28018
use admin
db.shutdownServer()
```
### pom.xml - add this (as first dependency) to use MongoTemplate and embedded mongo
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```
### application.properties - add
```
spring.data.mongodb.host=localhost
```
### create: Employee.java
```java
package com.example.demo;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Employee {
    @Id
    public String id;
    public String firstName;
    public String lastName;
    public String address;

    public Employee() {
    }

    public Employee(String id, String firstName, String lastName, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName)
                && Objects.equals(lastName, employee.lastName) && Objects.equals(address, employee.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", address='" + getAddress() + "'" +
                "}";
    }
}
```
### DemoApplication.java
```java
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void run(String... args) throws Exception {
		Employee employee = new Employee();
		employee.setFirstName("John");
		employee.setLastName("Doe");
		employee.setAddress("Some City");

		mongoTemplate.save(employee);

		var employees = mongoTemplate.findAll(Employee.class);

		employees.forEach(emp -> System.out.println(emp.toString()));
	}

}
```

### TODO - to include
- helm
- job scheduler
- mapping with @Mapper (org.mapstruct.Mapper)
- more MongoTemplate Query examples

### start

### https://start.spring.io/
- Select:
    - Project: Maven Project
    - Language: Java
    - Spring Boot: version (2.7.0)
    - Packaging: jar
    - Java: 11

- Project Metadata:
    - Group: com.example
    - Artifact: mdb-spring-boot
    - Name: mdb-spring-boot

- Add dependencies:
    - Spring Web
    - Spring Data MongoDB
    - Lombok
    - Validation

- click on the Generate button
- once the ZIP file is downloaded, unzip the project 
- open the project from the IDE

### pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.0</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>mdb-spring-boot</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>mdb-spring-boot</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>11</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>
```

### We can run the app simply by using a single command
```
mvn install -DskipTests
mvn spring-boot:run
```
### you will see something like
```
o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http) 

o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
```

### add default page - mdb-spring-boot\src\main\resources\static\index.html
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Lorem Ipsum Dolor Sit Amet</h1>
</body>
</html>
```

### installing mongodb
```
https://docs.mongodb.org/manual/core/introduction/
```

### check if employeesdb already exists, and, if yes, drop it
```
mongo
show dbs
use employeesdb
show collections
db.employees.find().count()
db.employees.remove({})
db.employees.find().count()
db.employees.drop()
db.dropDatabase()
show dbs
exit
```

### info: https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html
### define application properties - resources\application.properties
```yml
### App config
#### the app will run on port 8102
server.port=8102 
spring.application.name=BootMongo
server.servlet.context-path=/mdb-spring-boot

spring.main.banner-mode=off

### Local MongoDB config
# spring.data.mongodb.authentication-database=admin
# spring.data.mongodb.username=rootuser
# spring.data.mongodb.password=rootpass
# spring.data.mongodb.database=employeesdb
# spring.data.mongodb.port=27017
# spring.data.mongodb.host=localhost

### Local MongoDB config - one-liners
#### with authentication
# spring.data.mongodb.uri=mongodb://rootuser:rootpass@127.0.0.1:27017/employeesdb?authSource=admin&ssl=false
#### without authentication
spring.data.mongodb.uri=mongodb://127.0.0.1:27017/employeesdb?ssl=false
```

### using docker
- nvm use 14
- install Docker Desktop: https://www.docker.com/get-started/

### check the version of Docker
```
docker --version
```
### check the version of Docker Compose
```
docker-compose version
```
### create a file: docker-compose.yml in the project directory
```
touch docker-compose.yml
```
### docker-compose.yml
```yml
version: "3.8" # the version of the docker-compose file syntax
services:
# container 1 - mongodb
  mongodb:
    image: mongo # would get the latest version for mongodb
    container_name: mongodb
    ports: 
        - 27017:27017 # host:container
    volumes: 
        - data:/data
    environment: 
        - MONGO_INITDB_ROOT_USERNAME=rootuser
        - MONGO_INITDB_ROOT_PASSWORD=rootpass
# container 2 - mongo-express GUI
  mongo-express:
    image: mongo-express
    container_name: mongo-express
    depends_on:
      - mongodb
    restart: always
    # port 8081 is exposed to allow access to the web interface
    ports: 
        - 8081:8081 # host:container
    environment: 
        - ME_CONFIG_MONGODB_SERVER=mongodb
        - ME_CONFIG_MONGODB_ADMINUSERNAME=rootuser
        - ME_CONFIG_MONGODB_ADMINPASSWORD=rootpass
        - ME_CONFIG_MONGODB_ENABLE_ADMIN=true
        - ME_CONFIG_BASICAUTH_USERNAME=admin #
        - ME_CONFIG_BASICAUTH_PASSWORD=admin123 #
# for storing the data
volumes: 
    data: {}
# for communication between containers
networks:
    default:
        # overriding default name
        name: mongodb_network
```
### create and run docker containers
```
docker-compose -f docker-compose.yml up
```
### check if the containers are indeed up and running
```
docker ps
```
### stop and remove everything
```
docker-compose -f docker-compose.yml down
```
### create and run docker containers - detached mode
```
docker-compose -f docker-compose.yml up -d
```
### now visit this addres for Mongo Express GUI:
http://localhost:8081/

### start - stop the containers without removing them
```
docker-compose -f docker-compose.yml stop #or: ctrl C
docker-compose -f docker-compose.yml start
```
### using terminal - mongo shell
#### list running containers and check the CONTAINER ID for IMAGE: mongo, NAMES: mongodb
```
docker ps
```
#### get bash interactive shell for the IMAGE: mongo, NAMES: mongodb
```
docker exec -it {mongo CONTAINER ID} bash
```
#### connect to a MongoDB server
```
mongo mongodb://localhost:27017 -u rootuser -p rootpass
```
#### list all databases
```
show dbs;
```
### importing data into db
#### open a new terminal
#### list running containers
```
docker ps
```
### finally, modify application.properties
```
### Local MongoDB config - one-liners
#### with authentication
spring.data.mongodb.uri=mongodb://rootuser:rootpass@127.0.0.1:27017/employeesdb?authSource=admin&ssl=false
#### without authentication
# spring.data.mongodb.uri=mongodb://127.0.0.1:27017/employeesdb?ssl=false
```
### using docker END


### create a package named com.example.mdbspringboot.model and add the class Project.java
```java
package com.example.mdbspringboot.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private String id;
    @NotBlank(message = "project title shouldn't be blank")
    private String title;
    @NotBlank(message = "project description shouldn't be blank")
    private String description;
}
```

### create a class Employee.java
```java
package com.example.mdbspringboot.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data // for getters, setters, constructors...
@Document(value = "employees") // so that it can be stored as mongo document
public class Employee {
    @Id // will be autogenerated
    private String id;
    private String firstName;
    private String lastName;
    // @Indexed(unique = true) => will ensure that email is unique
    // - to enable index, in application.properties we put:
    // spring.data.mongodb.auto-index-creation=true
    @Indexed(unique = true)
    private String email;
    private String gender;
    private String department;
    private List<String> projects;
    @Field("projects_2") // the name of the field in mongodb
    private List<Project> projects2;
    private Double salary;
    private String mobile;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime createdAt;

    public Employee(String firstName, String lastName, String email, String gender, String department,
            List<String> projects, List<Project> projects2, Double salary, String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.projects = projects;
        this.projects2 = projects2;
        this.salary = salary;
        this.mobile = mobile;
        this.createdAt = LocalDateTime.now();
    }
}
```

### add to: src\main\resources\application.properties
```
# to enable auto-index creation
spring.data.mongodb.auto-index-creation=true
```

### create a package called com.example.mdbspringboot.dto
### create a file: dto/EmployeeDTO.java
```java
package com.example.mdbspringboot.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;

import com.example.mdbspringboot.model.Project;

@Data
@Builder
@AllArgsConstructor(staticName = "build")
/*
 * @AllArgsConstructor(staticName = "build") => with this we can create new
 * object like this:
 * 
 * EmployeeDTO employeeDTO = EmployeeDTO.build("Alex", "Moore",
 * "alex@moore.com", "Male", "IT",
 * List.of("Project 3", "Project 5", "Project 6"), 6350.0,
 * "123 345 6789");
 */
@NoArgsConstructor // empty => default
public class EmployeeDTO {
    private String id;
    @NotBlank(message = "first name shouldn't be blank")
    private String firstName;
    @NotBlank(message = "last name shouldn't be blank")
    private String lastName;
    @Email(message = "invalid email address")
    @NotBlank(message = "email shouldn't be blank")
    private String email;
    private String gender;
    @NotBlank
    private String department;
    @NotEmpty(message = "projects shouldn't be empty")
    private List<String> projects;
    @NotEmpty(message = "projects2 shouldn't be empty")
    @Valid // to include validations that are present in Project class
    private List<Project> projects2;
    @Min(2500)
    @Max(8000)
    @NotNull(message = "salary shouldn't be null")
    private Double salary;
    @NotBlank(message = "mobile shouldn't be blank")
    @Pattern(regexp = "^(\\d{3}[- .]?){2}\\d{4}$", message = "invalid mobile number entered ")
    private String mobile;
    private LocalDateTime created;
}
```

### create a package called com.example.mdbspringboot.repository

### create an EmployeeRepository public interface which extends the MongoRepository interface
```java
package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    
}

// to get JSON converter to ignore null values => 
// - add this to the application.properties file:
// spring.jackson.default-property-inclusion=non-null
```

### add to: src\main\resources\application.properties
```
# to get JSON converter to ignore null values
spring.jackson.default-property-inclusion=non-null
```

### MdbSpringBootApplication
```java
package com.example.mdbspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.model.Project;
import com.example.mdbspringboot.repository.EmployeeRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class MdbSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}

	// db.employees.deleteOne( { "email" : "john@smith.com" } );

	@Bean
	CommandLineRunner runner(EmployeeRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			String email = "john@smith.com";

			Employee employee = new Employee("John", "Smith", email, "Male", "Finance",
					List.of("Project 3", "Project 5", "Project 6"), List.of(
							Project.builder().title("Test 1").description("1 Lorem ipsum dolor sit amet").build(),
							Project.builder().title("Test 2").description("2 Lorem ipsum dolor sit amet").build()),
					5500.0, "123 345 6789");

			repository.findAll().stream().filter(e -> e.getEmail().equals(email)).findFirst().ifPresentOrElse(s -> {
				System.out.println("\n>>>>>> " + s + " already exists");
			}, () -> {
				System.out.println("\n>>>>>> Inserting employee " + employee);
				repository.insert(employee);
			});
		};
	}
}
```

### check if data inserted
```
mongosh
show dbs
use employeesdb
show collections
db.employees.find().count()
db.employees.find().pretty()
exit
```

### add to: src\main\resources\application.properties
```
# to enable logging MongoDB queries
logging.level.org.springframework.data.mongodb.core.MongoTemplate=DEBUG
```

### MdbSpringBootApplication - another way
```java
package com.example.mdbspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.model.Project;
import com.example.mdbspringboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class MdbSpringBootApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}

	// db.employees.deleteOne( { "email" : "john@smith.com" } );

	@Override
	public void run(String... args) throws Exception {
		String email = "john@smith.com";

		Employee employee = new Employee("John", "Smith", email, "Male", "Finance",
				List.of("Project 3", "Project 5", "Project 6"), List.of(
						Project.builder().title("Test 1").description("1 Lorem ipsum dolor sit amet").build(),
						Project.builder().title("Test 2").description("2 Lorem ipsum dolor sit amet").build()),
				5500.0, "123 345 6789");

		repository.findAll().stream().filter(e -> e.getEmail().equals(email)).findFirst().ifPresentOrElse(s -> {
			System.out.println("\n>>>>>> " + s + " already exists");
		}, () -> {
			System.out.println("\n>>>>>> Inserting employee " + employee);
			repository.insert(employee);
		});
	}
}
```

### MdbSpringBootApplication - another way
### create: mdb-spring-boot\src\main\java\com\example\mdbspringboot\runner\Runner.java
```java
package com.example.mdbspringboot.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.model.Project;
import com.example.mdbspringboot.repository.EmployeeRepository;

public class Runner implements CommandLineRunner {

    @Autowired
    private EmployeeRepository repository;

    // db.employees.deleteOne( { "email" : "john@smith.com" } );

    @Override
    public void run(String... args) throws Exception {
        String email = "john@smith.com";

        Employee employee = new Employee("John", "Smith", email, "Male", "Finance",
                List.of("Project 3", "Project 5", "Project 6"), List.of(
                        Project.builder().title("Test 1").description("1 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 2").description("2 Lorem ipsum dolor sit amet").build()),
                5500.0, "123 345 6789");

        repository.findAll().stream().filter(e -> e.getEmail().equals(email)).findFirst().ifPresentOrElse(s -> {
            System.out.println("\n>>>>>> " + s + " already exists");
        }, () -> {
            System.out.println("\n>>>>>> Inserting employee " + employee);
            repository.insert(employee);
        });
    }
}
```
### MdbSpringBootApplication
```java
package com.example.mdbspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.mdbspringboot.runner.Runner;

@SpringBootApplication
public class MdbSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}

	@Bean
	public Runner getRunner() {
		return new Runner();
	}
}
```

### using @Component annotation
### Runner.java
```java
import org.springframework.stereotype.Component;

// ...

// add above the class
@Component
```

### MdbSpringBootApplication
```java
// remove this
import org.springframework.context.annotation.Bean;
import com.example.mdbspringboot.runner.Runner;

// remove this
@Bean
public Runner getRunner() {
    return new Runner();
}
```

### Employee.java
#### remove the existing constructor
#### add @Builder annotation to the Employee class
#### (for that you also need to add: @NoArgsConstructor and @AllArgsConstructor annotations)
```java
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder // now we can create Employee instances using builder API: Employee.builder().firstName("Alex").build()
@AllArgsConstructor
@NoArgsConstructor
public class Employee { /* remove the existing constructor */ }
```

### Runner.java - initialize Employee this way
```java
import java.time.LocalDateTime;

// ...

String email = "john@smith.com";

Employee employee = Employee.builder()
        .firstName("John")
        .lastName("Smith")
        .email(email)
        .gender("Male")
        .department("Finance")
        .projects(List.of("Project 3", "Project 5", "Project 6"))
        .projects2(List.of(
                Project.builder().title("Test 1").description("1 Lorem ipsum dolor sit amet").build(),
                Project.builder().title("Test 2").description("2 Lorem ipsum dolor sit amet").build()))
        .salary(5500.0)
        .mobile("123 345 6789")
        .createdAt(LocalDateTime.now())
        .build();
```

### inside the project directory create a file employees.json (mongodb database data)
```json

```

### create and import data into mongodb database employeesdb (win)
```
mongoimport --db=employeesdb --collection=employees mongodb://localhost:27017 --drop --file=abs\path\to\mdb-spring-boot\employees.json --jsonArray
```
#### (if we need to authenticate first)
```
mongoimport --authenticationDatabase admin --username=rootuser --password=rootpass --db=employeesdb --collection=employees mongodb://localhost:27017 --drop --file=abs\path\to\mdb-spring-boot\employees.json --jsonArray
```

#### if using DOCKER - import the data (containers must be running)
```
docker exec -i {mongo CONTAINER ID} sh -c 'mongoimport --authenticationDatabase admin --username=rootuser --password=rootpass -c employees -d employeesdb --drop --jsonArray' < /path/mdb-spring-boot/employees.json
```

### check if data imported
```
mongosh mongodb://localhost:27017/employeesdb
db.version()
db.employees.find().count()
db.employees.find().limit(1).pretty()
```
#### (if we need to authenticate first)
```mongosh "mongodb://rootuser:rootpass@localhost:27017/employeesdb?authSource=admin"```
```mongosh "mongodb://localhost:27017/employeesdb" --username rootuser --password rootpass --authenticationDatabase admin```


### create a package called com.example.mdbspringboot.services

### create an interface: EmployeeService
```java
package com.example.mdbspringboot.services;

import java.util.List;

import com.example.mdbspringboot.dto.EmployeeDTO;
import com.example.mdbspringboot.model.Employee;

public interface EmployeeService {
    List<Employee> getAll();

    Employee getById(String employeeId);

    Employee create(EmployeeDTO employee);

    Employee update(EmployeeDTO employee);

    void delete(String id);
}
```

### create the service implementation
```java
package com.example.mdbspringboot.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.example.mdbspringboot.dto.EmployeeDTO;
import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(String employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public Employee create(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeDTO.fromEmployeeDTO(employeeDTO);
        employee.setCreatedAt(LocalDateTime.now());
        return employeeRepository.save(employee);
    }

    public Employee update(EmployeeDTO employeeDTO) {
        if (employeeRepository.existsById(employeeDTO.getId())) {
            Employee employee = EmployeeDTO.fromEmployeeDTO(employeeDTO);
            return employeeRepository.save(employee);
        }
        return null;
    }

    public void delete(String id) {
        if (Objects.nonNull(id) && !id.isBlank()) {
            employeeRepository.deleteById(id);
        }
    }
}
```

### EmployeeDTO.java - add
```java
import com.example.mdbspringboot.model.Employee;
import java.util.Objects;

// ...

public static EmployeeDTO toEmployeeDTO(Employee employee) {
    return EmployeeDTO.builder()
            .id(employee.getId())
            .firstName(employee.getFirstName())
            .lastName(employee.getLastName())
            .email(employee.getEmail())
            .gender(employee.getGender())
            .department(employee.getDepartment())
            .projects(employee.getProjects())
            .projects2(employee.getProjects2())
            .salary(employee.getSalary())
            .mobile(employee.getMobile())
            .created(employee.getCreatedAt())
            .build();
}

public static Employee fromEmployeeDTO(EmployeeDTO employeeDTO) {
    return Employee.builder()
            .id(Objects.nonNull(employeeDTO.getId()) ? employeeDTO.getId() : null)
            .firstName(employeeDTO.getFirstName())
            .lastName(employeeDTO.getLastName())
            .email(employeeDTO.getEmail())
            .gender(employeeDTO.getGender())
            .department(employeeDTO.getDepartment())
            .projects(employeeDTO.getProjects())
            .projects2(employeeDTO.getProjects2())
            .salary(employeeDTO.getSalary())
            .mobile(employeeDTO.getMobile())
            .createdAt(employeeDTO.getCreated())
            .build();
}
```


### create a package called com.example.mdbspringboot.controllers

### create a controller EmployeeController.java
```java
package com.example.mdbspringboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.services.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // http://localhost:8102/mdb-spring-boot/api/v1/employees/
    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        LOG.info("\n>>>>> Getting all employees.\n");
        return employeeService.getAll();
    }
}
```


### create: src\main\java\com\example\mdbspringboot\services\EmployeeServiceImplV2.java
```java
package com.example.mdbspringboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mdbspringboot.dto.EmployeeDTO;
import com.example.mdbspringboot.model.Employee;

@Service
public class EmployeeServiceImplV2 implements EmployeeService {

    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public Employee getById(String employeeId) {
        return null;
    }

    @Override
    public Employee create(EmployeeDTO employee) {
        return null;
    }

    @Override
    public Employee update(EmployeeDTO employee) {
        return null;
    }

    @Override
    public void delete(String id) {
        
    }
}
```


### EmployeeController - in case of multiple service implementations you have to specify which one to use
```java
import org.springframework.beans.factory.annotation.Qualifier; // new code

// ...

    @Autowired
    @Qualifier("employeeServiceImpl") // new code
    EmployeeService employeeService;
```

### EmployeeServiceImpl - add class annotation that says this is primary implementation
```java
import org.springframework.context.annotation.Primary;

// ...

@Service
@Primary // new code
public class EmployeeServiceImpl implements EmployeeService
```

### EmployeeController - remove @Qualifier("employeeServiceImpl") annotation

### testing
https://mkyong.com/spring-boot/spring-rest-integration-test-example/
https://www.petrikainulainen.net/programming/testing/junit-5-tutorial-writing-assertions-with-junit-5-api/
https://github.com/pkainulainen/junit5-examples/tree/master/unit-tests/writing-assertions/junit5-api
https://reflectoring.io/spring-boot-test/
https://www.baeldung.com/spring-boot-testing

### mock vs spy
```java
/*
 * @MockBean //or Mockito's @Mock
 * - it mocks the object and all its methods with do nothing and their result
 * value will be null,
 * - use for example: when(...) methods to create mocked method behaviour
 * - use when you want to completely get rid of the object's normal behaviour
 * 
 * 
 * @SpyBean //or Mockito's @Spy
 * - an object will behave like an @Autowired object
 * - all its methods will actually works, but we can define some custom behavior
 * for its methods
 * - use doReturn(...) / doNothing(...) to add custom (mocked) method behaviour
 * - use if you want to provide your mock behaviour but not dismiss entirely its
 * normal behaviour
 * - the spy object is a wrapper around an actual object of a class
 */

// source: https://javapointers.com/tutorial/difference-between-spy-and-mock-in
```

### pom.xml: add this dependency
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.7.0</version>
    <scope>test</scope>
</dependency>
```

### src/test/java/com/example/mdbspringboot/MdbSpringBootApplicationTests.java
```java
package com.example.mdbspringboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MdbSpringBootApplicationTests {

	// object under test
	Calculator calculator = new Calculator();

	@Test
	void itShouldAddTwoNumbers() {
		// given
		int numberOne = 20;
		int numberTwo = 30;

		// when
		int result = calculator.add(numberOne, numberTwo);

		// then
		int expected = 50;
		assertEquals(expected, result);
	}

	class Calculator {
		int add(int a, int b) {
			return a + b;
		}
	}
}
```

### run unit tests with maven
```
mvn clean test   
```

### create src\test\java\com\example\mdbspringboot\services\EmployeeServiceTest.java
```java
package com.example.mdbspringboot.services;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import com.example.mdbspringboot.model.Employee;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.CollectionUtils;

// we do not mock EmployeeRepository here

@SpringBootTest
class EmployeeServiceTest {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeeService employeeService;

    @DisplayName("getAll 1 | GIVEN ...  SHOULD ...")
    @Test
    void getAll_x1() {

        List<Employee> employees = employeeService.getAll();

        assertFalse(CollectionUtils.isEmpty(employees));

        LOG.info("\n\n>>>>> employees count: {}\n", employees.size());
    }
}
```

### search: maven repository de.flapdoodle.embed.mongo
https://mvnrepository.com/artifact/de.flapdoodle.embed/de.flapdoodle.embed.mongo

### pom.xml
```xml
<dependency>
    <groupId>de.flapdoodle.embed</groupId>
    <artifactId>de.flapdoodle.embed.mongo</artifactId>
    <scope>test</scope>
</dependency>
```

### create src\test\resources\application-test.properties
```
# test db (embedded mongodb) will be used
# specified version will be automatically downloaded - for available versions see:
# https://mvnrepository.com/artifact/de.flapdoodle.embed/de.flapdoodle.embed.mongo
spring.mongodb.embedded.version=3.4.6
```
### create src\test\resources\application-it.properties
```
# excluding embedded mongodb (and using "real" db) 
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
```

### src\test\java\com\example\mdbspringboot\MdbSpringBootApplicationTests.java - add class annotation
### src\test\java\com\example\mdbspringboot\services\EmployeeServiceTest.java - add class annotation
```java
import org.springframework.test.context.ActiveProfiles;

// @SpringBootTest
@SpringBootTest
@ActiveProfiles("it")
```

### src\test\java\com\example\mdbspringboot\services\EmployeeServiceMockTest.java
```java
package com.example.mdbspringboot.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.mdbspringboot.dto.EmployeeDTO;
import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.model.Project;
import com.example.mdbspringboot.repository.EmployeeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// we mock EmployeeRepository here

@SpringBootTest
@ActiveProfiles("it")
class EmployeeServiceMockTest {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeeService employeeService;

    // Spring Boot's Annotation (different from Mockito's @Mock Annotation)
    // The mock will replace any existing bean of the same type in the application context
    @MockBean
    private EmployeeRepository employeeRepository;

    private Employee emp1;
    private Employee emp2;
    private Employee emp3;
    List<Employee> employees;

    @BeforeEach
    void setUp() {

        emp1 = Employee.builder()
                .id("alex123")
                .firstName("Alex")
                .lastName("Moore")
                .email("alex@moore.com")
                .gender("Male")
                .department("IT")
                .projects(List.of("Project 3", "Project 5", "Project 6"))
                .projects2(List.of(
                        Project.builder().title("Test 1").description("1 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 2").description("2 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 3").description("3 Lorem ipsum dolor sit amet").build()))
                .salary(6350.0)
                .mobile("123 345 6789")
                .createdAt(LocalDateTime.now())
                .build();

        emp2 = Employee.builder()
                .id("ingrid321")
                .firstName("Ingrid")
                .lastName("French")
                .email("ingrid@french.com")
                .gender("Female")
                .department("Production")
                .projects(List.of("Project 2", "Project 3", "Project 4"))
                .projects2(List.of(
                        Project.builder().title("Test 1").description("1 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 2").description("2 Lorem ipsum dolor sit amet").build()))
                .salary(4450.0)
                .mobile("223 345 7789")
                .createdAt(LocalDateTime.now())
                .build();

        emp3 = Employee.builder()
                .id("austin423")
                .firstName("Austin")
                .lastName("Walker")
                .email("austin@walker.com")
                .gender("Male")
                .department("HR")
                .projects(List.of("Project 1", "Project 3", "Project 6"))
                .projects2(List.of(
                        Project.builder().title("Test 1").description("1 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 2").description("2 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 3").description("3 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 4").description("4 Lorem ipsum dolor sit amet").build()))
                .salary(5150.0)
                .mobile("723 545 8789")
                .createdAt(LocalDateTime.now())
                .build();

        employees = List.of(emp1, emp2, emp3);
    }

    @DisplayName("getAll 1 | GIVEN ... SHOULD ...")
    @Test
    void getAll_x1() {
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> employees = employeeService.getAll();

        LOG.info("\n\n>>>>> employees: {}\n", employees);

        assertFalse(CollectionUtils.isEmpty(employees));
        assertEquals(3, employees.size());
    }

    @DisplayName("getById 1 | GIVEN ...  SHOULD ...")
    @Test
    void getById_x1() {
        String employeeId = "alex123";

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(emp1));

        Employee employee = employeeService.getById(employeeId);

        assertTrue(Objects.nonNull(employee));
        assertEquals(employeeId, employee.getId());
        assertTrue(employee.getFirstName().equals("Alex"));
        assertEquals(3, employee.getProjects2().size());
    }

    @DisplayName("create 1 | GIVEN ...  SHOULD ...")
    @Test
    void create_x1() {
        // Mockito uses the equals for argument matching, try using ArgumentMatchers.any
        // for the save / insert method
        when(employeeRepository.save(any(Employee.class))).thenReturn(emp1);

        Employee savedEmployee = employeeService.create(new EmployeeDTO());

        assertNotNull(savedEmployee);
        assertEquals(emp1.getId(), savedEmployee.getId());
    }

    @DisplayName("update 1 | GIVEN ...  SHOULD ...")
    @Test
    void update_x1() {
        // Mockito uses the equals for argument matching, try using ArgumentMatchers.any
        // for the save / insert method
        when(employeeRepository.save(any(Employee.class))).thenReturn(emp1);

        when(employeeRepository.existsById(any(String.class))).thenReturn(true);

        Employee updatedEmployee = employeeService.update(EmployeeDTO.toEmployeeDTO(emp1));

        assertNotNull(updatedEmployee);
        assertEquals(emp1.getId(), updatedEmployee.getId());
    }

    @DisplayName("delete 1 | GIVEN ...  SHOULD ...")
    @Test
    void delete_x1() {
        ArgumentCaptor<String> valueCapture = ArgumentCaptor.forClass(String.class);

        doNothing().when(employeeRepository).deleteById(valueCapture.capture());

        employeeService.delete("foo123");

        assertEquals("foo123", valueCapture.getValue());
    }
}
```

### create src\test\java\com\example\mdbspringboot\repository\EmployeeRepositoryTest.java
```java
package com.example.mdbspringboot.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.model.Project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee emp1;
    private Employee emp2;
    private Employee emp3;
    List<Employee> employees;

    @BeforeEach
    void setUp() {

        employeeRepository.deleteAll();

        emp1 = Employee.builder()
                .id("alex123")
                .firstName("Alex")
                .lastName("Moore")
                .email("alex@moore.com")
                .gender("Male")
                .department("IT")
                .projects(List.of("Project 3", "Project 5", "Project 6"))
                .projects2(List.of(
                        Project.builder().title("Test 1").description("1 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 2").description("2 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 3").description("3 Lorem ipsum dolor sit amet").build()))
                .salary(6350.0)
                .mobile("123 345 6789")
                .createdAt(LocalDateTime.now())
                .build();

        emp2 = Employee.builder()
                .id("ingrid321")
                .firstName("Ingrid")
                .lastName("French")
                .email("ingrid@french.com")
                .gender("Female")
                .department("Production")
                .projects(List.of("Project 2", "Project 3"))
                .projects2(List.of(
                        Project.builder().title("Test 1").description("1 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 2").description("2 Lorem ipsum dolor sit amet").build()))
                .salary(4450.0)
                .mobile("223 345 7789")
                .createdAt(LocalDateTime.now())
                .build();

        emp3 = Employee.builder()
                .firstName("Austin")
                .lastName("Walker")
                .email("austin@walker.com")
                .gender("Male")
                .department("HR")
                .projects(List.of("Project 1"))
                .projects2(List.of(
                        Project.builder().title("Test 1").description("1 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 2").description("2 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 3").description("3 Lorem ipsum dolor sit amet").build(),
                        Project.builder().title("Test 4").description("4 Lorem ipsum dolor sit amet").build()))
                .salary(5150.0)
                .mobile("723 545 8789")
                .createdAt(LocalDateTime.now())
                .build();

        employees = List.of(emp1, emp2);

        employeeRepository.saveAll(employees);
    }

    @AfterEach
    public void tearDown() {
        employeeRepository.deleteAll();
    }

    @DisplayName("findAll 1 | GIVEN ...  SHOULD ...")
    @Test
    void findAll_x1() {
        List<Employee> employees = employeeRepository.findAll();

        LOG.info("\n\n>>>>> employees: {}\n", employees);

        assertFalse(CollectionUtils.isEmpty(employees));

        assertEquals(2, employees.size());

        assertTrue(employees.stream().anyMatch(x -> x.getFirstName().equals("Alex") && x.getProjects2().size() == 3));
    }

    @DisplayName("findById 1 | GIVEN ...  SHOULD ...")
    @Test
    void findById_x1() {
        String employeeId = "alex123";

        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        assertTrue(Objects.nonNull(employee));
        assertEquals(employeeId, employee.getId());
        assertTrue(employee.getFirstName().equals("Alex"));
        assertEquals(3, employee.getProjects2().size());
    }

    @DisplayName("create 1 | GIVEN ...  SHOULD ...")
    @Test
    void create_x1() {
        assertFalse(employeeRepository.findAll().stream().anyMatch(p -> p.getEmail().equals(emp3.getEmail())));

        Employee savedEmployee = employeeRepository.save(emp3);

        assertNotNull(savedEmployee);
        assertEquals(emp3.getEmail(), savedEmployee.getEmail());

        assertTrue(employeeRepository.findAll().stream().anyMatch(p -> p.getEmail().equals(emp3.getEmail())));
    }

    @DisplayName("update 1 | GIVEN ...  SHOULD ...")
    @Test
    void update_x1() {
        assertTrue(employeeRepository.existsById(emp1.getId()));

        emp1.setDepartment("foobar");

        Employee updatedEmployee = employeeRepository.save(emp1);

        assertNotNull(updatedEmployee);
        assertEquals("foobar", updatedEmployee.getDepartment());
    }

    @DisplayName("delete 1 | GIVEN ...  SHOULD ...")
    @Test
    void delete_x1() {
        assertTrue(employeeRepository.existsById(emp1.getId()));

        employeeRepository.deleteById(emp1.getId());

        assertFalse(employeeRepository.existsById(emp1.getId()));
    }
}
```

### EmployeeRepository - new methods
```java
import java.util.List;
import org.springframework.data.mongodb.repository.Query;

// the rest skipped for brevity

@Query(value = "{ 'projects': { $elemMatch : { $in: ?0 } } }", fields = "{'firstName': 1, 'lastName': 1, 'projects': 1}")
List<Employee> getBy(String[] projects);

// query by method names
List<Employee> findByFirstNameStartingWith(String firstName);

List<Employee> findByDepartment(String department);

// using @Query
@Query(value = "{'salary':{$gte:?0}}", fields = "{'salary':1, 'firstName':1, 'lastName':1, '_id':0}")
List<Employee> getAllBySalaryGTE(int salary);

```

### EmployeeService
```java
import java.util.Map;

//...

Map<String, Object> getByProjects(String[] projects);

// query by Example Executor
List<Employee> getAllByExample(Employee employee);

// query by method names
List<Employee> getAllByFirstNameStartingWith(String firstName);

// query by method names
List<Employee> getAllByDepartment(String department);

// using @Query
List<Employee> getAllBySalaryGTE(int salary);
```

### EmployeeServiceImpl
```java
import org.springframework.data.domain.Example;

import java.util.HashMap;
import java.util.Map;

// ...

public Map<String, Object> getByProjects(String[] projects) {
    Map<String, Object> response = new HashMap<String, Object>();

    List<Employee> listOfEmployees = employeeRepository.getBy(projects);

    response.put("data", listOfEmployees);
    response.put("Total no of employees", listOfEmployees.size());

    return response;
}

// query by Example Executor
public List<Employee> getAllByExample(Employee employee) {
    Example<Employee> example = Example.of(employee);

    // ExampleMatcher employeeMatcher =
    // ExampleMatcher.matchingAll().withIgnoreCase("lastName", "firstName")
    // .withIgnorePaths("id").withNullHandler(ExampleMatcher.NullHandler.INCLUDE)
    // .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    List<Employee> employees = employeeRepository.findAll(example);

    return employees;
}

// query by method names
public List<Employee> getAllByFirstNameStartingWith(String firstName) {
    return employeeRepository.findByFirstNameStartingWith(firstName);
}

// query by method names
public List<Employee> getAllByDepartment(String department) {
    return employeeRepository.findByDepartment(department);
}

// using @Query
public List<Employee> getAllBySalaryGTE(int salary) {
    return employeeRepository.getAllBySalaryGTE(salary);
}
```

### EmployeeServiceImplV2.java - add
```java
    @Override
    public Map<String, Object> getByProjects(String[] projects) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Employee> getAllByExample(Employee employee) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Employee> getAllByFirstNameStartingWith(String firstName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Employee> getAllByDepartment(String department) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Employee> getAllBySalaryGTE(int salary) {
        // TODO Auto-generated method stub
        return null;
    }
```

### EmployeeServiceTest.java
```java
import static org.junit.jupiter.api.Assertions.assertTrue;

// ...

@DisplayName("getAllByFirstNameStartingWith 1 | GIVEN ... SHOULD ...")
@Test
void getAllByFirstNameStartingWith_x1() {
    String firstName = "Al";

    List<Employee> employees = employeeService.getAllByFirstNameStartingWith(firstName);

    assertFalse(CollectionUtils.isEmpty(employees));

    employees.forEach(x -> {
        assertTrue(x.getFirstName().startsWith(firstName));
    });
}
```

### create another interface: src\main\java\com\example\mdbspringboot\repository\CustomEmployeeRepositoryTwo.java
```java
package com.example.mdbspringboot.repository;

import java.util.List;

import com.example.mdbspringboot.model.Employee;

public interface CustomEmployeeRepositoryTwo {
    List<Employee> findAll();

    void saveAll(final List<Employee> employees);

    Employee findById(final String employeeId);

    String getEmployeesCount(String inputArg);
}
```

### create the interface implementation: src\main\java\com\example\mdbspringboot\repository\CustomEmployeeRepositoryTwoImpl.java
```java
package com.example.mdbspringboot.repository;

import java.util.List;

import com.example.mdbspringboot.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class CustomEmployeeRepositoryTwoImpl implements CustomEmployeeRepositoryTwo {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Employee> findAll() {
        return mongoTemplate.findAll(Employee.class);
    }

    public void saveAll(final List<Employee> employees) {
        mongoTemplate.insertAll(employees);
    }

    public Employee findById(final String employeeId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(employeeId));
        
		return mongoTemplate.findOne(query, Employee.class);
    }

    @Override
    public String getEmployeesCount(String inputArg) {
        var employeesCount = mongoTemplate.findAll(Employee.class).size();
        var resultMessage = "REPOSITORY - " + inputArg + " | COUNT: " + employeesCount;

        LOG.info("\n\n>>>>> CustomEmployeeRepositoryTwoImpl \n");
        LOG.info("\n\n>>>>> getEmployeesCount: {}\n", resultMessage);

        return resultMessage;
    }
}
```

### EmployeeServiceImpl.java
#### importing CustomEmployeeRepositoryTwo
```java
import com.example.mdbspringboot.repository.CustomEmployeeRepositoryTwo;
```
#### handle CustomEmployeeRepositoryTwo DI
```java
import org.springframework.beans.factory.annotation.Autowired;

// ...

@Autowired
CustomEmployeeRepositoryTwo customEmployeeRepositoryTwo;
```


### EmployeeService.java
```java
String getEmployeesCount(String inputArg);

// here we are using MongoTemplate based repository
Employee findById(String id);
```


### EmployeeServiceImpl.java
```java
public String getEmployeesCount(String inputArg) {
    var employeesCount = customEmployeeRepositoryTwo.getEmployeesCount(inputArg);
    var resultMessage = "SERVICE: " + employeesCount;

    LOG.info("\n\n>>>>> EmployeeServiceImpl \n");
    LOG.info("\n\n>>>>> getEmployeesCount: {}\n", resultMessage);
    
    return resultMessage;
}

// here we are using MongoTemplate based repository
public Employee findById(String id) {
    return customEmployeeRepositoryTwo.findById(id);
}
```



### src\test\java\com\example\mdbspringboot\services\EmployeeServiceSpyTest.java
```java
package com.example.mdbspringboot.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.mdbspringboot.repository.CustomEmployeeRepositoryTwo;
import com.example.mdbspringboot.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@ActiveProfiles("it")
public class EmployeeServiceSpyTest {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    EmployeeService employeeService;

    @SpyBean
    private EmployeeRepository employeeRepository;

    @SpyBean
    private CustomEmployeeRepositoryTwo customEmployeeRepositoryTwo;

    @DisplayName("getEmployeesCount - spying, but without exploiting it | GIVEN ... " +
            "SHOULD ...")
    @Test
    void getEmployeesCount_x1() {
        String message = employeeService.getEmployeesCount("foobar");
        LOG.info("\n\n::::: getEmployeesCount_x1: {}\n", message);

        assertTrue(message.startsWith("SERVICE: REPOSITORY"));
    }

    @DisplayName("getEmployeesCount - spying and exploiting it | GIVEN ... " +
            "SHOULD ...")
    @Test
    void getEmployeesCount_x2() {
        String mockMessage = "mock message";

        Mockito.when(customEmployeeRepositoryTwo.getEmployeesCount("foobar")).thenReturn(mockMessage);

        String message = employeeService.getEmployeesCount("foobar");
        LOG.info("\n\n::::: getEmployeesCount_x2: {}\n", message);

        String expected = "SERVICE: " + mockMessage;

        assertEquals(expected, message);
    }

    // use Mockito.spy() to mock the same class you are testing
    @DisplayName("getEmployeesCount - spying the same class we are testing | GIVEN ... " +
            "SHOULD ...")
    @Test
    void getEmployeesCount_x3() {
        EmployeeService empService = Mockito.spy(employeeService);

        String expected = "FOO";

        Mockito.doReturn(expected).when(empService).getEmployeesCount("foobar");

        String actual = empService.getEmployeesCount("foobar");
        LOG.info("\n\n::::: getEmployeesCount_x3: {}\n", actual);

        assertEquals(expected, actual);
    }
}
```

### how to test private methods
### EmployeeServiceImpl.java
```java
private String getEmployeesCountPrivate(String inputArg) {
    var employeesCount = customEmployeeRepositoryTwo.getEmployeesCount(inputArg);
    var resultMessage = "SERVICE: " + employeesCount;

    LOG.info("\n\n>>>>> EmployeeServiceImpl \n");
    LOG.info("\n\n>>>>> getEmployeesCountPrivate: {}\n", resultMessage);
    
    return resultMessage;
}
```

### src\test\java\com\example\mdbspringboot\services\EmployeeServiceReflectionTestUtilsTest.java
```java
package com.example.mdbspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// ReflectionTestUtils
// - used to set the non-public fields, invoke non-public methods, and inject dependencies

@SpringBootTest
@ActiveProfiles("it")
public class EmployeeServiceReflectionTestUtilsTest {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    EmployeeService employeeService;

    @Test
    public void getEmployeesCountPrivate_x1() {

        var result = ReflectionTestUtils.invokeMethod(employeeService, "getEmployeesCountPrivate", "Lorem");
        LOG.info("\n\n::::: getEmployeesCountPrivate_x1: {}\n", result);

        assertTrue(result.toString().startsWith("SERVICE: REPOSITORY - Lorem | COUNT:"));
    }
}
```

### src\test\java\com\example\mdbspringboot\services\EmployeeServiceArgumentCaptureTest.java
```java
package com.example.mdbspringboot.services;

import com.example.mdbspringboot.repository.CustomEmployeeRepositoryTwo;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("it")
public class EmployeeServiceArgumentCaptureTest {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeeService employeeService;

    // Spring Boot's Annotation (different from Mockito's @Mock Annotation)
    // The mock will replace any existing bean of the same type in the application context
    @MockBean
    private CustomEmployeeRepositoryTwo customEmployeeRepositoryTwo;

    @Captor
    ArgumentCaptor<String> argCaptor;

    @Test
    public void getEmployeesCount_x1() {
        employeeService.getEmployeesCount("This is an argument");

        Mockito.verify(customEmployeeRepositoryTwo).getEmployeesCount(argCaptor.capture());

        String capturedArg = argCaptor.getValue();

        assertEquals("This is an argument", capturedArg);
    }
}
```

### throw an exception if an employee not found 
### create a package called com.example.mdbspringboot.exception
### create a file: exception\EmployeeNotFoundException.java
```java
package com.example.mdbspringboot.exception;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
```
### EmployeeService - change getById and findById methods signature
```java
import com.example.mdbspringboot.exception.EmployeeNotFoundException;

// ...

Employee getById(String employeeId) throws EmployeeNotFoundException;

// here we are using MongoTemplate based repository
Employee findById(String id) throws EmployeeNotFoundException;
```
#### EmployeeServiceImpl - refactor getById and findById methods
```java
import com.example.mdbspringboot.exception.EmployeeNotFoundException;

// ...

public Employee getById(String employeeId) throws EmployeeNotFoundException {
    return employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EmployeeNotFoundException("employee not found with id: " + employeeId));
}

// here we are using MongoTemplate based repository
public Employee findById(String id) throws EmployeeNotFoundException {
    Employee employee = customEmployeeRepositoryTwo.findById(id);

    if (Objects.nonNull(employee)) {
        return employee;
    } else {
        throw new EmployeeNotFoundException("employee not found with id : " + id);
    }
}
```

### EmployeeServiceMockTest - refactor getById test
```java
import org.junit.jupiter.api.Assertions;

// ...

@DisplayName("getById 1 | GIVEN ...  SHOULD ...")
@Test
void getById_x1() {
    String employeeId = "alex123";

    when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(emp1));

    var employee = Assertions.assertDoesNotThrow(() -> {
        return employeeService.getById(employeeId);
    });

    assertTrue(Objects.nonNull(employee));
    assertEquals(employeeId, employee.getId());
    assertTrue(employee.getFirstName().equals("Alex"));
    assertEquals(3, employee.getProjects2().size());
}
```

### EmployeeRepository - add: findEmployeeByEmail
```java
import java.util.Optional;

// queries from method names
Optional<Employee> findEmployeeByEmail(String email);
```

### getByEmail - EmployeeService
```java
Employee getByEmail(String email) throws EmployeeNotFoundException;
```


### getByEmail - EmployeeServiceImpl
```java
@Override
public Employee getByEmail(String email) throws EmployeeNotFoundException {
    return employeeRepository.findEmployeeByEmail(email)
            .orElseThrow(() -> new EmployeeNotFoundException("employee not found with email: " + email));
}
```

### testing exceptions
### src/test/java/com/example/mdbspringboot/services/EmployeeServiceExceptionTest.java
```java
package com.example.mdbspringboot.services;

import com.example.mdbspringboot.exception.EmployeeNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("it")
public class EmployeeServiceExceptionTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    @DisplayName("Should throw an Exception if an employee does not exist")
    void findEmployeeByEmail_x1() {
        String email = "foo@bar.com";

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getByEmail(email);
        });

        EmployeeNotFoundException thrown = Assertions.assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.getByEmail(email));

        Assertions.assertEquals("employee not found with email: foo@bar.com", thrown.getMessage());
    }

    @Test
    @DisplayName("Should not throw an Exception if an employee does exist")
    public void findEmployeeByEmail_x2() {
        String email = "john@smith.com";

        var employee = Assertions.assertDoesNotThrow(() -> {
            return employeeService.getByEmail(email);
        });

        Assertions.assertEquals(email, employee.getEmail());
    }
}
```


### src/test/java/com/example/mdbspringboot/services/EmployeeServiceRepeatedTest.java
```java
package com.example.mdbspringboot.services;

import com.example.mdbspringboot.exception.EmployeeNotFoundException;
import com.example.mdbspringboot.model.Employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@ActiveProfiles("it")
public class EmployeeServiceRepeatedTest {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    EmployeeService employeeService;

    @DisplayName("Should repeat 5 times")
    @RepeatedTest(value = 5, name = "Repeating getting employee {currentRepetition} of {totalRepetitions}")
    void findEmployeeByEmail_x1(RepetitionInfo repetitionInfo) throws EmployeeNotFoundException {
        String email = "john@smith.com";

        Employee employee = employeeService.getByEmail(email);

        Assertions.assertEquals(email, employee.getEmail());

        var currentRepetition = repetitionInfo.getCurrentRepetition();

        LOG.info("\n\n>>>>> repetitionInfo: {}\n", currentRepetition);
    }
}
```

### create file src/test/resources/employees-emails.csv
```csv
john@smith.com, 
mrobertelli3@tinyurl.com, 
shakonsen7@dyndns.org
```

### src/test/java/com/example/mdbspringboot/services/EmployeeServiceParameterizedTest.java
```java
package com.example.mdbspringboot.services;

import java.util.Arrays;
import java.util.List;

import com.example.mdbspringboot.exception.EmployeeNotFoundException;
import com.example.mdbspringboot.model.Employee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("it")
public class EmployeeServiceParameterizedTest {

    @Autowired
    EmployeeService employeeService;

    @ParameterizedTest
    @DisplayName("Should ...")
    @ValueSource(strings = { "john@smith.com", "mrobertelli3@tinyurl.com", "shakonsen7@dyndns.org" })
    void findEmployeeByEmail_x1(String email) throws EmployeeNotFoundException {
        Employee employee = employeeService.getByEmail(email);
        Assertions.assertEquals(email, employee.getEmail());
    }

    @ParameterizedTest
    @DisplayName("Should ... (CSV Source Case)")
    @CsvSource({ "john@smith.com", "mrobertelli3@tinyurl.com", "shakonsen7@dyndns.org" })
    public void findEmployeeByEmail_x2(String email) throws EmployeeNotFoundException {
        Employee employee = employeeService.getByEmail(email);
        Assertions.assertEquals(email, employee.getEmail());
    }

    @ParameterizedTest
    @DisplayName("Should ... (CSV File Source Case)")
    @CsvFileSource(resources = "/employees-emails.csv")
    public void findEmployeeByEmail_x3(String email) throws EmployeeNotFoundException {
        Employee employee = employeeService.getByEmail(email);
        Assertions.assertEquals(email, employee.getEmail());
    }

    @ParameterizedTest
    @DisplayName("Should ... (Method Source Case)")
    @MethodSource("employeeEmailList")
    public void findEmployeeByEmail_x4(String email) throws EmployeeNotFoundException {
        Employee employee = employeeService.getByEmail(email);
        Assertions.assertEquals(email, employee.getEmail());
    }

    private static List<String> employeeEmailList() {
        return Arrays.asList("john@smith.com", "mrobertelli3@tinyurl.com", "shakonsen7@dyndns.org");
    }
}
```


### src/test/java/com/example/mdbspringboot/services/EmployeeServiceMiscTest.java
```java
package com.example.mdbspringboot.services;

import com.example.mdbspringboot.exception.EmployeeNotFoundException;
import com.example.mdbspringboot.model.Employee;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("it")
public class EmployeeServiceMiscTest {

    @Autowired
    EmployeeService employeeService;

    @BeforeAll
    public static void setupAll() {
        System.out.println("\n~~~~~ Should Print BEFORE ALL Tests");
    }

    @BeforeEach
    public void setup() {
        System.out.println("\n..... Should Print BEFORE EACH Test");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("\n..... Should Print AFTER EACH Test");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("\n~~~~~ Should Print AFTER ALL Tests");
    }

    @Test
    @DisplayName("Should Run only on LINUX")
    @EnabledOnOs(value = OS.LINUX, disabledReason = "Should Run only on LINUX")
    public void getByEmail_x1() throws EmployeeNotFoundException {
        String email = "john@smith.com";
        Employee employee = employeeService.getByEmail(email);
        Assertions.assertEquals(email, employee.getEmail());
    }

    @Test
    @DisplayName("Should Run only on WINDOWS")
    @EnabledOnOs(value = OS.WINDOWS, disabledReason = "Should Run only on WINDOWS")
    public void getByEmail_x2() throws EmployeeNotFoundException {
        String email = "john@smith.com";
        Employee employee = employeeService.getByEmail(email);
        Assertions.assertEquals(email, employee.getEmail());
    }

    @Nested
    class NestedTests {
        @Test
        @DisplayName("Should Run only on Developer Machine")
        public void getByEmail_x1() throws EmployeeNotFoundException {
            Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));

            String email = "john@smith.com";
            Employee employee = employeeService.getByEmail(email);
            Assertions.assertEquals(email, employee.getEmail());
        }

        @Test
        @DisplayName("Test Should Be Disabled")
        @Disabled
        public void shouldBeDisabled() {
            throw new RuntimeException("Test Should Not be executed");
        }
    }
}
```

### creating custom repository - interface src\main\java\com\example\mdbspringboot\repository\CustomEmployeeRepository.java
```java
package com.example.mdbspringboot.repository;

public interface CustomEmployeeRepository {
    boolean existsByEmail(String email);
}
```

### custom repository implementation - src\main\java\com\example\mdbspringboot\repository\CustomEmployeeRepositoryImpl.java
```java
package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CustomEmployeeRepositoryImpl implements CustomEmployeeRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean existsByEmail(String email) {
        Query query = new Query();

        query.addCriteria(Criteria.where("email").is(email));

        return mongoTemplate.exists(query, Employee.class);
    }
}
```

### make EmployeeRepository extend CustomEmployeeRepository
```java
// public interface EmployeeRepository extends MongoRepository<Employee, String>

public interface EmployeeRepository extends MongoRepository<Employee, String>, CustomEmployeeRepository
```


### existsByEmail - EmployeeService
```java
// here we are using our custom repository
boolean existsByEmail(String email);
```

### existsByEmail - EmployeeServiceImpl
```java
// here we are using our custom repository
public boolean existsByEmail(String email) {
    return employeeRepository.existsByEmail(email);
}
```

### refactoring EmployeeServiceImpl DI
```java
import lombok.RequiredArgsConstructor;

// ...

// @Service
@Service
@RequiredArgsConstructor

// ...

private final EmployeeRepository employeeRepository;

// public EmployeeService(EmployeeRepository employeeRepository) {
//     this.employeeRepository = employeeRepository;
// }
```

### refactoring EmployeeServiceImpl DI - another way
```java
// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
@Service

// ...

// private final EmployeeRepository employeeRepository;
@Autowired
EmployeeRepository employeeRepository;
```


### src\test\java\com\example\mdbspringboot\services\EmployeeServiceMockTwoTest.java => another way when mocking
#### copy the content from EmployeeServiceMockTest and replace these lines
```java
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.mock.mockito.MockBean;

import org.mockito.InjectMocks;
import org.mockito.Mock;

// @Autowired
// private EmployeeService employeeService;

// @MockBean
// private EmployeeRepository employeeRepository;

@InjectMocks // will auto-inject EmployeeService
private EmployeeService employeeService = new EmployeeServiceImpl();

// Mockito's Annotation (a shorthand for the Mockito.mock() method)
// - when used in conjunction with @InjectMocks, it can reduce the amount of setup code significantly
@Mock
private EmployeeRepository employeeRepository;
```

### EmployeeController.java
```java
package com.example.mdbspringboot.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.example.mdbspringboot.dto.EmployeeDTO;
import com.example.mdbspringboot.exception.EmployeeNotFoundException;
import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.services.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // http://localhost:8102/mdb-spring-boot/api/v1/employees/
    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        LOG.info("\n>>>>> Getting all employees.\n");
        return employeeService.getAll();
    }

    @GetMapping("/{employeeId}")
    // @PathVariable - if not provided => 404
    public Employee getEmployeeById(@PathVariable String employeeId) {
        LOG.info("\n>>>>> Getting employee with ID: {}.\n", employeeId);
        try {
            return employeeService.getById(employeeId);
        } catch (EmployeeNotFoundException e) {
            LOG.info("\n>>>>> {}\n", e.getMessage());
            return null;
        }
    }

    // validation example
    @PostMapping("/create")
    // ResponseEntity - to configure the whole HTTP response: status code, headers and body
    // RequestBody - maps the HttpRequest body to a transfer or domain object (automatic deserialization)
    public ResponseEntity<Employee> addEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        LOG.info("\n>>>>> Saving employee.\n");
        return new ResponseEntity<>(employeeService.create(employeeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public Employee update(@RequestBody @Valid EmployeeDTO employeeDTO) {
        LOG.info("\n>>>>> Updating employee.\n");
        return employeeService.update(employeeDTO);
    }

    // ...delete?id=6
    @DeleteMapping("/delete")
    // @RequestParam - if not provided, nothing, no exception
    public String delete(@RequestParam String id) {
        LOG.info("\n>>>>> Deleting employee with ID: {}.\n", id);
        employeeService.delete(id);
        return "Deleted";
    }

    // ...delete-v2/6
    @DeleteMapping("/delete-v2/{id}")
    public String deleteNo2(@PathVariable String id) {
        LOG.info("\n>>>>> Deleting employee with ID: {}.\n", id);
        employeeService.delete(id);
        return "Deleted";
    }

    // ...delete-v3?id=6
    @DeleteMapping("/delete-v3")
    public String deleteNo3(@PathParam("id") String id) {
        LOG.info("\n>>>>> Deleting employee with ID: {}.\n", id);
        employeeService.delete(id);
        return "Deleted";
    }
}
```

### create: src\main\java\com\example\mdbspringboot\other\DemoComponent.java
```java
package com.example.mdbspringboot.other;

import org.springframework.stereotype.Component;

@Component
public class DemoComponent {
    public String dummyMethod() {
        return "DemoComponent - dummyMethod";
    }
}
```

### EmployeeController: add
```java
import com.example.mdbspringboot.other.DemoComponent;
import org.springframework.beans.factory.annotation.Autowired;

// ...

    @Autowired
    DemoComponent demoComponent;


    // add new log to getAllEmployees method
    LOG.info("\n>>>>> {}\n", demoComponent.dummyMethod());
```

### now remove @Component annotation from the DemoComponent.java

### create: src\main\java\com\example\mdbspringboot\config\BeanConfig.java
```java
package com.example.mdbspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.mdbspringboot.other.DemoComponent;

@Configuration
public class BeanConfig {

    @Bean
    public DemoComponent demoComponent() {
        return new DemoComponent();
    }

}
```

### create a file requests.http (at the root level)
#### write a code to test endpoints
```
###
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/

### valid data
POST http://localhost:8102/mdb-spring-boot/api/v1/employees/create
Content-Type: application/json

{
   "firstName":"Tom",
   "lastName":"Gibson",
   "email":"tom@gibson.com",
   "gender":"Male",
   "department":"R&D",
   "projects":[
      "Project 2",
      "Project 10"
   ],
   "projects2":[
      {
         "title":"Test 1",
         "description":"1 Lorem ipsum dolor sit amet"
      },
      {
         "title":"Test 2",
         "description":"2 Lorem ipsum dolor sit amet"
      }
   ],
   "salary":5600,
   "mobile":"123 345 6789"
}


###
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/62b0b347b374da2a812986cf


### invalid data
POST http://localhost:8102/mdb-spring-boot/api/v1/employees/create
Content-Type: application/json

{
  "firstName": "",
  "lastName": null,
  "email": "tomgibson.com",
  "gender": "Male",
  "projects": [],
  "projects2": [],
  "salary": 15600,
  "mobile": "123*345/6789"
}


###
PUT http://localhost:8102/mdb-spring-boot/api/v1/employees/ HTTP/1.1
content-type: application/json

{
   "id":"62b0b347b374da2a812986cf",
   "firstName":"Tom",
   "lastName":"Gibson",
   "email":"tom@gibson.com",
   "gender":"Male",
   "department":"IT",
   "projects":[
      "Project 2",
      "Project 5",
      "Project 14"

   ],
   "projects2":[
      {
         "id":null,
         "title":"Test 1",
         "description":"1 Lorem ipsum dolor sit amet"
      },
      {
         "id":null,
         "title":"Test 2",
         "description":"2 Lorem ipsum dolor sit amet"
      }
   ],
   "salary":6600.0,
   "mobile":"123 345 6789",
   "created":"2022-06-20T19:49:59.341"
}


###
DELETE http://localhost:8102/mdb-spring-boot/api/v1/employees/delete/?id=62b0b347b374da2a812986cf HTTP/1.1


###
DELETE http://localhost:8102/mdb-spring-boot/api/v1/employees/delete-v2/62b0b508b374da2a812986d0 HTTP/1.1


###
DELETE http://localhost:8102/mdb-spring-boot/api/v1/employees/delete-v3?id=62b0b50db374da2a812986d1 HTTP/1.1

```


### validation exception handling - to return a proper error message
### create a package called com.example.mdbspringboot.advice
### create a file: advice\ApplicationExceptionHandler.java
```java
package com.example.mdbspringboot.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @ExceptionHandler - used to handle specific exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        // sending the custom responses to the client
        return errorMap;
    }
}
```


### EmployeeController - getEmployeeById
```java
@GetMapping("/{employeeId}")
public ResponseEntity<Employee> getEmployeeById(@PathVariable String employeeId) throws EmployeeNotFoundException {
    LOG.info("\n>>>>> Getting employee with ID: {}.\n", employeeId);

    return ResponseEntity.ok(employeeService.getById(employeeId));
}
```
### ApplicationExceptionHandler.java - add EmployeeNotFoundException handler
```java
import com.example.mdbspringboot.exception.EmployeeNotFoundException;

// ...

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@ExceptionHandler(EmployeeNotFoundException.class)
public Map<String, String> handleBusinessException(EmployeeNotFoundException ex) {
    Map<String, String> errorMap = new HashMap<>();
    errorMap.put("errorMessage", ex.getMessage());
    return errorMap;
}
```



### refactoring EmployeeController DI
#### replace this
```java
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // the rest skipped for brevity

}
```
#### with this
```java
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final EmployeeService employeeService;

    // the rest skipped for brevity

}
```


### findEmployeeByEmail - EmployeeController
```java
@GetMapping("/employee-by-email")
public ResponseEntity<Employee> getEmployeeByEmail(@RequestParam(name = "email") String email) throws EmployeeNotFoundException {
    LOG.info("\n>>>>> Getting employee with EMAIL: {}\n", email);

    return ResponseEntity.ok(employeeService.getByEmail(email));
}
```

### requests.http
```
###
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/employee-by-email?email=john@smith.com
```


### refactor EmployeeController to use ResponseEntity ... TODO




### refactoring EmployeeController DI - another way
#### replace this
```java
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final EmployeeService employeeService;

    // the rest skipped for brevity

}
```
#### with this
```java
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    EmployeeService employeeService;

    // the rest skipped for brevity

}
```

### EmployeeService - get paged data
```java
Map<String, Object> getAllPaged(int pageNo, int pageSize, String[] fields, String sortBy);
```

### EmployeeServiceImpl - get paged data
```java
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

// the rest skipped for brevity

public Map<String, Object> getAllPaged(int pageNo, int pageSize, String[] fields, String sortBy) {
    Map<String, Object> response = new HashMap<String, Object>();

    Sort sort = Sort.by(sortBy);
    Pageable page = PageRequest.of(pageNo, pageSize, sort);
    Page<Employee> employeePage = employeeRepository.findAll(page);

    response.put("data", employeePage.getContent());
    response.put("Total no of pages", employeePage.getTotalPages());
    response.put("Total no of elements", employeePage.getTotalElements());
    response.put("Current page no", employeePage.getNumber());

    return response;
}
```


### EmployeeController - get paged data
```java
import java.util.Map;

// the rest skipped for brevity

@GetMapping("/page")
public Map<String, Object> getAllPaged(@RequestParam(name = "pageno",defaultValue = "0") int pageNo,
        @RequestParam(name = "pagesize", defaultValue = "10") int pageSize,
        @RequestParam(name = "fields", defaultValue = "firstName,lastName,salary") String[] fields,
        @RequestParam(name = "sortby", defaultValue = "salary") String sortBy) {
    return employeeService.getAllPaged(pageNo, pageSize, fields, sortBy);
}
```


### requests.http
```
###
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/page?pagesize=20&fields=firstName,lastName,salary&sortby=firstName&pageno=0 HTTP/1.1
```



### EmployeeController
```java
@GetMapping("/projects")
public Map<String, Object> getByProjects(@RequestParam(name = "projects", required = true) String[] projects) {
    return employeeService.getByProjects(projects);
}

// query by Example Executor
@GetMapping("/example")
public List<Employee> getAllByExample(@RequestBody Employee employee) {
    return employeeService.getAllByExample(employee);
}

// query by method names
@GetMapping("/first-name")
public List<Employee> getAllByFirstName(@RequestParam(name = "firstName") String firstName) {
    return employeeService.getAllByFirstNameStartingWith(firstName);
}

// query by method names
@GetMapping("/department")
public List<Employee> getAllByDepartment(@RequestParam(name = "department") String department) {
    return employeeService.getAllByDepartment(department);
}

// using @Query
@GetMapping("/salary")
public List<Employee> getAllBySalaryGTE(@RequestParam(name = "salary") int salary) {
    return employeeService.getAllBySalaryGTE(salary);
}
```

### requests.http
```
###
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/projects?projects=Project 9,Project 10 HTTP/1.1

## not working
### 
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/example HTTP/1.1
content-type: application/json

{

}

### 
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/first-name?firstName=Al HTTP/1.1


### 
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/department?department=IT HTTP/1.1

### 
GET http://localhost:8102/mdb-spring-boot/api/v1/employees/salary?salary=4000 HTTP/1.1
```



### EmployeeController
#### add new method
```java
import org.springframework.web.bind.annotation.PatchMapping;

// here we are using our custom repository
@PatchMapping("/{employeeId}")
public Boolean testMethod(@PathVariable final String employeeId, @RequestBody final Employee employee) {
    return employeeService.existsByEmail(employee.getEmail());
}
```
#### modify getEmployeeById method
```java
@GetMapping("/{employeeId}")
public ResponseEntity<Employee> getEmployeeById(@PathVariable String employeeId) throws EmployeeNotFoundException {
    LOG.info("\n>>>>> Getting employee with ID: {}.\n", employeeId);

    // return ResponseEntity.ok(employeeService.getById(employeeId)); // delete this

    // here we are using MongoTemplate based repository
    return ResponseEntity.ok(employeeService.findById(employeeId));
}
```

### requests.http
```
###
PATCH http://localhost:8102/mdb-spring-boot/api/v1/employees/625c6a85c131622dc1789587
Content-Type: application/json

{
  "id": "625c6a85c131622dc1789587",
  "department": "IT",
  "projects": [
    "Project 2",
    "Project 10"
  ]
}
```




### you can build the JAR file with
```
mvn clean package
```

### run the JAR file
```
java -jar target/mdb-spring-boot-0.0.1-SNAPSHOT.jar
```


### using swagger
### adding dependency in pom.xml
- visit: https://mvnrepository.com/ 
- search: springfox swagger
### pom.xml - add swagger.version to the properties
```xml
<properties>
    <java.version>11</java.version>
    <swagger.version>3.0.0</swagger.version>
</properties>
```
### pom.xml - add the dependency
```xml
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-boot-starter</artifactId>
	<version>${swagger.version}</version>
</dependency>
```
### create a file mdbspringboot\config\SwaggerConfig.java, enable Swagger
```java
package com.example.mdbspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/mdb-spring-boot/api/v1/employees/*"))
                .apis(RequestHandlerSelectors.basePackage("com.example.mdbspringboot"))
                // .paths(PathSelectors.any())
                // .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("MY API")
                .description("This is demo for Swagger")
                .version("1.0.0")
                .build();
    }
}
```

### application.properties - add this:
```
# related to Swagger - change the SpringBoot path matching pattern to AntPathMatcher 
spring.mvc.pathmatch.matching-strategy=ant-path-matcher
```

### visit
- http://localhost:8102/mdb-spring-boot/v2/api-docs
- http://localhost:8102/mdb-spring-boot/swagger-ui/
- http://localhost:8102/mdb-spring-boot/swagger-ui/index.html

### EmployeeController.java - add this annotation above the getEmployeeById method
```java
import io.swagger.annotations.ApiOperation;

// ...

@ApiOperation(value = "getEmployeeById - allowed arguments that this method accepts are: [...]") // Swagger
```

### EmployeeDTO.java
```java
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// ...

// class annotation
@ApiModel(description = "This is Employee data transfer object and ...") // Swagger

// ...

// email field annotation
@ApiModelProperty(notes = "email - no duplicates are allowed") // Swagger
```

### using swagger END

## velocity
### pom.xml: add this dependency
```xml
<!-- Velocity dependencies -->
<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>velocity-spring-boot-starter</artifactId>
    <version>1.0.4.RELEASE</version>
</dependency>
```

### application.properties: add this velocity configuration
```yaml
## ======= Velocity configuration =======
spring.velocity.resource-loader-path=classpath:/velocity/views
spring.velocity.suffix=.vm
spring.velocity.layout-url=/layouts/layout-1.vm
spring.velocity.screen-content-key=body_content
## ======= Velocity configuration END =======
```

### inside src\main\resources folder create folders:
- velocity\views\fragments
- velocity\views\layouts
- velocity\views\home


### create a file: \velocity\views\fragments\footer.vm
```html
<div class="footer-container">
    <h1>FOOTER</h1>
</div>
```


### create a file: \velocity\views\fragments\header.vm
```html
<div class="header-container">
    <h1>HEADER</h1>
    <ul class="menu">
        <li>
            <a href="/mdb-spring-boot/home/">Home</a>
        </li>
        <li>
            <a href="/mdb-spring-boot/home/view-two">View 2</a>
        </li>
        <li>
            <a href="/mdb-spring-boot/home/view-three">View 3</a>
        </li>
    </ul>
</div>
```

### create a file: \velocity\views\home\view-1.vm
```html
<div class="view-1-container">
    <h1>VIEW 1</h1>
    <div>$request.getRequestURI()</div>
</div>
```

### create a file: \velocity\views\home\view-2.vm
```html
<div class="view-2-container">
    <h1>VIEW 2</h1>
    <div>$request.getRequestURI()</div>
</div> 
```


### create a file: \velocity\views\home\view-3.vm
```html
<div class="view-3-container">
    <h1>VIEW 3</h1>
    <div>$request.getRequestURI()</div>
</div>
```

### create a file: \velocity\views\home\view-4.vm
```html
<div class="view-4-container">
    <h1>VIEW 4</h1>
    <div>$request.getRequestURI()</div>
</div>
```

### create a file: \velocity\views\layouts\layout-1.vm
```html
<html>
    <head>
        <style>
            body {
                background-color: LightSkyBlue;
                font-family: "Arial";
            }
        </style>
    </head>
    <body>
        <div>
            <div>
                #parse("/fragments/header.vm")
            </div>

            <!-- see application.properties -->
            $body_content

            <div>
                #parse("/fragments/footer.vm")
            </div>
        </div>
    </body>
</html>
```

### create a file: resources/static/styles/styles.css
```css
.header-container {
    background: #63B175;
    padding: 5px;
}

.menu {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: SteelBlue;
}

.menu li {
    float: left;
}

.menu li a {
    display: block;
    color: white;
    text-align: center;
    padding: 16px;
    text-decoration: none;
}

.menu li a:hover {
    background-color: LightSteelBlue;
    color: black;
}

.footer-container {
    background: #63B175;
    padding: 5px;
}

.view-1-container {
    background-color: crimson;
    color: white;
    padding: 5px;
    margin: 5px auto;
}

.view-2-container {
    background-color: lightpink;
    color: white;
    padding: 5px;
    margin: 5px auto;
}

.view-3-container {
    background-color: SlateGray;
    color: white;
    padding: 5px;
    margin: 5px auto;
}
```

### layout-1.vm - import styles.css file
```
<head>
    <link href="/mdb-spring-boot/styles/styles.css" rel="stylesheet" type="text/css">
```

### create a controller: HomeController.java
```java
package com.example.mdbspringboot.controllers;

import com.alibaba.boot.velocity.annotation.VelocityLayout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/home")
@VelocityLayout("/layouts/layout-1.vm") // default layout page URL for this controller
@RequiredArgsConstructor
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    // http://localhost:8102/mdb-spring-boot/home/view-one
    @RequestMapping(method = RequestMethod.GET, value = { "/", "/view-one" })
    public String method_1() {
        return "home/view-1";
    }

	@RequestMapping(method = RequestMethod.GET, value = "/view-two")
	public String method_2() {
		return "home/view-2";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/view-three")
	public String method_3() {
		return "home/view-3";
	}
}
```

### src/test/java/com/example/mdbspringboot/controllers/HomeControllerTest.java
```java
package com.example.mdbspringboot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("it")
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void method_1_x1() throws Exception {
        // when
        ResultActions resultActions = mockMvc
                .perform(get("/home/view-one"));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/html;charset=UTF-8")))
                .andExpect(content().string(containsString("<div class=\"view-1-container\">")))
                .andReturn();
    }
}
```

### modify method_1() in HomeController.java so that we can use a Model object for passing attributes to the view
```java
import org.springframework.ui.Model;

// ...

// http://localhost:8102/mdb-spring-boot/home/view-one
@RequestMapping(method = RequestMethod.GET, value = { "/", "/view-one" })
public String method_1(Model model) {
    model.addAttribute("pageTitle", "VIEW 1");
    return "home/view-1";
}
```

### modify view-1.vm file so that we use a model attribute sent by the HomeController
#### replace this
```
<h1>VIEW 1</h1>
```
### with this
```
<h1>$!pageTitle</h1>
```

### let's display an info about layout that we use:
#### application.properties: add this to velocity configuration
```
spring.velocity.layout-key=which_layout
```
#### layout-1.vm: 
```
<body>
    <div>
        <div>$!which_layout</div>
```

### create CustomErrorController
```java
package com.example.mdbspringboot.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody // it binds a method return value to the web response body, it is not interpreted as a view name
    String error(HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        String message = exception == null ? "" : exception.getMessage();

        return "<div>" + HttpStatus.valueOf(status) + "</div>" + "<div>" + message + "</div>";
    }
}
```

### create a file: resources/velocity/views/macros/macros.vm
```html
#macro(renderAlertMessage $message)

<div id="alert-div" class="alert alert-message-container closed">
    <div id="alert-msg" class="alert-item"></div>
    <div class="alert-item alert-item-close">
        &times;
    </div>
</div>

#end
```

### add new styles to: resources/static/styles/styles.css
```css
.alert-message-container {
    box-sizing: border-box;
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 48px;
    border-radius: 4px;
    background-color: DodgerBlue;
    color: white;
    padding: 0 10px;
}

.alert-message-container .alert-item {
    font-size: 15px;
}

.alert-message-container .alert-item.alert-item-close {
    cursor: pointer;
}

.alert-message-container.closed {
    display: none;
}
```

### create a file: resources/static/scripts/scripts.js
```js
var messageModule = (function () {
    var alertDiv = document.getElementById("alert-div");
    var messageElement = document.getElementById('alert-msg');
    var closeBtn = document.querySelector(".alert-item-close");

    function addEventListeners() {
        closeBtn.addEventListener("click", function () {
            resetAlertMessage();
        })
    }

    function resetAlertMessage() {
        alertDiv
            .classList
            .add("closed");

        messageElement.innerHTML = "";
    }

    function setMessage(message) {
        messageElement.innerHTML = message;
    }

    function setShowHideClass() {
        alertDiv.classList.remove("closed");
    }

    function init(message) {
        if (alertDiv && message) {
            setMessage(message);
            addEventListeners();
            setShowHideClass();
        }
    }

    return {
        init: init
    };
}());
```

### layout-1.vm - import scripts.js file
```html
    <script src="/mdb-spring-boot/scripts/scripts.js"></script>
</body>
```

### macros.vm: add a script section to the renderAlertMessage macro
```html
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function (event) {
        // note: "$!message"
        messageModule.init("$!message");
    });
</script>
#end
```

#### application.properties: add this to velocity configuration
```
spring.velocity.properties.velocimacro.library=/macros/macros.vm
```

### view-1.vm
```html
<div class="view-1-container">
    #renderAlertMessage()
    <h1>$!pageTitle</h1>
    <div>$request.getRequestURI()</div>
    <button onclick="javascript:messageModule.init('Lorem Ipsum Dolor Sit Amet!')">Show message</button>
</div>
```

### view-2.vm
```html
<div class="view-2-container">
    #renderAlertMessage("View 2 - a special message")
    <h1>VIEW 2</h1>
    <div>$request.getRequestURI()</div>
</div>
```

### view-3.vm
```html
<div class="view-3-container">
    <h1>VIEW 3</h1>
    <div>$request.getRequestURI()</div>

    ## Quiet reference notation
    #set ($test = $null)
    <div>$test</div>
    <div>$!test</div>
    <script type="text/javascript">
        console.log("$test");
        console.log("$!test");
    </script>

    ## Formal notation - with curly braces
    #set ($fruit = "apple")
    <div>$fruit-juice</div>
    <div>${fruit}-juice</div>

    ## Booelan
    #set ($ok = false)
    #if($ok)
        <div>"OK"</div>
    #elseif(!$ok)
        <div>"NOK"</div>
    #end
</div>
```

### HomeController.java
```java
import java.util.List;

import com.example.mdbspringboot.model.Employee;
import com.example.mdbspringboot.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

// ...

@Autowired
EmployeeService employeeService;

// ...

// @RequestMapping(method = RequestMethod.GET, value = "/view-two")
@GetMapping("/view-two") // another syntax
public String method_2(Model model) {
	List<Employee> employees = employeeService.getAllByFirstNameStartingWith("Al");
	model.addAttribute("employees", employees);
	return "home/view-2";
}
```

### view-2.vm: add this to the view-2-container
```html
<table class="employees-table">
    <tr>
        <th>No</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Gender</th>
        <th>Email</th>
        <th>Department</th>
        <th>Salary</th>
    </tr>
    #foreach($x in $employees)
        <tr>
            <td>$foreach.count</td>
            <td>$x.firstName</td>
            <td>$x.lastName</td>
            <td>$x.gender</td>
            <td>$x.email</td>
            <td>$x.department</td>
            <td>$x.salary</td>
        </tr>
    #end
</table>
```

### add new styles to: resources/static/styles/styles.css
```css
.employees-table,
.employees-table td,
.employees-table th {
    border: 1px solid;
}
```

### HomeController.java - mvc controller can return raw (json) data, not a view 
```java
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// ...

// http://localhost:8102/mdb-spring-boot/home/employees-by-department?department=IT
@RequestMapping(path = "/employees-by-department", produces = "application/json; charset=UTF-8")
@ResponseBody // it binds a method return value to the web response body, it is not interpreted as a view name
public List<Employee> getEmployeesByDepartment(@RequestParam(name = "department") String department) {
    return employeeService.getAllByDepartment(department);
}
```

### create HomeController2Test - testing getEmployeesByDepartment method
#### demo for using TestRestTemplate and WebEnvironment.RANDOM_PORT
```java
package com.example.mdbspringboot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;

import com.example.mdbspringboot.model.Employee;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // for restTemplate
@AutoConfigureMockMvc
@ActiveProfiles("it")
public class HomeController2Test {

    @Autowired
    // can also be used as client to send http requests
    private TestRestTemplate restTemplate;

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Test
    public void getEmployeesByDepartment_x1() throws Exception {

        String department = "IT";

        ResponseEntity<Employee[]> response = restTemplate
                .getForEntity(
                        new URL("http://localhost:" + port
                                + "/mdb-spring-boot/home/employees-by-department?department="
                                + department)
                                .toString(),
                        Employee[].class);

        Employee[] employees = response.getBody();
        assertTrue(employees.length > 0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.valueOf("application/json;charset=UTF-8"),
                response.getHeaders().getContentType());

        for (Employee employee : employees) {
            assertTrue(employee.getDepartment().equals(department));
        }
    }
}
```

### including jQuery
### create file velocity\views\common\scripts.vm
```html
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
```

### add this to layout-1.vm
```html
    #parse("common/scripts.vm")
</head>
```

### view-1-vm - add this
```html
<button id="btnGetEmployees">Get employees</button>

<div>
    <ul id="employeesByDepartment"></ul>
</div>

<script>
    $(document).ready(function () {
        $('#btnGetEmployees').click(function () {

            $.getJSON('/mdb-spring-boot/home/employees-by-department?department=IT', function (data) {

                $("ul#employeesByDepartment > li").remove();

                $.each(data, function (key, value) {
                    $("#employeesByDepartment").append(
                        '<li>' + value['firstName'] + " " + value['lastName'] + '</li>'
                    );
                });
            });
        });
    });
</script>
```


### view-2.vm: add this to the view-2-container (bellow the table)
```html
<a href="/mdb-spring-boot/home/employee/edit">Create new</a>
```
### HomeController.java:
```java
import com.example.mdbspringboot.exception.EmployeeNotFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.mdbspringboot.dto.EmployeeDTO;
import java.util.Objects;

// ...

private static final String EMPLOYEE_DTO = "employeeDTO";

@GetMapping(value = "/employee/edit")
public String renderEdit(@RequestParam(name = "id", required = false) String id, Model model,
        final RedirectAttributes redirectAttributes) {
    if (Objects.isNull(id) || id.equals("")) {
        model.addAttribute(EMPLOYEE_DTO, new EmployeeDTO());
        return "home/view-4";
    }

    try {
        var employeeDTO = EmployeeDTO.toEmployeeDTO(employeeService.getById(id));
        model.addAttribute(EMPLOYEE_DTO, employeeDTO);
        return "home/view-4";
    } catch (EmployeeNotFoundException | IllegalArgumentException e) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        // this is redirection to the controller, not returning a view
        return "redirect:/home/view-two";
    }
}
```

### view-4.vm: add this to the view-4-container 
```html
<form class="employee-form" method="post" action="/mdb-spring-boot/home/save">

    <div>
        <label for="firstname">First name:</label>
        <input type="text" id="firstname" name="firstName" value="$!employeeDTO.firstName">
    </div>

    <div>
        <label for="lastname">Last name:</label>
        <input type="text" id="lastname" name="lastName" value="$!employeeDTO.lastName">
    </div>

    <div>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" value="$!employeeDTO.email">
    </div>

    <div>
        <label for="gender">Gender:</label>
        <input type="text" id="gender" name="gender" value="$!employeeDTO.gender">
    </div>

    <div>
        <label for="department">Department:</label>
        <input type="text" id="department" name="department" value="$!employeeDTO.department">
    </div>

    #set ($employeeProjects = "")
    #foreach( $project in $!employeeDTO.projects )
        #if($!employeeProjects == "")
            #set ($employeeProjects = $project)
        #else
            #set ($employeeProjects = $employeeProjects + ', ' + $project)
        #end
    #end

    <div>
        <label for="projects">Projects:</label>
        <input type="text" id="projects" name="projects" value="$!employeeProjects">
    </div>

    <div>
        <label for="salary">Salary:</label>
        <input type="text" id="salary" name="salary" value="$!employeeDTO.salary">
    </div>

    <div>
        <label for="mobile">Mobile:</label>
        <input type="text" id="mobile" name="mobile" value="$!employeeDTO.mobile">
    </div>

    <button type="submit">Save</button>
</form>
```
### add new styles to: resources/static/styles/styles.css
```css
.employee-form {
    width: 300px;
}

.employee-form div {
    display: flex;
    justify-content: space-between;
}
```


### add src\main\java\com\example\mdbspringboot\validation\EmployeeValidator.java
```java
package com.example.mdbspringboot.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.mdbspringboot.dto.EmployeeDTO;

import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Component
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return EmployeeDTO.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        EmployeeDTO employeeDTO = (EmployeeDTO) target;

        var distinctSize = employeeDTO.getProjects().stream()
        .map(x -> x.trim())
        .distinct().collect(Collectors.toList()).size();

        if (distinctSize != employeeDTO.getProjects().size()) {
            errors.reject("", "No duplicate projects are allowed!");
        }
    }
}
```


### HomeController.java: new employee + validation example
```java
import com.example.mdbspringboot.dto.EmployeeDTO;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.mdbspringboot.validation.EmployeeValidator;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;

// ...

// private static final String EMPLOYEE_DTO = "employeeDTO";
private static final String EMPLOYEE_DTO = "employeeDTO";

private final EmployeeValidator employeeValidator; // works with: @RequiredArgsConstructor

// ...

// The value of @InitBinder is the name of model attribute
@InitBinder(value = EMPLOYEE_DTO)
protected void initBinder(WebDataBinder binder) {
    binder.addValidators(employeeValidator);
}

// ...

// validation example
@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public String save(final RedirectAttributes redirectAttributes,
        @Valid @ModelAttribute(EMPLOYEE_DTO) EmployeeDTO employeeDTO, BindingResult result, Model model) {

    if (result.hasErrors()) {
        model.addAttribute("errorMessage", "Failed to save");
        model.addAttribute("validationErrors", result.getAllErrors());
        model.addAttribute(EMPLOYEE_DTO, employeeDTO);

        return "home/view-4"; // returning a view
    }

    employeeService.create(employeeDTO);

    redirectAttributes.addFlashAttribute("successMessage", "employee successfully saved");

    // this is redirection to the controller, not returning a view
    return "redirect:/home/view-two";
}
```

### macros.vm: add new macros
```html
#macro(renderValidationErrors $erros)
    <div class="validation-errors">
        #if($!erros && $erros.size() != 0)
            #foreach($error in $erros)
                <div style="color:red">
                    <span>$!{error.getField()} - $!{error.getDefaultMessage()}</span>
                </div>
            #end
        #end
    </div>
#end

#macro(renderSuccessErrorMessage)
    #if ($!{successMessage})
    <div style="background-color:green;color:white;padding:10px;border-radius: 4px;margin: 5px auto;">
        <strong>${successMessage}</strong>
    </div>
    #end
    #if ($!{errorMessage})
    <div style="background-color:red;color:white;padding:10px;border-radius: 4px;margin: 5px auto;">
        <strong>${errorMessage}</strong>
    </div>
    #end
#end
```


### view-4.vm: add below the form
```html
#renderValidationErrors($validationErrors)
```
### view-4.vm: add above the form
```html
#renderSuccessErrorMessage()
```
### view-2.vm: add above the table
```html
#renderSuccessErrorMessage()
```


### view-4.vm
#### add new form button
```html
<button id="btnAjaxSubmit" type="button">Save (ajax)</button>
```
#### add script section
```html
<script>
    (function ($) {
        $.fn.getFormData = function () {
            let data = {};
            let dataArray = $(this).serializeArray();

            for (let i = 0; i < dataArray.length; i++) {
                data[dataArray[i].name] = dataArray[i].value;
            }

            return data;
        };
    })(jQuery);

    $("#btnAjaxSubmit").on("click", (e) => {
        e.preventDefault();

        var formData = $(".employee-form").getFormData();

        formData.projects = formData.projects ? formData.projects.split(",") : [];

        $.ajax({
            type: "POST",
            url: "/mdb-spring-boot/home/ajax-save",
            data: JSON.stringify(formData),
            contentType: 'application/json', // what type of content we're sending
        }).done(function (data) {
            window.location.href = "/mdb-spring-boot/home/view-two";
        }).fail(function (jqXHR, textStatus, errorThrown) {
            if (jqXHR?.responseText) {
                try {
                    let validationErrors = JSON.parse(jqXHR.responseText);

                    if (validationErrors.length > 0) {
                        $('.validation-errors').empty();
                        for (let error of validationErrors) {
                            let html = `<div style="color:red">
                                            <span>${error.field} - ${error.defaultMessage}</span>
                                        </div>`;
                            $('.validation-errors').append(html);
                        }
                    }
                } catch (e) {
                    console.log('invalid json', jqXHR?.responseText);
                }
            }
        })
    });
</script>
```


### HomeController.java: new employee AJAX + validation
```java
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

// ...

@RequestMapping(value = "/ajax-save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> ajaxSave(final RedirectAttributes redirectAttributes,
        @Valid @RequestBody EmployeeDTO employeeDTO, BindingResult result) {

    if (result.hasErrors()) {
        return ResponseEntity.badRequest().body(result.getAllErrors());
    }

    employeeService.create(employeeDTO);

    redirectAttributes.addFlashAttribute("successMessage", "employee successfully saved");

    return new ResponseEntity<>(HttpStatus.OK);
}
```


### application.properties
```yml
# ***** @Value annotation demo *****
# we will read this from within method_3 of HomeController
mail.from=test@test.com
mail.host=test@test.com
mail.port=25

# array example
numbers1to6=1,2,3,4,5,6 # @Value("${numbers1to6}") private int[] someNumbers
# ***** @Value annotation demo END *****
```

### ### HomeController.java: modify method_3 so that it returns ModelAndView
```java
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Value;
import java.util.Locale;

// ...

// ******* application.properties I *******
// we bind values from the application.properties file to local variables
@Value("${mail.from}")
private String from;
@Value("${mail.host}")
private String host;
@Value("${mail.port}")
private String port;
// ******* application.properties I END *******

// ...

@RequestMapping(method = RequestMethod.GET, value = "/view-three")
public ModelAndView method_3(Locale locale) {
	var mav = new ModelAndView("home/view-3");
    mav.addObject("message", from + " " + host + " " + port);

    logger.info("\n>>>>>> Welcome home! The client locale is {}.", locale);

	return mav;
}
```

### view-3.vm: add this to the view-3-container
```
<div>$message</div>
```

### create: src\main\resources\custom.properties
```yml
# ***** @Value annotation demo *****
# used in HomeController.java with:
# @PropertySource("classpath:custom.properties")
message=loading from external props
```

### HomeController.java: add this
```java
import org.springframework.context.annotation.PropertySource;
//...
@PropertySource("classpath:custom.properties") // new class annotation

// ******* custom.properties *******
// you also need to add @PropertySource on the class
@Value("${message}")
private String customPropertiesMessage;

// method_3
mav.addObject("message", from + " " + host + " " + port + " " + customPropertiesMessage);
```

### create: src\main\java\com\example\mdbspringboot\props\MailProps.java
```java
package com.example.mdbspringboot.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

// here we map fields from application.properties that are prefixed with: mail

@ConfigurationProperties(prefix = "mail")
@Component
@Data
public class MailProps {

    private String from;
    private String host;
    private String port;
}
```

### HomeController.java: add this
```java
import com.example.mdbspringboot.props.MailProps;

// ...

// ******* application.properties II *******
// here we are using DTO class
@Autowired
private MailProps mailProps;
// ******* application.properties II END *******

// ... 

// method-3
mav.addObject("mailProps", mailProps);
```

### view-3.vm: add this to the view-3-container
```
<div>$mailProps.from</div>
<div>$mailProps.host</div>
<div>$mailProps.port</div>
```

### create: src\main\resources\application-dev.properties
```yml
# DEVELOPMENT DB config properties
# together with class DBConfig.java
```
### create: src\main\resources\application-stg.properties
```yml
# STAGE DB config properties
# together with class DBConfig.java
```
### create: src\main\resources\application-prod.properties
```yml
# PROD DB config properties
# together with class DBConfig.java
```

### move (cut - paste) this code from application.properties to application-dev.properties
```yml
### Local MongoDB config
# spring.data.mongodb.authentication-database=admin
# spring.data.mongodb.username=rootuser
# spring.data.mongodb.password=rootpass
# spring.data.mongodb.database=employeesdb
# spring.data.mongodb.port=27017
# spring.data.mongodb.host=localhost

### Local MongoDB config - one-liners
#### with authentication
spring.data.mongodb.uri=mongodb://rootuser:rootpass@127.0.0.1:27017/employeesdb?authSource=admin&ssl=false
#### without authentication
# spring.data.mongodb.uri=mongodb://127.0.0.1:27017/employeesdb?ssl=false
```

### add this to main's application.properties
```yml
# setting the active profile 
# (if we have application -dev, -stg, -prod .properties files)
spring.profiles.active=dev
```

### add this to test's application-it.properties
```yml
### MongoDB config - one-liners
#### with authentication
spring.data.mongodb.uri=mongodb://rootuser:rootpass@127.0.0.1:27017/employeesdb?authSource=admin&ssl=false
#### without authentication
# spring.data.mongodb.uri=mongodb://127.0.0.1:27017/employeesdb?ssl=false
```

### create: src\main\java\com\example\mdbspringboot\config\DBConfig.java
```java
package com.example.mdbspringboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

// used with application -dev, -stg, -prod ... .properties files

@Configuration
@Slf4j
public class DBConfig {

    @Value("${spring.data.mongodb.uri}")
    private String url;

    @Bean
    public String dataSourceProps() {
        log.info("\n\n>>>>> Data Source Connection For: " + url + "\n\n");
        return url;
    }
}
```

### ### HomeController.java: add this
```java
// used in: footer.vm
@ModelAttribute("WikipediaLinkLabel")
public String wikipediaLinkLabel() {
	return "Wikipedia";
}

// used in: footer.vm
@ModelAttribute("WikipediaLink")
public String wikipediaLink() {
	return "https://en.wikipedia.org/wiki/Java_(programming_language)";
}
```

### footer.vm
```html
<div class="footer-container">
    <h1>FOOTER</h1>
    <a href="$!WikipediaLink" target="_blank">$!WikipediaLinkLabel</a>
</div>
```

### HomeController.java: delete wikipediaLinkLabel and wikipediaLink and add this
```java
// used in: footer.vm
@ModelAttribute
public void addAttributes(Model model) {
    model.addAttribute("WikipediaLinkLabel", "Wikipedia");
    model.addAttribute("WikipediaLink", "https://en.wikipedia.org/wiki/Java_(programming_language)");
}
```

### create a new file: resources/velocity/views/layouts/layout-2.vm
```html
<html>
    <head>
        <link href="/mdb-spring-boot/styles/styles.css" rel="stylesheet" type="text/css">
        <style>
            body {
                background-color: lightgreen;
                font-family: arial;
            }
        </style>
    </head>
    <body>
        <div>
            <div>$!which_layout</div>

            #parse("/fragments/header.vm")

            <!-- see application.properties -->
            $body_content
        </div>
    </body>
</html>
```


### HomeController.java: add this decorator above method_3
```java
@VelocityLayout("/layouts/layout-2.vm") // overrides default layout
```
### velocity END


### testing HomeController's addEmployee method
### pom.xml
```xml
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
</dependency>
```

### HomeControllerTest.java
```java
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


@Test
void addEmployee_x1() throws Exception {
    var employee = List.of(
            new BasicNameValuePair("firstName", "Alex"),
            new BasicNameValuePair("lastName", "Moore"),
            new BasicNameValuePair("email", "alex@moore.com"),
            new BasicNameValuePair("gender", "Male"),
            new BasicNameValuePair("department", "IT"),
            new BasicNameValuePair("projects", "['Project 3', 'Project 5', 'Project 6']"),
            new BasicNameValuePair("salary", "6350.0"),
            // an invalid input + no projects2
            new BasicNameValuePair("mobile", "123x345x6789"));

    mockMvc.perform(post("/home/save")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .content(EntityUtils.toString(new UrlEncodedFormEntity(employee))))
            .andExpect(model().hasErrors())
            .andExpect(model().errorCount(2))
            .andExpect(model().attributeExists("validationErrors"))
            .andExpect(view().name("home/view-4"));
}
```

### create file: src\main\java\com\example\mdbspringboot\interceptor\CoreInterceptor.java
```java
package com.example.mdbspringboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CoreInterceptor implements HandlerInterceptor {

    /**
     * to perform some action on requests sent to the controller
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("\n\n %%%%%%%%% Request intercepted\n\n");
        return true;
    }

    /**
     * to perform some action on responses sent to the client like addng attributes
     * to the model
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) {
        log.info("\n\n %%%%%%%%% Response intercepted\n\n");
    }
}
```

### to register the interceptor create file: src\main\java\com\example\mdbspringboot\config\WebConfiguration.java
```java
package com.example.mdbspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.example.mdbspringboot.interceptor.CoreInterceptor;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * All web related config. Serves as replacement for @EnableWebMvc.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public CoreInterceptor coreInterceptor() {
        return new CoreInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(coreInterceptor());
    }
}
```

### create src\main\java\com\example\mdbspringboot\other\EagerLoadingBean.java
```java
package com.example.mdbspringboot.other;

import org.springframework.stereotype.Component;

@Component
public class EagerLoadingBean {

    public EagerLoadingBean() {
        System.out.println("\n\n>>>>> EagerLoadingBean object created ..\n\n");
    }
}
```

### create src\main\java\com\example\mdbspringboot\other\LazyLoadingBean.java
```java
package com.example.mdbspringboot.other;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LazyLoadingBean {

    public LazyLoadingBean() {
        System.out.println("\n\n>>>>> LazyLoadingBean object created ..\n\n");
    }
}
```

### when you start the app you will see that only EagerLoadingBean object is created

### HomeController - add LazyLoadingBean
```java
import com.example.mdbspringboot.other.LazyLoadingBean;

// ...

    @Autowired
    private LazyLoadingBean lazyLoadingBean;
```


























