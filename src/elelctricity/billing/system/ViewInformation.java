
package elelctricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class ViewInformation extends JFrame implements ActionListener{
    
    JButton cancel;
    String meter;
    ViewInformation(String meter){
//        super = "View Information";
          this.meter = meter;
          setBounds(350,150,850,650);
          getContentPane().setBackground(Color.WHITE);
          setLayout(null);
          
          JLabel heading = new JLabel ("VIEW CUSTOMER INFORMATION");
          heading.setBounds(250,0,500,40);
          heading.setFont(new Font("Tahoma", Font.BOLD, 20));
          add(heading);
          
          //NAME
          JLabel lblName = new JLabel ("Name");
          lblName.setBounds(70,80,100,20);
          add(lblName);
          
          JLabel name = new JLabel ("");
          name.setBounds(250,80,100,20);
          add(name);

          
          //MERTER NUMBER
          JLabel lblMeterNumber = new JLabel ("Meter Number");
          lblMeterNumber.setBounds(70,140,100,20);
          add(lblMeterNumber);
          
          JLabel meterNumber = new JLabel ("");
          meterNumber.setBounds(250,140,100,20);
          add(meterNumber);
          
          
          //ADDRESS
          JLabel lblAddress = new JLabel ("Address");
          lblAddress.setBounds(70,200,100,20);
          add(lblAddress);
          
          JLabel address = new JLabel ("");
          address.setBounds(250,200,100,20);
          add(address);
          
          //CITY
          JLabel lblCity = new JLabel ("City");
          lblCity.setBounds(70,260,100,20);
          add(lblCity);
          
          JLabel city = new JLabel ("");
          city.setBounds(250,260,100,20);
          add(city);
          
          //STATE
          JLabel lblState = new JLabel ("State");
          lblState.setBounds(500,80,100,20);
          add(lblState);
          
          JLabel state = new JLabel ("");
          state.setBounds(650,80,100,20);
          add(state);
          
          //EMAIL
          JLabel lblEmail = new JLabel ("Email");
          lblEmail.setBounds(500,140,100,20);
          add(lblEmail);
          
          JLabel email = new JLabel ("");
          email.setBounds(650,140,100,20);
          add(email);
          
          //PHONE
          JLabel lblPhone = new JLabel ("Phone");
          lblPhone.setBounds(500,200,100,20);
          add(lblPhone);
          
          JLabel phone = new JLabel ("");
          phone.setBounds(650,200,100,20);
          add(phone);
          
          try{
              Conn c = new Conn();
              ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
              while(rs.next()){
                  name.setText(rs.getString("name"));
                  meterNumber.setText(rs.getString("meter_no"));
                  address.setText(rs.getString("address"));
                  city.setText(rs.getString("city"));
                  state.setText(rs.getString("state"));
                  email.setText(rs.getString("email"));
                  phone.setText(rs.getString("phone"));
              }
              
          } catch (Exception e){
              e.printStackTrace();
          }
          
          //BUTTON
          cancel = new JButton("Cancel");
          cancel.setBackground(Color.BLACK);
          cancel.setForeground(Color.WHITE);
          cancel.setBounds(350,340,100,25);
          add(cancel);
          cancel.addActionListener(this);
          
          ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
          Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
          ImageIcon i3 = new ImageIcon(i2);
          JLabel image = new JLabel(i3);
          image.setBounds(20,350,600,300);
          add(image);
          
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
    }
    
    public static void main(String[] args){
        new ViewInformation("");
    }
}
