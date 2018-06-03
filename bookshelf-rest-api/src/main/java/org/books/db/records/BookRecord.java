package org.books.db.records;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.books.db.tables.BookTable;
import org.jooq.Result;
import org.jooq.impl.UpdatableRecordImpl;

import java.sql.Timestamp;

public class BookRecord extends UpdatableRecordImpl<BookRecord> {
    private String isbn;
    private String title;
    private String subtitle;
    private Timestamp published;
    private String publisher;
    private Integer pages;
    private String description;
    private boolean inStock;

    public BookRecord() {
        super(BookTable.instance);
    }

    public BookRecord(Result<BookRecord> result) {
        super(BookTable.instance);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }


    public Timestamp getPublished() {
        return published;
    }

    public void setPublished(Timestamp published) {
        this.published = published;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}