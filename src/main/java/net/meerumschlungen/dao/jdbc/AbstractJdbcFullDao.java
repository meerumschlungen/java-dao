package net.meerumschlungen.dao.jdbc;

import javax.sql.DataSource;

public abstract class AbstractJdbcFullDao<T> implements JdbcReadDao<T>, JdbcWriteDao<T>, JdbcDeleteDao<T>, JdbcManageDao<T> {
    private DataSource ds;
    private StatementProvider sp;
    public AbstractJdbcFullDao(DataSource ds, StatementProvider sp) {
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
