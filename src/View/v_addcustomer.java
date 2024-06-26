package View;
import javax.swing.*;
import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import java.awt.Image;
import Controll.c_addcus;
import java.awt.event.*;
import Controll.ValidationController;
import java.awt.Color;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class v_addcustomer implements ActionListener{
    private JFrame frame1;
    private JLabel label1,label2,label3,label4,label5,label6,label7,label8,label9,validlb;
    private JComboBox combobox1;
    private JTextField textfield1,textfield2,textfield3,textfield4,textfield5,textfield6,tf;
    private JRadioButton radiobtn1,radiobtn2;
    private JButton add,clear;
    private ButtonGroup bg;
    private boolean isFormValid = false;

    
    v_bookhotel bh;
    v_checkout chk;
    public String username;
    
    
        private void addRealTimeValidation(JTextField textField, ValidationType validationType, String errorMessage) {
    DocumentListener documentListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            validate();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            validate();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            // Plain text components don't fire these events
        }

        private void validate() {
            String text = textField.getText();
            boolean valid = false;

            switch (validationType) {
                case USERNAME:
                    valid = ValidationController.isValidUsername(text);
                    break;
                case NUMBER:
                    valid = ValidationController.isValidNumber(text);
                    break;
                case FULL_NAME:
                    valid = ValidationController.isValidFullName(text);
                    break;
                case COUNTRY:
                    valid = ValidationController.isValidCountry(text);
                    break;
                case PHONE:
                    valid = ValidationController.isValidPhone(text);
                    break;
                case EMAIL:
                    valid = ValidationController.isValidEmail(text);
                    break;
            }

            textField.setForeground(valid ? Color.BLACK : Color.RED);
             isFormValid = ValidationController.isValidUsername(textfield1.getText())
                    && ValidationController.isValidNumber(textfield6.getText())
                    && ValidationController.isValidFullName(tf.getText())
                    && ValidationController.isValidCountry(textfield2.getText())
                    && ValidationController.isValidPhone(textfield4.getText())
                    && ValidationController.isValidEmail(textfield5.getText());

            // Enable or disable the "ADD" button based on isFormValid
            add.setEnabled(isFormValid);
            
            // Set the error message in the validlb label
            if (!valid) {
                validlb.setText(errorMessage);
            } else {
                validlb.setText("");
            }
        }
    };
    textField.getDocument().addDocumentListener(documentListener);
}

    
    public void addcustomer(){
        frame1 = new JFrame("Add Customer");
        frame1.setBounds(470, 200, 850, 550);
        frame1.setBackground(WHITE);
        frame1.setLayout(null);
        frame1.setUndecorated(true);
        frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        validlb = new JLabel();//validation error label
        validlb.setBounds(30, 15, 300, 15);
        frame1.add(validlb);
        
        label1 = new JLabel("Username");
        label1.setBounds(30,50,150,25);
        frame1.add(label1);
        
        textfield1 = new JTextField();
        textfield1.setBounds(220,50,150,25);
        textfield1.setEditable(true);
        frame1.add(textfield1);
        
        label2 = new JLabel("ID");
        label2.setBounds(30,90,150,25);
        frame1.add(label2);
        
        String[] id ={"PassPort","NationalID"};
        combobox1 = new JComboBox(id);
        combobox1.setBounds(220, 90, 150, 25);
        frame1.add(combobox1);
        
        label3 = new JLabel("Number");
        label3.setBounds(30,130,150,25);
        frame1.add(label3);

        textfield6 = new JTextField();
        textfield6.setBounds(220,130,150,25);
        frame1.add(textfield6);
        
        label4 = new JLabel("Full Name");
        label4.setBounds(30,170,150,25);
        frame1.add(label4);
        
        tf = new JTextField();
        tf.setBounds(220,170,150,25);
        frame1.add(tf);
        
        label5 = new JLabel("Gender");
        label5.setBounds(30,210,150,25);
        frame1.add(label5);
        
        radiobtn1 = new JRadioButton();
        radiobtn1.setBounds(220,210,20,25);
        frame1.add(radiobtn1);
        
        JLabel lbl1 = new JLabel("Male");
        lbl1.setBounds(240, 210, 100, 25);
        frame1.add(lbl1);
        
        radiobtn2 = new JRadioButton();
        radiobtn2.setBounds(280,210,20,25);
        frame1.add(radiobtn2);
        
        bg = new ButtonGroup();
        bg.add(radiobtn1);
        bg.add(radiobtn2);
        
        JLabel lbl2 = new JLabel("Female");
        lbl2.setBounds(300, 210, 100, 25);
        frame1.add(lbl2);
        
        label6 = new JLabel("Country");
        label6.setBounds(30,250,150,25);
        frame1.add(label6);
        
        textfield2 = new JTextField();
        textfield2.setBounds(220,250,150,25);
        frame1.add(textfield2);
        
        label7 = new JLabel("Address");
        label7.setBounds(30,290,150,25);
        frame1.add(label7);
        
        textfield3 = new JTextField();
        textfield3.setBounds(220,290,150,25);
        frame1.add(textfield3);
        
        label8 = new JLabel("Phone");
        label8.setBounds(30,330,150,25);
        frame1.add(label8);
        
        textfield4 = new JTextField();
        textfield4.setBounds(220,330,150,25);
        frame1.add(textfield4);
        
        label9 = new JLabel("Email");
        label9.setBounds(30,370,150,25);
        frame1.add(label9);
        
        textfield5 = new JTextField();
        textfield5.setBounds(220,370,150,25);
        frame1.add(textfield5);
        
        addRealTimeValidation(textfield1, ValidationType.USERNAME, "Enter a valid username eg:C23");
        addRealTimeValidation(textfield6, ValidationType.NUMBER, "Enter a valid 12 numbers of ID");
        addRealTimeValidation(tf, ValidationType.FULL_NAME, "Enter a valid full name without numbers");
        addRealTimeValidation(textfield2, ValidationType.COUNTRY, "Enter a valid country name without numbers");
        addRealTimeValidation(textfield4, ValidationType.PHONE, "Enter a valid phone number starting with 07");
        addRealTimeValidation(textfield5, ValidationType.EMAIL, "Enter a valid email eg:amal@gmail.com");

        add = new JButton("ADD");
        add.setBounds(70,430,100,25);
        add.setBackground(BLACK);
        add.setForeground(WHITE);
        add.addActionListener(this);
        add.setEnabled(false);
        frame1.add(add);
        
        clear = new JButton("Clear");
        clear.setBounds(220,430,100,25);
        clear.setBackground(BLACK);
        clear.setForeground(WHITE);
        clear.addActionListener(this);
        frame1.add(clear);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/newcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400,500,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,40, 450, 420);
        frame1.add(image);
        
        frame1.setVisible(true);
        
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == add) {
       c_addcus addcus = new c_addcus();
       username = textfield1.getText();
        String idType = combobox1.getSelectedItem().toString();
        String number = textfield6.getText();
        String fullName = tf.getText();
        String country = textfield2.getText();
        String address = textfield3.getText();
        String phone = textfield4.getText();
        String email = textfield5.getText();                
        String gender = "";
        if (radiobtn1.isSelected()) {
            gender = "Male";
        } else if (radiobtn2.isSelected()) {
            gender = "Female";
        }

        addcus.addcus(username, idType, number, fullName, gender, country, address, phone, email);
        v_bookpackage bp = new v_bookpackage(username);
        bp.bookpackage();
        //bp.bookpackage(username);
        frame1.setVisible(false);                  
          
    }
 
}
     public enum ValidationType {
        USERNAME,
        NUMBER,
        FULL_NAME,
        COUNTRY,
        PHONE,
        EMAIL
    }

    public void closeFrame() {
        frame1.dispose();
    }

    public static void main(String[] args) {
            
        v_addcustomer c1 =new v_addcustomer();
        c1.addcustomer();
    }

    //void setUsername(String username) {
        //this.username = username;
   // }

}


