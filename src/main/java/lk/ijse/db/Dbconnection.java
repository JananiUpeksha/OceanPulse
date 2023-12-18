package lk.ijse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {

    private static Dbconnection dbconnection;

    private Connection connection;

    private Dbconnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/oceanPulse",
                "root",
                "Ijse@1234"
        );
    }

    public static Dbconnection getInstance() throws SQLException {
        if(dbconnection == null){
            dbconnection = new Dbconnection();
        }
        return dbconnection;
    }

    public Connection getConnection(){
        return connection;
    }
}
