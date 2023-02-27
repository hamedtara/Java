import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;
import java.util.List;

public class AddUpdate extends ProductManagement {

    List<Map<String, Object>> products = new ArrayList<>();

    List<Integer> productIDLIst;

    // creating a new file object to represent the product database file
    File file = new File("products.dat");

    // GUI components

    JLabel productID = new JLabel("Product ID");
    JLabel name = new JLabel("Name");
    JLabel desc = new JLabel("Description");
    JLabel qty = new JLabel("Quantity in hand");
    JLabel price = new JLabel("Unit Price");
    JButton btnadd = new JButton("Add");
    JButton btnupdate = new JButton("Update");
    JButton btnnext = new JButton("Next");
    JButton btnlast = new JButton("Last");
    JButton btnfirst = new JButton("First");
    JButton btnprevious = new JButton("Previous");
    JTextField txtProdID = new JTextField();
    JTextField txtName = new JTextField();
    JTextField txtDesc = new JTextField();
    JTextField txtqty = new JTextField();
    JTextField txtPrice = new JTextField();


    //constructor method for AddUpdate
    public AddUpdate() {

        productIDLIst = new ArrayList<Integer>();
        // read the product IDs from the database file and store in a list
        try (DataInputStream dis = new DataInputStream(new FileInputStream("products.dat"))) {
            while (dis.available() > 0) {
                String productID = dis.readUTF();
                String name = dis.readUTF();
                String description = dis.readUTF();
                int quantity = dis.readInt();
                double price = dis.readDouble();
                productIDLIst.add(Integer.valueOf(productID));

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

        // display the product IDs on the console
        for (int productID : productIDLIst) {
            System.out.println(productID);
        }

        // set the properties for the main window
        mainWindow.setSize(500, 500);
        mainWindow.setResizable(false);
        mainWindow.getContentPane().setBackground(Color.WHITE);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setLayout(null);
        // hide the menu bar and title
        menuBar.setVisible(false);
        title.setVisible(false);
        mainWindow.setTitle("Add/Update Product");

        //Adding components to add/update window
        productID.setBounds(15, 10, 100, 20);
        mainWindow.add(productID);
        name.setBounds(15, 35, 100, 20);
        mainWindow.add(name);
        desc.setBounds(15, 60, 100, 20);
        mainWindow.add(desc);
        qty.setBounds(260, 60, 100, 20);
        mainWindow.add(qty);
        price.setBounds(260, 90, 85, 20);
        mainWindow.add(price);


        txtProdID.setBounds(120, 10, 100, 20);
        mainWindow.add(txtProdID);
        txtName.setBounds(120, 35, 200, 20);
        mainWindow.add(txtName);
        txtqty.setBounds(360, 60, 100, 20);
        mainWindow.add(txtqty);
        txtPrice.setBounds(360, 90, 100, 20);
        mainWindow.add(txtPrice);
        txtDesc.setBounds(120, 60, 120, 80);
        mainWindow.add(txtDesc);

        btnadd.setBounds(10, 150, 85, 20);
        mainWindow.add(btnadd);
        btnfirst.setBounds(10, 180, 85, 20);
        mainWindow.add(btnfirst);
        btnupdate.setBounds(125, 150, 85, 20);
        mainWindow.add(btnupdate);
        btnprevious.setBounds(125, 180, 85, 20);
        mainWindow.add(btnprevious);
        btnnext.setBounds(240, 180, 85, 20);
        mainWindow.add(btnnext);
        btnlast.setBounds(345, 180, 85, 20);
        mainWindow.add(btnlast);


        btnadd.addActionListener(this);
        btnfirst.addActionListener(this);
        btnlast.addActionListener(this);
        btnnext.addActionListener(this);
        btnprevious.addActionListener(this);
        btnupdate.addActionListener(this);

    }

    // Overrides the actionPerformed method to handle user actions on  buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();          // Gets the source of the event
        if (source == btnadd) {
            addProduct();                   //Call the addProduct method
        }
        if (source == btnfirst) {
            readFirst();                    // Call the readFirst method
        }
        if (source == btnlast) {
            readLast();                     // Call the readLast method
        }
        if (source == btnprevious) {
            readPreviousProduct(txtProdID.getText());               // Call the readPreviousProduct method with the text from txtProdID as a parameter
        }
        if (source == btnnext) {
            readNextProduct(txtProdID.getText());           // Call the readNextProduct method with the text from txtProdID as a parameter
        }
        if (source == btnupdate) {
            updateProduct();                    // Call the updateProduct method

        }


    }

    //Updates the information for a specific product by searching the product with its ID in the file
    // and updating the fields with the new values.
    public void updateProduct() {
        try (RandomAccessFile raf = new RandomAccessFile("src/products.dat", "rw")) {

            while (raf.getFilePointer() < raf.length()) {
                String id = raf.readUTF();
                System.out.println(id);
                if (id.equals(txtProdID.getText())) {
                    // found the record to update
                    raf.writeUTF(txtProdID.getText());
                    raf.writeUTF(txtName.getText());
                    raf.writeUTF(txtDesc.getText());
                    raf.writeInt(Integer.parseInt(txtqty.getText()));
                    raf.writeDouble(Integer.parseInt(txtPrice.getText()));
                    JOptionPane.showMessageDialog(null, "Record updated successfully.");
                    return;
                } else {
                    // skip to the next record
                    raf.readUTF();
                    raf.readUTF();
                    raf.readUTF();
                    raf.readInt();
                    raf.readDouble();
                }
            }
            //if reached here, record not found
            System.out.println("Product with ID " + txtProdID.getText() + " not found in the file.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
    
    // Adds a new product to the database if the ID is unique, and if the required fields (name, quantity, and price)
    // are filled correctly. This method checks the user input for correctness and displays an error message
    // if something is missing.
    private void addProduct() {
        //•	Add – add the information of new product to the file and Product ID should be unique,
        // quantity in hand and unit price should be number and above 0 and name is required
        String productId = txtProdID.getText().trim();
        int intProductId = Integer.parseInt(productId);
        if (productId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "product Id is required");
            return;
        }
        if (productIDLIst.contains(intProductId)) {
            JOptionPane.showMessageDialog(null, "Product ID already exists." +
                    " Please enter a unique product ID.");
            return;
        } else

            productIDLIst.add(intProductId);

        String name = txtName.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name is required.");
            return;
        }

        String description = txtDesc.getText().trim();
        int quantity;
        try {

            quantity = Integer.parseInt(txtqty.getText().trim());

            if (quantity <= 0) {

                JOptionPane.showMessageDialog(null, "Quantity in hand must be a number above 0.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantity in hand must be a number.");
            return;
        }
        double price;
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
            if (price <= 0) {
                JOptionPane.showMessageDialog(null, "Unit price must be a number above 0.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Unit price must be a number.");
            return;
        }
        // write the information to a file

        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeUTF(productId);
            dos.writeUTF(name);
            dos.writeUTF(description);
            dos.writeInt(quantity);
            dos.writeDouble(price);
            JOptionPane.showMessageDialog(null, "Have Successfully added the product to the list.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to write to file: " + e.getMessage());
        }
    }

    //Reads the last record (product) from the file and displays its information on the GUI.
    // This method uses the RandomAccessFile class to seek to the end of the file and read the last record.
    private void readLast() {
        //   File file = new File("products.dat");
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(0); // reset the position to the beginning of the file
            String productId = raf.readUTF();
            String name = raf.readUTF();
            String description = raf.readUTF();
            int quantity = raf.readInt();
            double price = raf.readDouble();

            txtProdID.setText(productId);
            txtName.setText(name);
            txtDesc.setText(description);
            txtqty.setText(String.valueOf(quantity));
            txtPrice.setText(String.valueOf(price));

            /*System.out.println("First Record:");
            System.out.println("Product ID: " + productId);
            System.out.println("Name: " + name);
            System.out.println("Description: " + description);
            System.out.println("Quantity: " + quantity);
            System.out.println("Price: " + price);
            System.out.println("---------------");*/
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to read from file: " + e.getMessage());
        }

    }

    // Reads the first record (product) from the file and displays its information on the GUI.
    // This method uses the DataInputStream class to read the file sequentially and stop
    // at the first record.
    private void readFirst() {
        String productId = "";
        String name = "";
        String description = "";
        int quantity = 0;
        double price = 0.0;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            while (true) {
                productId = dis.readUTF();
                name = dis.readUTF();
                description = dis.readUTF();
                quantity = dis.readInt();
                price = dis.readDouble();
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (IOException e) {
            System.out.println("Failed to read from file: " + e.getMessage());
        }
        txtProdID.setText(productId);
        txtName.setText(name);
        txtDesc.setText(description);
        txtqty.setText(String.valueOf(quantity));
        txtPrice.setText(String.valueOf(price));

        /*System.out.println("Last Record:");
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price: " + price);
        System.out.println("---------------");*/
    }

    //The readPreviousProduct method loops through the products List to find the product with the given ID.
    // Once found, the previous product is retrieved by accessing the List at the previous index (i-1).
    // The data for the previous product is then displayed and used to populate text fields in the GUI.
    public void readPreviousProduct(String productID) {
        List<Product> products = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream("products.dat"))) {
            while (dis.available() > 0) {
                String id = dis.readUTF();
                String name = dis.readUTF();
                String description = dis.readUTF();
                int quantity = dis.readInt();
                double price = dis.readDouble();
                products.add(new Product(id, name, description, quantity, price));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

        for (int i = 1; i < products.size(); i++) {
            if (products.get(i).getProductID().equals(productID)) {
                Product previousProduct = products.get(i - 1);
                System.out.println(previousProduct);
                txtProdID.setText(previousProduct.productID);
                txtName.setText(previousProduct.name);
                txtDesc.setText(previousProduct.description);
                txtqty.setText(String.valueOf(previousProduct.quantity));
                txtPrice.setText(String.valueOf(previousProduct.price));
                return;
            }
        }
        // if reached here, record not found
        System.out.println("Product with ID " + productID + " not found in the file.");
    }

    //The readNextProduct method is similar to readPreviousProduct but instead retrieves
    // the next product by accessing the List at the next index (i+1).
    public void readNextProduct(String productID) {
        List<Product> products = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream("products.dat"))) {
            while (dis.available() > 0) {
                String id = dis.readUTF();
                String name = dis.readUTF();
                String description = dis.readUTF();
                int quantity = dis.readInt();
                double price = dis.readDouble();
                products.add(new Product(id, name, description, quantity, price));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

        for (int i = 0; i < products.size() - 1; i++) {
            if (products.get(i).getProductID().equals(productID)) {
                Product nextProduct = products.get(i + 1);
                System.out.println(nextProduct);
                txtProdID.setText(nextProduct.productID);
                txtName.setText(nextProduct.name);
                txtDesc.setText(nextProduct.description);
                txtqty.setText(String.valueOf(nextProduct.quantity));
                txtPrice.setText(String.valueOf(nextProduct.price));
                return;
            }
        }
        // if reached here, record not found
        System.out.println("Product with ID " + productID + " not found in the file.");
    }
}