package net.meerumschlungen.dao.jdbc;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.IntStream;

import org.apache.commons.dbutils.QueryRunner;

import net.meerumschlungen.dao.CreateDao;
import net.meerumschlungen.dao.ReplaceDao;
import net.meerumschlungen.dao.UpdateDao;
import net.meerumschlungen.dao.jdbc.StatementProvider.StatementType;

public interface JdbcWriteDao<T> extends JdbcDao<T>, CreateDao<T>, UpdateDao<T>, ReplaceDao<T> {
    /**
     * create
     */
    public Function<T, Object[]> getInsertMapper();
    public default CharSequence getInsertStatement() {
        return this.getStatementProvider().get(this.getDataSource(), this.getClass(), StatementType.INSERT).orElseThrow(UnsupportedOperationException::new);
    }
    @Override
    public default int create(final T dto) {
        try {
            QueryRunner run = new QueryRunner(this.getDataSource());
            return run.update(this.getInsertStatement().toString(), this.getInsertMapper().apply(dto));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public default int create(final Collection<T> dtos) {
        try {
            QueryRunner run = new QueryRunner(this.getDataSource());
            return Arrays.stream(run.batch(this.getInsertStatement().toString(), dtos.stream().map(this.getInsertMapper()).toArray(Object[][]::new))).sum();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * update
     */
    public Function<T, Object[]> getUpdateMapper();
    public default CharSequence getUpdateStatement() {
        return this.getStatementProvider().get(this.getDataSource(), this.getClass(), StatementType.UPDATE).orElseThrow(UnsupportedOperationException::new);
    }
    @Override
    public default int update(final T dto) {
        try {
            QueryRunner run = new QueryRunner(this.getDataSource());
            return run.update(this.getUpdateStatement().toString(), this.getUpdateMapper().apply(dto));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public default int update(final Collection<T> dtos) {
        try {
            QueryRunner run = new QueryRunner(this.getDataSource());
            return Arrays.stream(run.batch(this.getUpdateStatement().toString(), dtos.stream().map(this.getUpdateMapper()).toArray(Object[][]::new))).sum();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * replace
     */
    public Function<T, Object[]> getReplaceMapper();
    @Override
    public default int replace(final Collection<T> dtos) {
        try {
            QueryRunner run = new QueryRunner(this.getDataSource());
            Object[][] updParams = dtos.stream().map(this.getReplaceMapper()).toArray(Object[][]::new);
            int[] updAffected = run.batch(this.getUpdateStatement().toString(), updParams);
            Object[][] insParams = IntStream.range(0, updAffected.length)
                    .filter(i -> updAffected[i] < 1)
                    .mapToObj(i -> updParams[i])
                    .toArray(Object[][]::new);
            int[] insAffected = run.batch(this.getInsertStatement().toString(), insParams);
            return IntStream.concat(Arrays.stream(updAffected), Arrays.stream(insAffected)).sum();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
