package ru.blestar.example.mapper.impl;

import ru.blestar.example.mapper.ManMapper;
import ru.blestar.example.model.Man;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManMapperImpl implements ManMapper {

    @Override
    public Man map(ResultSet rs) throws SQLException {
        int age = rs.getInt("AGE");
        boolean ageIsNull = rs.wasNull();
        return new Man(
                rs.getInt("GID"),
                rs.getString("NAME"),
                ageIsNull ? null : age
        );
    }
}
