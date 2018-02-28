package net.meerumschlungen.dao.jdbc.handlers.columns;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;

import org.apache.commons.dbutils.ColumnHandler;

public class DurationColumnHandler implements ColumnHandler {
    @Override
    public boolean match(Class<?> propType) {
        return propType.equals(Duration.class);
    }
    @Override
    public Object apply(ResultSet rs, int columnIndex) throws SQLException {
        return Duration.ofMillis(rs.getLong(columnIndex));
    }
}
