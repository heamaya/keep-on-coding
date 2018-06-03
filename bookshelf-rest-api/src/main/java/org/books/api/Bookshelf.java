package org.books.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Bookshelf {

    @JsonProperty("bookshelf")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
