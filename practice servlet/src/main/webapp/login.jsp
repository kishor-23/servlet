<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="login.css">
</head>
<body>
  <div class="center">
      <h1> Login</h1>
      <form method="post"  action="LoginServlet" >
        <div class="txt_field">
          <input type="email" name="email" required/>
          <span></span>
          <label>Email</label>
        </div>
        <div class="txt_field">
          <input type="password" name="password" required/>
          <span></span>
          <label>Password</label>
        </div>
        <% 
        String msg = request.getParameter("msg");
        if ("1".equals(msg)) {
        %>
            <p class="error">Password not match.</p>
        <% 
        } 
        %>
        <br>
        <!-- <div class="pass">Forgot Password?</div> -->
        <input type="submit" value="Login" name="sign">
        <div class="signup_link">
          Not a member? <a href="register.jsp">Signup</a>
        </div>
      </form>
    </div>
</body>
</html>
