package net.meerumschlungen.dao.jdbc;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import net.meerumschlungen.dao.jdbc.StatementProvider.StatementType;

public interface JdbcManageDao<T> extends JdbcDao<T> {
    /*
     * CREATE TABLE
     */
    public default CharSequence getCreateTableStatement() {
        return this.getStatementProvider().get(this.getDataSource(), this.getClass(), StatementType.CREATE_TABLE).orElseThrow(UnsupportedOperationException::new);
    }
    public default void createTable() {
        try {
            QueryRunner run = new QueryRunner(this.getDataSource());
            run.update(this.getCreateTableStatement().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /*
     * DROP TABLE
     */
    public default CharSequence getDropTableStatement() {
        return this.getStatementProvider().get(this.getDataSource(), this.getClass(), StatementType.DROP_TABLE).orElseThrow(UnsupportedOperationException::new);
    }
    public default void dropTable() {
        try {
            QueryRunner run = new QueryRunner(this.getDataSource());
            run.update(this.getDropTableStatement().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /*
     * TRUNCATE TABLE
     */
    public default CharSequence getTruncateTableStatement() {
        return this.getStatementProvider().get(this.getDataSource(), this.getClass(), StatementType.TRUNCATE_TABLE).orElseThrow(UnsupportedOperationException::new);
    }
    public default void truncateTable() {
        try {
            QueryRunner run = new QueryRunner(this.getDataSource());
            run.update(this.getTruncateTableStatement().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
