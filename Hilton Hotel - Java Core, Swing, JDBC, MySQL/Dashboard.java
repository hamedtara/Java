import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {
    JLabel label1;
    public Dashboard(){
        super("Hotel Management System");
       setSize(1280,720);
       setLayout(null);
       setLocation(600,600);

       ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
       Image image = imageIcon1.getImage().getScaledInstance(1280,720,Image.SCALE_DEFAULT);
       ImageIcon imageIcon3 = new ImageIcon(image);
       JLabel newLabel = new JLabel(imageIcon3);
       newLabel.setBounds(0,0,1280,720);
       add(newLabel);

       JLabel label1 = new JLabel("Welcome TO Hotel Hilton");
       label1.setForeground(Color.WHITE);
       label1.setFont(new Font("Times New Roman",Font.BOLD,40));
       label1.setBounds(400,60,1000,85);
       newLabel.add(label1);

       JMenuBar menuBar = new JMenuBar();
       setJMenuBar(menuBar);

       JMenu menu1 = new JMenu("Hotel Management");
       menu1.setForeground(Color.BLUE);
       menuBar.add(menu1);


       JMenuItem  menuItem1 = new JMenuItem("Reception");
       menu1.add(menuItem1);

       // new reception function


       JMenu menu2 = new JMenu("ADMIN");
       menu2.setForeground(Color.RED);
       menuBar.add(menu2);

       JMenuItem jMenuItem2 = new JMenuItem("Add Employee");
       menu2.add(jMenuItem2);

       jMenuItem2.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
               try {
                   new AddEmployee().setVisible(true);
                   System.out.println("test Add Employee");
                   setVisible(false);
               }catch (Exception e){}
           }
       });

       JMenuItem jMenuItem3 = new JMenuItem("ADD ROOMS");
       menu2.add(jMenuItem3);

       jMenuItem3.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
               try{
                   new AddRooms().setVisible(true);
                   System.out.println("test Add Rooms");
                   setVisible(false);
               }catch (Exception e){}
           }
       });


        menuItem1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.out.println("New Reception");
                new Reception().setVisible(true);
                setVisible(false);
            }
        });

       JMenuItem jMenuItem4 = new JMenuItem("ADD DRIVERS");
       menu2.add(jMenuItem4);

       jMenuItem4.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ae) {
               try{
                   new AddDriver().setVisible(true);
                   System.out.println("test Add Driver");
                   setVisible(false);

               }catch (Exception e){}
           }
       });


       setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
