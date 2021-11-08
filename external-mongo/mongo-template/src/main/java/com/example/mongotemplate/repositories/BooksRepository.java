package com.example.mongotemplate.repositories;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BooksRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Document> getAllBooks() {
        log.info("================= get all");
        return mongoTemplate.findAll(Document.class, "books");
    }

    public Document add(Document doc) {
        return mongoTemplate.insert(doc, "books");
    }

    public String update(Document doc, int id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new BasicUpdate(doc);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, "books");

        return "Updated documents " + updateResult.getModifiedCount();
    }

    // id :: int or String?
    public String delete(int id) {
        Query query = new Query(Criteria.where("_id").is(id));
        DeleteResult deleteResult = mongoTemplate.remove(query, "books");

        String response;
        if (deleteResult.getDeletedCount() > 0) {
            response = "Successfully deleted";
        } else {
            response = "Something went wrong. Delete failed.";
        }

        return response;
    }

    public Map<String, Object> getAllPaged(int pageNo, int pageSize, String[] fields, String sortBy) {
        Query query = new Query();

        for (String field : fields) {
            query.fields().include(field);
        }

        
        Sort sort = Sort.by(sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        query.with(pageable);
        
        List<Document> books = mongoTemplate.find(query, Document.class, "books");

        Page<Document> docPage = PageableExecutionUtils.getPage(books, pageable,
                () -> mongoTemplate.count(query, "books"));
        
        Map<String, Object> response = new HashMap<String, Object>();

        response.put("data", docPage.getContent());
        response.put("Total No of Pages", docPage.getTotalPages());
        response.put("Current Page", pageNo);

        return response;
    }

    public Map<String, Object> countPage() {
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.group().sum("$pageCount").as("count"));
        return mongoTemplate.aggregate(aggregation, "books", Document.class).getRawResults();
    }

    public List<Document> getByCategory(String[] categories) {
        Query query = new Query();
        query.fields().include("title").exclude("_id");
        query.addCriteria(Criteria.where("categories").in(Arrays.asList(categories)));

        return mongoTemplate.find(query, Document.class, "books");
    }
}
