package ru.job4j.jdbc;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = Config.getConfig();
        Class.forName(config.get("driver"));
        try (java.sql.Connection connection = DriverManager.getConnection(config.get("url"), config.get("login"), config.get("password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
