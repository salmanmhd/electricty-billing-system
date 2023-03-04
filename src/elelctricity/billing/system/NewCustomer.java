
package elelctricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class NewCustomer extends JFrame implements ActionListener{
    
    JTextField tfName, tfAddress, tfCity, tfState, tfEmail, tfPhone;
    JButton next, cancel;
    JLabel lblMeterNo;
    NewCustomer(){
        
        setSize(700,500);
        setLocation(400,200);
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);
        
        JLabel heading = new JLabel("New Customer");
        heading.setBounds(180,10,200,25);
        heading.setFont(new Font("Tahoma", Font.PLAIN,24));
        p.add(heading);
        
        
        //customer name
        JLabel lblName = new JLabel("Customer Name");
        lblName.setBounds(100,80,100,20);
        p.add(lblName);
        
        tfName = new JTextField();
        tfName.setBounds(240, 80, 200,25);
        p.add(tfName);
        
        //meter number
        JLabel lblMeter = new JLabel("Meter Number");
        lblMeter.setBounds(100,120,100,20);
        p.add(lblMeter);
        
        lblMeterNo = new JLabel("");
        lblMeterNo.setBounds(240,120,100,20);
        p.add(lblMeterNo);
        
        Random ran = new Random();
        long number = ran.nextLong()%1000000;
        lblMeterNo.setText("" + Math.abs(number));
        
        
        JLabel lblAddress = new JLabel("Adress");
        lblAddress.setBounds(100,160,100,20);
        p.add(lblAddress);
        
        tfAddress = new JTextField();
        tfAddress.setBounds(240, 160, 200,25);
        p.add(tfAddress);
        
        //CITY
        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(100,200,100,20);
        p.add(lblCity);
        
        tfCity = new JTextField();
        tfCity.setBounds(240, 200, 200,25);
        p.add(tfCity);
        
        //State
        JLabel lblState = new JLabel("State");
        lblState.setBounds(100,240,100,20);
        p.add(lblState);
        
        tfState = new JTextField();
        tfState.setBounds(240, 240, 200,25);
        p.add(tfState);
        
        
        //email
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(100,280,100,20);
        p.add(lblEmail);
        
        tfEmail = new JTextField();
        tfEmail.setBounds(240, 280, 200,25);
        p.add(tfEmail);
        
        
        //Phone
        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(100,320,100,20);
        p.add(lblPhone);
        
        tfPhone = new JTextField();
        tfPhone.setBounds(240, 320, 200,25);
        p.add(tfPhone);
        
        
        //BUTTONS
        next = new JButton("Next");
        next.setBounds(120,390,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(250,390,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);
        
        setLayout(new BorderLayout());
        add(p, "Center");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");
        
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == next){
            String name = tfName.getText();
            String meter = lblMeterNo.getText();
            String address = tfAddress.getText();
            String city = tfCity.getText();
            String state = tfState.getText();
            String email = tfEmail.getText();
            String phone = tfPhone.getText();
            
            String query1 = "insert into customer value('"+name+"', '"+meter+"', '"+address+"', '"+city+"', '"+state+"', '"+email+"', '"+phone+"')";   
            String query2 = "insert into login value ('"+meter+"','', '"+name+"','','')";
            
            
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "Customer details added successfully");
                setVisible(false);
                
                //new Frame
                new MeterInfo(meter);
                
            } catch(Exception e){
                e.printStackTrace();
            }
            
        } else{
            setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new NewCustomer();
    }
    
}
