package ca.myjava.update;
import java.sql.*;
import java.util.Scanner;

public class UpdateTableUpdateResultSet {
    public UpdateTableUpdateResultSet(Connection conn) throws SQLException {
        String query = "SELECT * FROM Country WHERE code = ?";
        Scanner sc = new Scanner(System.in);
        PreparedStatement selectStatement = conn.prepareStatement(query);
        System.out.print("Enter code: ");
        String code = sc.next();

        selectStatement.setString(1, code);

        ResultSet resultSet = selectStatement.executeQuery();
                                // Check if the result set is not empty
        if (resultSet.next()) {
                    // Get the current LifeExpectancy value from the result set
        float currentLifeExpectancy = resultSet.getFloat("LifeExpectancy");

                    // Prompt the user to enter the new LifeExpectancy value
        System.out.print("Enter new LifeExpectancy value: ");
        float newLifeExpectancy = sc.nextFloat();

                    // Update the table using a PreparedStatement
        String updateQuery = "UPDATE Country SET LifeExpectancy = ? WHERE code = ?";
        PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
        updateStatement.setFloat(1, newLifeExpectancy);
        updateStatement.setString(2, code);
        updateStatement.executeUpdate();

        System.out.println("LifeExpectancy updated from " + currentLifeExpectancy + " to " + newLifeExpectancy + " for code " + code);
                } else {
        System.out.println("No country found with code " + code);
        resultSet.close();
        sc.close();
                }
            }
        }
