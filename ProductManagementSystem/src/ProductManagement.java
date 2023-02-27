import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ProductManagement class implements ActionListener interface
public class ProductManagement implements ActionListener {

    // Declare class variables
    JFrame mainWindow=new JFrame("Product Management System");
    JMenuBar menuBar=new JMenuBar();
    JMenu filemenu= new JMenu("File");
    JMenu productMenu=new JMenu("Product");
    JMenuItem exitappl=new JMenuItem("Exit");
    JMenuItem AddUpdate = new JMenuItem("Add/Update");
    JMenuItem findDispl=new JMenuItem("Find/Display");
    JLabel title= new JLabel("Product Management System");

    JButton btnFindDispl = new JButton("Find/Display");
    JTextField txtTo=new JTextField("To");
    JTextField txtFrom = new JTextField("From");
    JTextField txtKeyword = new JTextField("Keyword");
    JTextArea txtDispl=new JTextArea();
    JScrollPane scrollPane = new JScrollPane(txtDispl);



    JRadioButtonMenuItem rbtnPriceRange =new JRadioButtonMenuItem("Price Range");
    JRadioButtonMenuItem rbtnKeyword =new JRadioButtonMenuItem("Keyword");
    JRadioButtonMenuItem rbtnAll =new JRadioButtonMenuItem("All");
    ButtonGroup group = new ButtonGroup();
    JMenu rmenu = new JMenu();
    JMenuBar rmenubar = new JMenuBar();

    // Constructor for ProductManagement class
    public ProductManagement(){

        // Set up main window
        mainWindow.setSize(350, 420);
        mainWindow.setResizable(false);
        mainWindow.getContentPane().setBackground(Color.WHITE);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setLayout(null);
        title.setBounds(80,100, 200, 20);


        // Menubar
        mainWindow.setJMenuBar(menuBar);
        menuBar.add(filemenu);
        menuBar.add(productMenu);
        filemenu.add(exitappl);
        productMenu.add(AddUpdate);
        productMenu.add(findDispl);
        // Add components to main window
        mainWindow.add(title);
        menuBar.setVisible(true);
        mainWindow.setVisible(true);

        // Register action listeners for menu items
        exitappl.addActionListener(this);
        AddUpdate.addActionListener(this);
        findDispl.addActionListener(this);
    }

    // Implement actionPerformed method from ActionListener interface
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        // Handle Exit menu item
        if (source == exitappl) {
            System.exit(0);
        }
        // Handle Add/Update menu item
       if (source == AddUpdate) {
            new AddUpdate();// Add code to add/update a product
        }
        // Handle Find/Display menu item
        if (source == findDispl) {
            new FindDispl(); // Add code to find/display a product
        }
    }
}
