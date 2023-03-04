
package elelctricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;


public class PayTm extends JFrame implements ActionListener{
    
    
    String meter;
    JButton back;
     PayTm(String meter){
         this.meter = meter;
         JEditorPane j = new JEditorPane();
         j.setEditable(false);
         
         
         try{
             j.setPage("https://paytm.com/online-payments");
             
         } catch(Exception e){
             j.setContentType("text/html");
             j.setText("<html>Could not load<html>");
         }
         
         JScrollPane pane = new JScrollPane(j);
         add(pane);
         
         setSize(800,600);
         setLocation(400,150);
         
         
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(640,20,80,30);
        back.addActionListener(this);
        j.add(back);
         
         
         setVisible(true);
     }
     
     public void actionPerformed(ActionEvent ae){
         setVisible(false);
         new PayBill(meter);
            
         
     }
    
    public static void main(String[] args){
        new PayTm("");
    }
}
