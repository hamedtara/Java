import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Employee extends JFrame implements ActionListener {

    JLabel label1,label2,label3,label4,label5,label6,label7,label8;
    JTable table1;
    JButton button1,button2;


    public Employee(){
        super("Employee Details - Hilton Hotel");
        setSize(1280,720);
        setLocation(300,300);
        setLayout(null);

        // Label1 - Name
        label1 = new JLabel("Name");
        label1.setBounds(40,0,40,20);
        add(label1);

        // Label2 - Age
        label2 = new JLabel("Age");
        label2.setBounds(140,0,40,20);
        add(label2);


        // Label3 - Gender
        label3 = new JLabel("Gender");
        label3.setBounds(240,0,60,20);
        add(label3);



        // Label4 - Job
        label4 = new JLabel("Job");
        label4.setBounds(340,0,40,20);
        add(label4);


        // Label5 - Salary
        label5 = new JLabel("Salary");
        label5.setBounds(440,0,40,20);
        add(label5);

        // Label6 - Phone
        label6 = new JLabel("Phone");
        label6.setBounds(540,0,40,20);
        add(label6);


        // Label7 - Aadhar
        label7 = new JLabel("Aadhar");
        label7.setBounds(640,0,40,20);
        add(label7);


        // Label8 - Aadhar
        label8 = new JLabel("Gmail");
        label8.setBounds(840,0,40,20);
        add(label8);

        table1 = new JTable();
        table1.setBounds(0, 34, 900, 450);
        add(table1);


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
                    String query = "select * from Employee";
                    ResultSet resultSet = conn.s.executeQuery(query);
                    table1.setModel(DbUtils.resultSetToTableModel(resultSet));

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        button2 = new JButton("Back");
        button2.setBounds(200, 600, 120, 30);
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


        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new Employee();
    }
}
