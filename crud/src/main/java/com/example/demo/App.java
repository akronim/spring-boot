package com.example.demo;

import java.util.Arrays;

import com.example.demo.entities.Employee;
import com.example.demo.repositories.DemoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

	@Autowired
	DemoRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee e1 = new Employee(1, "John", "London");
		Employee e2 = new Employee(2, "Hanna", "Berlin");

		repository.employees.addAll(Arrays.asList(e1, e2));
	}

}
