import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerInfo extends JFrame implements ActionListener {
    JLabel label1,label2,label3,label4,label5,label6,label7,label8;
    JButton button1,button2;
    JTable table;

    public CustomerInfo(){
        super("Customer Info - Hilton Hotel");
        setSize(1280,720);
        setLocation(300,300);
        setLayout(null);

        //label1 - Id

        label1 = new JLabel("ID");
        label1.setBounds(20,0,100,20);
        add(label1);

        label2 =  new JLabel("Number");
        label2.setBounds(120,0,100,20);
        add(label2);


        label3 =  new JLabel("Name");
        label3.setBounds(220,0,100,20);
        add(label3);


        label4 = new JLabel("Gender");
        label4.setBounds(320,0,100,20);
        add(label4);


        label5 = new JLabel("Country");
        label5.setBounds(420,0,100,20);
        add(label5);

        label6 = new JLabel("Room");
        label6.setBounds(520,0,100,20);
        add(label6);


        label7 = new JLabel("Status");
        label7.setBounds(620,0,100,20);
        add(label7);



        label8 = new JLabel("Deposit");
        label8.setBounds(720,0,100,20);
        add(label8);



        button1 = new JButton("Load Data");
        button1.setBounds(30, 600, 100, 30);
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.BLACK);
        add(button1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    Conn conn = new Conn();
                    String query = "Select * from Customer";
                    ResultSet resultSet = conn.s.executeQuery(query);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));

                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
        });


        button2 = new JButton("Back");
        button2.setBounds(300,600,100,30);
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        add(button2);


        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });

        table = new JTable();
        table.setBounds(0, 30, 1280, 500);
        add(table);




        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }

    public static void main(String[] args) {
        new CustomerInfo();
    }
}
