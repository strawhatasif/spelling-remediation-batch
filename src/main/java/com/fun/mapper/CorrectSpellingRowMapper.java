package com.fun.mapper;

import com.fun.model.SettledAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CorrectSpellingRowMapper implements RowMapper<SettledAccount> {

    @Override
    public SettledAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SettledAccount(rs.getString("ID"),
                rs.getString("NAME"),
                rs.getString("PRODUCT"),
                rs.getString("NOTES"),
                rs.getString("STATUS"));
    }
}
