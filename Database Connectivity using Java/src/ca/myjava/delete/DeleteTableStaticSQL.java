package ca.myjava.delete;

import java.sql.*;
import java.util.Scanner;

public class DeleteTableStaticSQL {
    public DeleteTableStaticSQL(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code: ");
        String code= scanner.next();
        String query = "DELETE FROM Country WHERE code = '" + code + "'";
        Statement stmt=conn.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
    }
}
