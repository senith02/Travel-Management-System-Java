package View;
import Model.myDBcon;
import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.LineBorder;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class v_checkout implements ActionListener{
    private JFrame frame1;
    private JTextField textfield1,textfield2,textfield3,textfield4;
    private JRadioButton radiobtn1,radiobtn2;
    private JButton pay,back;
    private JLabel label5,label6,label7,label8;
    double hotelPrice,packagePrice,totalPayment;
    private String username;

    
    public void Checkout(String username) {
    this.username = username;
    frame1 = new JFrame("Checkout");
    frame1.setBounds(500, 250, 800, 400);
    frame1.setUndecorated(true);
    frame1.setLayout(null);
    frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame1.getContentPane().setBackground(Color.WHITE);

    JLabel label = new JLabel("Checkout");
    label.setBounds(100, 20, 200, 30);
    label.setFont(new Font("Tahoma", Font.BOLD, 20));
    frame1.add(label);

    JLabel label1 = new JLabel("Customer ID");
    label1.setBounds(30, 80, 100, 30);
    frame1.add(label1);

    JLabel label2 = new JLabel("Package Payment");
    label2.setBounds(30, 130, 100, 30);
    frame1.add(label2);

    JLabel label3 = new JLabel("Hotel Payment");
    label3.setBounds(30, 180, 100, 30);
    frame1.add(label3);

    JLabel label4 = new JLabel("Total Payment");
    label4.setBounds(30, 230, 100, 30);
    frame1.add(label4);

    label5 = new JLabel(username);
    label5.setBounds(180, 80, 150, 25);
    frame1.add(label5);

    label6 = new JLabel();
    label6.setBounds(180, 130, 150, 25);
    frame1.add(label6);

    label7 = new JLabel();
    label7.setBounds(180, 180, 150, 25);
    frame1.add(label7);

    label8 = new JLabel();
    label8.setBounds(180, 230, 150, 25);
    frame1.add(label8);

    pay = new JButton("Print Bill");
    pay.setBounds(30, 280, 120, 25);
    pay.setBackground(Color.BLACK);
    pay.setForeground(WHITE);
    pay.addActionListener(this);
    frame1.add(pay);

    back = new JButton("Back");
    back.setBounds(170, 280, 100, 25);
    back.setBackground(Color.BLACK);
    back.setForeground(WHITE);
    frame1.add(back);

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
    Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel tick = new JLabel(i3);
    tick.setBounds(265, 80, 20, 20);
    frame1.add(tick);

    ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
    Image i6 = i5.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
    ImageIcon i7 = new ImageIcon(i6);
    JLabel tick1 = new JLabel(i7);
    tick1.setBounds(350, 50, 400, 250);
    frame1.add(tick1);

    try {
            ResultSet rst;
            Statement st=myDBcon.createDBcon().createStatement();
            rst = st.executeQuery("select cost from bookpackage where username = '"+username+"'");

            if (rst.next()) {
                packagePrice = rst.getDouble("cost");
                label6.setText(String.valueOf(packagePrice));
                
            } else {
                // Handle the case where no matching username is found
                JOptionPane.showMessageDialog(null, "cost not found in the database.");
            }

            rst.close();
            st.close();
} 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
}

    try {
            ResultSet dst;
            Statement st=myDBcon.createDBcon().createStatement();
            dst = st.executeQuery("select cost from bookhotel where username = '"+username+"'");

            if (dst.next()) {
                hotelPrice = dst.getDouble("cost");
                label7.setText(String.valueOf(hotelPrice));
                
            } else {
                // Handle the case where no matching username is found
                JOptionPane.showMessageDialog(null, "cost not found in the database.");
            }

            dst.close();
            st.close();
} 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
}
            
    totalPayment = packagePrice + hotelPrice;
    label8.setText(String.valueOf(totalPayment));



    frame1.setVisible(true);
}

    public void closeFrame() {
        frame1.dispose();
    }
    public static void main(String[] args){
        v_checkout chk = new v_checkout();
        chk.Checkout("");
    }    

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == pay){
            Map<String, Object> params = new HashMap<>();
            params.put("username", username);
            try {
            JasperDesign jasperDesign=JRXmlLoader.load("C:\\Users\\senit\\OneDrive - National Institute of Business Management\\Documents\\NetBeansProjects\\TravelManagementSystem\\src\\Model\\billreport.jrxml");
            JRDesignQuery designquery=new JRDesignQuery();
            designquery.setText("SELECT\n" +
"    c.name AS full_name,\n" +
"    IFNULL(SUM(bp.cost), 0) AS package_bill,\n" +
"    IFNULL(SUM(bh.cost), 0) AS hotel_bill,\n" +
"    IFNULL(SUM(bp.cost), 0) + IFNULL(SUM(bh.cost), 0) AS total_bill\n" +
"FROM\n" +
"    customer c\n" +
"LEFT JOIN\n" +
"    bookpackage bp ON c.username = bp.username\n" +
"LEFT JOIN\n" +
"    bookhotel bh ON c.username = bh.username\n" +
"WHERE\n" +
"    c.username = $P{username}");
            jasperDesign.setQuery(designquery);
            JasperReport  report=JasperCompileManager.compileReport(jasperDesign);
            JasperPrint print=JasperFillManager.fillReport(report, params, myDBcon.createDBcon());
            JasperViewer.viewReport(print);        
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }  
        else if (ae.getSource() == back){
           frame1.setVisible(false);
        }
    }       
}
    

