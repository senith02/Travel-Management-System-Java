package View;
import Controll.c_bookpackage;
import Model.myDBcon;
import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class v_bookpackage implements ActionListener{
     private JFrame frame1, frame2;
    private JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9, lb, l1, l2, l3; 
    private JComboBox<String> combobox1;
    private JTextField textfield3, textfield4, textfield6;
    private JButton book, back;
    private JPanel packageDetailPanel;
    private String initialPackage;
    private JSpinner spinner;
    public String username;
    

    public v_bookpackage(String username) {
        // Initialize the combobox1 before using it to set initialPackage
        String[] id = {"Gold", "Silver", "Bronze"};
        combobox1 = new JComboBox<>(id);
        this.username = username;
        // Now you can initialize initialPackage
        initialPackage = combobox1.getSelectedItem().toString();
        
    }
    
    public void bookpackage(){
        
        frame1 = new JFrame("Book Package");
        frame1.setBounds(500, 200, 800, 500);
        frame1.setBackground(WHITE);
        frame1.setUndecorated(true);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        JLabel label = new JLabel("Book Package");
        label.setBounds(100,15,200,30);
        label.setFont(new Font("Tahoma",Font.BOLD,20)); 
        frame1.add(label);
        
        label1 = new JLabel("Username");
        label1.setBounds(30,55,150,25);
        frame1.add(label1);
        
        lb = new JLabel(username);
        lb.setBounds(220,55,150,25);
        frame1.add(lb);
        
        label2 = new JLabel("Select Package");
        label2.setBounds(30,95,150,25);
        frame1.add(label2);
        
         String[] id = {"Gold", "Silver", "Bronze"};
        combobox1 = new JComboBox<>(id);
        combobox1.setBounds(220, 95, 150, 25);
        frame1.add(combobox1);
         
        
        label3 = new JLabel("Total Persons");
        label3.setBounds(30,135,150,25);
        frame1.add(label3);

        /*textfield6 = new JTextField();
        textfield6.setBounds(220,135,150,25);
        frame1.add(textfield6);*/
        
        SpinnerModel model = new SpinnerNumberModel(1, 1, 10, 1);
        spinner = new JSpinner(model);
        spinner.setBounds(220, 135, 50, 25);
        frame1.add(spinner);
        
        spinner.addChangeListener(new ChangeListener() {
    @Override
    public void stateChanged(ChangeEvent e) {
        // Handle changes in the selected number of persons here
        int numberOfPersons = (int) spinner.getValue();
        String selectedPackage = combobox1.getSelectedItem().toString();
        int cost = calculatePackageCost(selectedPackage, numberOfPersons);
        label9.setText(Integer.toString(cost));
        // You can update other components or perform calculations based on the selected number of persons
    }
});


        
        label4 = new JLabel("ID");
        label4.setBounds(30,175,150,25);
        frame1.add(label4);
        
        l1 = new JLabel();
        l1.setBounds(220,175,150,25);
        frame1.add(l1);
        
        label5 = new JLabel("ID Number");
        label5.setBounds(30,215,150,25);
        frame1.add(label5);
        
        l3 = new JLabel();
        l3.setBounds(220,215,150,25);
        frame1.add(l3);
        
        label6 = new JLabel("Phone");
        label6.setBounds(30,255,150,25);
        frame1.add(label6);
        
        l2 = new JLabel();
        l2.setBounds(220,255,150,25);
        frame1.add(l2);
        
        label7 = new JLabel("Price Rs :");
        label7.setBounds(30,295,150,25);
        frame1.add(label7);
        
        label9 = new JLabel();
        label9 .setBounds(220,295,150,25);
        frame1.add(label9 );
        
        
        book = new JButton("BOOK");
        book.setBounds(70,375,100,25);
        book.setBackground(BLACK);
        book.setForeground(WHITE);
        book.addActionListener(this);
        frame1.add(book);
        
        back = new JButton("BACK");
        back.setBounds(220,375,100,25);
        back.setBackground(BLACK);
        back.setForeground(WHITE);
        frame1.add(back);
    
        /*ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/package1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,450,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(550,40, 450, 380);
        frame1.add(image);*/
        
        
        packageDetailPanel = new JPanel();
        packageDetailPanel.setBounds(400, 40, 350, 280);
        packageDetailPanel.setBackground(Color.WHITE);
        frame1.add(packageDetailPanel);
        combobox1.addActionListener(this);
        initialPackage = combobox1.getSelectedItem().toString();

    // Call updatePackageDetails with the initial package name
        updatePackageDetails(initialPackage);
       
        try {
            ResultSet rst;
            Statement st=myDBcon.createDBcon().createStatement();
            rst = st.executeQuery("select * from customer where username = '"+username+"'");

            if (rst.next()) {
                l1.setText(rst.getString("id_type"));
                l2.setText(rst.getString("phone"));
                l3.setText(rst.getString("number"));
            } else {
                // Handle the case where no matching username is found
                JOptionPane.showMessageDialog(null, "Please Add Customer Before Book Package");
            }

            rst.close();
            st.close();
} 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
}
        
        frame1.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == combobox1) {
        String selectedPackage = combobox1.getSelectedItem().toString();
        updatePackageDetails(selectedPackage);
    } else if (ae.getSource() == book) {
        String selectedPackage = combobox1.getSelectedItem().toString();
        int numberOfPersons = (int) spinner.getValue();

        // Calculate the cost based on the selected package and number of persons
        int cost = calculatePackageCost(selectedPackage, numberOfPersons);

        // Update the label9 with the calculated cost
        label9.setText(Integer.toString(cost));

        // Rest of your book button logic here
        c_bookpackage book1 = new c_bookpackage();
        username = lb.getText();
        String PackageName = combobox1.getSelectedItem().toString();
        String no_persons = String.valueOf(numberOfPersons);
        String phone = l2.getText();
        String number = l3.getText();
        double price = Double.parseDouble(label9.getText());
        String id = l1.getText();

        book1.bookpackage(username, PackageName, no_persons, id, number, phone, price);
        
        v_bookhotel bh = new v_bookhotel();
        bh.bookhotel(username);
        frame1.setVisible(false);
    }
        
        
        
    }
    
    private void updatePackageDetails(String packageName) {
    // Clear the package details panel
    packageDetailPanel.removeAll();
     int y = 60;
    // Create a label to display the package name
    JLabel packageLabel = new JLabel(packageName + " Package Details");
    packageLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
    packageLabel.setBounds(10, 10, 400, 45);
    packageDetailPanel.add(packageLabel);
    
    JTextPane detailPane = new JTextPane();
    detailPane.setEditable(false);
    detailPane.setContentType("text/html"); // Use HTML for formatting
    detailPane.setBackground(Color.WHITE);
    detailPane.setBounds(10, 80, 450, 380);

    // Add package-specific details based on the selected package
    StringBuilder details = new StringBuilder();
   if (packageName.equals("Gold")) {
        int x = 75000;
        details.append("<html><font size='5' color='blue'>• 6 days and 7 nights</font><br>");
        details.append("<font size='5' color='green'>• Airport Assistance</font><br>");
        details.append("<font size='5' color='red'>• Full Day City Tour</font><br>");
        details.append("<font size='5' color='green'>• Daily Buffet</font><br>");
        details.append("<font size='5' color='blue'>• Welcome Drinks on Arrival</font><br>");
        details.append("<font size='5' color='red'>• Full Day 3 Island Cruise</font><br>");
        details.append("<font size='5'>• English Speaking Guide</font><br>");
        details.append("<font size='5' color='red'>• Book now Summer Special Rs: " + x + "</font></html>");
    } else if (packageName.equals("Silver")) {
        int x = 60000;
        details.append("<html><font size='5' color='blue'>• 4 Days and 5 nights</font><br>");
        details.append("<font size='5' color='green'>• Airport Assistance</font><br>");
        details.append("<font size='5' color='red'>• Half Day City Tour</font><br>");
        details.append("<font size='5' color='blue'>• Welcome Drinks on Arrival</font><br>");
        details.append("<font size='5'>• English Speaking Guide</font><br>");
        details.append("<font size='5' color='red'>• Book now Summer Special Rs: " + x + "</font></html>");
    } else if (packageName.equals("Bronze")) {
        int x = 40000;
        details.append("<html><font size='5' color='blue'>• 2 Days and 3 nights</font><br>");
        details.append("<font size='5' color='green'>• Airport Assistance</font><br>");
        details.append("<font size='5' color='red'>• Half Day City Tour</font><br>");
        details.append("<font size='5'>• English Speaking Guide</font><br>");
        details.append("<font size='5' color='red'>• Book now Summer Special Rs: " + x + "</font></html>");
    }
    
    //detailPane.setText("<html>" + details.toString() + "</html>");
    detailPane.setText(details.toString());
    packageDetailPanel.add(detailPane);
    // Repaint the panel to reflect the changes
    packageDetailPanel.revalidate();
    packageDetailPanel.repaint();
    calculateAndSetCost();
}
    public void closeFrame() {
        frame1.dispose();
    }
    private void calculateAndSetCost() {
    String selectedPackage = combobox1.getSelectedItem().toString();
    int numberOfPersons = (int) spinner.getValue();
    int cost = calculatePackageCost(selectedPackage, numberOfPersons);
    label9.setText(Integer.toString(cost)); // Convert int to String
}

    
    private int calculatePackageCost(String packageName, int numberOfPersons) {
    int basePrice = 0;

    // Determine the base price based on the selected package
    if (packageName.equals("Gold")) {
        basePrice = 75000;
    } else if (packageName.equals("Silver")) {
        basePrice = 60000;
    } else if (packageName.equals("Bronze")) {
        basePrice = 40000;
    }

    // Calculate the total cost
    int totalCost = basePrice * numberOfPersons;
    return totalCost;
}

    
    public static void main(String[] args) {
    
    v_bookpackage bp = new v_bookpackage("");
    bp.bookpackage();
}
   
}

