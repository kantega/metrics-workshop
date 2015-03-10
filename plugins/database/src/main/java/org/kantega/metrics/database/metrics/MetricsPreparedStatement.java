package org.kantega.metrics.database.metrics;

import com.codahale.metrics.MetricRegistry;
import org.kantega.metrics.database.proxy.ProxyPreparedStatement;

import java.sql.PreparedStatement;

/**
 *
 */
public class MetricsPreparedStatement extends ProxyPreparedStatement {

    private final String sql;
    private final MetricRegistry metricRegistry;

    public MetricsPreparedStatement(String sql, PreparedStatement wrapped, MetricRegistry metricRegistry) {
        super(wrapped);
        this.sql = sql;
        this.metricRegistry = metricRegistry;
    }


    //TODO: Override executeQuery() and register the execution time in a timer named "JDBC.query.sql" where sql is the actual sql
}
