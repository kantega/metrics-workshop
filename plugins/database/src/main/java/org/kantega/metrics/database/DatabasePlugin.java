
package org.kantega.metrics.database;

import com.codahale.metrics.MetricRegistry;
import org.kantega.reststop.api.Export;
import org.kantega.reststop.api.Plugin;

import javax.sql.DataSource;
import java.io.IOException;

@Plugin
public class DatabasePlugin {

    @Export
    private final DataSource dataSource;

    public DatabasePlugin(MetricRegistry metricRegistry) throws IOException {
        dataSource = DbInitializer.initializeDatasource("jdbc:derby:memory:blogdb;create=true");
    }

}
