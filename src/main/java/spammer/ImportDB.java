package spammer;

import ru.job4j.jdbc.Config;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImportDB {

    private final Config config = Config.getConfig("import.properties");
    private final String dump;

    public ImportDB() {
        this.dump = config.get("path_to_dump");
    }

    public void importDB() throws IOException, ClassNotFoundException, SQLException {
        save(load());
    }

    private List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        List<String[]> sepList;
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            sepList = separator(rd.lines());
        }
        for (String[] strings : sepList) {
            users.add(new User(strings[0], strings[1]));
        }
        return users;
    }

    private static List<String[]> separator(Stream<String> stream) {
        List<String> list = stream.collect(Collectors.toList());
        List<String[]> sepList = new ArrayList<>();
        for (String str : list) {
            sepList.add(str.split(";"));
        }
        return sepList;
    }

    private void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.get("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                config.get("jdbc.url"),
                config.get("jdbc.username"),
                config.get("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(String.format("insert into %s(name, email) values (?, ?)",
                        config.get("table_name")))) {
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
        ImportDB db = new ImportDB();
        db.importDB();
    }
}