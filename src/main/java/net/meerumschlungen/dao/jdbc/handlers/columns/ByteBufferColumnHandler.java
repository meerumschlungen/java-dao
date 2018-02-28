package net.meerumschlungen.dao.jdbc.handlers.columns;

import java.nio.ByteBuffer;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ColumnHandler;

public class ByteBufferColumnHandler implements ColumnHandler {
    @Override
    public boolean match(Class<?> propType) {
        return propType.equals(ByteBuffer.class);
    }
    @Override
    public Object apply(ResultSet rs, int columnIndex) throws SQLException {
        return ByteBuffer.wrap(rs.getBytes(columnIndex));
    }
}
