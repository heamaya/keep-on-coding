package org.books.db.tables;

import org.books.db.records.BookAuthorRecord;
import org.jooq.TableField;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class BookAuthorTable extends TableImpl<BookAuthorRecord> {

    public final TableField<BookAuthorRecord, String> isbn =
            createField("isbn", SQLDataType.VARCHAR(100), this);
    public final TableField<BookAuthorRecord, Long> authorId =
            createField("author_id", SQLDataType.BIGINT, this);

    public BookAuthorTable() {
        super("book_author");
    }

    public static BookAuthorTable instance = new BookAuthorTable();
}
