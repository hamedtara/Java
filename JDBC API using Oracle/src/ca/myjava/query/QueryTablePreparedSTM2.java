package ca.myjava.query;



import java.sql.*;


public class QueryTablePreparedSTM2 {

    public QueryTablePreparedSTM2(Connection conn) {

        // Define the SQL query with placeholders
        String query = "SELECT * FROM Country WHERE LifeExpectancy BETWEEN ? AND ?";
        try {

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, 75.0);
            pstmt.setDouble(2, 85.0);

            ResultSet rs = pstmt.executeQuery();

            // Process the results
            while (rs.next()) {
                String name = rs.getString("Name");
                String code = rs.getString("Code");
                String continent = rs.getString("location");
                double lifeExpectancy = rs.getDouble("LifeExpectancy");
                System.out.println(name + ", " + code + ", " + continent + ", " + lifeExpectancy);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}