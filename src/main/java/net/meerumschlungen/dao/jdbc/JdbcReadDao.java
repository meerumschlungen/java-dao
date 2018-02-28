package net.meerumschlungen.dao.jdbc;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import net.meerumschlungen.dao.ReadDao;
import net.meerumschlungen.dao.jdbc.StatementProvider.StatementType;

public interface JdbcReadDao<T> extends JdbcDao<T>, ReadDao<T> {
    public ResultSetHandler<List<T>> getSelectHandler();
    public default CharSequence getSelectStatement() {
        return this.getStatementProvider().get(this.getDataSource(), this.getClass(), StatementType.SELECT).orElseThrow(UnsupportedOperationException::new);
    }
    @Override
    public default List<T> read() {
        try {
            QueryRunner run = new QueryRunner(this.getDataSource());
            return run.query(this.getSelectStatement().toString(), this.getSelectHandler());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
