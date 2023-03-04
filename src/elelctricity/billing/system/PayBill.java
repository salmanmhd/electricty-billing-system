
package elelctricity.billing.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class PayBill extends JFrame implements ActionListener{
    
    Choice cMonth;
    String meter;
    JButton pay, back;
    PayBill(String meter){
        this.meter = meter;
        setLayout(null);
        setBounds(300,150,900,600);
        
          JLabel heading = new JLabel ("Electricty Bill");
          heading.setBounds(120,5,400,30);
          heading.setFont(new Font("Tahoma", Font.BOLD, 24));
          add(heading);
        
          
          JLabel lblMeterNumber = new JLabel ("Meter Number");
          lblMeterNumber.setBounds(35,80,200,20);
          add(lblMeterNumber);
                    
          JLabel meterNumber = new JLabel ("");
          meterNumber.setBounds(300,80,200,20);
          add(meterNumber);
          
          
          JLabel lblName = new JLabel ("Name");
          lblName.setBounds(35,140,200,20);
          add(lblName);
                    
          JLabel name = new JLabel ("");
          name.setBounds(300,140,200,20);
          add(name);
          
          JLabel lblMonth = new JLabel ("Month");
          lblMonth.setBounds(35,200,200,20);
          add(lblMonth);
                    
//          JLabel month = new JLabel ("");
//          month.setBounds(300,140,200,20);
//          add(month);

        cMonth = new Choice();
        cMonth.setBounds(300,200,200,20);
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
        add(cMonth);
        
        JLabel lblUnits = new JLabel ("Units");
        lblUnits.setBounds(35,260,200,20);
        add(lblUnits);
                    
        JLabel units = new JLabel ("");
        units.setBounds(300,260,200,20);
        add(units);
        
        JLabel lblTotalBill = new JLabel ("Total Bill");
        lblTotalBill.setBounds(35,320,200,20);
        add(lblTotalBill);
                    
        JLabel totalBill = new JLabel ("");
        totalBill.setBounds(300,320,200,20);
        add(totalBill);
        
        JLabel lblStatus = new JLabel ("Status");
        lblStatus.setBounds(35,380,200,20);
        add(lblStatus);
                    
        JLabel status = new JLabel ("");
        status.setBounds(300,380,200,20);
        status.setForeground(Color.RED);
        add(status);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
            while(rs.next()){
                meterNumber.setText(meter);
                name.setText(rs.getString("name"));
                
            }
            
            rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+cMonth.getSelectedItem()+"'");
            while(rs.next()){
                units.setText(rs.getString("units"));
                totalBill.setText(rs.getString("totalBill"));
                status.setText(rs.getString("status"));
                
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        cMonth.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
                try{
            Conn c = new Conn();
             
            ResultSet rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+cMonth.getSelectedItem()+"'");
            while(rs.next()){
                units.setText(rs.getString("units"));
                totalBill.setText(rs.getString("totalBill"));
                status.setText(rs.getString("status"));
                
            }
        } catch(Exception e){
            e.printStackTrace();
        }
            }
        });
        
        
        //BUTTON
        pay = new JButton("Pay Bill");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100,460,100,25);
        pay.addActionListener(this);
        add(pay);
        
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(230,460,100,25);
        back.addActionListener(this);
        add(back);
        
        getContentPane().setBackground(Color.WHITE);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
          Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
          ImageIcon i3 = new ImageIcon(i2);
          JLabel image = new JLabel(i3);
          image.setBounds(400,120,600,300);
          add(image);
                  
                
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == pay){
            try{
                Conn c = new Conn();
                c.s.executeUpdate("update bill set status = 'Paid' where meter_no = '"+meter+"' and month = '"+cMonth.getSelectedItem()+"'");
            } catch(Exception e){
                e.printStackTrace();
            }
            setVisible(false);
            new PayTm(meter);
        } else{
            setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new PayBill("");
    }
}
