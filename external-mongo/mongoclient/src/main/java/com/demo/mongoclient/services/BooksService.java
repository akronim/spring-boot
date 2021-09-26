package com.demo.mongoclient.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.mongoclient.repositories.BooksRepository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksService {
    
    @Autowired
    BooksRepository booksRepository;

    public List<Object> getAllBooks() {
        return booksRepository.getAllBooks();
    }

    public String addBook(Map<String, Object> book) {
        Document doc = new Document(book);
        return booksRepository.add(doc);
    }

    public String update(Map<String, Object> book) {
        Document doc = new Document(book);
        int id = doc.getInteger("_id");

        doc.remove("_id");
        return booksRepository.update(doc, id);
    }

    public String delete(int id) {
        return booksRepository.delete(id);
    }

    public Map<String, Object> getAllPaged(int pageNo, int pageSize, String[] fields, String sortBy) {
        Map<String, Object> response = new HashMap<String, Object>();

        List<Object> books = booksRepository.getAllPaged(pageNo, pageSize, fields, sortBy);

        response.put("data", books);

        long count = booksRepository.getTotalElements();

        response.put("Total no of elements", count);
        response.put("Total no of pages", Math.ceil(count / pageSize));
        response.put("Current page no", pageNo);

        return response;
    }

    public Map<String, Object> countPage() {
        Map<String, Object> response = new HashMap<String, Object>();

        response.put("Total no of pages", booksRepository.countPage());

        return response;
    }

    public Map<String, Object> getByCategory(String[] categories) {
        Map<String, Object> response = new HashMap<String, Object>();

        List<Object> listOfBooks = booksRepository.getByCategory(categories);

        response.put("data", listOfBooks);
        response.put("Total no of books", listOfBooks.size());

        return response;
    }
}
