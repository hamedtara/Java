package businessPackage;

//defining the class Person
public class Person {

    //declaring the fields firstName and recNum
    private String firstName;
    private int recNum;

    // Define the maximum lengths of first name, last name, and phone number
    public static final int MAX_FIRST_NAME_LENGTH = 20;
    private String lastName;
    public static final int MAX_LAST_NAME_LENGTH = 25;
    private String phone;
    public static final int MAX_PHONE_LENGTH = 10;
    private int age;

    //Constructor function with five arguments
    public Person(int rec, String firstName, String lastName,int age,String phone){
        this.recNum = rec;
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        this.age = age;
    }

    //getter for record number
    public int getRecNum(){
        return recNum;
    }
    //setter for record number
    public void setRecNum(int rec)
    {
        this.recNum = rec;
    }
    //getter for first name
    public String getFirstName() {
        return firstName;
    }

    //setter for first name
    public void setFirstName(String firstName) {
        if (firstName.length() <= MAX_FIRST_NAME_LENGTH) {
            this.firstName = firstName;
        } else {

            throw new IllegalArgumentException("max length of first name is 20 .");

        }

    }

    //getter for last name
    public String getLastName() {
        return lastName;
    }

    //setter for lastname
    public void setLastName(String lastName) {
        if (lastName.length() <= MAX_LAST_NAME_LENGTH) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("max length of last name is 25.");
        }


    }

    //getter for phone number
    public String getPhone() {
        return phone;
    }

    //setter for phone number
    public void setPhone(String phone) {
        if (phone.length() <= MAX_PHONE_LENGTH) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("max length of phone number is 10.");
        }

    }

    //getter for age
    public int getAge() {
        return age;
    }

    //setter for age
    public void setAge(int age) {
        this.age = age;
    }
}
