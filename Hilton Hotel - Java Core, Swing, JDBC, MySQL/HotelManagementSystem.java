import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelManagementSystem extends JFrame implements ActionListener {
    JLabel label1 ;
    JButton button1;

    public HotelManagementSystem(){
        super("HOTEL HILTON - Management System");
        setSize(1280,720);
        setLayout(null);
        setLocation(300,300);

        label1 = new JLabel("");
        button1 = new JButton("NEXT");


        button1.setBackground(Color.white);
        button1.setForeground(Color.black);

        ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icons/first2.jpg"));
        Image imageIcon3 = imageIcon1.getImage().getScaledInstance(1280,700,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(imageIcon3);
        label1 = new JLabel(imageIcon2);

        JLabel lid = new JLabel("HOTEL MANAGEMENT SYSTEM");
        lid.setBounds(10,580,1500,100);
        lid.setFont(new Font("Times New Roman",Font.BOLD,70));
        lid.setForeground(Color.red);
        label1.add(lid);

        button1.setBounds(1150,600,80,40);
        label1.setBounds(0,0,1280,720);

        label1.add(button1);
        add(label1);

        button1.addActionListener(this);
        setVisible(true);

        while (true){
            lid.setVisible(false);
            try{
                Thread.sleep(500);
            }catch (Exception e){}
            lid.setVisible(true);
            try{
                Thread.sleep(500);
            }catch (Exception e ){}
        }

    }



    @Override
    public void actionPerformed(ActionEvent e) {

        new Login().setVisible(true);
        this.setVisible(false);

    }

    public  static void main (String[] args){
        HotelManagementSystem hotelManagementSystem = new HotelManagementSystem();
        hotelManagementSystem.setVisible(true);
    }
}
