
package org.kantega.metrics.database;

import com.codahale.metrics.MetricRegistry;
import org.kantega.reststop.api.DefaultReststopPlugin;
import org.kantega.reststop.api.Export;

import javax.sql.DataSource;
import java.io.IOException;

public class DatabasePlugin
    extends DefaultReststopPlugin
{


    @Export
    private final DataSource dataSource;

    public DatabasePlugin(MetricRegistry metricRegistry) throws IOException {
        dataSource = DbInitializer.initializeDatasource("jdbc:derby:memory:blogdb;create=true");
    }

}
