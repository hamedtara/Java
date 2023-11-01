<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%! int day = 3; %>
<%! int fontSize;  %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<% 
out.println("Hello my name is Hamed Tara ");

%>

<p>Today's Date: <%= new java.util.Date() %></p> <%-- I assumed you wanted to print the current date --%>

<% if(day == 1 || day == 7) { %>
	<p>Today is weekend </p>
<% } else { %>
	<p>Today is not weekend</p>
<% } %>

<% for (fontSize = 1; fontSize <= 3; fontSize++) { %>
	<font color="green" size="<%= fontSize %>">Text with font size <%= fontSize %></font><br>
<% } %>

</body>
</html>
