package Model;
import java.sql.*;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class m_bookpackage {
     public void bookpackage(String username, String package_name, String no_persons, String id,
                            String number, String phone, double cost) {
        try {
            Connection con = myDBcon.createDBcon();
            Statement st = con.createStatement();
            String insertQuery = "INSERT INTO bookpackage values (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(insertQuery);
            ps.setString(1, username);
            ps.setString(2, package_name);
            ps.setString(3, no_persons);
            ps.setString(4, id);
            ps.setString(5, number);
            ps.setString(6, phone);
            ps.setDouble(7, cost);
            int raw_count = ps.executeUpdate();

            if (raw_count > 0) {
                JOptionPane.showMessageDialog(null, "Package Booked Successfully", "Book Package", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
