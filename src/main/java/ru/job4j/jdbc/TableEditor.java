package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.Connection;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static Properties properties = getProp();

    private Connection connection = getConnection();

    private Statement statement = getStatement();

    public TableEditor() throws SQLException, ClassNotFoundException {
    }

    private Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    private static Properties getProp() {
        Properties prop = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
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
        String sql = String.format("create table if not exists %s(%s, %s)",
                tableName,
                "id serial primary key",
                properties.getProperty("arguments"));
        statement.executeUpdate(sql);
        System.out.println(getTableScheme(tableName));
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format("drop table if exists %s",
                tableName);
        statement.executeUpdate(sql);
        String isExists = String.format("SELECT EXISTS (SELECT * FROM information_schema.columns where table_name = '%s')", tableName);
        statement.executeQuery(isExists);
        try (ResultSet set = statement.getResultSet()) {
            set.next();
            System.out.println("Таблица существует: " + set.getBoolean(1));
        }
    }

    public void addColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format("insert into %s(name) values %s", tableName,  columnName);
        statement.executeUpdate(sql);
        System.out.println(getTableScheme(tableName));
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format("delete from %s where %1$s.name = %s", tableName,  columnName);
        statement.executeUpdate(sql);
        System.out.println(getTableScheme(tableName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format("update %s set name = %s where %1$s.name = %s",
                tableName,
                newColumnName,
                columnName);
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
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try (TableEditor editor = new TableEditor()) {
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
