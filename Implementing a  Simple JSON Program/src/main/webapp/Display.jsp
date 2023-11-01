<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Display Employees</title>
</head>
<body>

<h2>Employee Data</h2>

<table border="1">
    <tr>
        <th>Emp No</th>
        <th>Emp Name</th>
        <th>Job</th>
        <th>Salary</th>
    </tr>
<%
String dbURL = "jdbc:mysql://localhost:3306/activity8";
String username = "root";
String password = "havijKhan1371";
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;

try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    connection = DriverManager.getConnection(dbURL, username, password);
    statement = connection.createStatement();
    
    String sql = "SELECT * FROM emp";
    resultSet = statement.executeQuery(sql);
    
    while(resultSet.next()) {
%>
        <tr>
            <td><%= resultSet.getString("empno") %></td>
            <td><%= resultSet.getString("empname") %></td>
            <td><%= resultSet.getString("job") %></td>
            <td><%= resultSet.getString("salary") %></td>
        </tr>
<%
    }

} catch(Exception e) {
    out.print("Database error: " + e.getMessage());
} finally {
    try {
        if(resultSet != null) resultSet.close();
        if(statement != null) statement.close();
        if(connection != null) connection.close();
    } catch(SQLException se) {
        se.printStackTrace();
    }
}
%>
</table>

</body>
</html>
