package com.example.mybatis.common.config.typeHandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Enumerable.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class EnumerableTypeHandler<E extends Enumerable> implements TypeHandler<E> {

    private final E[] elements;


    public EnumerableTypeHandler(Class<E> type) {
        this.elements = type.getEnumConstants();
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.value());
    }

    @Override
    public E getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return matchElement(value);
    }

    @Override
    public E getResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return matchElement(value);
    }

    @Override
    public E getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return matchElement(value);
    }

    private E matchElement(String value) {
        if (value == null) {
            return null;
        }

        for (E element : elements) {
            if (element.value().equals(value)) {
                return element;
            }
        }

        return null;
    }
}
