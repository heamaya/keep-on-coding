package org.books.db.tables;

import org.books.db.records.BookRecord;
import org.jooq.TableField;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.sql.Timestamp;

public class BookTable extends TableImpl<BookRecord> {

    public final TableField<BookRecord, String> isbn =
            createField("isbn", SQLDataType.VARCHAR(100), this);
    public final TableField<BookRecord, String> title =
            createField("title", SQLDataType.VARCHAR(200), this);
    public final TableField<BookRecord, String> subtitle =
            createField("subtitle", SQLDataType.VARCHAR(200), this);
    public final TableField<BookRecord, Timestamp> published =
            createField("published", SQLDataType.TIMESTAMP, this);
    public final TableField<BookRecord, String> publisher =
            createField("publisher", SQLDataType.VARCHAR(200), this);
    public final TableField<BookRecord, Integer> pages =
            createField("pages", SQLDataType.INTEGER, this);
    public final TableField<BookRecord, String> description =
            createField("description", SQLDataType.VARCHAR(500), this);
    public final TableField<BookRecord, Boolean> inStock =
            createField("in_stock", SQLDataType.BOOLEAN, this);

    public BookTable() {
        super("book");
    }

    public static BookTable instance = new BookTable();
}
