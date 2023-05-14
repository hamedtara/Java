import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JLabel label1,label2;
    JTextField textField1;
    JPasswordField passwordField1;
    JButton button1,button2;

    public Login(){
        super("Login");

        setSize(580,320);
        setLayout(null);
        setLocation(600,300);

        label1 = new JLabel("Username:");
        label1.setBounds(40,20,100,30);
        add(label1);

        label2 = new JLabel("Password:");
        label2.setBounds(40,60,100,30);
        add(label2);

        textField1 = new JTextField();
        textField1.setBounds(220,25,120,20);
        add(textField1);

        passwordField1 = new JPasswordField();
        passwordField1.setBounds(220,60,120,20);
        add(passwordField1);


        ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icons/secondpng.png"));
        Image image2 = imageIcon1.getImage().getScaledInstance(200,300,Image.SCALE_DEFAULT);
        ImageIcon imageIcon3 = new ImageIcon(image2);
        JLabel label3 = new JLabel(imageIcon3);
        label3.setBounds(350,30,150,250);
        add(label3);


        button1 =  new JButton("Login");
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);
        button1.setBounds(40,200,100,30);
        button1.setFont(new Font("serif",Font.BOLD,15));
        button1.addActionListener(this);
        add(button1);


        button2 =  new JButton("Cancel");
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        button2.setBounds(220,200,100,30);
        button2.setFont(new Font("serif",Font.BOLD,15));
        button2.addActionListener(this);
        add(button2);

        getContentPane().setBackground(Color.WHITE);
        setVisible(true);





    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==button1){
            try {
                Conn c1 = new Conn();
                String user = textField1.getText();
                String password = passwordField1.getText();
                String query = "select * from login where username ='"+user+"'and passwrod = '"+password+"'";

                ResultSet resultSet =c1.s.executeQuery(query);
                if(resultSet.next()){
                    System.out.println("test complete");
                    new Dashboard().setVisible(true);
                    setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(null,"Invalid input");
                    setVisible(false);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource()==button2){
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        new Login();
    }
}
