package View;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionEvent;

import java.awt.event.ActionEvent;

public class v_dashboard implements ActionListener{
    JFrame frame1;
    JPanel p1,p2;
    JButton addcus,bpackage,bhotel,chkout,reports;
    v_addcustomer addc;
    v_bookpackage bp;
    v_bookhotel bh;
    v_checkout chk;
    String username1;
    
    public v_dashboard() {
        // Initialize all frame objects to null in the constructor
        addc = null;
        bp = null;
        bh = null;
        chk = null;
        addc = new v_addcustomer();
        
    }
    
    /*public void setUsername(String username){
        
        this.username = username;
    }*/
    
    public void createUI(){
        frame1 = new JFrame("Dashboard");
        frame1.setBounds(250,50, 1100, 750);
        //frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(null);
        
        
        
        
        p1 = new JPanel();
        p1.setBounds(0,0,1600,80);
        p1.setLayout(null);
        p1.setBackground(new Color(82,127,238));
        frame1.add(p1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/dashboard.png"));
        Image i2 = i1.getImage().getScaledInstance(70, 70,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2); 
	JLabel icon = new JLabel(i3);
	icon.setBounds(5, 0, 70, 70); 
        p1.add(icon);
        
        ImageIcon im1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image im2 = im1.getImage().getScaledInstance(1100, 850,Image.SCALE_DEFAULT);
        ImageIcon im3 = new ImageIcon(im2); 
	JLabel icon1 = new JLabel(im3);
	icon1.setBounds(200, 80, 1100, 850); 
        frame1.add(icon1);
        
        JLabel l1 = new JLabel("Travel and Tourism Management System");
	l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Tahoma", Font.PLAIN, 25));
	l1.setBounds(350, 0, 800, 100);
	p1.add(l1);
        
        JLabel heading = new JLabel("Dashboard");
        heading.setBounds(80, 5, 200, 70);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        p1.add(heading);
        
        p2 = new JPanel();
        p2.setBounds(0,65,200,900);
        p2.setLayout(null);
        p2.setBackground(new Color(82,127,238));
        frame1.add(p2);
        
        addcus = new JButton("Add Cusotmer");
        addcus.setBounds(0, 150, 200, 50);
        addcus.setBackground(new Color(184,174,239));
        addcus.setFont(new Font("Tahoma", Font.PLAIN, 16)); 
        addcus.setForeground(new Color(51,0,102));
        p2.add(addcus);
        addcus.addActionListener(this);
        
        bpackage = new JButton("Book Package");
        bpackage.setBounds(0, 200, 200, 50);
        bpackage.setBackground(new Color(184,174,239));
        bpackage.setFont(new Font("Tahoma", Font.PLAIN, 16)); 
        bpackage.setForeground(new Color(51,0,102));
        p2.add(bpackage);
        bpackage.addActionListener(this);
        
        bhotel = new JButton("Book Hotel");
        bhotel.setBounds(0, 250, 200, 50);
        bhotel.setBackground(new Color(184,174,239));
        bhotel.setFont(new Font("Tahoma", Font.PLAIN, 16)); 
        bhotel.setForeground(new Color(51,0,102));
        p2.add(bhotel);
        bhotel.addActionListener(this);
        
        chkout = new JButton("Checkout");
        chkout.setBounds(0, 300, 200, 50);
        chkout.setBackground(new Color(184,174,239));
        chkout.setFont(new Font("Tahoma", Font.PLAIN, 16)); 
        chkout.setForeground(new Color(51,0,102));
        p2.add(chkout);
        chkout.addActionListener(this);
        
        reports = new JButton("Reports");
        reports.setBounds(0, 350, 200, 50);
        reports.setBackground(new Color(184,174,239));
        reports.setFont(new Font("Tahoma", Font.PLAIN, 16)); 
        reports.setForeground(new Color(51,0,102));
        p2.add(reports);
        reports.addActionListener(this);
        
        frame1.setVisible(true);
    }
    
    
    public static void main(String[] args){
        v_dashboard v = new v_dashboard();
        v.createUI();
    }

    @Override
   public void actionPerformed(ActionEvent ae) {
       addc = new v_addcustomer();
    if (ae.getSource() == addcus) {
        addc = new v_addcustomer(); 
        addc.addcustomer();
    } else if (ae.getSource() == bpackage) {
        bp = new v_bookpackage(username1);
        // Set the username for the bookpackage form
        bp.bookpackage();
    } else if (ae.getSource() == bhotel) {
        bh = new v_bookhotel(); // Set the username for the bookhotel form
        bh.bookhotel(addc.username);
    } else if (ae.getSource() == chkout) {
        chk = new v_checkout(); // Set the username for the checkout form
        chk.Checkout(addc.username);
    } else if(ae.getSource() == reports){
        reports rp = new reports();
        rp.setVisible(true);
    }
}

}
