package org.books.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.books.db.tables.BookAuthorTable;
import org.jooq.impl.UpdatableRecordImpl;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BookAuthor {
    @JsonProperty
    private String isbn;

    @JsonProperty
    private Long authorId;

    public BookAuthor() {
    }

    public BookAuthor(String isbn, Long authorId) {
        this.isbn = isbn;
        this.authorId = authorId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}