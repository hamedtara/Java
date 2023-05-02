package ca.myjava.insert;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTableStaticSQL {
    public InsertTableStaticSQL(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Name of the Country: ");
        String name= scanner.next();
        System.out.print("Enter code: ");
        String code= scanner.next();
        System.out.print("Enter the Location/Continent: ");
        String location= scanner.next();
        System.out.print("Enter the LifeExpectancy:");
        int population = scanner.nextInt();
        String query = "INSERT INTO Country (name, code, location, LifeExpectancy) VALUES ('" + name + "', '" + code+ "', '"+location + "',"+population+")";
        stmt.executeUpdate(query);
        stmt.close();
    }
}
