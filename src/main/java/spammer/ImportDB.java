package spammer;

import ru.job4j.jdbc.TableEditor;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ImportDB {

    private final Properties properties;
    private final String dump;

    public ImportDB(String fileToDump, String resource) {
        this.properties = getProp(resource);
        this.dump = fileToDump;
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

    public void importDB() throws IOException, ClassNotFoundException, SQLException {
        save(load());
    }

    private List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(line -> {
                String[] array = line.split(";", 2);
                if (array.length != 2 || array[0].isEmpty() || array[1].isEmpty()) {
                    throw new IllegalArgumentException();
                }
                users.add(new User(array[0], array[1]));
            });
        }
        return users;
    }

    private void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(String.format("insert into %s(name, email) values (?, ?)",
                        properties.getProperty("table_name")))) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        ImportDB db = new ImportDB("import.txt", "import.properties");
        db.importDB();
    }
}