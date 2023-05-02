package ca.myjava.unknown;

import java.sql.*;
import java.util.Scanner;

public class AnySQL {

    public AnySQL(Connection conn) {

            try {

                // Creating a statement object to execute SQL commands
                Statement stmt = conn.createStatement();

                // Accepting user input of SQL command from the console
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter query: ");
                String query = scanner.nextLine();


                // Executing the SQL command using JDBC API
                boolean isResultSet = stmt.execute(query);

                // Checking if the SQL command resulted in a ResultSet
                if (isResultSet) {
                    // Retrieving and displaying the query results on the console
                    ResultSet rs = stmt.getResultSet();
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int numCols = rsmd.getColumnCount();
                    while (rs.next()) {
                        for (int i = 1; i <= numCols; i++) {
                            System.out.print(rs.getString(i) + "\t");
                        }
                        System.out.println();
                    }
                } else {
                    // Displaying the number of rows affected by the SQL command on the console
                    int num = stmt.getUpdateCount();
                    System.out.println(num + " rows affected.");
                }

                // Closing the database connection
                stmt.close();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }