import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class About extends JFrame implements ActionListener {
    JButton button1;
    JLabel label1;
    Font font1,font2,font3;
    TextArea textArea1;
    String string;

    public About(){
        setLayout((null));
        JButton button1 = new JButton("Exit");
        add(button1);
        button1.setBounds(180,430,120,20);
        button1.addActionListener(this);

        Font f1 = new Font("Arial",Font.BOLD,180);
        setFont(font1);

        string="Hamed TAra ";
         TextArea text1 = new TextArea(string,10,40,Scrollbar.VERTICAL);
         text1.setEditable(false);
         text1.setBounds(20,100,450,300);

         add(text1);

         Font f2 = new Font("Arial",Font.BOLD,16);
         Container contentPane = this.getContentPane();
         text1.setFont(f2);

         JLabel label1 = new JLabel("About Project");
         add(label1);
         label1.setBounds(170,10,180,80);
         label1.setForeground(Color.red);

         Font f3 = new Font("ARIAL", Font.BOLD,20);
         label1.setFont(f3);

         setBounds(700,220,500,550);
         setLayout(null);
         setVisible(true);



    }


    public static void main(String[] args) {
        new About().setVisible(true);

//        try {
//            // create a new instance of Conn
//            Conn conn = new Conn();
//
//            // use the connection to execute a query on the login table
//            Statement stmt = conn.getConnection().createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM login");
//            while (rs.next()) {
//                System.out.println(rs.getString("meter_no") + " " + rs.getString("username") + " " + rs.getString("name") + " " + rs.getString("password") + " " + rs.getString("user"));
//            }
//
//            // use the connection to execute a query on the customer table
//            stmt = conn.getConnection().createStatement();
//            rs = stmt.executeQuery("SELECT * FROM customer");
//            while (rs.next()) {
//                System.out.println(rs.getString("name") + " " + rs.getString("meter") + " " + rs.getString("address") + " " + rs.getString("city") + " " + rs.getString("state") + " " + rs.getString("email") + " " + rs.getString("phone"));
//            }
//
//            // use the connection to execute a query on the meter_info table
//            stmt = conn.getConnection().createStatement();
//            rs = stmt.executeQuery("SELECT * FROM meter_info");
//            while (rs.next()) {
//                System.out.println(rs.getString("meter_number") + " " + rs.getString("meter_location") + " " + rs.getString("meter_type") + " " + rs.getString("phase_code") + " " + rs.getString("bill_type") + " " + rs.getString("days"));
//            }
//
//            // use the connection to execute a query on the tax table
//            stmt = conn.getConnection().createStatement();
//            rs = stmt.executeQuery("SELECT * FROM tax");
//            while (rs.next()) {
//                System.out.println(rs.getString("cost_per_unit") + " " + rs.getString("meter_rent") + " " + rs.getString("service_tax") + " " + rs.getString("swacch_bharat_cess") + " " + rs.getString("fixed_tax") + " " + rs.getString("service_charge"));
//            }
//
//            // use the connection to execute a query on the bill table
//            stmt = conn.getConnection().createStatement();
//            rs = stmt.executeQuery("SELECT * FROM bill");
//            while (rs.next()) {
//                System.out.println(rs.getString("meter") + " " + rs.getString("month") + " " + rs.getString("units") + " " + rs.getString("total_bill") + " " + rs.getString("status"));
//            }
//
//            // close the connection when done
//            conn.closeConnection();
//        } catch (SQLException e) {
//            System.err.println("Unable to execute query");
//            e.printStackTrace();
//        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();

    }
}
