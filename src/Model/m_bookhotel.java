package Model;
import java.sql.*;
import java.sql.Statement;
import javax.swing.JOptionPane;
public class m_bookhotel {
    public void bookhotel(String username, String hotelname, String no_persons, Date checkindate, Date checkoutdate, String AC,
                            String number, String phone, double cost) {
        try {
            Connection con = myDBcon.createDBcon();
            Statement st = con.createStatement();
            String insertQuery = "INSERT INTO bookhotel (username, name, persons, check_in, check_out, ac, number, phone, cost) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(insertQuery);
            ps.setString(1, username);
            ps.setString(2, hotelname);
            ps.setString(3, no_persons);
            ps.setDate(4, checkindate);
            ps.setDate(5, checkoutdate);
            ps.setString(6, AC);
            ps.setString(7, number);
            ps.setString(8, phone);
            ps.setDouble(9, cost);
            int raw_count = ps.executeUpdate();

            if (raw_count > 0) {
                JOptionPane.showMessageDialog(null, "Hotel Booked Successfully", "Booking", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    
}
