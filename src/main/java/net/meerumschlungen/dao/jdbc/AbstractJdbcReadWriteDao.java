package net.meerumschlungen.dao.jdbc;

import javax.sql.DataSource;

public abstract class AbstractJdbcReadWriteDao<T> implements JdbcReadDao<T>, JdbcWriteDao<T> {
    private DataSource ds;
    private StatementProvider sp;
    public AbstractJdbcReadWriteDao(DataSource ds, StatementProvider sp) {
        this.ds = ds;
    }
    @Override
    public final DataSource getDataSource() {
        return this.ds;
    }
    @Override
    public StatementProvider getStatementProvider() {
        return this.sp;
    }
}
