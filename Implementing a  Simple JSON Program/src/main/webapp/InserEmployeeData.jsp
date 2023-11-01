<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String empNo = request.getParameter("empno");
String empName = request.getParameter("empname");
String job = request.getParameter("job");
String salary = request.getParameter("salary");

String dbURL = "jdbc:mysql://localhost:3306/activity8";
String username = "root";
String password = "havijKhan1371";
Connection connection = null;
PreparedStatement statement = null;

try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    // Establish a connection to the database
    connection = DriverManager.getConnection(dbURL, username, password);
    
    String sql = "INSERT INTO emp (empno, empname, job, salary) VALUES (?, ?, ?, ?)";
    statement = connection.prepareStatement(sql);
    
    statement.setString(1, empNo);
    statement.setString(2, empName);
    statement.setString(3, job);
    statement.setString(4, salary);
    
    int result = statement.executeUpdate();
    if(result > 0) {
        out.print("Data inserted successfully!");
    } else {
        out.print("Error inserting data.");
    }

} catch(Exception e) {
    out.print("Database error: " + e.getMessage());
} finally {
    try {
        if(statement != null) statement.close();
        if(connection != null) connection.close();
    } catch(SQLException se) {
        se.printStackTrace();
    }
}
%>
</body>
</html>
