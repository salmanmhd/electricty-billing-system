
package elelctricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class CustomerDetails extends JFrame implements ActionListener{
    Choice meterNumber, cMonth;
    JTable table;
    JButton  print;
    CustomerDetails(){
        super("Customer Details");
        
        
       setSize(1200,600);
        setLocation(200,150);
        
         
        
       
        
        table = new JTable();
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane sp = new JScrollPane(table);
//        sp.setBounds(0,100,700,600);
        add(sp);
        
        
        
        print = new JButton("Print");
//        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print, "South");
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
           
         try{
         table.print();
        } catch(Exception e){
            e.printStackTrace();
        }
            }
        }
    
    public static void main(String[] args){
        new CustomerDetails();
    }
}
