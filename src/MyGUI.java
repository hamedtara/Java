import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MyGUI {

    // Defining all GUI components like panel, frames, textfields,labels
    JPanel mypanel= new JPanel();
    JLabel title= new JLabel("Staff Information");
    JLabel lblId=new JLabel("ID:");
    JLabel lblLastname=new JLabel("Last Name:");
    JLabel lblFirstname=new JLabel("First Name:");
    JLabel lblMi=new JLabel("mi:");
    JLabel lblAddr=new JLabel("Address:");
    JLabel lblCity=new JLabel("City:");
    JLabel lblState=new JLabel("State:");
    JLabel lblTelep=new JLabel("Telephone:");

    JLabel lblmail = new JLabel("EMail:");

    JLabel lbl_dbconn=new JLabel("Status:");

    JLabel lbl_error=new JLabel("Please fill all of the fields");
    JButton btnView=new JButton("View");
    JButton btnInsert=new JButton("Insert");
    JButton btnUpdate=new JButton("Update");
    JButton btnClear=new JButton("Clear");
    JTextField txtID=new JTextField();
    JTextField txtLastName=new JTextField();
    JTextField txtFirstName=new JTextField();
    JTextField txtMI=new JTextField();
    JTextField txtAddr=new JTextField();
    JTextField txtCity=new JTextField();
    JTextField txtState=new JTextField();
    JTextField txtTele=new JTextField();
    JTextField txtmail = new JTextField();
    JFrame mainWindow= new JFrame("Staff Information");

    // Using statement class and defining a variable for it.
    Statement stmt;

    // Constructor method
    public MyGUI(){


        // Adding components to the main frame window
        //mypanel.setSize(500, 170);
        mypanel.setBounds(10, 10, 500, 170);
        mypanel.setLayout(null);
       /* title.setBounds(10, 10, 100, 20);
        title.setVisible(true);
        mypanel.add(title);*/

        lblId.setBounds(10, 34, 15, 20);
        lblId.setVisible(true);
        mypanel.add(lblId);

        lblLastname.setBounds(10, 59, 100, 20);
        lblLastname.setVisible(true);
        mypanel.add(lblLastname);

        lblFirstname.setBounds(188, 59, 100, 20);
        lblFirstname.setVisible(true);
        mypanel.add(lblFirstname);

        lblAddr.setBounds(10, 88, 100, 20);
        lblAddr.setVisible(true);
        mypanel.add(lblAddr);

        lblCity.setBounds(10, 114, 100, 20);
        lblCity.setVisible(true);
        mypanel.add(lblCity);

        lblState.setBounds(150, 114, 100, 20);
        lblState.setVisible(true);
        mypanel.add(lblState);

        lblTelep.setBounds(10, 138, 100, 20);
        lblTelep.setVisible(true);
        mypanel.add(lblTelep);

        lblmail.setBounds(340,138,100,20);
        lblmail.setVisible(true);
        mypanel.add(lblmail);

        txtID.setBounds(28, 34, 100, 20);
        txtID.setBackground(Color.CYAN);
        txtID.setVisible(true);
        mypanel.add(txtID);

        txtLastName.setBounds(78, 59, 100, 20);
        txtLastName.setVisible(true);
        mypanel.add(txtLastName);

        txtFirstName.setBounds(258, 59, 100, 20);
        txtFirstName.setVisible(true);
        mypanel.add(txtFirstName);

        txtAddr.setBounds(70, 88, 100, 20);
        txtAddr.setVisible(true);
        mypanel.add(txtAddr);

        txtCity.setBounds(40, 114, 100, 20);
        txtCity.setVisible(true);
        mypanel.add(txtCity);

        txtState.setBounds(190, 114, 100, 20);
        txtState.setVisible(true);
        mypanel.add(txtState);

        txtTele.setBounds(75, 138, 100, 20);
        txtTele.setVisible(true);
        mypanel.add(txtTele);

        txtmail.setBounds(382, 138, 100, 20);
        txtmail.setVisible(true);
        mypanel.add(txtmail);

        lblMi.setBounds(362, 59, 100, 20);
        lblMi.setVisible(true);
        mypanel.add(lblMi);

        txtMI.setBounds(382, 59, 100, 20);
        txtMI.setVisible(true);
        mypanel.add(txtMI);

        mainWindow.add(mypanel);
        mypanel.setVisible(true);
        btnView.setBounds(50, 188,100, 20);
        btnView.setVisible(true);
        mainWindow.add(btnView);

        btnInsert.setBounds(160, 188,100, 20);
        btnInsert.setVisible(true);
        mainWindow.add(btnInsert);

        btnUpdate.setBounds(270, 188,100, 20);
        btnUpdate.setVisible(true);
        mainWindow.add(btnUpdate);

        btnClear.setBounds(380, 188,100, 20);
        btnClear.setVisible(true);
        mainWindow.add(btnClear);

        lbl_dbconn.setBounds(10, 215, 300, 20);
        mainWindow.add(lbl_dbconn);
        lbl_dbconn.setVisible(true);

        lbl_error.setBounds(10, 240, 1000, 20);
        lbl_error.setVisible(true);
        mainWindow.add(lbl_error);


        mainWindow.setSize(540, 300);
        mainWindow.setResizable(false);
        mainWindow.getContentPane().setBackground(Color.WHITE);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setLayout(null);
        mypanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),   // border color
                "Staff Information",                           // legend
                TitledBorder.LEFT,                           // title justification
                TitledBorder.DEFAULT_POSITION,                // legend position
                new Font("Arial", Font.BOLD, 14)));            // legend fon
        mainWindow.setVisible(true);

        // Calling the method DB to verify and check database connectivity
        initialiseDB();

        // action listener for Insert Button
        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validating if any of the fields are empty
                if (txtID.getText().isEmpty() || txtLastName.getText().isEmpty() || txtFirstName.getText().isEmpty() || txtMI.getText().isEmpty() || txtAddr.getText().isEmpty() || txtCity.getText().isEmpty() || txtState.getText().isEmpty() || txtTele.getText().isEmpty()) {
                    lbl_error.setText("Error : All fields should be filled");
                    lbl_error.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
                    lbl_error.setForeground(Color.RED);
                    txtLastName.setText("");
                    txtFirstName.setText("");
                    txtmail.setText("");
                    txtAddr.setText("");
                    txtCity.setText("");
                    txtState.setText("");
                    txtMI.setText("");
                    txtTele.setText("");
                }

                else
                {
                    // Calling the AddRecord() Method
                    AddRecord();
                }

            }
        });

        // Action Listener for the View Button
        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calling the viewRecord() method.
                viewRecord();
            }
        });

        // Action Listener for the Update button
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtID.getText().isEmpty() || txtLastName.getText().isEmpty() || txtFirstName.getText().isEmpty() || txtMI.getText().isEmpty() || txtAddr.getText().isEmpty() || txtCity.getText().isEmpty() || txtState.getText().isEmpty() || txtTele.getText().isEmpty()) {
                    lbl_error.setText("Error : All fields should be filled");
                    lbl_error.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
                    lbl_error.setForeground(Color.RED);
                    txtLastName.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
                    txtLastName.setText("");
                    txtFirstName.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
                    txtFirstName.setText("");
                    txtmail.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
                    txtmail.setText("");
                    txtAddr.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
                    txtAddr.setText("");
                    txtCity.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
                    txtCity.setText("");
                    txtState.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
                    txtState.setText("");
                    txtMI.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
                    txtMI.setText("");
                    txtTele.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
                    txtTele.setText("");
                }
                else
                {
                    updateRecord();
                }
            }
        });

        // Action Listener for the Clear Button.
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //clearRecord();
                txtID.setText("");
                txtLastName.setText("");
                txtFirstName.setText("");
                txtmail.setText("");
                txtAddr.setText("");
                txtCity.setText("");
                txtState.setText("");
                txtMI.setText("");
                txtTele.setText("");
            }
        });



    }
    private void initialiseDB()
    {
        /*To validate the process of loading the Oracle JDBC driver into JVM and using displaying
        the CLASSNOTFOUNDEXCEPTION in case driver didn't load correctly
        */
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver loaded successfully");


        }
        catch(ClassNotFoundException e)
        {
            lbl_dbconn.setText(e.getMessage());
            lbl_error.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
            lbl_error.setForeground(Color.RED);
        }

        // Using Connection class and defining the variable connection of type Connection class.
        Connection connection;

        /*
            Establishing the connection to Oracle Database using JDBC facility by using the
            getConnection() method of the DriverManager with URL, username, and password passed as parameters.

            After successful connection with Oracle Database
            Using stmt with method createStatement() method of connection variable(of type Connection) to
            create Statement in which queries are passed and the CRUD operation are done in database via JVM.

            Under Catch block, displaying the SQLEXCEPTION error in label lbl_dbconn.

         */
        try{
            String URL = "jdbc:oracle:thin:@calvin.humber.ca:1521:grok";
            connection = DriverManager.getConnection(URL,"n01540404", "oracle");
            lbl_dbconn.setText("Database Connected");
            stmt = connection.createStatement();
        }

        catch (SQLException e) {
            //e.printStackTrace();
            lbl_dbconn.setText(e.getMessage());
            lbl_dbconn.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
            lbl_dbconn.setForeground(Color.RED);
        }
    }


    // AddRecord Method for Insert Button
    private void AddRecord(){
        String id  = txtID.getText();
        String lnm = txtLastName.getText();
        String fnm = txtFirstName.getText();
        char mi = txtMI.getText().charAt(0);
        String add = txtAddr.getText();
        String city = txtCity.getText();
        String state = txtState.getText();
        String phn = txtTele.getText();
        String em = txtmail.getText();

        //To validate the id length must be 9 digits.
        if(id.length() !=9){
            lbl_error.setText("Id Should be exact 9 digits");
            txtID.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
            return;
        }
        if(state.length() !=2){
            lbl_error.setText("State Should be exactly 2 digits");
            txtID.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
            return;
        }

        // Query for inserting the record into the table.
        String qry_to_add = "INSERT INTO"+"\n"+"Staff (id, lastName, firstName, mi, address, city, state, telephone, email) "+"VALUES('" + id + "','" + lnm + "','" + fnm + "','" + mi + "','" + add + "','"+ city + "','" + state + "','" + phn + "','" + em + "')";

        /*Using executeUpdate() method of stmt (of type Statement) to run the insert query
          and retrieving the number of rows added in variable rslt.

          In catch block, if the constraint is regarding primarykey constraint then displaying the message
          in lbl_err else displaying the  SQLException error.
         */
        try {
            int rslt = stmt.executeUpdate(qry_to_add);
            lbl_error.setText(rslt+ "Records Successfully");
            lbl_error.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
            lbl_error.setForeground(Color.GREEN);

            txtID.setText("");
            txtLastName.setText("");
            txtFirstName.setText("");
            txtmail.setText("");
            txtAddr.setText("");
            txtCity.setText("");
            txtState.setText("");
            txtMI.setText("");
            txtTele.setText("");


        } catch (SQLException e1) {
            // TODO Auto-generated catch block

            txtLastName.setText("");
            txtFirstName.setText("");
            txtmail.setText("");
            txtAddr.setText("");
            txtCity.setText("");
            txtState.setText("");
            txtMI.setText("");
            txtTele.setText("");

            if(e1.getMessage().contains("unique constraint"))
            {

                lbl_error.setText("Id already exists. Try Another Id");
                txtID.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));

            }
            else
            {
                lbl_error.setText(e1.getMessage());
                lbl_error.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
                lbl_error.setForeground(Color.RED);
            }


        }


    }

    /*
        In viewRecord() method, using ResultSet to execute the query using executeQuery("Query") method of stmt(of Statement type)
        and getting each record iteratively using next() method of ResultSet and storing it in variables.
        Displaying the success message in label and clearing the textfield.

        Else, in catch block, displaying the SQLException in lbl_err label.
     */
    private void viewRecord()
    {
        String id_to_fetch = txtID.getText();
        String qry_to_get = "SELECT * "+"FROM Staff "+"WHERE id="+id_to_fetch;
        try {
            ResultSet rs = stmt.executeQuery(qry_to_get);

            if (rs.next()) {
                String id = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String mi = rs.getString(4);
                String address = rs.getString(5);
                String city = rs.getString(6);
                String state = rs.getString(7);
                String phn = rs.getString(8);
                String email = rs.getString(9);

                txtID.setText(id);
                txtLastName.setText(firstName);
                txtFirstName.setText(lastName);
                txtMI.setText(mi);
                txtAddr.setText(address);
                txtCity.setText(city);
                txtState.setText(state);
                txtTele.setText(phn);
                txtmail.setText(email);
                //System.out.println(id+" "+firstName+" "+lastName+" "+mi+" "+address+" "+city+" "+state+" "+phn+" "+email);
                lbl_error.setText("Data Retrieved Successfully");

            } else {
                lbl_error.setText("RECORD NOT FOUND");
                txtLastName.setText("");
                txtFirstName.setText("");
                txtmail.setText("");
                txtAddr.setText("");
                txtCity.setText("");
                txtState.setText("");
                txtMI.setText("");
                txtTele.setText("");
            }

        } catch (SQLException e1) {

            lbl_error.setText(e1.getMessage());
            lbl_error.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
            lbl_error.setForeground(Color.RED);
        }


    }

    /*
        In updateRecord() method, using executeUpdate() method of stmt(of type Statement), which will return an integer value.
        If it returns 0, the id meant for updation doesnt exist. The message is displayed in label
        Else, the updation is done with message Updated Successfully displayed in label.

        In catch, block displaying the SQLEXCEPTION error in the label.
     */

    private void updateRecord()
    {
        String id_to_update = txtID.getText();
        String lnm = txtLastName.getText();
        String fnm = txtFirstName.getText();
        char mi = txtMI.getText().charAt(0);
        String add = txtAddr.getText();
        String city = txtCity.getText();
        String state = txtState.getText();
        String phn = txtTele.getText();
        String em = txtmail.getText();
        if(state.length() !=2){
            lbl_error.setText("State Should be exactly 2 char");
            txtID.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
            return;
        }

        String qry_to_update = "UPDATE Staff SET lastName='" + lnm + "', firstName='" + fnm + "', mi='" + mi + "', address='" + add + "', city='" + city + "', state='" + state + "', telephone='" + phn + "', email='" + em + "' WHERE id='" + id_to_update + "'";
        try {
            int RecordsUpdated = stmt.executeUpdate(qry_to_update);
            if(RecordsUpdated == 0)
            {
                lbl_error.setText("The Id Doesnt Exists");
                txtID.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));

            }
            else
            {
                lbl_error.setText(RecordsUpdated+" records updated successfully");
                lbl_error.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
                lbl_error.setForeground(Color.GREEN);
                txtID.setText("");
                txtLastName.setText("");
                txtFirstName.setText("");
                txtmail.setText("");
                txtAddr.setText("");
                txtCity.setText("");
                txtState.setText("");
                txtMI.setText("");
                txtTele.setText("");
            }




        } catch (SQLException e1) {
            lbl_error.setText(e1.getMessage());
            lbl_error.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
            lbl_error.setForeground(Color.RED);

        }


    }

    // This method was meant to delete the record from the database table on the basis of id entered in GUI.

    /*
    private void clearRecord()
    {
        String id_to_clear = txtID.getText();
        String qry_to_delete ="DELETE FROM Staff WHERE id='" + id_to_clear + "'";
        try{
            int DeletedRows = stmt.executeUpdate(qry_to_delete);
            if(DeletedRows==0)
            {
                lbl_error.setText("The Id doesnt Exists");
                txtID.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
            }
            else
            {
                lbl_error.setText(DeletedRows+" deleted successfully");
                lbl_error.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
                lbl_error.setForeground(Color.GREEN);
            }
        }
        catch(SQLException e1)
        {
            lbl_error.setText(e1.getMessage());
            lbl_error.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
            lbl_error.setForeground(Color.RED);
        }
    }*/

}
