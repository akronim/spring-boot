package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringInitializerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringInitializerDemoApplication.class, args);
	}

	// # Runners
	// 1
	// ... implements CommandLineRunner, ApplicationRunner
	// @Override
	// public void run(String... args) throws Exception {
	// System.out.println("Hello from CommandLineRunner");
	// }

	// @Override
	// public void run(ApplicationArguments args) throws Exception {
	// System.out.println("Hello from ApplicationRunner");
	// }

	// 2
	// @Bean
	// public Runner getRunner() {
	// return new Runner();
	// }

	// 3, where class Runner is marked as @Component
}
