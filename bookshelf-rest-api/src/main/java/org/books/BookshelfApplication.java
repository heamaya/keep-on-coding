package org.books;

import com.bendb.dropwizard.jooq.JooqBundle;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.books.resources.BookResource;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.jooq.SQLDialect;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class BookshelfApplication extends Application<BookshelfConfiguration> {

    public static void main(final String[] args) throws Exception {
        new BookshelfApplication().run(args);
    }

    @Override
    public String getName() {
        return "Bookshelf";
    }

    @Override
    public void initialize(final Bootstrap<BookshelfConfiguration> bootstrap) {
        bootstrap.addBundle(new JooqBundle<BookshelfConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(BookshelfConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }

            @Override
            public void run(BookshelfConfiguration configuration, Environment environment) throws Exception {
                super.run(configuration, environment);
                getConfiguration().derive(SQLDialect.MYSQL);
            }
        });
    }

    @Override
    public void run(final BookshelfConfiguration configuration,
                    final Environment environment) {
        final FilterRegistration.Dynamic corsFilter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        corsFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM,
                "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        corsFilter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        final BookResource bookResource = new BookResource();
        environment.jersey().register(bookResource);
    }

}
