package org.kantega.metrics.database.proxy;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 *
 */
public class ProxyDataSource implements DataSource {
    private final DataSource wrapped;

    public ProxyDataSource(DataSource wrapped) {
        this.wrapped = wrapped;
    }

    public Connection getConnection() throws SQLException {
        return wrapped.getConnection();
    }

    public void setLogWriter(PrintWriter out) throws SQLException {
        wrapped.setLogWriter(out);
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return wrapped.getParentLogger();
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return wrapped.isWrapperFor(iface);
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return wrapped.unwrap(iface);
    }

    public void setLoginTimeout(int seconds) throws SQLException {
        wrapped.setLoginTimeout(seconds);
    }

    public int getLoginTimeout() throws SQLException {
        return wrapped.getLoginTimeout();
    }

    public PrintWriter getLogWriter() throws SQLException {
        return wrapped.getLogWriter();
    }

    public Connection getConnection(String username, String password) throws SQLException {
        return wrapped.getConnection(username, password);
    }
}
