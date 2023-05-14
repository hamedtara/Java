import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reception extends JFrame implements ActionListener {

    JButton button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11;

    public Reception(){
        super("Reception Of Hilton Hotel");
        setSize(900,580);
        setLayout(null);
        setLocation(300,300);

        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
        Image i3 = i1.getImage().getScaledInstance(500, 500,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(250,30,500,470);
        add(l1);

        // button1 - new Customer Form
        button1 = new JButton("New Customer Form");
        button1.setBounds(10,30,200,30);
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);
        add(button1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    System.out.println("New costumer Test ");
                  NewCustomer costumer = new NewCustomer();
                    costumer.setVisible(true);
                    setVisible(false);

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        // button 2 - Room

        button2 = new JButton("Room");
        button2.setBounds(10,70,200,30);
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        add(button2);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                   Room room = new Room();
                   room.setVisible(true);
                   setVisible(false);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // button 3 - All Employee Info

        button3 =new JButton("All Employee Info");
        button3.setBounds(10,150,200,30);
        button3.setBackground(Color.BLACK);
        button3.setForeground(Color.WHITE);

        add(button3);

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    Employee employee = new Employee();
                    employee.setVisible(true);
                    setVisible(false);
                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
        });


        // button 4 - Customer info
        button4 = new JButton("Customer Info");
        button4.setBounds(10,200,200,30);
        button4.setBackground(Color.BLACK);
        button4.setForeground(Color.WHITE);
        add(button4);

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    CustomerInfo customerInfo =  new CustomerInfo();
                    customerInfo.setVisible(true);
                    setVisible(false);
                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
        });

        // button 5 - Manager Info

        button5 = new JButton("Manager Info");
        button5.setBounds(10,240,200,30);
        button5.setBackground(Color.BLACK);
        button5.setForeground(Color.WHITE);
        add(button5);

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    ManagerInfo managerInfo = new ManagerInfo();
                    managerInfo.setVisible(true);
                    setVisible(false);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        button6 = new JButton("Check Out");
        button6.setBounds(10,280,200,30);
        button6.setBackground(Color.BLACK);
        button6.setForeground(Color.WHITE);
        add(button6);

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    Checkout checkout = new Checkout();
                    checkout.setVisible(true);
                    setVisible(false);

                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
        });


        button7 = new JButton("Update Check Status");
        button7.setBounds(10,320,200,30);
        button7.setBackground(Color.BLACK);
        button7.setForeground(Color.WHITE);
        add(button7);

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{

                    UpdateCheck updateCheck = new UpdateCheck();
                    updateCheck.setVisible(true);
                    setVisible(false);

                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
        });


        button8 = new JButton("Update Room Status");
        button8.setBounds(10,360,200,30);
        button8.setBackground(Color.BLACK);
        button8.setForeground(Color.WHITE);
        add(button8);

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{

                    UpdateRoom updateRoom = new UpdateRoom();
                    updateRoom.setVisible(true);
                    setVisible(false);


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        button9 = new JButton("Pick up Service");
        button9.setBounds(10,400,200,30);
        button9.setBackground(Color.BLACK);
        button9.setForeground(Color.white);
        add(button9);

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    PickUp pickUp = new PickUp();
                    pickUp.setVisible(true);
                    setVisible(false);

                }catch (Exception e ){
                    e.printStackTrace();

                }
            }
        });


        button10 = new JButton("Search Room");
        button10.setBackground(Color.BLACK);
        button10.setForeground(Color.WHITE);
        button10.setBounds(10,440,200,30);
        add(button10);

        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    SearchRoom searchRoom = new SearchRoom();
                    searchRoom.setVisible(true);
                    setVisible(false);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        button11 = new JButton("Log Out");
        button11.setBounds(10,480,200,30);
        button11.setBackground(Color.BLACK);
        button11.setForeground(Color.WHITE);
        add(button11);

        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{
                    new Login().setVisible(true);
                    setVisible(false);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new Reception();
    }
}
