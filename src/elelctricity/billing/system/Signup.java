package elelctricity.billing.system;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener{
    JButton create, back;
    Choice accountType;
    JTextField name, password, meter, userName;
    Signup(){
        setBounds(450,150,700,400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(30,30,630,300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230),5), "New Account", TitledBorder.LEADING, TitledBorder.TOP, null));
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139,34));
        add(panel);
        
        JLabel heading = new JLabel("Create account as");
        heading.setBounds(100,50,140,20);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(heading);
        
        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(260,50,150,20);
        panel.add(accountType);
        
        
        
        JLabel lblMeter = new JLabel("Meter Number");
        lblMeter.setBounds(100,90,140,20);
        lblMeter.setForeground(Color.GRAY);
        lblMeter.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblMeter);
        lblMeter.setVisible(false);
        
        meter = new JTextField();
        meter.setBounds(260,90,150,20);
        panel.add(meter);
        meter.setVisible(false);
        
        JLabel lblUserName = new JLabel("Username");
        lblUserName.setBounds(100,130,140,20);
        lblUserName.setForeground(Color.GRAY);
        lblUserName.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblUserName);
        
        userName = new JTextField();
        userName.setBounds(260,130,150,20);
        panel.add(userName);
        
        
        JLabel lblName = new JLabel("Name");
        lblName.setBounds(100,170,140,20);
        lblName.setForeground(Color.GRAY);
        lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblName);
        
        name = new JTextField();
        name.setBounds(260,170,150,20);
        panel.add(name);
        
        meter.addFocusListener(new FocusListener(){
            
            public void focusGained(FocusEvent fe){
                
            }
            
            public void focusLost(FocusEvent fe){
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from login where meter_no = '"+meter.getText()+"'");
                    while(rs.next()){
                        name.setText(rs.getString("name"));
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            
        });
        
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(100,210,140,20);
        lblPassword.setForeground(Color.GRAY);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblPassword);
        
        password = new JTextField();
        password.setBounds(260,210,150,20);
        panel.add(password);
        
        
        accountType.addItemListener(new ItemListener(){
           public void itemStateChanged(ItemEvent ie){
               String user = accountType.getSelectedItem();
               if(user.equals("Customer")){
                   meter.setVisible(true);
                   lblMeter.setVisible(true);
                   name.setEditable(false);
               } else {
                   meter.setVisible(false);
                   lblMeter.setVisible(false);
                   name.setEditable(false);
               }
           } 
            
        });
        
        
        create = new JButton("Create");
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.setBounds(140,260,120,25);
        create.addActionListener(this);
        panel.add(create);
        
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(300,260,120,25);
        back.addActionListener(this);
        panel.add(back);
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(405,30,250,250);
        panel.add(image);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == create){
            String aType = accountType.getSelectedItem();
            String sUserName = userName.getText();
            String sName = name.getText();
            String sPassword = password.getText();
            String sMeter = meter.getText();
            
            try{
                Conn c = new Conn();
                String query = null;
                if(aType.equals("Admin")){
                    query = "insert into login values('"+sMeter+"', '"+sUserName+"', '"+sName+"', '"+sPassword+"', '"+aType+"')";
                } else {
                    query = "update login set userName = '"+sUserName+"' , password = '"+sPassword+"', user = '"+aType+"' where meter_no = '"+sMeter+"'";
                }
                
                
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Account created successfully");
                
                setVisible(false);
                new Login();
                
            }catch(Exception e){
                e.printStackTrace();
                
            }
        } else if(ae.getSource() == back){
            setVisible(false);
            
            new Login();
        }
    }
    
    public static void main(String[] args){
        new Signup();
    }
}
