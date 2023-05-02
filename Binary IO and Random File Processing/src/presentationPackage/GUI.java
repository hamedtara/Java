package presentationPackage;

import dataPackage.RandomIO;
import businessPackage.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    // Create a new JFrame
    JFrame frame = new JFrame("Random File Processing ");

    // Create various GUI components
    JLabel lblRecord = new JLabel("Record #");
    JTextField txtRecord = new JTextField();
    JLabel lblFirstName = new JLabel("First Name");
    JTextField txtFirstName = new JTextField();
    JLabel lblLastName = new JLabel("Last Name");
    JTextField txtLastName = new JTextField();
    JLabel lblAge = new JLabel("Age");
    JTextField txtAge = new JTextField();


    JLabel lblPhone = new JLabel("Phone");
    JTextField txtPhone = new JTextField();
    JButton addButton = new JButton("Add");
    JButton findButton = new JButton("Find");
    JLabel errlbl = new JLabel("");

    // Constructor for the GUI class
    public GUI()
    {
        // Set properties for the JFrame
        frame.setSize(350, 420);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // Set properties for various GUI components and add them to the JFrame

        lblRecord.setBounds(80, 50, 100, 20);
        txtRecord.setBounds(160, 50, 100, 20);
        lblFirstName.setBounds(80, 100, 100, 20);
        txtFirstName.setBounds(160, 100, 100, 20);
        lblLastName.setBounds(80, 150, 100, 20);
        txtLastName.setBounds(160, 150, 100, 20);
        lblAge.setBounds(80, 200, 100, 20);
        txtAge.setBounds(160, 200, 100, 20);
        lblPhone.setBounds(80, 250, 100, 20);
        txtPhone.setBounds(160, 250, 100, 20);
        addButton.setBounds(80, 290, 85, 20);
        findButton.setBounds(215, 290, 85, 20);
        errlbl.setBounds(80,310,400,20);
        errlbl.setForeground(Color.red);

        frame.add(lblRecord);
        frame.add(txtRecord);
        frame.add(lblFirstName);
        frame.add(txtFirstName);
        frame.add(lblLastName);
        frame.add(txtLastName);
        frame.add(lblAge);
        frame.add(txtAge);
        frame.add(lblPhone);
        frame.add(txtPhone);
        frame.add(addButton);
        frame.add(findButton);
        frame.add(errlbl);

        frame.setVisible(true);
        /*RandomIO rf = new RandomIO();
        try{
            rf.Clear();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }*/

        // ActionListener for the addButton
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try
                {
                    // Get input from the GUI components
                    int recn = Integer.parseInt(txtRecord.getText());
                    String fname = txtFirstName.getText();
                    String lname   = txtLastName.getText();
                    String phn = txtPhone.getText();
                    int age = Integer.parseInt(txtAge.getText());

                    // Create a new Person object and write it to a file
                    Person person = null;
                    RandomIO raf  = new RandomIO();
                    raf.open("person.dat");


                    person = new Person(recn,fname,lname,age,phn);
                    raf.Seek();
                    raf.write(person);

                    // Clear the input fields
                    txtRecord.setText("");
                    txtFirstName.setText("");
                    txtLastName.setText("");
                    txtAge.setText("");
                    txtPhone.setText("");



                }

                catch (Exception ex)
                {
                    ex.printStackTrace();
                }


            }
        });

        findButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    // Create a new RandomIO object and open the "person.dat" file
                    RandomIO raf = new RandomIO();
                    raf.open("person.dat");

                    // Parse the record number from the txtRecord JTextField
                    int toFind = Integer.parseInt(txtRecord.getText());
                    // Call the find method of the RandomIO object to find the Person object with the given record number
                    Person p  = raf.find(toFind);
                    //System.out.println(p.getLastName());
                    // Set the contents of the JTextFields to the values of the Person object
                    txtRecord.setText(String.valueOf(p.getRecNum()));
                    txtFirstName.setText(p.getFirstName());
                    txtLastName.setText(p.getLastName());
                    txtAge.setText(String.valueOf(p.getAge()));
                    txtPhone.setText(p.getPhone());
                    // Close the RandomIO object and clear the error message
                    raf.close();
                    errlbl.setText("");



                }
                // Catch any exceptions that occur and display an error message in the errlbl JLabel
                catch (Exception ex)
                {
                    errlbl.setText("Record Number Not Found");
                    // Clear the contents of the JTextFields
                    txtRecord.setText("");
                    txtFirstName.setText("");
                    txtLastName.setText("");
                    txtAge.setText("");
                    txtPhone.setText("");
                    //ex.printStackTrace();
                }
            }
        });



    }
}
