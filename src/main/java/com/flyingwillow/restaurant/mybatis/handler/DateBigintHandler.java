package com.flyingwillow.restaurant.mybatis.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by liuxuhui on 2017/9/7.
 */
@MappedTypes(Date.class)
@MappedJdbcTypes(JdbcType.BIGINT)
public class DateBigintHandler extends BaseTypeHandler<Date> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        preparedStatement.setBigDecimal(i, BigDecimal.valueOf(date.getTime()));
    }

    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        BigDecimal bigDecimal = resultSet.getBigDecimal(s);
        if(null==bigDecimal){
            return null;
        }else {
            return new Date(bigDecimal.toBigInteger().longValue());
        }
    }

    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        BigDecimal bigDecimal = resultSet.getBigDecimal(i);
        if(null==bigDecimal){
            return null;
        }else {
            return new Date(bigDecimal.toBigInteger().longValue());
        }
    }

    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return new Date(callableStatement.getLong(i));
    }
}
