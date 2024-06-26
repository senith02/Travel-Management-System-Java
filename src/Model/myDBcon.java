package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
public class myDBcon {
    private static Connection con;
    public static Connection createDBcon(){
        try {
            String dbiog = "jdbc:mysql://localhost/travel";
            con=DriverManager.getConnection(dbiog,"root","");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return con;
    }
}
