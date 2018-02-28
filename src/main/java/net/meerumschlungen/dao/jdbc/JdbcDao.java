package net.meerumschlungen.dao.jdbc;

import javax.sql.DataSource;

public interface JdbcDao<T> {
    public DataSource getDataSource();
    public StatementProvider getStatementProvider();
}
