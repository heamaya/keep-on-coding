package org.books.db.records;

import org.books.db.tables.AuthorTable;
import org.jooq.impl.UpdatableRecordImpl;

public class AuthorRecord extends UpdatableRecordImpl<AuthorRecord> {
    private Long id;
    private String name;

    public AuthorRecord() {
        super(AuthorTable.instance);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}