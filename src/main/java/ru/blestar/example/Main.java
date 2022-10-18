package ru.blestar.example;

import ru.blestar.example.mapper.impl.ManMapperImpl;
import ru.blestar.example.model.Man;
import ru.blestar.example.repository.impl.InitRepositoryImpl;
import ru.blestar.example.repository.impl.ManRepositoryImpl;
import ru.blestar.example.service.ManService;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try (var conn = DriverManager.getConnection("jdbc:h2:mem:")) {
            boolean init = new InitRepositoryImpl(conn).initTables();
            if (!init) {
                log.info("App stopped: fail init");
                return;
            }

            var manService = new ManService(new ManRepositoryImpl(conn, new ManMapperImpl()));

            log.info("Find all:");
            manService.findAll().stream()
                    .map(Man::toString)
                    .forEach(log::info);

            int ageThreshold = 10;
            log.info("Find all older " + ageThreshold + ":");
            manService.findAllOlderThan(ageThreshold).stream()
                    .map(Man::toString)
                    .forEach(log::info);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "Fail open connect", ex);
        }
    }
}
