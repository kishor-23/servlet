<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="login.css">
</head>
<body>
    <div class="center">
      <h1>Register</h1>
      <form method="post" action="RegisterServlet">
        <div class="txt_field">
          <input type="text" name="username" required/>
          <span></span>
          <label>Username</label>
        </div>
        <div class="txt_field">
          <input type="password" name="password" required/>
          <span></span>
          <label>Password</label>
        </div>
        <div class="txt_field">
            <input type="email" name="email" required/>
            <span></span>
            <label>Email</label>
          </div>
          <br>
        <input type="submit" name="sign" value="Register">
        <div class="signup_link">
          Already a member? <a href="login.jsp">Sign in</a>
        </div>
        
        <!-- Display error message if registration fails -->
        <% 
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
        %>
        <p class="error"><%= errorMessage %></p>
        <% 
        } 
        %>
        
      </form>
    </div>

</body>
</html>
