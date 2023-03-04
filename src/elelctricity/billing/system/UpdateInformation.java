
package elelctricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; 

public class UpdateInformation extends JFrame implements ActionListener{
        
        JButton cancel, update;
        JTextField tfAddress, tfCity, tfState, tfEmail, tfPhone;
        String meter;
        
        UpdateInformation(String meter){
            this.meter = meter;
            setBounds(300,150,1050,450);
            getContentPane().setBackground(Color.WHITE);
            setLayout(null);

            
            JLabel heading = new JLabel ("UPDATE CUSTOMER INFORMATION");
          heading.setBounds(110,0,400,30);
          heading.setFont(new Font("Tahoma", Font.BOLD, 20));
          add(heading);
          
          //NAME
          JLabel lblName = new JLabel ("Name");
          lblName.setBounds(30,70,100,20);
          add(lblName);
          
          JLabel name = new JLabel ("");
          name.setBounds(230,70,200,20);
          add(name);

          
          //MERTER NUMBER
          JLabel lblMeterNumber = new JLabel ("Meter Number");
          lblMeterNumber.setBounds(30,110,100,20);
          add(lblMeterNumber);
          
          JLabel meterNumber = new JLabel ("");
          meterNumber.setBounds(230,110,200,20);
          add(meterNumber);
          
          
          //ADDRESS
          JLabel lblAddress = new JLabel ("Address");
          lblAddress.setBounds(30,150,100,20);
          add(lblAddress);
          
          tfAddress = new JTextField ();
          tfAddress.setBounds(230,150,200,20);
          add(tfAddress);
          
          //CITY
          JLabel lblCity = new JLabel ("City");
          lblCity.setBounds(30,190,100,20);
          add(lblCity);
          
          tfCity = new JTextField ();
          tfCity.setBounds(230,190,200,20);
          add(tfCity);
          
          //STATE
          JLabel lblState = new JLabel ("State");
          lblState.setBounds(30,230,100,20);
          add(lblState);
          
          tfState = new JTextField ();
          tfState.setBounds(230,230,200,20);
          add(tfState);
          
          //EMAIL
          JLabel lblEmail = new JLabel ("Email");
          lblEmail.setBounds(30,270,100,20);
          add(lblEmail);
          
          tfEmail = new JTextField ();
          tfEmail.setBounds(230,270,200,20);
          add(tfEmail);
          
          //PHONE
          JLabel lblPhone = new JLabel ("Phone");
          lblPhone.setBounds(30,310,100,20);
          add(lblPhone);
          
          tfPhone = new JTextField ();
          tfPhone.setBounds(230,310,200,20);
          add(tfPhone);
          
          try{
              Conn c = new Conn();
              ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
              while(rs.next()){
                  name.setText(rs.getString("name"));
                  meterNumber.setText(rs.getString("meter_no"));
                  tfAddress.setText(rs.getString("address"));
                  tfCity.setText(rs.getString("city"));
                  tfState.setText(rs.getString("state"));
                  tfEmail.setText(rs.getString("email"));
                  tfPhone.setText(rs.getString("phone"));
              }
              
          } catch (Exception e){
              e.printStackTrace();
          }
          
          //BUTTON
          update = new JButton("Update");
          update.setBackground(Color.BLACK);
          update.setForeground(Color.WHITE);
          update.setBounds(70,360,100,25);
          add(update);
          update.addActionListener(this);
          
          
          cancel = new JButton("Cancel");
          cancel.setBackground(Color.BLACK);
          cancel.setForeground(Color.WHITE);
          cancel.setBounds(230,360,100,25);
          add(cancel);
          cancel.addActionListener(this);
          
          ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
          Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
          ImageIcon i3 = new ImageIcon(i2);
          JLabel image = new JLabel(i3);
          image.setBounds(550,50,400,300);
          add(image);
            
            
            setVisible(true);
        }
        
//        @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== update){
            String address = tfAddress.getText();
            String city = tfCity.getText();
            String state = tfState.getText();
            String email = tfEmail.getText();
            String phone = tfPhone.getText();
            
//            try{
//                Conn c = new Conn();
//                c.s.executeUpdate("update customer set address = '"+address+"', city = '"+city+"', state = '"+state+"', email = `'"+email+"', phone = '"+phone+"' where meter_no = '"+meter+"'");
//                        
//               JOptionPane.showMessageDialog(null, "User Information Updated");
//               setVisible(false);
//            } catch(Exception e){
//                e.printStackTrace();
//            }
//            
//        } else{
//        setVisible(false);
//        }

            try {
                Conn c = new Conn();
                c.s.executeUpdate("update customer set address = '"+address+"', city = '"+city+"', state = '"+state+"', email = '"+email+"', phone = '"+phone+"' where meter_no = '"+meter+"'");

                JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

        
        public static void main(String[] args){
            new UpdateInformation("");
        }
}
