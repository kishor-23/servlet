package com.chainsys.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chainsys.dao.UserDAO;
import com.chainsys.impl.UserImpl;
import com.chainsys.model.User;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userOperations;

    public RegisterServlet() {
        super();
        try {
            userOperations = new UserImpl();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database connection failed");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
 System.out.println(username);
 System.out.println(password);
        User user = new User(username, email, password);

        try {
            userOperations.addUser(user);
            // Redirect to login page after successful registration
            response.sendRedirect("login.jsp?registered=true");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Set error message attribute
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            // Redirect back to register page with error message
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
