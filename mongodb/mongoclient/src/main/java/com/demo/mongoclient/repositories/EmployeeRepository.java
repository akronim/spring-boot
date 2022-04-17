package com.demo.mongoclient.repositories;

import java.util.ArrayList;
import java.util.List;

import com.demo.mongoclient.entities.Employee;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

    MongoClient client;

    MongoClient getClient() {
        if (client == null) {
            client = new MongoClient("localhost", 27017);
        }
        return client;
    }

    public List<Employee> getAll() {
        MongoClient mongoClient = getClient();
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("employee");

        List<Employee> employees = new ArrayList<Employee>();

        for (Document e : collection.find()) {
            Employee emp = new Employee(e.get("_id").toString(), e.getString("firstName"), e.getString("lastName"),
                    e.getString("address"));

            employees.add(emp);
        }

        return employees;
    }

    public String create(Employee emp) {
        MongoClient mongoClient = getClient();
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("employee");

        Document emp1 = new Document();
        emp1.append("firstName", emp.getFirstName());
        emp1.append("lastName", emp.getLastName());
        emp1.append("address", emp.getAddress());

        String response;
        try {
            collection.insertOne(emp1);
            response = "Successfully added";
        } catch (Exception e) {
            response = "Something went wrong. Insertion failed.";
        }

        return response;
    }

    public String edit(Employee emp) {
        MongoClient mongoClient = getClient();
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("employee");

        Document emp1 = new Document();
        emp1.append("firstName", emp.getFirstName());
        emp1.append("lastName", emp.getLastName());
        emp1.append("address", emp.getAddress());

        String response;
        try {
            BasicDBObject filter = new BasicDBObject("_id", new ObjectId(emp.getId()));
            collection.updateOne(filter, new BasicDBObject("$set", emp1));
            response = "Successfully updated";
        } catch (Exception e) {
            response = "Something went wrong. Update failed.";
        }

        return response;
    }

    public String delete(String id) {
        MongoClient mongoClient = getClient();
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("employee");

        String response;
        try {
            BasicDBObject filter = new BasicDBObject("_id", new ObjectId(id));
            collection.deleteOne(filter);
            response = "Successfully deleted";
        } catch (Exception e) {
            response = "Something went wrong. Delete failed.";
        }

        return response;
    }
}
