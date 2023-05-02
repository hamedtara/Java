
import ca.myjava.delete.DeleteTablePreparedSTM;
import ca.myjava.delete.DeleteTableStaticSQL;
import ca.myjava.insert.InsertTablePreparedSTM;
import ca.myjava.insert.InsertTableStaticSQL;
import ca.myjava.query.QueryTablePreparedSTM2;
import ca.myjava.query.QueryTableStaticSQL;
import ca.myjava.unknown.AnySQL;
import ca.myjava.update.UpdateTablePreparedSTM;
import ca.myjava.update.UpdateTableStaticSQL;
import ca.myjava.update.UpdateTableUpdateResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@calvin.humber.ca:1521:grok", "n01514629", "oracle");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Hi , Welcome to Country Table program ");
            System.out.println("------------------------------------- ");

            while(true) {
                System.out.println("Please choose an operation to perform:");
                System.out.println("------------------------------------- ");
                System.out.println("1. Query table using static SQL");
                System.out.println("2. Query table using prepared statement");
                System.out.println("3. Insert into table using static SQL");
                System.out.println("4. Insert into table using prepared statement");
                System.out.println("5. Delete from table using static SQL");
                System.out.println("6. Delete from table using prepared statement");
                System.out.println("7. Update table using static SQL");
                System.out.println("8. Update table using prepared statement");
                System.out.println("9. Run any SQL command");
                System.out.println("10. Update table update result set ");
                System.out.println("0. Exit program");
                System.out.println("Enter choice: ");
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 0:
                            conn.close();
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a number from 0 to 9.");
                            break;
                        case 1:
                            new QueryTableStaticSQL(conn);
                            break;
                        case 2:
                            new QueryTablePreparedSTM2(conn);
                            break;
                        case 3:
                            new InsertTableStaticSQL(conn);
                            break;
                        case 4:
                            new InsertTablePreparedSTM(conn);
                            break;
                        case 5:
                            new DeleteTableStaticSQL(conn);
                            break;
                        case 6:
                            new DeleteTablePreparedSTM(conn);
                            break;
                        case 7:
                            new UpdateTableStaticSQL(conn);
                            break;
                        case 8:
                            new UpdateTablePreparedSTM(conn);
                            break;
                        case 9:
                            new AnySQL(conn);
                            break;
                        case 10:
                            new UpdateTableUpdateResultSet(conn);
                            break;
                    }
                }
        } catch (ClassNotFoundException var4) {
            var4.printStackTrace();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

    }
}



/*
import ca.myjava.delete.DeleteTablePreparedSTM;
import ca.myjava.delete.DeleteTableStaticSQL;
import ca.myjava.insert.InsertTablePreparedSTM;
import ca.myjava.insert.InsertTableStaticSQL;
import ca.myjava.query.QueryTablePreparedSTM2;
import ca.myjava.query.QueryTableStaticSQL;
import ca.myjava.unknown.AnySQL;
import ca.myjava.update.UpdateTablePreparedSTM;
import ca.myjava.update.UpdateTableStaticSQL;
import ca.myjava.update.UpdateTableUpdateResultSet;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

            try {
                // Load the Oracle JDBC driver
                Class.forName("oracle.jdbc.driver.OracleDriver");

                // Connect to the database
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@calvin.humber.ca:1521:grok", "n01514629", "oracle");

                new UpdateTableUpdateResultSet(conn);
//                new QueryTablePreparedSTM2(conn);98
//                new QueryTableStaticSQL(conn);
//                new AnySQL(conn);
                //new InsertTablePreparedSTM(conn);
                //new InsertTableStaticSQL(conn);
                //new DeleteTablePreparedSTM(conn);
               // new AnySQL(conn);
                // Close the resources

                conn.close();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
*/
