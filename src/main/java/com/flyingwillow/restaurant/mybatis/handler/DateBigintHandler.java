package com.flyingwillow.restaurant.mybatis.handler;

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
public class DateBigintHandler implements TypeHandler<Date> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        ps.setBigDecimal(i, BigDecimal.valueOf(parameter.getTime()));
    }

    @Override
    public Date getResult(ResultSet rs, String columnName) throws SQLException {
        BigDecimal bigDecimal = rs.getBigDecimal(columnName);
        if(null==bigDecimal){
            return null;
        }else {
            return new Date(bigDecimal.toBigInteger().longValue());
        }
    }

    @Override
    public Date getResult(ResultSet rs, int columnIndex) throws SQLException {
        BigDecimal bigDecimal = rs.getBigDecimal(columnIndex);
        if(null==bigDecimal){
            return null;
        }else {
            return new Date(bigDecimal.toBigInteger().longValue());
        }
    }

    @Override
    public Date getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new Date(cs.getLong(columnIndex));
    }
}
