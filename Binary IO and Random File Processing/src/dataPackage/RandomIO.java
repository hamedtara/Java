package dataPackage;

import businessPackage.Person;

import java.io.IOException;
import java.io.RandomAccessFile;

// Define the RandomIO class
public class RandomIO {
    // Declare a private RandomAccessFile object
    private RandomAccessFile file;

    // Define the maximum lengths of first name, last name, and phone number
//    public static final int MAX_FIRST_NAME_LENGTH = 20;
//    public static final int MAX_LAST_NAME_LENGTH = 25;
//    public static final int MAX_PHONE_LENGTH = 10;
//
//    public static final int AGE = 4;
//
//    public static final int RECORD_SIZE = AGE+MAX_FIRST_NAME_LENGTH+MAX_LAST_NAME_LENGTH+MAX_PHONE_LENGTH+AGE;

    // Define the constructor of the RandomIO class
    public RandomIO(){
        file = null;
    }

    // Define the open method, which opens the specified file in read/write mode
    public void open(String filename) throws IOException {
        if(file!= null)
        {
            file.close();
        }
        file = new RandomAccessFile(filename,"rw");
    }

    // Define the Seek method, which sets the file pointer to the end of the file
    public void Seek() throws IOException
    {
       file.seek(file.length());

    }
    /*public void Clear() throws IOException{
        try {
            // Create a new RandomAccessFile object
            RandomAccessFile file = new RandomAccessFile("person.dat", "rw");

            // Set the length of the file to 0
            file.setLength(0);

            // Close the file
            file.close();

            System.out.println("The binary file has been emptied successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while trying to empty the binary file.");
            e.printStackTrace();
        }

    }*/
    // Define the size method, which returns the number of records in the file
//    public int size() throws IOException
//    {
//        return (int)(file.length()/RECORD_SIZE);
//    }
    // Define the close method, which closes the file
    public void close() throws IOException
    {
        if(file!= null)
        {
            file.close();
        }
        file = null;
    }

    // Define the write method, which writes a Person object to the file
    public void write(Person p) throws IOException
    {


        file.writeInt(p.getRecNum());
        file.writeUTF(p.getFirstName());
        file.writeUTF(p.getLastName());
        file.writeInt(p.getAge());
        file.writeUTF(p.getPhone());
    }
    // Define the find method, which searches for a record with the given record number in the file

    public Person find(int recn) throws IOException {
        file.seek(0);
        while (file.getFilePointer() < file.length()) {
            int recordNumber = file.readInt();
            if (recordNumber == recn) {
                String firstName = file.readUTF();
                String lastName = file.readUTF();
                int age = file.readInt();
                String phone = file.readUTF();
                return new Person(recordNumber, firstName, lastName, age, phone);
            } else {
                file.readUTF();
                file.readUTF();
                file.readInt();
                file.readUTF();
            }
        }
        return null; // record not found
    }



}
