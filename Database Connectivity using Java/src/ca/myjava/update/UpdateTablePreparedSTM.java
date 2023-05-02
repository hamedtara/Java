package ca.myjava.update;

import java.sql.*;
import java.util.Scanner;

public class UpdateTablePreparedSTM {

    public UpdateTablePreparedSTM(Connection conn) throws SQLException {
        String query = "UPDATE Country SET LifeExpectancy = ? WHERE code = ?";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter LifeExpectancy: ");
        int population = scanner.nextInt();
        System.out.print("Enter code: ");
        String code= scanner.next();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, population);
        statement.setString(2, code);
        statement.executeUpdate();
        statement.close();
    }
}
