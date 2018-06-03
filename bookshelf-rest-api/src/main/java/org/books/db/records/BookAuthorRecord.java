package org.books.db.records;

import org.books.db.tables.BookAuthorTable;
import org.jooq.impl.UpdatableRecordImpl;

public class BookAuthorRecord extends UpdatableRecordImpl<BookAuthorRecord> {
    private String isbn;
    private Long authorId;


    public BookAuthorRecord() {
        super(BookAuthorTable.instance);
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