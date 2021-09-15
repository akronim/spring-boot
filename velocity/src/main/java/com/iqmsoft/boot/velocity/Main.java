package com.iqmsoft.boot.velocity;

import java.util.Arrays;

import com.iqmsoft.boot.velocity.dto.User;
import com.iqmsoft.boot.velocity.enums.Gender;
import com.iqmsoft.boot.velocity.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
	UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		User user = new User("John", "Doe", Gender.M, "test", "test123", "ADMIN");

        repository.users.addAll(Arrays.asList(user));
	}
}
