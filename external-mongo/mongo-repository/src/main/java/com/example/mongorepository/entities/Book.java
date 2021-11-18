package com.example.mongorepository.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "books")
public class Book {

    @Id
    private String _id;
    private String title;
    private String isbn;
    private int pageCount;
    private String publishedDate;
    private String thumbnailUrl;
    private String shortDescription;
    private String longDescription;
    private String status;
    private List<String> authors;
    private List<String> categories;

    public Book() {
        super();
    }

    public Book(String _id, String title, String isbn, int pageCount, String publishedDate, String thumbnailUrl,
            String shortDescription, String longDescription, String status, List<String> authors,
            List<String> categories) {
        super();

        this._id = _id;
        this.title = title;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.publishedDate = publishedDate;
        this.thumbnailUrl = thumbnailUrl;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.status = status;
        this.authors = authors;
        this.categories = categories;
    }

    public String get_id() {
        return this._id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPublishedDate() {
        return this.publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
