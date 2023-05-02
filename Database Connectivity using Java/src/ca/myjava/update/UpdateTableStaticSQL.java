package ca.myjava.update;

import java.sql.*;
import java.util.Scanner;

public class UpdateTableStaticSQL {

    public UpdateTableStaticSQL(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter LifeExpectancy: ");
        int population = scanner.nextInt();
        System.out.print("Enter code: ");
        String code= scanner.next();
        String query = "UPDATE Country SET LifeExpectancy= " + population + " WHERE code= '" + code+"'";
        stmt.executeUpdate(query);
        stmt.close();
    }
}
