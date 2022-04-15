package com.example.mongorepository.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mongorepository.entities.Book;
import com.example.mongorepository.repositories.BooksRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BooksService {
    
    @Autowired
    BooksRepository booksRepository;

    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    public Book addBook(Book book) {
        return booksRepository.insert(book);
    }

    public Book update(Book book) {
        if (booksRepository.existsById(book.get_id())) {
            return booksRepository.save(book);
        }
        return book;
    }

    public void delete(String id) {
        booksRepository.deleteById(id);
    }

    public Map<String, Object> getAllPaged(int pageNo, int pageSize, String[] fields, String sortBy) {
        Map<String, Object> response = new HashMap<String, Object>();

        Sort sort = Sort.by(sortBy);
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<Book> bookPage = booksRepository.findAll(page);

        response.put("data", bookPage.getContent());
        response.put("Total no of pages", bookPage.getTotalPages());
        response.put("Total no of elements", bookPage.getTotalElements());
        response.put("Current page no", bookPage.getNumber());

        return response;
    }

    public Map<String, Object> getByCategory(String[] categories) {
        Map<String, Object> response = new HashMap<String, Object>();

        List<Book> listOfBooks = booksRepository.getBy(categories);
        response.put("data", listOfBooks);
        response.put("Total no of books", listOfBooks.size());

        return response;
    }

    // query by Example Executor
    public List<Book> getAllByExample(Book book) {
        Example<Book> example = Example.of(book);

        // ExampleMatcher employeeMatcher = ExampleMatcher.matchingAll().withIgnoreCase("lastName", "firstName")
        //         .withIgnorePaths("id").withNullHandler(ExampleMatcher.NullHandler.INCLUDE)
        //         .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        List<Book> books = booksRepository.findAll(example);

        return books;
    }

    // query by method names
    public List<Book> getAllByFirstName(String title) {
        return booksRepository.findByTitleStartingWith(title);
    }

    // query by method names
    public List<Book> getAllByAuthor(String[] authors) {
        return booksRepository.findByAuthors(authors);
    }

    // using @Query
    public List<Book> getAllByPageCountGTE(int pageCount) {
        return booksRepository.getAllByPageCountGTE(pageCount);
    }
}
