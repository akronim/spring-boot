package com.demo.mongoclient.controllers;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import com.demo.mongoclient.services.BooksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.bson.Document;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    BooksService booksService;

    @GetMapping("/")
    public List<Document> getAllBooks() {
        return booksService.getAllBooks();
    }

    @PostMapping("/")
    public Document addBook(@RequestBody Map<String, Object> book) {
        return booksService.addBook(book);
    }

    @PutMapping("/")
    public String update(@RequestBody Map<String, Object> book) {
        return booksService.update(book);
    }

    // ...delete?id=6
    @DeleteMapping("/delete")
    public String delete(@RequestParam int id) {
        return booksService.delete(id);
    }

    // ...delete-v2/6
    @DeleteMapping("/delete-v2/{id}")
    public String deleteNo2(@PathVariable int id) {
        return booksService.delete(id);
    }

    // ...delete-v3?id=6
    @DeleteMapping("/delete-v3")
    public String deleteNo3(@PathParam("id") int id) {
        return booksService.delete(id);
    }

    @GetMapping("/page")
    public Map<String, Object> getAllPaged(@RequestParam(name = "pageno", defaultValue = "0") int pageNo,
            @RequestParam(name = "pagesize", defaultValue = "10") int pageSize,
            @RequestParam(name = "fields", defaultValue = "title,pageCount") String[] fields,
            @RequestParam(name = "sortby", defaultValue = "_id") String sortBy) {
        return booksService.getAllPaged(pageNo, pageSize, fields, sortBy);
    }

    @GetMapping("/countpage")
    public Map<String, Object> countPage() {
        return booksService.countPage();
    }

    @GetMapping("/category")
    public Map<String, Object> getByCategory(@RequestParam(name = "category", required = true) String[] categories) {
        return booksService.getByCategory(categories);
    }
}
