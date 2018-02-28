package net.meerumschlungen.dao.jdbc.handlers.columns;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.commons.dbutils.ColumnHandler;

public class UUIDColumnHandler implements ColumnHandler {
    @Override
    public boolean match(Class<?> propType) {
        return propType.equals(UUID.class);
    }
    @Override
    public Object apply(ResultSet rs, int columnIndex) throws SQLException {
        return UUID.fromString(rs.getString(columnIndex));
    }
}
