package net.meerumschlungen.dao.jdbc;

import javax.sql.DataSource;

public abstract class AbstractJdbcReadWriteDeleteDao<T> implements JdbcReadDao<T>, JdbcWriteDao<T>, JdbcDeleteDao<T> {
    private DataSource ds;
    private StatementProvider sp;
    public AbstractJdbcReadWriteDeleteDao(DataSource ds, StatementProvider sp) {
        this.ds = ds;
        this.sp = sp;
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
