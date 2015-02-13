package org.kantega.metrics.database;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.derby.jdbc.EmbeddedDriver;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public final class DbInitializer {

    private static final String DB_ALREADY_EXISTS = "X0Y32";

    private static final List<String> STATEMENTS = Arrays.asList(
            "create table blog (blogid  integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                    " blogname varchar(255) NOT NULL UNIQUE, " +
                    " color varchar(7) )",

            "create table blogpost (blogpostid  integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                    " blogid integer NOT NULL,  " +
                    " posttitle varchar(255) NOT NULL, " +
                    " postcontent clob (500K) NOT NULL, " +
                    " publishdate timestamp NOT NULL, " +
                    " CONSTRAINT unique_post_title_in_blog UNIQUE (blogid, posttitle))",
            "create table blogpostcomment (blogpostcommentid  integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                    " blogpostid integer NOT NULL,  " +
                    " commentauthor varchar(255) NOT NULL, " +
                    " commentcontent clob (500K) NOT NULL, " +
                    " commentpublishdate timestamp NOT NULL " +
                    " )");

    private DbInitializer() {
    }

    /**
     * Prepare data source (database connection).
     *
     * @param connectString The string used to connect to the database
     * @return The data source to use
     * @throws java.io.IOException If tables can't be created
     */
    public static DataSource initializeDatasource(String connectString) throws IOException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(EmbeddedDriver.class.getName());
        dataSource.setUrl(connectString);

        //dataSource.setMaxActive(50);

        createTables(dataSource);
        return dataSource;
    }

    /**
     * Create tables used in the application.
     *
     * @param dataSource Data source to create them in
     * @throws IOException In case tables can't be created
     */
    private static void createTables(DataSource dataSource) throws IOException {
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                for (String sql : STATEMENTS) {
                    statement.execute(sql);
                }
            }
        } catch (SQLException e) {
            if (!DB_ALREADY_EXISTS.equals(e.getSQLState())) {
                throw new IOException("Can't connect to database", e);
            }
        }
    }
}
