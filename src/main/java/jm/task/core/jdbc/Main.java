package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Alex", "Volzhanin", (byte) 33);
        service.saveUser("Anton", "Serebryanikov", (byte) 45);
        service.saveUser("Michail", "Uvarov", (byte) 42);
        service.saveUser("German", "Kireev", (byte) 28);
        System.out.println("-----------------------------------------------");
        service.getAllUsers();
        System.out.println("-----------------------------------------------");
        service.removeUserById(2);
        System.out.println("-----------------------------------------------");
        service.getAllUsers();
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
