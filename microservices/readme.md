




### zipkin

#### download zipkin jar
https://zipkin.io/pages/quickstart.html


#### start the zipkin server
$ java -jar zipkin-server-2.23.15-exec.jar 

#### add dependencies
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-sleuth</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-zipkin</artifactId>
		</dependency>

#### copy the zipkin server url and open it in browser (http://127.0.0.1:9411/)
