package org.books.db.mapper;

import org.books.api.Book;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.RecordMapperProvider;
import org.jooq.RecordType;
import org.jooq.impl.DefaultRecordMapper;

public class BookshelfRecordMapperProvider implements RecordMapperProvider {

    @Override
    public <R extends Record, E> RecordMapper<R, E> provide(RecordType<R> recordType, Class<? extends E> type) {

        if (type == Book.class) {
            return new BookMapper();
        }

        return new DefaultRecordMapper(recordType, type);
    }
}
