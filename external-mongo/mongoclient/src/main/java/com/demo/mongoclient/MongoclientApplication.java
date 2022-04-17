package com.demo.mongoclient;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoclientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MongoclientApplication.class, args);
	}

	public MongoClient getClient() {
		return new MongoClient("localhost", 27017);
	}

	@Override
	public void run(String... args) throws Exception {
		MongoClient mongoClient = getClient();
		MongoDatabase database = mongoClient.getDatabase("test");
		MongoCollection<Document> employeeCollection = database.getCollection("employee");

		Document employee1 = new Document();
		employee1.append("firstName", "John");
		employee1.append("lastName", "Doe");
		employee1.append("address", "London");

		employeeCollection.insertOne(employee1);
	}
}
