package com.demo.mongoclient.repositories;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.springframework.stereotype.Repository;

@Repository
public class BooksRepository {

    MongoClient mongoClient;

    MongoClient getMongoClient() {
        if (mongoClient == null) {
            // ServerAddress server = new ServerAddress("localhost", 27017);
            // MongoCredential credentials = MongoCredential.createCredential("rootuser", "admin",
            //         "rootpass".toCharArray());
            // mongoClient = new MongoClient(server, credentials, MongoClientOptions.builder().build());

            mongoClient = new MongoClient("localhost", 27017);
        }

        return mongoClient;
    }

    public List<Object> getAllBooks() {

        MongoClient mongoClient = getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("bookdb");
        MongoCollection<Document> collection = database.getCollection("books");

        FindIterable<Document> findIterable = collection.find();

        List<Object> booksResponse = new ArrayList<Object>();

        for (Document doc : findIterable) {
            booksResponse.add(doc);
        }

        return booksResponse;
    }

    public String add(Document doc) {
        MongoClient mongoClient = getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("bookdb");
        MongoCollection<Document> collection = database.getCollection("books");

        try {
            collection.insertOne(doc);
            return "Successfully inserted!";
        } catch (Exception e) {

        }

        return "Not inserted, Please try again.";
    }

    public String update(Document doc, int id) {
        MongoClient mongoClient = getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("bookdb");
        MongoCollection<Document> collection = database.getCollection("books");

        String response;
        try {
            BasicDBObject filter = new BasicDBObject("_id", id);
            BasicDBObject update = new BasicDBObject("$set", doc);

            collection.updateOne(filter, update);

            response = "Successfully updated";
        } catch (Exception e) {
            response = "Something went wrong. Update failed.";
        }

        return response;
    }

    public String delete(int id) {
        MongoClient mongoClient = getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("bookdb");
        MongoCollection<Document> collection = database.getCollection("books");

        String response;
        try {
            BasicDBObject filter = new BasicDBObject("_id", id);
            collection.deleteOne(filter);

            response = "Successfully deleted";
        } catch (Exception e) {
            response = "Something went wrong. Delete failed.";
        }

        return response;
    }

    public List<Object> getAllPaged(int pageNo, int pageSize, String[] fields, String sortBy) {
        MongoClient mongoClient = getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("bookdb");
        MongoCollection<Document> collection = database.getCollection("books");

        BasicDBObject projection = new BasicDBObject();
        for (String field : fields) {
            projection.append(field, 1);
        }

        BasicDBObject sort = new BasicDBObject(sortBy, 1);

        FindIterable<Document> findIterable = collection.find().projection(projection).sort(sort)
                .skip(pageNo * pageSize).limit(pageSize);

        List<Object> booksResponse = new ArrayList<Object>();

        for (Document doc : findIterable) {
            booksResponse.add(doc);
        }

        return booksResponse;

    }

    public long getTotalElements() {
        MongoClient mongoClient = getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("bookdb");
        MongoCollection<Document> collection = database.getCollection("books");

        return collection.countDocuments();
    }

    public String countPage() {
        MongoClient mongoClient = getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("bookdb");
        MongoCollection<Document> collection = database.getCollection("books");

        BasicDBObject sum = new BasicDBObject("$sum", "$pageCount");

        BasicDBObject group = new BasicDBObject();
        group.append("_id", null);
        group.append("count", sum);

        BasicDBObject _group = new BasicDBObject("$group", group);
        System.out.println(_group.toJson());

        List<BasicDBObject> pipeline = new ArrayList<BasicDBObject>();
        pipeline.add(_group);
        AggregateIterable<Document> aggregateIterable = collection.aggregate(pipeline);

        return aggregateIterable.first().get("count").toString();
    }

    public List<Object> getByCategory(String[] categories) {
        MongoClient mongoClient = getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("bookdb");
        MongoCollection<Document> collection = database.getCollection("books");

        BasicDBObject in = new BasicDBObject("$in", categories);

        BasicDBObject elemMatch = new BasicDBObject("$elemMatch", in);

        BasicDBObject _categories = new BasicDBObject("categories", elemMatch);

        BasicDBObject projection = new BasicDBObject();

        projection.append("title", 1);
        projection.append("_id", 0);

        FindIterable<Document> result = collection.find(_categories).projection(projection);

        List<Object> response = new ArrayList<Object>();

        for (Document doc : result) {
            response.add(doc);
        }

        return response;
    }

}
