import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDriver extends JFrame implements ActionListener {

    JLabel label1,label2,label3,label4,label5,label6,label7,label8;
    JTextField textField1,textField2,textField3,textField4,textField5;
    JButton button1,button2;
    JComboBox comboBox1, comboBox2;

    public AddDriver(){
        super("Hilton Hotel - Add Driver");
        setSize(950,450);
        setLocation(500,500);
        setLayout(null);

        ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image image2  = imageIcon1.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(image2);
        JLabel jLabel =new JLabel(imageIcon);
        jLabel.setBounds(400,30,500,370);
       add(jLabel);

        // Title : Add Driver
        label1 = new JLabel("Add Drivers");
        label1.setFont(new Font("Time New ROMAN",Font.BOLD,18));
        label1.setBounds(194, 10, 120, 22);
        add(label1);

        //label 2

        label2 = new JLabel("Name");
        label2.setForeground(Color.BLUE);
        label2.setBounds(60,70,102,22);
        add(label2);

        // textField1
        textField1 = new JTextField();
        textField1.setBounds(180,70,200,20);
        add(textField1);

        //label 3

        label3 = new JLabel("Age");
        label3.setForeground(Color.BLUE);
        label3.setBounds(60,100,200,20);
        add(label3);

        //textField2

        textField2 = new JTextField();
        textField2.setBounds(180,100,200,20);
        add(textField2);

        //label 4 : gender

        label4 = new JLabel("Gender");
        label4.setBounds(60,130,200,20);
        label4.setForeground(Color.BLUE);
        add(label4);


        //ComboBox1

        comboBox1 = new JComboBox(new String[] { "Male", "Female" });
        comboBox1.setBounds(180, 130, 200, 20);
        add(comboBox1);

        // label 5 : car company

        label5 = new JLabel("Car Company");
        label5.setBounds(60,160,200,20);
        label5.setForeground(Color.BLUE);
        add(label5);

        //  TextField03

        textField3 = new JTextField();
        textField3.setBounds(180,160,200,20);
        add(textField3);


        // label 06 : Car Brand

        label6 = new JLabel("Car Brand");
        label6.setBounds(60,190,200,20);
        label6.setForeground(Color.BLUE);
        add(label6);


        // TextField04

        textField4 = new JTextField();
        textField4.setBounds(180,190,200,20);
        add(textField4);


        // label 07 : Available

        label7 = new JLabel("Available");
        label7.setBounds(60,220,200,20);
        label7.setForeground(Color.BLUE);
        add(label7);


        // TextField04

        comboBox2 = new JComboBox(new String[]{"YES","NO"});
        comboBox2.setBounds(180,220,200,20);
        add(comboBox2);


        // label 08 : Location

        label8 = new JLabel("Location");
        label8.setBounds(60,250,200,20);
        label8.setForeground(Color.BLUE);
        add(label8);

        // TextField

        textField5 = new JTextField();
        textField5.setBounds(180,250,200,20);
        add(textField5);

        //Button 1

        button1 = new JButton("Add");
        button1.addActionListener(this);
        button1.setBounds(60,320,100,20);
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);
        add(button1);


        //Button 2

        button2 = new JButton("Back");
        button2.addActionListener(this);
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.BLACK);
        button2.setBounds(180,320,100,20);
        add(button2);









        setVisible(true);




    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
            if(ae.getSource() ==button1){
                try {
                    Conn conn = new Conn();
                    String name = textField1.getText();
                    String age = textField2.getText();
                    String gender = (String)comboBox1.getSelectedItem();
                    String carCompany = textField3.getText();
                    String carBrand = textField4.getText();
                    String available = (String) comboBox2.getSelectedItem();
                    String location = textField5.getText();

                    String query = "INSERT INTO driver values ('"+name+"','"+age+"','"+gender+"','"+carCompany+"','"+carBrand+"','"+available+"','"+location+"')";

                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Data added to database");
                    this.setVisible(false);
                }catch (Exception ee){
                    System.out.println(ee);
                }

            }

            else if(ae.getSource() == button2) {
                this.setVisible(false);
            }

        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AddDriver();
    }
}
