
package elelctricity.billing.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

    public class Login extends JFrame implements ActionListener{
        JButton login, cancel, signup;
        JTextField password, username;
        Choice loginas;
        Login(){
            super("Login Page");
            getContentPane().setBackground(Color.WHITE);
            setLayout(null);
            
            JLabel lblusername = new JLabel("Username");
            lblusername.setBounds(300, 20, 100, 20);
            add(lblusername);
            
            username = new JTextField();
            username.setBounds(400,20,150,20);
            add(username);
            
            JLabel lblpassword = new JLabel("Password");
            lblpassword.setBounds(300, 60, 100, 20);
            add(lblpassword);
            
            password = new JTextField();
            password.setBounds(400,60,150,20);
            add(password);
            
            JLabel lblloginas = new JLabel("Login in as");
            lblloginas.setBounds(300, 100, 100, 20);
            add(lblloginas);
            
            loginas = new Choice();
            loginas.add("Admin");
            loginas.add("Customer");
            loginas.setBounds(400,100,150,20);
            add(loginas);
            
            
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
            Image i2 = i1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
            login = new JButton("Login", new ImageIcon(i2));
            login.setBounds(330,160,100,20);
            login.addActionListener(this);
            add(login);
            
            ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
            Image i4 = i3.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
             cancel = new JButton("cancel", new ImageIcon(i4));
            cancel.setBounds(450,160,100,20);
            cancel.addActionListener(this);
            add(cancel);
            
            ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
            Image i6 = i5.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
            signup = new JButton("Sign up", new ImageIcon(i6));
            signup.setBounds(380,200,100,20);
            signup.addActionListener(this);
            add(signup);
            
            ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
            Image i8 = i7.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
            ImageIcon i9 = new ImageIcon(i8);
            JLabel image = new JLabel(i9);
            image.setBounds(0,0,250,250);
            add(image);
            
            
            setSize(640,300);
            setLocation(490, 250);
            setVisible(true);
        }
        
    public void actionPerformed(ActionEvent ae){
            if(ae.getSource() == login){
                String sUserName = username.getText();
                String sPassword = password.getText();
                String user = loginas.getSelectedItem();
                
                try{
                    Conn c = new Conn();
                    String query = "select * from login where userName = '"+sUserName+"' and password = '"+sPassword+"' and user = '"+user+"'";
                    
                   ResultSet rs = c.s.executeQuery(query);
                   
                   if(rs.next()){
                       String meter = rs.getString("meter_no");
                       setVisible(false);
                       new Project(user, meter);
                   } else{
                       JOptionPane.showMessageDialog(null, "Invalid Login!");
                       username.setText("");
                       password.setText("");
                   }
                }catch(Exception e){
                    e.printStackTrace();
                }
                
            } else if(ae.getSource() == cancel ){
                setVisible(false);
            } else if(ae.getSource() == signup){
                setVisible(false);
                
                new Signup();
            }
    }
    
        public static void main(String[] args){
          new Login();
        }
}

