
package elelctricity.billing.system;
import javax.swing.*;
import java.awt.*;
public class Splash extends JFrame implements Runnable{
    
       Splash(){
           Thread t;
           ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
           Image i2 = i1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT);
           ImageIcon i3 = new ImageIcon(i2);
           JLabel image = new JLabel(i3);
           add(image);
           setVisible(true);
           
           int x = 1;
           for(int i = 2; i <= 600; i+= 4, x++){
             setSize(i + x, i);
             setLocation(700-(i+x)/2,430-(i/2));
           //used to make the animation slow
             try{
               Thread.sleep(5);
             } catch(Exception e){
               e.prinStackTrace();
             }
           } 
           t = new Thread(this);
           t.start();
       }
       
       public void run(){
           try{
               Thread.sleep(2000);
               setVisible(false);
               new Login();
           }catch(Exception e){
               e.printStackTrace();
           }
       }
    
    public static void main(String[] args){
        new Splash();
    }
}
