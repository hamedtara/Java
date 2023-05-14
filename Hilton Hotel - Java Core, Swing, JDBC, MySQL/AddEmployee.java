import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployee extends JFrame implements ActionListener {
    JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9;
    JTextField textField1, textField2, textField3, textField4, textField5, textField6;
    JRadioButton radioButton1,radioButton2;

    JButton button1;
    JComboBox comboBox1, comboBox2;

    public AddEmployee() {
        super("Hilton Hotel - ADD EMPLOYEE DETAILS");
        setSize(1000, 540);
        setLocation(200, 200);
        setLayout(null);

        // Label1 : name
        label1 = new JLabel("NAME");
        label1.setBounds(60, 30, 150, 30);
        label1.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 16));
        add(label1);

        // TextField1
        textField1 = new JTextField();
        textField1.setBounds(200, 30, 150, 30);
        add(textField1);

        // Label2 : AGE
        label2 = new JLabel("AGE");
        label2.setBounds(60, 70, 150, 30);
        label2.setFont(new Font("TIMES NEW ROMAN", Font.BOLD, 16));
        add(label2);

        //TextField 2

        textField2 = new JTextField();
        textField2.setBounds(200,70,150,30);
        add(textField2);

        //Label 03 - Gender
        label3 = new JLabel("GENDER");
        label3.setBounds(60,100,150,30);
        label3.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
        add(label3);

        //Radio Button
        radioButton1 = new JRadioButton("MALE");
        radioButton1.setBounds(200,100,70,30);
        radioButton1.setBackground(Color.white);
        add(radioButton1);


        radioButton2 = new JRadioButton("FEMALE");
        radioButton2.setBounds(280,100,100,30);
        radioButton2.setBackground(Color.white);
        add(radioButton2);


        //Label 04 - JOB

        label4 =  new JLabel("JOB");
        label4.setBounds(60,130,150,30);
        label4.setFont(new Font("TIMES NEW ROMAN", Font.BOLD,16));
        add(label4);


        String course[] = {"Front Desk Clerks","Porters","Housekeeping","Kitchen Staff","Room Service","Waiter/Waitress","Manager","Accountant","Chef"};
        comboBox1 = new JComboBox(course);
        comboBox1.setBackground(Color.WHITE);
        comboBox1.setBounds(200,130,150,30);
        add(comboBox1);

        //Label 05 - SALARY

        label5 = new JLabel("SALARY");
        label5.setBounds(60,180,150,30);
        label5.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
        add(label5);

        //TextField 3

        textField3 = new JTextField();
        textField3.setBounds(200,180,150,30);
        add(textField3);


        //Label 06 - PHONE

        label6 = new JLabel("PHONE");
        label6.setBounds(60,220,150,30);
        label6.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
        add(label6);

        //TextField 4

        textField4 = new JTextField();
        textField4.setBounds(200,220,150,30);
        add(textField4);


        //Label 07 - ADDHAR

        label7 = new JLabel("ADDHAR");
        label7.setBounds(60,260,150,30);
        label7.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
        add(label7);

        //TextField 5

        textField5 = new JTextField();
        textField5.setBounds(200,260,150,30);
        add(textField5);


        //Label 08 - EMAIL

        label8 = new JLabel("EMAIL");
        label8.setBounds(60,300,150,30);
        label8.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
        add(label8);

        //TextField 6

        textField6 = new JTextField();
        textField6.setBounds(200,300,150,30);
        add(textField6);


        button1 = new JButton("SAVE");
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.white);
        button1.setBounds(200,350,150,30);
        add(button1);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image newImage = imageIcon.getImage().getScaledInstance(720,480,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(newImage);
        JLabel image = new JLabel(imageIcon2);
        image.setBounds(410,80,480,410);
        add(image);

        label9 = new JLabel("ADD EMPLOYEE DETAILS");
        label9.setBounds(400,20,700,50);
        label9.setFont(new Font("TAHOMA",Font.BOLD,40));
        add(label9);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String name = textField1.getText();
                String age = textField2.getText();
                String salary = textField3.getText();
                String phone = textField4.getText();
                String aadhar = textField5.getText();
                String email = textField6.getText();

                String gender = null;

                if(radioButton1.isSelected()){
                    gender ="male";
                }else if(radioButton2.isSelected()){
                    gender = "female";
                }

                String job = (String) comboBox1.getSelectedItem();

                try {
                    Conn conn = new Conn();
                    String query = "INSERT INTO employee values ('"+name+"','"+age+"','"+gender+"','"+job+"','"+salary+"','"+phone+"','"+aadhar+"','"+email+"')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Done! , Employee Added");
                    new Reception().setVisible(true);
                    setVisible(false);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });





//        JRadioButton NewRadioButton = new JRadioButton("MALE");
//        NewRadioButton.setBackground(Color.WHITE);
//        NewRadioButton.setBounds(200, 120, 70, 27);
//        add(NewRadioButton);


       getContentPane().setBackground(Color.white);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {


    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
