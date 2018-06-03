package org.books.db.mapper;

import org.books.api.Book;
import org.jooq.Record;
import org.jooq.RecordMapper;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BookMapper<R, E> implements RecordMapper<Record, Book> {
    Set<Book> books = new HashSet<>();

    @Override
    public Book map(Record record) {
        Optional<Book> optionalBook = books.stream()
                .filter(b -> b.getIsbn().equals(String.valueOf(record.get("isbn"))))
                .findFirst();
        Book book;
        if(optionalBook.isPresent()) {
            book = optionalBook.get();
        } else {
            book = new Book();
            book.setIsbn(String.valueOf(record.get("isbn")));
            book.setTitle(record.get("title") != null ? String.valueOf(record.get("title")) : null);
            book.setSubtitle(record.get("subtitle") != null ? String.valueOf(record.get("subtitle")) : null);
            book.setPublishedTimestamp(record.get("published") != null
                    ? Timestamp.valueOf(String.valueOf(record.get("published"))) : null);
            book.setPublisher(record.get("publisher") != null ? String.valueOf(record.get("publisher")) : null);
            book.setPages(record.get("pages") != null ? Integer.valueOf(String.valueOf(record.get("pages"))) : null);
            book.setDescription(record.get("description") != null ? String.valueOf(record.get("description")) : null);
            book.setInStock(record.get("in_stock") != null
                    ? Boolean.valueOf(String.valueOf(record.get("in_stock"))) : null);
            books.add(book);
        }

        return book;
    }
}
