package net.meerumschlungen.dao.jdbc.handlers.columns;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.apache.commons.dbutils.ColumnHandler;

public class LocalDateTimeColumnHandler implements ColumnHandler {
    @Override
    public boolean match(Class<?> propType) {
        return propType.equals(LocalDateTime.class);
    }
    @Override
    public Object apply(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getTimestamp(columnIndex).toLocalDateTime();
    }
}
