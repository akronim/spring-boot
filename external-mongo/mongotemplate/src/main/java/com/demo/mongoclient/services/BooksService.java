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

    public List<Document> getAllBooks() {
        return booksRepository.getAllBooks();
    }

    public Document addBook(Map<String, Object> book) {
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
        return booksRepository.getAllPaged(pageNo, pageSize, fields, sortBy);
    }

    public Map<String, Object> countPage() {
        return booksRepository.countPage();
    }

    public Map<String, Object> getByCategory(String[] categories) {
        Map<String, Object> response = new HashMap<String, Object>();

        List<Document> listOfBooks = booksRepository.getByCategory(categories);

        response.put("data", listOfBooks);
        response.put("Total no of books", listOfBooks.size());

        return response;
    }
}
