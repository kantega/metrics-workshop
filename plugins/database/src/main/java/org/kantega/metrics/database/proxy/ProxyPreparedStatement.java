package org.kantega.metrics.database.proxy;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;

/**
 *
 */
public class ProxyPreparedStatement implements PreparedStatement{
    private final PreparedStatement wrapped;

    public ProxyPreparedStatement(PreparedStatement wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return wrapped.executeQuery();
    }

    @Override
    public int executeUpdate() throws SQLException {
        return wrapped.executeUpdate();
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        wrapped.setNull(parameterIndex, sqlType);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        wrapped.setBoolean(parameterIndex, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        wrapped.setByte(parameterIndex, x);
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        wrapped.setShort(parameterIndex, x);
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        wrapped.setInt(parameterIndex, x);
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        wrapped.setLong(parameterIndex, x);
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        wrapped.setFloat(parameterIndex, x);
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        wrapped.setDouble(parameterIndex, x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        wrapped.setBigDecimal(parameterIndex, x);
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        wrapped.setString(parameterIndex, x);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        wrapped.setBytes(parameterIndex, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        wrapped.setDate(parameterIndex, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        wrapped.setTime(parameterIndex, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        wrapped.setTimestamp(parameterIndex, x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        wrapped.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    @Deprecated
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        wrapped.setUnicodeStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        wrapped.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void clearParameters() throws SQLException {
        wrapped.clearParameters();
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        wrapped.setObject(parameterIndex, x, targetSqlType);
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        wrapped.setObject(parameterIndex, x);
    }

    @Override
    public boolean execute() throws SQLException {
        return wrapped.execute();
    }

    @Override
    public void addBatch() throws SQLException {
        wrapped.addBatch();
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        wrapped.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        wrapped.setRef(parameterIndex, x);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        wrapped.setBlob(parameterIndex, x);
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        wrapped.setClob(parameterIndex, x);
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        wrapped.setArray(parameterIndex, x);
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return wrapped.getMetaData();
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        wrapped.setDate(parameterIndex, x, cal);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        wrapped.setTime(parameterIndex, x, cal);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        wrapped.setTimestamp(parameterIndex, x, cal);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        wrapped.setNull(parameterIndex, sqlType, typeName);
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        wrapped.setURL(parameterIndex, x);
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return wrapped.getParameterMetaData();
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        wrapped.setRowId(parameterIndex, x);
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        wrapped.setNString(parameterIndex, value);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        wrapped.setNCharacterStream(parameterIndex, value, length);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        wrapped.setNClob(parameterIndex, value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        wrapped.setClob(parameterIndex, reader, length);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        wrapped.setBlob(parameterIndex, inputStream, length);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        wrapped.setNClob(parameterIndex, reader, length);
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        wrapped.setSQLXML(parameterIndex, xmlObject);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        wrapped.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        wrapped.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        wrapped.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        wrapped.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        wrapped.setAsciiStream(parameterIndex, x);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        wrapped.setBinaryStream(parameterIndex, x);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        wrapped.setCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        wrapped.setNCharacterStream(parameterIndex, value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        wrapped.setClob(parameterIndex, reader);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        wrapped.setBlob(parameterIndex, inputStream);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        wrapped.setNClob(parameterIndex, reader);
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        wrapped.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException {
        wrapped.setObject(parameterIndex, x, targetSqlType);
    }

    @Override
    public long executeLargeUpdate() throws SQLException {
        return wrapped.executeLargeUpdate();
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        return wrapped.executeQuery(sql);
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        return wrapped.executeUpdate(sql);
    }

    @Override
    public void close() throws SQLException {
        wrapped.close();
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return wrapped.getMaxFieldSize();
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        wrapped.setMaxFieldSize(max);
    }

    @Override
    public int getMaxRows() throws SQLException {
        return wrapped.getMaxRows();
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        wrapped.setMaxRows(max);
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        wrapped.setEscapeProcessing(enable);
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return wrapped.getQueryTimeout();
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        wrapped.setQueryTimeout(seconds);
    }

    @Override
    public void cancel() throws SQLException {
        wrapped.cancel();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return wrapped.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        wrapped.clearWarnings();
    }

    @Override
    public void setCursorName(String name) throws SQLException {
        wrapped.setCursorName(name);
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        return wrapped.execute(sql);
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return wrapped.getResultSet();
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return wrapped.getUpdateCount();
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return wrapped.getMoreResults();
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        wrapped.setFetchDirection(direction);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return wrapped.getFetchDirection();
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        wrapped.setFetchSize(rows);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return wrapped.getFetchSize();
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return wrapped.getResultSetConcurrency();
    }

    @Override
    public int getResultSetType() throws SQLException {
        return wrapped.getResultSetType();
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        wrapped.addBatch(sql);
    }

    @Override
    public void clearBatch() throws SQLException {
        wrapped.clearBatch();
    }

    @Override
    public int[] executeBatch() throws SQLException {
        return wrapped.executeBatch();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return wrapped.getConnection();
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return wrapped.getMoreResults(current);
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return wrapped.getGeneratedKeys();
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return wrapped.executeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return wrapped.executeUpdate(sql, columnIndexes);
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        return wrapped.executeUpdate(sql, columnNames);
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return wrapped.execute(sql, autoGeneratedKeys);
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return wrapped.execute(sql, columnIndexes);
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return wrapped.execute(sql, columnNames);
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return wrapped.getResultSetHoldability();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return wrapped.isClosed();
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        wrapped.setPoolable(poolable);
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return wrapped.isPoolable();
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        wrapped.closeOnCompletion();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return wrapped.isCloseOnCompletion();
    }

    @Override
    public long getLargeUpdateCount() throws SQLException {
        return wrapped.getLargeUpdateCount();
    }

    @Override
    public void setLargeMaxRows(long max) throws SQLException {
        wrapped.setLargeMaxRows(max);
    }

    @Override
    public long getLargeMaxRows() throws SQLException {
        return wrapped.getLargeMaxRows();
    }

    @Override
    public long[] executeLargeBatch() throws SQLException {
        return wrapped.executeLargeBatch();
    }

    @Override
    public long executeLargeUpdate(String sql) throws SQLException {
        return wrapped.executeLargeUpdate(sql);
    }

    @Override
    public long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return wrapped.executeLargeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public long executeLargeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return wrapped.executeLargeUpdate(sql, columnIndexes);
    }

    @Override
    public long executeLargeUpdate(String sql, String[] columnNames) throws SQLException {
        return wrapped.executeLargeUpdate(sql, columnNames);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return wrapped.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return wrapped.isWrapperFor(iface);
    }
}
