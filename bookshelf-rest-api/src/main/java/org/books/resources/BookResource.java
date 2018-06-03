package org.books.resources;

import org.apache.log4j.Logger;
import org.books.api.Author;
import org.books.api.Book;
import org.books.api.Bookshelf;
import org.books.db.mapper.AuthorMapper;
import org.books.db.mapper.BookMapper;
import org.books.db.tables.AuthorTable;
import org.books.db.tables.BookAuthorTable;
import org.books.db.tables.BookTable;
import org.jooq.DSLContext;
import org.jooq.Record;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {
    private final static Logger logger = Logger.getLogger(BookResource.class);

    @GET
    public Bookshelf getBooks(@Context DSLContext database) {
        BookMapper<Record, Book> bookMapper = new BookMapper<>();
        AuthorMapper<Record, Author> authorMapper = new AuthorMapper<>();
        Map<Book, List<Author>> result = database
                .select(BookTable.instance.isbn, BookTable.instance.title, BookTable.instance.subtitle,
                        BookTable.instance.published, BookTable.instance.publisher, BookTable.instance.pages,
                        BookTable.instance.description, BookTable.instance.inStock)
                .select(AuthorTable.instance.id, AuthorTable.instance.name)
                .from(BookTable.instance)
                .leftJoin(BookAuthorTable.instance)
                .on(BookTable.instance.isbn.equal(BookAuthorTable.instance.isbn))
                .leftJoin(AuthorTable.instance)
                .on(BookAuthorTable.instance.authorId.equal(AuthorTable.instance.id))
                .fetchGroups(bookMapper, authorMapper);
        Bookshelf bookshelf = new Bookshelf();
        bookshelf.setBooks(result.entrySet()
                .stream()
                .map(e -> {
                    Book book = e.getKey();
                    List<Author> authors = e.getValue();
                    if(authors != null && !authors.isEmpty()) {
                        long nullCount = authors.stream()
                                .filter(a -> a == null)
                                .count();
                        if(authors.size() > nullCount) {
                            book.setAuthors(e.getValue());
                        } else {
                            book.setAuthors(Collections.emptyList());
                        }
                    } else {
                        book.setAuthors(Collections.emptyList());
                    }
                    return book;
                }).collect(Collectors.toList()));
        return bookshelf;
    }
}
