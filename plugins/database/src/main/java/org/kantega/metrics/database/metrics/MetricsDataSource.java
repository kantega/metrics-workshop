package org.kantega.metrics.database.metrics;

import com.codahale.metrics.MetricRegistry;
import org.kantega.metrics.database.proxy.ProxyDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
public class MetricsDataSource extends ProxyDataSource{

    private final MetricRegistry metricRegistry;

    public MetricsDataSource(DataSource wrapped, MetricRegistry metricRegistry) {
        super(wrapped);
        this.metricRegistry = metricRegistry;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return new MetricConnection(super.getConnection(), metricRegistry);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return new MetricConnection(super.getConnection(username, password), metricRegistry);
    }
}
