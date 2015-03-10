package org.kantega.metrics.database.metrics;

import com.codahale.metrics.MetricRegistry;
import org.kantega.metrics.database.proxy.ProxyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 */
public class MetricConnection extends ProxyConnection {

    private final MetricRegistry metricRegistry;

    public MetricConnection(Connection connection, MetricRegistry metricRegistry) {
        super(connection);
        this.metricRegistry = metricRegistry;
    }


    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return new MetricsPreparedStatement(sql, super.prepareStatement(sql), metricRegistry);
    }
}
