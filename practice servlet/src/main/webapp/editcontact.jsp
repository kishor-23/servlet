<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.chainsys.model.Contact" %>
<%@ page import="com.chainsys.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
<%
    Contact contact = (Contact) request.getAttribute("contact");
    int id=(int)request.getAttribute("id");
    User user = (User) session.getAttribute("user");
    int userid = user.getId();
%>
<h2>Edit Contact</h2>
<form action="PhonebookServlet" method="post">
    <input type="hidden" name="action" value="update">
     <input type="hidden" name="updateid" value=<%= id%>>
      <input type="hidden" name="userid" value=<%= userid%>>
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="<%= contact.getName() %>" required><br>
    <label for="phone">Phone Number:</label>
    <input type="text" id="phone" name="phone" value="<%= contact.getPhoneNumber() %>" pattern="[0-9]{10}" required><br>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="<%= contact.getEmail() %>" required><br>
    <input type="submit" value="Add Contact">
</form>
</div>
</body>
</html>
