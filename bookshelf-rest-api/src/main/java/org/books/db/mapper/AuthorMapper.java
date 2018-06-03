package org.books.db.mapper;

import org.books.api.Author;
import org.books.api.Book;
import org.jooq.Record;
import org.jooq.RecordMapper;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AuthorMapper<R, E> implements RecordMapper<Record, Author> {
    Set<Author> authors = new HashSet<>();

    @Override
    public Author map(Record record) {
        Author author = null;
        if (record.get("id") != null) {
            Optional<Author> optionalAuthor = authors.stream()
                    .filter(a -> a.getId().equals(Long.valueOf(String.valueOf(record.get("id")))))
                    .findFirst();
            if (optionalAuthor.isPresent()) {
                author = optionalAuthor.get();
            } else {
                author = new Author();
                author.setId(Long.valueOf(String.valueOf(record.get("id"))));
                author.setName(record.get("name") != null ? String.valueOf(record.get("name")) : null);
                authors.add(author);
            }
        }
        return author;
    }
}
