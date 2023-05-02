package ca.myjava.query;
import java.sql.*;
import java.util.Scanner;

public class QueryTableStaticSQL {

    public QueryTableStaticSQL(Connection conn) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the minimum value");
        int min  = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the maximum value");
        int max = sc.nextInt();
        sc.nextLine();
        // Define the SQL query
        String query = "SELECT * FROM Country WHERE LifeExpectancy BETWEEN "+min+ " AND "+ max;

        // Create a statement object
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            // Execute the query and retrieve the results
            ResultSet rs = stmt.executeQuery(query);

            // Process the results
            while (rs.next()) {
                String name = rs.getString("Name");
                String code = rs.getString("Code");
                String continent = rs.getString("location");
                double lifeExpectancy = rs.getDouble("LifeExpectancy");
                System.out.println(name + ", " + code + ", " + continent + ", " + lifeExpectancy);
            }

            // Close the resources
            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
    }
}