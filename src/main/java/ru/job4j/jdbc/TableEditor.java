package ru.job4j.jdbc;

import java.sql.*;
import java.sql.Connection;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static Config config = Config.getConfig("app.properties");

    private Connection connection;

    public TableEditor(Connection connection) {
        this.connection = connection;
    }

    private void getInfo() throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println(metaData.getUserName());
        System.out.println(metaData.getURL());
    }

    private static java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(config.get("driver"));
        return DriverManager.getConnection(config.get("url"), config.get("login"), config.get("password"));
    }

    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
             String sql = String.format("create table if not exists %s(%s, %s)",
                     tableName,
                     "id serial primary key",
                     config.get("arguments"));
             statement.executeUpdate(sql);
            System.out.println(getTableScheme(connection, tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("drop table if exists %s",
                    tableName);
            statement.executeUpdate(sql);
            String isExists = String.format("SELECT EXISTS (SELECT * FROM information_schema.columns where table_name = '%s')", tableName);
            statement.executeQuery(isExists);
            try (ResultSet set = statement.getResultSet()) {
                set.next();
                System.out.println("Таблица существует: " + set.getBoolean(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("insert into %s(name) values %s", tableName, config.get("values"));
            statement.executeUpdate(sql);
            System.out.println(getTableScheme(connection, tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteColumn(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("delete from %s where %1$s.name = %s", tableName, config.get("column_to_delete"));
            statement.executeUpdate(sql);
            System.out.println(getTableScheme(connection, tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("update %s set name = %s where %1$s.name = %s",
                    tableName,
                    config.get("new_column_name"),
                    config.get("rename"));
            statement.executeUpdate(sql);
            System.out.println(getTableScheme(connection, tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getTableScheme(Connection connection, String tableName) {
        var rowSeparator = System.lineSeparator().concat("-".repeat(30).concat(System.lineSeparator()));
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (Statement statement = connection.createStatement()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
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
        try (TableEditor editor = new TableEditor(getConnection())) {
            String tableName = config.get("table_name");
            editor.getInfo();
            editor.createTable(tableName);
            editor.addColumn(tableName);
            editor.renameColumn(tableName);
            editor.deleteColumn(tableName);
            editor.dropTable(tableName);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
