package ca.myjava.insert;

import java.sql.*;
import java.util.Scanner;

public class InsertTablePreparedSTM {
    public InsertTablePreparedSTM(Connection conn) throws SQLException {
        String query = "INSERT INTO Country (name, code, location, LifeExpectancy) VALUES (?, ?, ?, ?)";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Name of the Country: ");
        String name= scanner.next();
        System.out.print("Enter code: ");
        String code= scanner.next();
        System.out.print("Enter the Location/Continent: ");
        String location= scanner.next();
        System.out.print("Enter the LifeExpectancy:");
        int population = scanner.nextInt();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, code);
        statement.setString(3, location);
        statement.setInt(4, population);
        statement.executeUpdate();
        statement.close();
    }
}
