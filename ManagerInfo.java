import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ManagerInfo extends JFrame implements ActionListener {
    JLabel label1,label2,label3,label4,label5,label6,label7,label8;
    JButton button1,button2;
    JTable table;

    public  ManagerInfo(){
        super("Manager Info - Hilton Hotel");
        setSize(900,720);
        setLocation(100,100);
        setLayout(null);

        //label1 - Id

        label1 = new JLabel("Name");
        label1.setBounds(20,0,100,20);
        add(label1);

        label2 =  new JLabel("Age");
        label2.setBounds(120,0,100,20);
        add(label2);


        label3 =  new JLabel("Gender");
        label3.setBounds(220,0,100,20);
        add(label3);


        label4 = new JLabel("Job");
        label4.setBounds(340,0,100,20);
        add(label4);


        label5 = new JLabel("Salary");
        label5.setBounds(450,0,100,20);
        add(label5);

        label6 = new JLabel("Phone");
        label6.setBounds(550,0,100,20);
        add(label6);


        label7 = new JLabel("Aadhar");
        label7.setBounds(660,0,100,20);
        add(label7);



        label8 = new JLabel("Gmail");
        label8.setBounds(770,0,100,20);
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
                    String query = "select * from Employee where job = 'Manager'";
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
        table.setBounds(0, 30, 880, 400);
        add(table);




        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }

    public static void main(String[] args) {
        new ManagerInfo();
    }
}
