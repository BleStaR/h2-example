package ru.blestar.example.mapper;

import ru.blestar.example.model.Man;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ManMapper {

    Man map(ResultSet rs) throws SQLException;
}
