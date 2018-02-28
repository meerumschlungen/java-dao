package net.meerumschlungen.dao.jdbc.handlers.columns;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ColumnHandler;

public class ByteArrayColumnHandler implements ColumnHandler {
    @Override
    public boolean match(Class<?> propType) {
        return propType.equals(byte[].class) || propType.equals(Byte[].class);
    }
    @Override
    public Object apply(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getBytes(columnIndex);
    }
}
