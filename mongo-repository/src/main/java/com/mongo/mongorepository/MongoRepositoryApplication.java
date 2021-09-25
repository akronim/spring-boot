package com.mongo.mongorepository;

import java.util.ArrayList;
import java.util.List;

import com.mongo.mongorepository.entities.Address;
import com.mongo.mongorepository.entities.Employee;
import com.mongo.mongorepository.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoRepositoryApplication implements CommandLineRunner {

	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(MongoRepositoryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Employee> lEmployees = new ArrayList<Employee>();

		Employee e1 = new Employee("John", "Doe", new Address("Lorem 3", "Ipsum 4", "AA", "BB", 123), 1000);
		Employee e2 = new Employee("Mark", "Smith", new Address("Dolor 3", "Sit 4", "CC", "DD", 456), 1200);
		Employee e3 = new Employee("Antonio", "Brooks", new Address("Lorem 13", "Ipsum 41", "XX", "FF", 789), 2000);
		Employee e4 = new Employee("Jonathan", "Harnden", new Address("Dolor 33", "Sit 43", "YY", "GG", 987), 2200);
		Employee e5 = new Employee("Michelle", "Knight", new Address("Lorem 7", "Ipsum 6", "HH", "NN", 654), 3000);
		Employee e6 = new Employee("Phillip", "Thrasher", new Address("Lorem 23", "Amet 432", "AA", "BB", 321), 1500);
		Employee e7 = new Employee("Sondra", "Garrett", new Address("Dolor 321", "Ipsum 153", "CC", "DD", 112), 4100);
		Employee e8 = new Employee("Richard", "Sergent", new Address("Lorem 43", "Amet 63", "YY", "GG", 223), 2700);
		Employee e9 = new Employee("William", "Eden", new Address("Lorem 16", "Ipsum 543", "CC", "DD", 334), 2900);
		Employee e10 = new Employee("Howard", "Park", new Address("Dolor 94", "Sit 66", "CC", "DD", 445), 4600);
		Employee e11 = new Employee("Nicole", "Owings", new Address("Lorem 82", "Sit 62", "AA", "BB", 556), 3300);
		Employee e12 = new Employee("James", "Barham", new Address("Dolor 15", "Ipsum 71", "RR", "EE", 667), 5000);

		lEmployees.add(e1);
		lEmployees.add(e2);
		lEmployees.add(e3);
		lEmployees.add(e4);
		lEmployees.add(e5);
		lEmployees.add(e6);
		lEmployees.add(e7);
		lEmployees.add(e8);
		lEmployees.add(e9);
		lEmployees.add(e10);
		lEmployees.add(e11);
		lEmployees.add(e12);

		employeeRepository.insert(lEmployees);

	}

}
