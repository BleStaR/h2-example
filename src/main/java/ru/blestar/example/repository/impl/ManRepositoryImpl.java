package ru.blestar.example.repository.impl;

import ru.blestar.example.mapper.ManMapper;
import ru.blestar.example.model.Man;
import ru.blestar.example.repository.ManRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManRepositoryImpl implements ManRepository {

    private static final Logger log = Logger.getLogger(ManRepositoryImpl.class.getName());
    private static final String QUERY_FIND_ALL = "SELECT GID, NAME, AGE FROM MANS";

    private final Connection conn;
    private final ManMapper manMapper;

    public ManRepositoryImpl(Connection conn, ManMapper manMapper) {
        this.conn = conn;
        this.manMapper = manMapper;
    }

    @Override
    public List<Man> findAll() {
        try (var stmt = conn.createStatement()) {
            var rs = stmt.executeQuery(QUERY_FIND_ALL);

            return parseFindAll(rs);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "Fail find all man", ex);
            return Collections.emptyList();
        }
    }

    private List<Man> parseFindAll(ResultSet rs) throws SQLException {
        List<Man> result = new ArrayList<>();
        while (rs.next()) {
            result.add(manMapper.map(rs));
        }
        return result;
    }
}
