package org.kantega.metrics.database.metrics;

import com.codahale.metrics.MetricRegistry;
import org.kantega.metrics.database.proxy.ProxyPreparedStatement;

import java.sql.PreparedStatement;

/**
 *
 */
public class MetricsPreparedStatement extends ProxyPreparedStatement {

    private final String sql;

    public MetricsPreparedStatement(String sql, PreparedStatement wrapped, MetricRegistry metricRegistry) {
        super(wrapped);
        this.sql = sql;
    }



}
