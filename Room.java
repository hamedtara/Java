import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Room extends JFrame implements ActionListener {
    JLabel label1,label2,label3,label4,label5;
    JTable table1;
    JButton button1,button2;

    public Room(){

        super("Room Details - Hilton Hotel");
        setSize(1280,520);
        setLocation(300,300);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        label1 = new JLabel("Room Number");
        label1.setBounds(10,10,100,30);
        add(label1);




        label2 = new JLabel("Availability");
        label2.setBounds(110,10,100,30);
        add(label2);


        label3 = new JLabel("Clean Status");
        label3.setBounds(210,10,100,30);
        add(label3);

        label4 = new JLabel("Price");
        label4.setBounds(310,10,100,30);
        add(label4);


        label5 = new JLabel("Bed Type");
        label5.setBounds(380,10,100,30);
        add(label5);

        table1 = new JTable();
        table1.setBounds(0, 40, 500, 400);
        add(table1);




        button1 = new JButton("Load Data");
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);
        button1.setBounds(10,450,100,30);
        add(button1);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Conn conn = new Conn();
                    String query = "select * from Room";
                    ResultSet resultSet = conn.s.executeQuery(query);
                    table1.setModel(DbUtils.resultSetToTableModel(resultSet));
                }catch (Exception e1){

                    e1.printStackTrace();

                }

            }
        });

        button2 = new JButton("Back");
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        button2.setBounds(310,450,100,30);
        add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new Reception().setVisible(true);
                setVisible(false);

            }
        });

        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i3 = i1.getImage().getScaledInstance(580, 400,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(540,40,580,400);
        add(l1);



        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Room frame = new Room();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
