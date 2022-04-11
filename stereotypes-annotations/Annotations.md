# https://github.com/Java-Techie-jt/annotations-cheatsheet

### What is Spring Bean or Components?
During Application startup, Spring instantiates objects and adds them to the Application Context. These objects in the Application Context are called ‘Spring Beans’ or ‘Spring Components’. 

### IoC container
A container that injects dependencies while creating the bean.


### Core Spring Framework Annotations
#### @Required
It applies to the bean setter method. It indicates that the affected bean must be populated at configuration time with the required property.

#### @Autowired
This annotation is used to auto-wire spring bean on setter methods, constructor and instance variable. It injects object dependency implicitly. When we use this annotation, the spring container auto-wires the bean by its matching data type.

#### @Configuration
The class annotated with @Configuration is used by Spring Containers as a source of bean definitions.

#### @ComponentScan
A way to identify Spring bean candidates. It is used to scan a package of beans. It is used with the annotation @Configuration to allow Spring to know the packages to be scanned for annotated components. This annotation is also used to specify base packages.

#### @Bean
We use @Bean at method level. It creates Spring beans. Is is used with @Configuration.

#### @Lazy
This annotation is used on component classes. By default all autowired dependencies are created and configured at startup. But if you want to initialize a bean lazily, you can use @Lazy annotation over the class. This means that the bean will be created and initialized only when it is first requested for. 

#### @Value
We can use this annotation to inject values into fields of Spring managed beans. We can apply it at field or constructor or method parameter level.







### Stereotype Annotations
#### @Component
It is a class-level annotation. It is used to mark a Java class as a bean during the component scanning. @Repository, @Service, and @Controller are specializations of @Component.

#### @Service

#### @Repository

### @Controller
@Controller is a specialized @Component marked as a controller in MVC architecture.
It is often used to serve web pages. By default, it returns a string that indicates which route to redirect. It is mostly used with @RequestMapping annotation.








### Spring Boot Annotations
#### @SpringBootApplication
It is a combination of three annotations @EnableAutoConfiguration, @ComponentScan, and @Configuration.

#### @EnableAutoConfiguration
It is placed on the main application class. Based on classpath settings, other beans, and various property settings, this annotation instructs SpringBoot to start adding beans.






### Spring MVC and REST Annotations
### @Controller
@Controller is a specialized @Component marked as a controller in MVC architecture.
It is often used to serve web pages. By default, it returns a string that indicates which route to redirect. It is mostly used with @RequestMapping annotation.

### @RestController
@RestController combines the @Controller and @ResponseBody into a single annotation. @RestController classes return domains instead of views.

#### @RequestMapping
It is used to map the web requests. It has many optional elements like consumes, header, method, name, params, path, produces, and value. We use it with classes as well as with methods.

#### @GetMapping 
It maps the HTTP GET requests on the specific handler method. It is used instead of: @RequestMapping(method = RequestMethod.GET).

#### @PostMapping 

#### @PutMapping 

#### @DeleteMapping 

#### @PatchMapping 

#### @RequestBody
It is used to bind HTTP request with an object in a method parameter. Internally it uses HTTP MessageConverters to convert the body of the request. When we annotate a method parameter with @RequestBody, the Spring framework binds the incoming HTTP request body to that parameter.

#### @ResponseBody
It binds the method return value to the response body. It tells the Spring Boot Framework to serialize a return an object into JSON and XML format.

#### @PathVariable 
It is used to extract the values from the URI. It is most suitable for the RESTful web service, where the URL contains a path variable. We can define multiple @PathVariable in a method.

#### @RequestParam 
It is used to extract the query parameters form the URL. It is also known as a query parameter. It is most suitable for web applications. It can specify default values if the query parameter is not present in the URL.

#### @RequestHeader 
It is used to get the details about the HTTP request headers. We use this annotation as a method parameter. The optional elements of the annotation are name, required, value, defaultValue. For each detail in the header, we should specify separate annotations. We can use it multiple time in a method.

#### @RequestAttribute
It binds a method parameter to request attribute. It provides convenient access to the request attributes from a controller method. With the help of @RequestAttribute annotation, we can access objects that are populated on the server-side.

#### @InitBinder





### Spring Framework Testing Annotations
#### @SpringBootTest
This annotation is used to start the Spring context for integration tests. This will bring up the full autoconfigruation context.

#### @BootstrapWith
A class level annotation.

#### @MockBean
The @MockBean annotation allows you to create a temporary version of a service for testing. 

#### @ContextConfiguration

#### @SpringApplicationConfiguration
It provides full Spring Boot treatment to your test classes e.g. it not only loads the beans in the Spring application context but also enables logging and loads properties from application.properties file.

#### @WebAppConfiguration
#### @Timed
#### @Repeat
#### @Commit
#### @RollBack
#### @DirtiesContext
#### @BeforeTransaction
#### @AfterTransaction
#### @Sql
#### @SqlConfig
#### @SqlGroup
#### @DataJpaTest
#### @DataMongoTest
#### @WebMVCTest
#### @AutoConfigureMockMVC
#### @JsonTest
#### @TestPropertySource



#### @Validated






### Spring Cloud Annotations
#### @EnableConfigServer
#### @EnableEurekaServer
#### @EnableDiscoveryClient
#### @EnableCircuitBreaker
#### @HystrixCommand




### Spring Framework DataAccess Annotations
#### @Transactional






### Cache-Based Annotations
#### @Cacheable
#### @CachePut
#### @CacheEvict
#### @CacheConfig




### Task Execution and Scheduling Annotations
#### @Scheduled
#### @Async














#### mvn spring-boot:run