package net.meerumschlungen.dao.jdbc;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.dbutils.QueryRunner;

import net.meerumschlungen.dao.DeleteDao;
import net.meerumschlungen.dao.jdbc.StatementProvider.StatementType;

public interface JdbcDeleteDao<T> extends JdbcDao<T>, DeleteDao<T> {
    /**
     * delete
     */
    public Object[] getDeleteIdentifier(final T dto);
    public default CharSequence getDeleteStatement() {
        return this.getStatementProvider().get(this.getDataSource(), this.getClass(), StatementType.DELETE).orElseThrow(UnsupportedOperationException::new);
    }
    @Override
    public default int delete(final T dto) {
        try {
            QueryRunner run = new QueryRunner(this.getDataSource());
            return run.update(this.getDeleteStatement().toString(), this.getDeleteIdentifier(dto));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public default int delete() {
        return this.delete(this.read());
    }
    @Override
    public default int delete(final Collection<T> dtos) {
        try {
            QueryRunner run = new QueryRunner(this.getDataSource());
            return Arrays.stream(run.batch(this.getDeleteStatement().toString(), dtos.stream().map(this::getDeleteIdentifier).toArray(Object[][]::new))).sum();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public default int delete(final Predicate<T> condition) {
        return this.delete(this.read().stream().filter(condition).collect(Collectors.toList()));
    }
}
