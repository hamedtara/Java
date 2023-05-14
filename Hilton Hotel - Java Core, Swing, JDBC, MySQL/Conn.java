import java.sql.*;

public class Conn {
    Connection conn;
    Statement s;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagmentsystem", "root", "******");
            s = conn.createStatement();
            System.out.println("Database connected");

        } catch (ClassNotFoundException e) {
            System.err.println("Unable to load MySQL JDBC driver");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Unable to establish a connection to the database");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Unable to close database connection");
            e.printStackTrace();
        }
    }
}
