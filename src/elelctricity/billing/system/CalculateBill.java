
package elelctricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.util.*;
import java.sql.*;
public class CalculateBill extends JFrame implements ActionListener{
    
    JTextField tfName, tfAddress, tfCity, tfState, tfEmail, tfPhone, tfUnitConsumed;
    JButton next, cancel;
    JLabel lblMeterNo, lblName, lblName1,tAddress;
    Choice meterNumber, cMonth;
    CalculateBill(){
        
        setSize(700,500);
        setLocation(400,200);
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);
        
        JLabel heading = new JLabel("Calculate Elctricity Bill");
        heading.setBounds(180,10,400,25);
        heading.setFont(new Font("Tahoma", Font.PLAIN,24));
        p.add(heading);
        
        
        //customer name
        JLabel lblMeter = new JLabel("Meter Number");
        lblMeter.setBounds(100,80,100,20);
        p.add(lblMeter);
        
        meterNumber = new Choice();
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){
               meterNumber.add(rs.getString("meter_no"));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        
        meterNumber.setBounds(240, 80, 200,25);
        p.add(meterNumber);
        
//        tfName = new JTextField();
//        tfName.setBounds(240, 80, 200,25);
//        p.add(tfName);
        
        //meter number
        lblName = new JLabel("Name");
        lblName.setBounds(100,120,100,20);
        p.add(lblName);
        
        lblName1 = new JLabel("");
        lblName1.setBounds(240,120,100,20);
        p.add(lblName1);
        
       
        
        JLabel lblAddress = new JLabel("Adress");
        lblAddress.setBounds(100,160,100,20);
        p.add(lblAddress);
        
        tAddress = new JLabel();
        tAddress.setBounds(240, 160, 200,25);
        p.add(tAddress);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meterNumber.getSelectedItem()+"'");
            while(rs.next()){
               lblName1.setText(rs.getString("name"));
               tAddress.setText(rs.getString("address"));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        meterNumber.addItemListener(new ItemListener(){
           public void itemStateChanged(ItemEvent ie){
               try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meterNumber.getSelectedItem()+"'");
            while(rs.next()){
               lblName1.setText(rs.getString("name"));
               tAddress.setText(rs.getString("address"));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
           } 
        });
        
        //CITY
        JLabel lblUnitConsumed = new JLabel("Units Consumed");
        lblUnitConsumed.setBounds(100,200,100,20);
        p.add(lblUnitConsumed);
        
        tfUnitConsumed = new JTextField();
        tfUnitConsumed.setBounds(240, 200, 200,25);
        p.add(tfUnitConsumed);
        
        //State
        JLabel lblMonth = new JLabel("Month");
        lblMonth.setBounds(100,240,100,20);
        p.add(lblMonth);
        
       cMonth = new Choice();
       cMonth.setBounds(240,240,100,20);
       cMonth.add("January");
       cMonth.add("Feburary");
       cMonth.add("March");
       cMonth.add("April");
       cMonth.add("May");
       cMonth.add("June");
       cMonth.add("July");
       cMonth.add("August");
       cMonth.add("September");
       cMonth.add("October");
       cMonth.add("November");
       cMonth.add("December");
       p.add(cMonth);
        
        
        
        
        
        //BUTTONS
        next = new JButton("Submit");
        next.setBounds(120,350,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(250,350,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);
        
        setLayout(new BorderLayout());
        add(p, "Center");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, "West");
        
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == next){
            String meter = meterNumber.getSelectedItem();
            String units = tfUnitConsumed.getText();
            String month = cMonth.getSelectedItem();
            
            int totalBill = 0;
            int unit_Consumed = Integer.parseInt(units);
            
            String query = "select * from tax";
            
            
            
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                
                while(rs.next()){
                  totalBill+=  unit_Consumed * Integer.parseInt(rs.getString("cost_per_unit"));
                  totalBill += Integer.parseInt(rs.getString("meter_rent"));
                  totalBill += Integer.parseInt(rs.getString("service_charge"));
                  totalBill += Integer.parseInt(rs.getString("service_tax"));
                  totalBill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
                  totalBill += Integer.parseInt(rs.getString("fixed_tax"));
                }
                                
            } catch(Exception e){
                e.printStackTrace();
            }
            String query2 = "insert into bill values('"+meter+"', '"+month+"', '"+units+"', '"+totalBill+"', 'Not Paid')";
            
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "Customer bill updated successfully");
                setVisible(false);
            } catch (Exception e){
                e.printStackTrace();
            }
            
        } else{
            setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new CalculateBill();
    }
    
}
