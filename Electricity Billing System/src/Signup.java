
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Signup extends JFrame implements ActionListener{
    JPanel panel;
    JTextField textField1, textField2, textField3, textField4;
    Choice choice1;
    JButton button1, button2;
    Signup(){
        super("Toronto Hydro - Signup Page");
        setBounds(600, 250, 700, 400);

        panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setLayout( null);
        panel.setBackground(Color.WHITE);
        panel.setForeground(new Color(34, 139, 34));
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Create-Account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173, 216, 230)));
        add(panel);

        JLabel l1 = new JLabel("Username");
        l1.setForeground(Color.DARK_GRAY);
        l1.setFont(new Font("Times New Roman", Font.BOLD, 14));
        l1.setBounds(100, 50, 100, 20);
        panel.add(l1);

        textField1 = new JTextField();
        textField1.setBounds(260, 50, 150, 20);
        panel.add(textField1);

        JLabel l2 = new JLabel("Name");
        l2.setForeground(Color.DARK_GRAY);
        l2.setFont(new Font("Times New Roman", Font.BOLD, 14));
        l2.setBounds(100, 90, 100, 20);
        panel.add(l2);

        textField2 = new JTextField();
        textField2.setBounds(260, 90, 150, 20);
        panel.add(textField2);


        JLabel l3 = new JLabel("Password");
        l3.setForeground(Color.DARK_GRAY);
        l3.setFont(new Font("Times New Roman", Font.BOLD, 14));
        l3.setBounds(100, 130, 100, 20);
        panel.add(l3);

        textField3 = new JTextField();
        textField3.setBounds(260, 130, 150, 20);
        panel.add(textField3);


        JLabel l4 = new JLabel("Create Account As");
        l4.setForeground(Color.DARK_GRAY);
        l4.setFont(new Font("Times New Roman", Font.BOLD, 14));
        l4.setBounds(100, 170, 140, 20);
        panel.add(l4);


        JLabel l5 = new JLabel("Meter Number");
        l5.setForeground(Color.DARK_GRAY);
        l5.setFont(new Font("Times New Roman", Font.BOLD, 14));
        l5.setBounds(100, 210, 100, 20);
        l5.setVisible(false);
        panel.add(l5);

        textField4 = new JTextField();
        textField4.setBounds(260, 210, 150, 20);
        textField4.setVisible(false);
        panel.add(textField4);

        choice1 = new Choice();
        choice1.add("Admin");
        choice1.add("Customer");
        choice1.setBounds(260, 170, 150, 20);
        panel.add(choice1);

        choice1.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent ae){
                String user = choice1.getSelectedItem();
                if(user.equals("Customer")){
                    l5.setVisible(true);
                    textField4.setVisible(true);
                }else{
                    l5.setVisible(false);
                    textField4.setVisible(false);
                }
            }
        });


        button1 = new JButton("Create");
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);
        button1.setBounds(140, 290, 120, 30);
        button1.addActionListener(this);
        panel.add(button1);

        button2 = new JButton("Back");
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        button2.setBounds(300, 290, 120, 30);
        button2.addActionListener(this);
        panel.add(button2);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l6 = new JLabel(i3);
        l6.setBounds(450, 30, 250, 250);
        panel.add(l6);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == button1){
            String username = textField1.getText();
            String name = textField2.getText();
            String password = textField3.getText();
            String user = choice1.getSelectedItem();
            String meter = textField4.getText();
            try{
                Conn c = new Conn();
                String str = null;
                if(user.equals("Admin")){
                    str = "insert into login values('"+meter+"', '"+username+"', '"+name+"', '"+password+"', '"+user+"')";
                }else{
                    str = "update login set username = '"+username+"', name = '"+name+"', password = '"+password+"', user = '"+user+"' where meter_no = '"+ textField4.getText()+"'";
                }

                c.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                this.setVisible(false);
                new Login().setVisible(true);
            }catch(Exception e){

            }
        } else if(ae.getSource()== button2){
            this.setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args){
        new Signup().setVisible(true);
    }
}
