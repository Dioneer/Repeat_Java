package Pegas;

import Pegas.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) {
        try(Connection connection = ConnectionManager.open()) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
