package Pegas.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/java_rapeat";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    public static Connection open() throws SQLException {
        return DriverManager.getConnection(URL, USER,PASSWORD);
    }

    private ConnectionManager(){
    }
}
