package net.meerumschlungen.dao.jdbc;

import java.util.Optional;

import javax.sql.DataSource;

public interface StatementProvider {
    public static enum StatementType { SELECT, INSERT, UPDATE, DELETE, CREATE_TABLE, DROP_TABLE, TRUNCATE_TABLE }
    public Optional<String> get(DataSource dataSource, Class<?> daoType, StatementType statementType);
}
