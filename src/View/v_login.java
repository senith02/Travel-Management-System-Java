package View;
import Model.myDBcon;
import java.awt.Color;
import static java.awt.Color.WHITE;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class v_login implements ActionListener{
    private JFrame frame1;
    private JLabel label1,label2,label3,label4,label5,label6,label7,label8,label9;
    JTextField textfield1,tf;
    JPasswordField tf1;
    JButton login,signup;
    
    public void Login(){
        frame1 = new JFrame("Login");
        frame1.setBounds(350, 200, 900, 400);
        frame1.setBackground(WHITE);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame1.getContentPane().setBackground(WHITE);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(131,193,233));
        panel.setBounds(0, 0, 400, 400);
        frame1.add(panel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/user.png"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(100,120,200,200);
        panel.setLayout(null);
        panel.add(image); 
        
        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(400, 30, 450, 300);
        frame1.add(p2);
        
        JLabel label1 = new JLabel("Username ");
        label1.setBounds(60, 20, 100, 25);
        label1.setFont(new Font("SAN SERIF",Font.PLAIN,20));
        p2.add(label1);
        
        tf = new JTextField();
        tf.setBounds(60,60,300,25);
        tf.setBorder(BorderFactory.createEmptyBorder()); 
        p2.add(tf);
        
        JLabel label2 = new JLabel("Password ");
        label2.setBounds(60, 100, 100, 25);
        label2.setFont(new Font("SAN SERIF",Font.PLAIN,20));
        p2.add(label2);
        
        tf1 = new JPasswordField();
        tf1.setBounds(60,140,300,25);
        tf1.setBorder(BorderFactory.createEmptyBorder()); 
        p2.add(tf1);
        
        login = new JButton("Login");
        login.setBounds(60,200,100,25);
        login.setBackground(new Color(133,193,233));
        login.setForeground(WHITE);
        login.setBorder(new LineBorder(new Color(133,193,233)));
        login.addActionListener(this);
        p2.add(login);
        
        /*signup = new JButton("Signup");
        signup.setBounds(220,200,100,25);
        signup.setBackground(new Color(133,193,233));
        signup.setForeground(WHITE);
        signup.setBorder(new LineBorder(new Color(133,193,233))); 
        p2.add(signup);
        
        JButton fgp = new JButton("Forget Password");
        fgp.setBounds(130,250,130,25);
        fgp.setBackground(new Color(133,193,233));
        fgp.setForeground(WHITE);
        fgp.setBorder(new LineBorder(new Color(133,193,233))); 
        p2.add(fgp);*/
        
        frame1.setVisible(true);
        
    }    

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == login){
            String username = tf.getText();
            char[] passwordChars = tf1.getPassword();
String password = new String(passwordChars);

try {
    ResultSet rst;
    Statement st = myDBcon.createDBcon().createStatement();
    rst = st.executeQuery("SELECT password FROM account WHERE username = '" + username + "'");
    
    if (rst.next()) {
        String password1 = rst.getString("password");
        if (password1 != null && password1.equals(password)) {
            JOptionPane.showMessageDialog(frame1, "Login Successful", "Login", 
                    JOptionPane.INFORMATION_MESSAGE);
            v_dashboard db = new v_dashboard();
            frame1.setVisible(false);
            db.createUI();
        } else {
            JOptionPane.showMessageDialog(frame1, "Check Login Credentials and try again");
        }
    } else {
        JOptionPane.showMessageDialog(frame1, "Username not found");
    }

    rst.close();
    st.close();
} catch (SQLException ex) {
    Logger.getLogger(v_login.class.getName()).log(Level.SEVERE, null, ex);
}

                    }
    }
    public static void main(String[] args){
        v_login log = new v_login();
        log.Login();
    }
}
