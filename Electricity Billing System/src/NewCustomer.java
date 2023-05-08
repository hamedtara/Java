import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NewCustomer extends JFrame implements ActionListener {
    JLabel label1,label2,label3,label4,
            label5,label6,label7,label8,label11;
    JTextField textField1,textField2,textField3,
            textField4,textField5,textField6,textField7;
    JButton button1,button2;

    NewCustomer(){
        setLocation(600,200);
        setSize(700,500);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.white);
        panel.setBackground(new Color(173,216,230));
        JLabel title = new JLabel("New Customer");

        title.setBounds(180,10,200,26);
        title.setFont(new Font("Tahoma",Font.PLAIN,24));
        panel.add(title);

        label1 = new JLabel("Customer Name");
        label1.setBounds(100,80,100,20);

        textField1 = new JTextField();
        textField1.setBounds(240,80,200,20);
        panel.add(label1);
        panel.add(textField1);

        label2 = new JLabel("Meter No");
        label2.setBounds(100,120,200,20);
        label11 = new JLabel();
        label11.setBounds(240 ,120,200,20);
        panel.add(label2);
        panel.add(label11);

        label3 = new JLabel("Address");
        label3.setBounds(100,160,100,20);
        textField3 = new JTextField();
        textField3.setBounds(240,160,200,20);
        panel.add(label3);
        panel.add(textField3);


        label5 = new JLabel("City");
        label5.setBounds(100,200,100,20);
        textField5 = new JTextField();
        textField5 = new JTextField();
        textField5.setBounds(240,200,200,20);
        panel.add(label5);
        panel.add(textField5);

        label4 = new JLabel("State");
        label4.setBounds(100,240,100,20);
        textField4 = new JTextField();
        textField4.setBounds(240,240,200,20);
        panel.add(label4);
        panel.add(textField4);

        label6 = new JLabel("Email");
        label6.setBounds(100,280,100,20);
        textField6 = new JTextField();
        textField6.setBounds(240,280,200,20);
        panel.add(label6);
        panel.add(textField6);

        label7 = new JLabel("Phone Number");
        label7.setBounds(100,320,100,20);
        textField7 = new JTextField();
        textField7.setBounds(240,320,200,20);

        panel.add(label7);
        panel.add(textField7);

        button1 = new JButton("Next");
        button1.setBounds(120,390,100,25);
        button2 = new JButton("Cancel");
        button2.setBounds(250,390,100,25);

        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.white);

        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.white);

        panel.add(button1);
        panel.add(button2);
        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image image1 = imageIcon1.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image1);
        label8 = new JLabel(imageIcon2);

        add(label8,"West");
        getContentPane().setBackground(Color.WHITE);

        button1.addActionListener(this);
        button2.addActionListener(this);

        Random ran = new Random();
        long first = (ran.nextLong() % 1000000);
        label11.setText(""+Math.abs(first));


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button1){
            String name = textField1.getText();
            String meter = label11.getText();
            String address = textField3.getText();
            String state = textField4.getText();
            String city = textField5.getText();
            String email = textField6.getText();
            String phone = textField7.getText();

            String q1 = "insert into customer values('"+name+"','"+meter+"','"+address+"','"+city+"','"+state+"','"+email+"','"+phone+"')";
            String q2 = "insert into login values('"+meter+"', '', '', '', '')";
            
            try{
                Conn connection1 = new Conn();
                connection1.s.executeUpdate(q1);
                connection1.s.executeUpdate(q2);
                JOptionPane.showMessageDialog(null,"Customer Details Added Successfully");
                this.setVisible(false);
               /* new MeterInfo(meter).setVisible(true);*/
            }catch (Exception exception){
                exception.printStackTrace();
            }
            
        } else if (e.getSource()==button2) {
            this.setVisible(false);
            
        }

    }
    public static void main(String[] args){
        new NewCustomer().setVisible(true);
    }
}
