package com.example.mongorepository.repositories;

import java.util.List;

import com.example.mongorepository.entities.Book;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends MongoRepository<Book, String> {

    @Query(value = "{ 'categories': { $elemMatch : { $in: ?0 } } }", fields = "{'title': 1}")
    public List<Book> getBy(String[] categories);

    // query by method names
    public List<Book> findByTitleStartingWith(String title);

    public List<Book> findByAuthors(String[] authors);

    // using @Query
    @Query(value = "{'pageCount':{$gte:?0}}", fields = "{'pageCount':1, 'title':1, '_id':0}")
    public List<Book> getAllByPageCountGTE(int pageCount);
}

// spring.jackson.default-property-inclusion=non-null