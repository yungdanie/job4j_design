package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.Connection;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static Properties properties;

    private Connection connection;

    private Statement statement;

    public TableEditor(String resource) throws SQLException, ClassNotFoundException {
        properties = getProp(resource);
        connection = getConnection();
        statement = getStatement();
    }

    private Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    private static Properties getProp(String resource) {
        Properties prop = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream(resource)) {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("login"), properties.getProperty("password"));
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format("create table if not exists %s(%s)",
                tableName,
                "id serial primary key");
        statement.executeUpdate(sql);
        System.out.println(getTableScheme(tableName));
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format("drop table if exists %s",
                tableName);
        statement.executeUpdate(sql);
    }

    public void addColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format("alter table %s add IF NOT EXISTS %s %s", tableName,  columnName, "text");
        statement.executeUpdate(sql);
        System.out.println(getTableScheme(tableName));
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format("alter table %s drop column IF EXISTS %s", tableName,  columnName);
        statement.executeUpdate(sql);
        System.out.println(getTableScheme(tableName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format("alter table %s rename column %s to %s",
                tableName,
                columnName,
                newColumnName);
        statement.executeUpdate(sql);
        System.out.println(getTableScheme(tableName));
    }

    private String getTableScheme(String tableName) throws SQLException {
        var rowSeparator = System.lineSeparator().concat("-".repeat(30).concat(System.lineSeparator()));
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (ResultSet set = statement.executeQuery(
                String.format("select * from %s", tableName)
        )) {
            ResultSetMetaData metaData = set.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n", metaData.getColumnName(i), metaData.getColumnTypeName(i)));
            }
            while (set.next()) {
                buffer.add("values:" + set.getString("name"));
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
            statement.close();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try (TableEditor editor = new TableEditor("app.properties")) {
            try (Statement statement = editor.statement) {
                String tableName =  properties.getProperty("table_name");
                editor.createTable(tableName);
                editor.addColumn(tableName, properties.getProperty("values"));
                editor.renameColumn(tableName, properties.getProperty("rename"), properties.getProperty("new_column_name"));
                editor.dropColumn(tableName, properties.getProperty("column_to_delete"));
                editor.dropTable(tableName);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
