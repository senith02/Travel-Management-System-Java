package View;
import Controll.ValidationController;
import Controll.c_bookhotel;
import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.ActiveEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import Model.myDBcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Date;
import org.jdesktop.swingx.JXDatePicker;

public class v_bookhotel implements ActionListener{
     private JFrame frame1;
    private JLabel label1,label2,label3,label4,label5,label6,label7,costlabel,label9,l1,l2,l3,l4;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
    private JComboBox combobox1,tf1;
    private JTextField textfield6;
    private JButton book,back;
    int noOfDays;
    double cost;
    JXDatePicker datePicker1,datePicker;
    Date checkinDate,checkoutDate;
    private String username;
    v_addcustomer addc;

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void bookhotel(String username){
        
        frame1 = new JFrame("Book Hotel");
        frame1.setBounds(460, 200, 880, 500);
        frame1.setBackground(WHITE);
        frame1.setUndecorated(true);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        JLabel label = new JLabel("Book Hotel");
        label.setBounds(100,10,200,30);
        label.setFont(new Font("Tahoma",Font.BOLD,20)); 
        frame1.add(label);
        
        label1 = new JLabel("Username");
        label1.setBounds(30,50,150,25);
        frame1.add(label1);
        
        addc = new v_addcustomer();
        l1 = new JLabel(addc.username);
        l1.setText(username);
        l1.setBounds(220,50,150,25);
        frame1.add(l1); 
        
        label2 = new JLabel("Select Hotel");
        label2.setBounds(30,90,150,25);
        frame1.add(label2);
        
        String[] id ={"Shrangila","Araliya","Hilton","Kingsbury"};
        combobox1 = new JComboBox(id);
        combobox1.setBounds(220, 90, 150, 25);
        frame1.add(combobox1);
        
        label3 = new JLabel("Total Persons");
        label3.setBounds(30,130,150,25);
        frame1.add(label3);
        
        textfield6 = new JTextField();
        textfield6.setBounds(220,130,150,25);
        frame1.add(textfield6);
        

       textfield6.addFocusListener(new FocusAdapter() {
    @Override
    public void focusLost(FocusEvent e) {
        String input = textfield6.getText().trim(); // Get the text and remove leading/trailing spaces
        
        try {
            int no_persons = Integer.parseInt(input); // Attempt to parse the input as an integer
            // Valid integer, no action needed
        } catch (NumberFormatException ex) {
            // Parsing failed, display an error message and clear the text field
            JOptionPane.showMessageDialog(frame1, "Please enter a valid integer for Total Persons.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            textfield6.setText(""); // Clear the text field
        }
        
        // Calculate and display the cost (you can call calculateAndDisplayCost() here if you want)
        calculateAndDisplayCost();
    }
});
        
        JLabel datelb = new JLabel("Check In Date");
        datelb.setBounds(30, 170, 150, 25);
        frame1.add(datelb);
        
        datePicker = new JXDatePicker();
        datePicker.setBounds(220, 170, 150, 25);
        datePicker.setDate(new Date());
        datePicker.getMonthView().setLowerBound(new Date());// Restrict selection to future dates only
        frame1.add(datePicker);
        
        
        label4 = new JLabel("Check Out Date");
        label4.setBounds(30,210,150,25);
        frame1.add(label4);
        
        datePicker1 = new JXDatePicker();
        datePicker1.setBounds(220, 210, 150, 25);
        datePicker1.setDate(new Date());
        datePicker1.getMonthView().setLowerBound(new Date());// Restrict selection to future dates only
        frame1.add(datePicker1);
        
        datePicker.addActionListener(e -> {
    checkinDate = datePicker.getDate();
    checkoutDate = datePicker1.getDate();
    
    if (checkinDate != null && checkoutDate != null) {
        long differenceInMillis = checkoutDate.getTime() - checkinDate.getTime();
        noOfDays = (int) (differenceInMillis / (1000 * 60 * 60 * 24));

        // Update the lower bound of the check-out date picker
        datePicker1.getMonthView().setLowerBound(checkinDate);
    }
    });
         datePicker.addActionListener(e -> calculateAndDisplayCost());
       datePicker1.addActionListener(e -> calculateAndDisplayCost());
        
        JLabel lbl = new JLabel("Ac/nonAC");
        lbl.setBounds(30,250,150,25);
        frame1.add(lbl);
        
        String[] ac = {"AC","Non-AC"};
        tf1 = new JComboBox(ac);
        tf1.setBounds(220,250,150,25);
        frame1.add(tf1);
        tf1.addActionListener(e -> calculateAndDisplayCost());

        
        label5 = new JLabel("ID Number");
        label5.setBounds(30,290,150,25);
        frame1.add(label5);
        
        l2 = new JLabel();
        l2.setBounds(220,290,150,25);
        frame1.add(l2);
        
        
        //textfield7 = new JTextField();
       // textfield7.setBounds(220,290,150,25);
        //frame1.add(textfield7);
        
        label6 = new JLabel("Phone");
        label6.setBounds(30,330,150,25);
        frame1.add(label6);
        
        l3 = new JLabel();
        l3.setBounds(220,330,150,25);
        frame1.add(l3);
        
        label7 = new JLabel("Cost (Rs)");
        label7.setBounds(30,370,150,25);
        frame1.add(label7);
        
        costlabel = new JLabel();
        costlabel.setBounds(220, 370, 250, 25);
        frame1.add(costlabel);
        
        book = new JButton("Book");
        book.setBounds(70,430,100,25);
        book.setBackground(BLACK);
        book.setForeground(WHITE);
        book.addActionListener(this);
        frame1.add(book);
        
        back = new JButton("BACK");
        back.setBounds(220,430,100,25);
        back.setBackground(BLACK);
        back.setForeground(WHITE);
        frame1.add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/hotel10.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600,450,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,40, 450, 380);
        frame1.add(image);
        
        try {
            ResultSet rst;
            Statement st=myDBcon.createDBcon().createStatement();
            rst = st.executeQuery("select * from customer where username = '"+username+"'");

            if (rst.next()) {
                l2.setText(rst.getString("number"));
                l3.setText(rst.getString("phone"));
            } else {
                // Handle the case where no matching username is found
                JOptionPane.showMessageDialog(null, "Please Add Customer Before Book Hotel");
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
        if (ae.getSource() == book) {
        c_bookhotel book = new c_bookhotel();
        String username = l1.getText();
        String HotelName = combobox1.getSelectedItem().toString();
        String no_persons =textfield6.getText();
        java.util.Date checkinDate = datePicker.getDate(); // Get the selected check-in date
        java.util.Date checkoutDate = datePicker1.getDate();;
        String phone = l3.getText();
        String number = l2.getText();
        double price = Double.parseDouble(costlabel.getText());
        String AC = tf1.getSelectedItem().toString();
        
        java.sql.Date checkindate = new java.sql.Date(checkinDate.getTime());
        java.sql.Date checkoutdate = new java.sql.Date(checkoutDate.getTime());

        
        book.bookhotel(username, HotelName, no_persons, checkindate, checkoutdate, AC, number, phone, price);
        
        v_checkout chk = new v_checkout();
        chk.Checkout(username);
        frame1.setVisible(false);
    }
  }
    // Method to calculate and display the cost
    private void calculateAndDisplayCost() {
    String no_personsStr = textfield6.getText();

    // Check if no_persons is a valid integer
    int no_persons = 0;
    try {
        no_persons = Integer.parseInt(no_personsStr); // Attempt to parse the input as an integer
    } catch (NumberFormatException ex) {
        // Parsing failed, display an error message and clear the text field
        costlabel.setText("Invalid number of persons");
        return;
    }
    if (!isNumberOfPersonsValid()) {
        costlabel.setText("Invalid number of persons\n (1-20 allowed)");
        return;
    }

    java.util.Date checkinDate = datePicker.getDate(); // Get the selected check-in date
    java.util.Date checkoutDate = datePicker1.getDate();

    if (checkinDate == null || checkoutDate == null) {
        costlabel.setText("Select check-in and check-out dates");
        return;
    }

    long differenceInMillis = checkoutDate.getTime() - checkinDate.getTime();
    noOfDays = (int) (differenceInMillis / (1000 * 60 * 60 * 24));

    // Calculate the cost based on the selection in tf1 (AC or Non-AC)
    double costPerPerson = 0;
    String selectedOption = tf1.getSelectedItem().toString();
    if ("AC".equals(selectedOption)) {
        costPerPerson = 5000.0; // Cost per person for AC
    } else if ("Non-AC".equals(selectedOption)) {
        costPerPerson = 2500.0; // Cost per person for Non-AC
    }

    // Calculate the total cost
    double cost = no_persons * noOfDays * costPerPerson;

    // Update the costlabel
    costlabel.setText(Double.toString(cost));
}
    public void closeFrame() {
        frame1.dispose();
    }
    
    private boolean isNumberOfPersonsValid() {
    String numberOfPersonsStr = textfield6.getText();
    return ValidationController.isValidNumberOfPersons(numberOfPersonsStr);
}


    public static void main(String[] args){
        v_bookhotel bh = new v_bookhotel();
        bh.bookhotel("");
    }
}    
