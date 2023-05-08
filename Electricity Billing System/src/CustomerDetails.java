import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class CustomerDetails extends JFrame implements ActionListener{

    JTable table1;
    JButton printButton;
    String x[] = {"Customer Name","Meter Number","Address","City","State","Email","Phone"};
    String y[][] = new String[40][8];
    int i=0, j=0;
    CustomerDetails(){
        super("Customer Details");
        setSize(1200,650);
        setLocation(400,150);

        try{
            Conn c1  = new Conn();
            String s1 = "select * from customer";
            ResultSet rs  = c1.s.executeQuery(s1);
            while(rs.next()){
                y[i][j++]=rs.getString("name");
                y[i][j++]=rs.getString("meter");
                y[i][j++]=rs.getString("address");
                y[i][j++]=rs.getString("city");
                y[i][j++]=rs.getString("state");
                y[i][j++]=rs.getString("email");
                y[i][j++]=rs.getString("phone");
                i++;
                j=0;
            }
            table1 = new JTable(y,x);

        }catch(Exception e){
            e.printStackTrace();
        }


        printButton = new JButton("Print");
        add(printButton,"South");
        JScrollPane sp = new JScrollPane(table1);
        add(sp);
        printButton.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae){
        try{
            table1.print();
        }catch(Exception e){}
    }

    public static void main(String[] args){
        new CustomerDetails().setVisible(true);
    }

}