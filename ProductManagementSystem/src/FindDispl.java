import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.io.*;
import java.sql.Array;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JFrame;

public class FindDispl extends ProductManagement {

    // creating a new file object to represent the product database file
    File file = new File("products.dat");

    //creating object for java.util.List<Product> class
    java.util.List<Product> productList = new ArrayList<Product>();

    //Constructor method for FindDispl
    public FindDispl() {

        //GUI Components
        mainWindow.setSize(700, 700);
        mainWindow.setResizable(false);
        mainWindow.getContentPane().setBackground(Color.WHITE);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setLayout(null);
        title.setVisible(false);
        menuBar.setVisible(true);

        rbtnPriceRange.setBounds(20, 20, 100, 20);
        rbtnKeyword.setBounds(20, 45, 100, 20);
        rbtnAll.setBounds(20, 70, 100, 20);
        txtFrom.setBounds(240, 20, 100, 20);
        txtTo.setBounds(125, 20, 100, 20);
        txtKeyword.setBounds(125, 45, 100, 20);
        btnFindDispl.setBounds(360, 20, 150, 20);
        scrollPane.setBounds(10,60,780,50);
        txtDispl.setBounds(20, 100, 550,550 );
        txtDispl.setBorder(BorderFactory.createEtchedBorder());
        txtDispl.setText("");



        group.add(rbtnPriceRange);
        group.add(rbtnKeyword);
        group.add(rbtnAll);

        // adding components to the mainWindow
        mainWindow.add(rbtnPriceRange);
        mainWindow.add(rbtnKeyword);
        mainWindow.add(rbtnAll);
        mainWindow.add(txtFrom);
        mainWindow.add(txtTo);
        mainWindow.add(txtKeyword);
        mainWindow.add(btnFindDispl);
        mainWindow.add(txtDispl);

        mainWindow.setVisible(true);

        btnFindDispl.addActionListener(this);
    }

    // This is a method override of the actionPerformed method from the ActionListener interface.
    @Override
    public void actionPerformed(ActionEvent e) {

        // Check if the "All" radio button is selected
        if(rbtnAll.isSelected())
        {
            txtDispl.setText(" ");
            // Read all products from the file
            java.util.List<Product> allProducts = (java.util.List<Product>) readAllFile();


            // Display information for each product in the text area
            for(int i=0;i<allProducts.size();i++)
            {
                txtDispl.append("Product Id: "+allProducts.get(i).getProductID()+"-"+"Name: "+allProducts.get(i).getName()+"-"+"Description: "+allProducts.get(i).getDescription()+"-"+"Quantity: "+String.valueOf(allProducts.get(i).getQuantity())+"-"+"Price: "+String.valueOf(allProducts.get(i).getPrice())+"\n");


            }
            // Clear the list of products
            allProducts.clear();
        }
        // Check if the "Keyword" radio button is selected
        else if(rbtnKeyword.isSelected())
        {
            txtDispl.setText("");
            String kywrd = txtKeyword.getText();        // Get the keyword from the text field
            java.util.List<Product> keyproductsList = (java.util.List<Product>) readAllFile(); // Read all products from the file
            //Iterate through each product in list and checks for keyword in each product by calling getter .getName()
            for(int i=0;i<keyproductsList.size();i++)
            {
                if(keyproductsList.get(i).getName().contains(kywrd))
                {
                    txtDispl.append("Product Id: "+keyproductsList.get(i).getProductID()+" - "+"Product Name: "+keyproductsList.get(i).getName()+" - "+"Product Description: "+keyproductsList.get(i).getDescription()+" - "+"Product Quantity: "+String.valueOf(keyproductsList.get(i).getQuantity())+" - "+"Product Price: "+String.valueOf(keyproductsList.get(i).getPrice())+"\n");
                }
            }
            //Clear the list of products
            keyproductsList.clear();

        }
        else if(rbtnPriceRange.isSelected())        // Check if the "Price Range" radio button is selected
        {
            txtDispl.setText("");

            // Get the lower and upper limits of the price range from the text fields
            double low_lim = Double.parseDouble(txtFrom.getText());

            double up_lim = Double.parseDouble(txtTo.getText());

            java.util.List<Product> priceRecordList = (java.util.List<Product>) readAllFile(); // Read all products from the file



            // Display information for each product whose price is within the specified range in the text area
            for(int i=0;i<priceRecordList.size();i++)
            {
                if(priceRecordList.get(i).getPrice()>=low_lim && priceRecordList.get(i).getPrice()<=up_lim)
                {

                    txtDispl.append("Product Id: "+priceRecordList.get(i).getProductID()+" - "+"Product Name: "+priceRecordList.get(i).getName()+" - "+"Product Description: "+priceRecordList.get(i).getDescription()+" - "+"Product Quantity: "+String.valueOf(priceRecordList.get(i).getQuantity())+" - "+"Product Price: "+String.valueOf(priceRecordList.get(i).getPrice())+"\n");


                }
//                else
//                {
//                    continue;
//                }


            }
            priceRecordList.clear();        //Clear list of product



        }

    }


    // This method reads all the data from the "products.dat"
    // file and returns a list of Product objects.
    public List readAllFile() {
        // Creates a DataInputStream object that reads the data from the file.
        try (DataInputStream dis = new DataInputStream(new FileInputStream("products.dat")))
        {
            // Loops through the file until there is no data left to read.
            while (dis.available() > 0)
            {
                // Reads in the product data from the file.
                String productID = dis.readUTF();
                String name = dis.readUTF();
                String description = dis.readUTF();
                int quantity = dis.readInt();
                double price = dis.readDouble();

                // Creates a new Product object with the read in data and adds it to the productList.
                Product prdct = new Product(productID,name,description,quantity,price);
                productList.add(prdct);


            }
        }
        // Catches a FileNotFoundException and prints out an error message.
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        // Catches an IOException and prints out an error message.
        catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        // Returns the productList.
        return productList;

    }





}


