package ca.myjava.delete;

import java.sql.*;
import java.util.Scanner;

public class DeleteTablePreparedSTM {
    public DeleteTablePreparedSTM(Connection conn) throws SQLException {
        String query = "DELETE FROM Country WHERE code = ?";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code: ");
        String code= scanner.next();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, code);
        statement.executeUpdate();
        statement.close();
    }
}
