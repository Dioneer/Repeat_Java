package Pegas.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private static final String URL = "db.url";
    private static final String USER = "db.username";
    private static final String PASSWORD = "db.password";

    public static Connection open() throws SQLException {
        return DriverManager.getConnection(PropertiesUtil.get(URL), PropertiesUtil.get(USER),
                PropertiesUtil.get(PASSWORD));
    }

    private ConnectionManager(){
    }

}
