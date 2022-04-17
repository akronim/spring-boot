package com.example.mongorepository.controllers;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import com.example.mongorepository.entities.Book;
import com.example.mongorepository.services.BooksService;

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

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    BooksService booksService;

    @GetMapping("/")
    public List<Book> getAllBooks() {
        return booksService.getAllBooks();
    }

    @PostMapping("/")
    public Book addBook(@RequestBody Book book) {
        return booksService.addBook(book);
    }

    @PutMapping("/")
    public Book update(@RequestBody Book book) {
        return booksService.update(book);
    }

    // ...delete?id=6
    @DeleteMapping("/delete")
    public String delete(@RequestParam String id) {
        booksService.delete(id);
        return "Deleted";
    }

    // ...delete-v2/6
    @DeleteMapping("/delete-v2/{id}")
    public String deleteNo2(@PathVariable String id) {
        booksService.delete(id);
        return "Deleted";
    }

    // ...delete-v3?id=6
    @DeleteMapping("/delete-v3")
    public String deleteNo3(@PathParam("id") String id) {
        booksService.delete(id);
        return "Deleted";
    }

    @GetMapping("/page")
    public Map<String, Object> getAllPaged(@RequestParam(name = "pageno", defaultValue = "0") int pageNo,
            @RequestParam(name = "pagesize", defaultValue = "10") int pageSize,
            @RequestParam(name = "fields", defaultValue = "title,pageCount") String[] fields,
            @RequestParam(name = "sortby", defaultValue = "_id") String sortBy) {
        return booksService.getAllPaged(pageNo, pageSize, fields, sortBy);
    }

    @GetMapping("/category")
    public Map<String, Object> getByCategory(@RequestParam(name = "category", required = true) String[] categories) {
        return booksService.getByCategory(categories);
    }

    // query by Example Executor
    @GetMapping("/example")
    public List<Book> getAllByExample(@RequestBody Book book) {
        return booksService.getAllByExample(book);
    }

    // query by method names
    @GetMapping("/title")
    public List<Book> getAllByFirstName(@RequestParam(name = "title") String title) {
        return booksService.getAllByFirstName(title);
    }

    // query by method names
    @GetMapping("/authors")
    public List<Book> getAllByAuthor(@RequestParam(name = "authors") String[] authors) {
        return booksService.getAllByAuthor(authors);
    }

    // using @Query
    @GetMapping("/page-count")
    public List<Book> getAllByPageCountGTE(@RequestParam(name = "pageCount") int pageCount) {
        return booksService.getAllByPageCountGTE(pageCount);
    }
}
