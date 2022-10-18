package ru.blestar.example.repository.impl;

import ru.blestar.example.repository.InitRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitRepositoryImpl implements InitRepository {

    private static final Logger log = Logger.getLogger(InitRepositoryImpl.class.getName());

    private static final String QUERY_INIT =
            "CREATE TABLE MANS (GID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255), AGE INT);" +
            "INSERT INTO MANS (NAME, AGE) VALUES ('Anton', 5);" +
            "INSERT INTO MANS (NAME, AGE) VALUES ('Ivan', 10);" +
            "INSERT INTO MANS (NAME, AGE) VALUES ('Petr', 15);" +
            "INSERT INTO MANS (NAME) VALUES ('Natali');";
    private final Connection conn;

    public InitRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean initTables() {
        try (var stmt = conn.createStatement()) {
            stmt.execute(QUERY_INIT);

            return true;
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "Fail init tables", ex);
            return false;
        }
    }
}
