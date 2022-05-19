package ru.job4j.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
        Config config = Config.getConfig();
        Class.forName(config.get("driver"));
        return DriverManager.getConnection(config.get("url"), config.get("login"), config.get("password"));
    }
}
