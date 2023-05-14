import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRooms extends JFrame implements ActionListener {

    JLabel label1,label2,label3,label4,label5,label6;
    JTextField textField1,textField2;
    JComboBox comboBox1,comboBox2,comboBox3;
    JButton addButton,backButton;

    public AddRooms(){
        super("Add Rooms - Hilton Hotel");
        setSize(1000,380);
        setLocation(250,250);
        setLayout(null);

        label1 = new JLabel("Add Rooms");
        label1.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
        label1.setBounds(200,20,100,25);
        add(label1);

        label2 = new JLabel("Room Number");
        label2.setBounds(40,50,200,25);
        label2.setForeground(Color.blue);
        label2.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
        add(label2);

        textField1 = new JTextField();
        textField1.setBounds(200,50,200,20);
        add(textField1);

        label3 = new JLabel("Availability");
        label3.setBounds(40,80,200,25);
        label3.setForeground(Color.BLUE);
        label3.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
        add(label3);

        String availability []= {"Available", "Occupied"};
        comboBox1 = new JComboBox(availability);
        comboBox1.setBounds(200,80,200,25);
        add(comboBox1);


        //label 4 - cleaning status
        label4 = new JLabel("Cleaning Status");
        label4.setBounds(40,110,200,25);
        label4.setFont(new Font("TIMES NEW ROMAN ",Font.BOLD,16));
        label4.setForeground(Color.BLUE);
        add(label4);



        String cleaningAvailability[] = {"Cleaned", "Dirty" };
        comboBox2 = new JComboBox(cleaningAvailability);
        comboBox2.setBounds(200,110,200,25);
        add(comboBox2);

        //label 5 - Price

        label5 = new JLabel("Price");
        label5.setBounds(40,140,200,25);
        label5.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
        label5.setForeground(Color.BLUE);
        add(label5);

        textField2 = new JTextField();
        textField2.setBounds(200,140,200,25);
        add(textField2);


        // Label 06 - BED TYPE
        label6 = new JLabel("BED TYPE");
        label6.setBounds(40,170,200,25);
        label6.setFont(new Font("TIMES NEW ROMAN",Font.BOLD,16));
        label6.setForeground(Color.BLUE);
        add(label6);

        String bedType [] = {"Single Bed", "Double Bed"};
        comboBox3 = new JComboBox(bedType);
        comboBox3.setBounds(200,170,200,25);
        add(comboBox3);

        // button1 : addButton
        addButton = new JButton("Add");
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.white);
        addButton.setBounds(40,250,100,25);

        add(addButton);


        backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        backButton.setBounds(150,250,100,25);
        add(backButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {

                    if (ae.getSource() == addButton) {
                        try {

                            String roomNumber = textField1.getText();
                            String availability = (String) comboBox1.getSelectedItem();
                            String cleaningStatus = (String) comboBox2.getSelectedItem();
                            String price = textField2.getText();
                            String bedType = (String) comboBox3.getSelectedItem();

                            String query = "INSERT INTO room VALUES ('" + roomNumber + "','" + availability + "','" + cleaningStatus + "','" + price + "','" + bedType + "')";

                            Conn conn = new Conn();
                            conn.s.executeUpdate(query);
                            JOptionPane.showMessageDialog(null, "Room Successfully Added");
                            setVisible(false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (ae.getSource() == backButton) {
                        setVisible(false);
                    }
                }catch (Exception eee){

                }
            }
        });


        //Image

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon2);
        label.setBounds(420, 30, 500, 300);


        Border border = BorderFactory.createLineBorder(Color.RED, 2); // Red color and 2-pixel width


        label.setBorder(border);

        add(label);



//        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
//        Image i3 = i1.getImage().getScaledInstance(500, 300,Image.SCALE_DEFAULT);
//        ImageIcon i2 = new ImageIcon(i3);
//        JLabel l15 = new JLabel(i2);
//        l15.setBounds(400,30,500,370);
//        add(l15);


        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }


    public static void main(String[] args) {
        new AddRooms();
    }
}
