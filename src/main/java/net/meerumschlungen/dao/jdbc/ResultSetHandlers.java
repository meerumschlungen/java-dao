package net.meerumschlungen.dao.jdbc;

import java.beans.PropertyDescriptor;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class ResultSetHandlers {
    public static class CustomBeanProcessor extends BeanProcessor {
        @Override
        protected int[] mapColumnsToProperties(ResultSetMetaData rsmd, PropertyDescriptor[] props) throws SQLException {
            int cols = rsmd.getColumnCount();
            int[] columnToProperty = new int[cols + 1];
            Arrays.fill(columnToProperty, PROPERTY_NOT_FOUND);

            for (int col = 1; col <= cols; col++) {
                String columnName = rsmd.getColumnLabel(col);
                if (null == columnName || 0 == columnName.length()) {
                    columnName = rsmd.getColumnName(col);
                }
                String propertyName = columnName;
                propertyName = propertyName.replaceAll("_", ""); //$NON-NLS-1$ //$NON-NLS-2$
                for (int i = 0; i < props.length; i++) {
                    if (propertyName.equalsIgnoreCase(props[i].getName())) {
                        columnToProperty[col] = i;
                        break;
                    }
                }
            }
            return columnToProperty;
        }
    }

    public static <T> ResultSetHandler<List<T>> beanListHandler(Class<T> type) {
        return new BeanListHandler<>(type, new BasicRowProcessor(new CustomBeanProcessor()));
    }
}
