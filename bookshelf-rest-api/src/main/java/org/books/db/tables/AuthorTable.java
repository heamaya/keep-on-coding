package org.books.db.tables;

import org.books.db.records.AuthorRecord;
import org.jooq.TableField;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class AuthorTable extends TableImpl<AuthorRecord> {

    public final TableField<AuthorRecord, Long> id =
            createField("id", SQLDataType.BIGINT, this);
    public final TableField<AuthorRecord, String> name =
            createField("name", SQLDataType.VARCHAR(100), this);

    public AuthorTable() {
        super("author");
    }

    public static AuthorTable instance = new AuthorTable();
}
