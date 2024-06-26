
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class m_addcus {
    public void addCustomer(String username, String id, String number, String name, String gender,
                            String country, String address, String phone, String email) {
        try {
            // Get a database connection
            Connection con = myDBcon.createDBcon();
            
            // Create a PreparedStatement with the SQL query
            String insertQuery = "INSERT INTO customer (username, id_type, number, name, gender, country, address, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insertQuery);
            
            // Set parameters in the PreparedStatement
            ps.setString(1, username);
            ps.setString(2, id);
            ps.setString(3, number);
            ps.setString(4, name);
            ps.setString(5, gender);
            ps.setString(6, country);
            ps.setString(7, address);
            ps.setString(8, phone);
            ps.setString(9, email);
            
            // Execute the SQL query to insert the data
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Customer Registered", "Registration", JOptionPane.INFORMATION_MESSAGE);
            }
            ps.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);

        }
    }
}
