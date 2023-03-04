package elelctricity.billing.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;

public class MeterInfo extends JFrame implements ActionListener{
    
//    JTextField tfName, tfAddress, tfCity, tfState, tfEmail, tfPhone;
    JButton submit;
    JLabel lblMeterNo;
    Choice meterLocation, meterType, phaseCode, billType;
    String meterNumber;
    
    MeterInfo(String meterNumber){
        this.meterNumber = meterNumber;
        setSize(700,500);
        setLocation(400,200);
        
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);
        
        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180,10,200,25);
        heading.setFont(new Font("Tahoma", Font.PLAIN,24));
        p.add(heading);
        
        
        //customer name
        JLabel lblName = new JLabel("Meter Number");
        lblName.setBounds(100,80,100,20);
        p.add(lblName);
        
        JLabel lblMeterNumber = new JLabel(meterNumber);
        lblMeterNumber.setBounds(240,80,100,20);
        p.add(lblMeterNumber);
        
        //meter number
        JLabel lblMeter = new JLabel("Meter Location");
        lblMeter.setBounds(100,120,100,20);
        p.add(lblMeter);
        
        meterLocation = new Choice();
        meterLocation.add("outside");
        meterLocation.add("inside");
        meterLocation.setBounds(240,120,100,20);
        p.add(meterLocation);
        
        
       
        
        
        JLabel lblAddress = new JLabel("Meter Type");
        lblAddress.setBounds(100,160,100,20);
        p.add(lblAddress);
        
        meterType = new Choice();
        meterType.add("electric meter");
        meterType.add("Solar meter");
        meterType.add("Smart meter");
        meterType.setBounds(240,160,100,20);
        p.add(meterType);
        
        
        
        //CITY
        JLabel lblPhasecode = new JLabel("Phase code");
        lblPhasecode.setBounds(100,200,100,20);
        p.add(lblPhasecode);
        
        phaseCode = new Choice();
        phaseCode.add("011");
        phaseCode.add("022");
        phaseCode.add("033");
        phaseCode.add("044");
        phaseCode.add("055");
        phaseCode.add("066");
        phaseCode.add("077");
        phaseCode.add("088");
        phaseCode.add("099");
        phaseCode.setBounds(240,200,100,20);
        p.add(phaseCode);
        
        JLabel lblBillType = new JLabel("Bill Type");
        lblBillType.setBounds(100,240,100,20);
        p.add(lblBillType);
        
        
        billType = new Choice();
        billType.add("Normal");
        billType.add("Industrial");
        billType.setBounds(240,240,100,20);
        p.add(billType);
        
        
        JLabel lblDays = new JLabel("Days");
        lblDays.setBounds(100,280,100,20);
        p.add(lblDays);
        
        JLabel lblDaysInfo = new JLabel("30 Days");
        lblDaysInfo.setBounds(240,280,100,20);
        p.add(lblDaysInfo);
        
       
        
        
        //Phone
        JLabel lblNote = new JLabel("Note");
        lblNote.setBounds(100,320,100,20);
        p.add(lblNote);
        
        JLabel lblNoteInfo = new JLabel("By default the bill is calculated for 30 days only");
        lblNoteInfo.setBounds(240,320,500,20);
        p.add(lblNoteInfo);
        
        
        
        
        //BUTTONS
        submit = new JButton("Submit");
        submit.setBounds(220,390,100,25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        p.add(submit);
        
        
        
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
        if(ae.getSource() == submit){
            String meter = meterNumber;
            String mLocation = meterLocation.getSelectedItem();
            String mType = meterType.getSelectedItem();
            String pCode = phaseCode.getSelectedItem();
            String bType = billType.getSelectedItem();
            String days = "30";
            
            String query = "insert into meter_info value('"+meter+"', '"+mLocation+"', '"+mType+"', '"+pCode+"', '"+bType+"', '"+days+"')";   
//            String query2 = "insert into login value ('"+meter+"','', '"+name+"','','')";
            
            
            try{
                Conn c = new Conn();
                c.s.executeUpdate(query);
                
                
                JOptionPane.showMessageDialog(null, "Meter information added successfully");
                setVisible(false);
                
                //new Frame
                
            } catch(Exception e){
                e.printStackTrace();
            }
            
        } else{
            setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new MeterInfo("");
    }
}
