package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection conn;
    Statement statement;
    ResultSet rs;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {
        conn = Util.getConnection();
        statement = conn.createStatement();
        if (statement.executeUpdate("CHECK TABLE USER") != -1) {
            statement.executeUpdate("CREATE TABLE USER" +
                    "(id int PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(100) not null, " +
                    "lastName VARCHAR(100) not null, " +
                    "age int not null)");
        }
        statement.close();
        conn.close();
    }

    public void dropUsersTable() throws SQLException {
        conn = Util.getConnection();
        statement = conn.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS USER");
        statement.close();
        conn.close();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        conn = Util.getConnection();
        PreparedStatement preStatement = conn.prepareStatement("INSERT INTO USER (name, lastName, age) Values (?, ?, ?)");
        preStatement.setString(1, name);
        preStatement.setString(2, lastName);
        preStatement.setInt(3, age);
        System.out.println("User с именем – " + name + " " + lastName + " добавлен в базу данных.");
        preStatement.executeUpdate();
        conn.close();
    }

    public void removeUserById(long id) throws SQLException {
        conn = Util.getConnection();
        statement = conn.createStatement();
        statement.executeUpdate("DELETE FROM USER WHERE id");
        System.out.println("Удален пользователь id = " + id);
    }

    public List<User> getAllUsers() throws SQLException {
        conn = Util.getConnection();
        statement = conn.createStatement();
        List<User> userList = new ArrayList<>();
        rs = statement.executeQuery("SELECT * FROM USER");
        while (rs.next()) {
            userList.add(new User(rs.getString(2), rs.getString(3), rs.getByte(4)));
        }
        for (User usr : userList) {
            System.out.println(usr);
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        conn = Util.getConnection();
        statement = conn.createStatement();
        statement.executeUpdate("TRUNCATE TABLE USER");
        statement.close();
        conn.close();
    }
}
