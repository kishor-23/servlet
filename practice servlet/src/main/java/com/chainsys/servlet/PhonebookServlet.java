package com.chainsys.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chainsys.dao.ContactDAO;
import com.chainsys.impl.ContactImpl;
import com.chainsys.model.Contact;
import com.chainsys.model.User;

@WebServlet("/PhonebookServlet")
public class PhonebookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ContactDAO contactOperations;

    public PhonebookServlet() {
        super();
        try {
            contactOperations = new ContactImpl();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database connection failed");
        }
    }

    private synchronized void viewContacts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            int userId = user.getId();
            try {
                List<Contact> contacts = contactOperations.getAllContacts(userId);
                request.setAttribute("contacts", contacts);
                request.getRequestDispatcher("viewcontact.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private synchronized void addContact(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phone");
        String email = request.getParameter("email");

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        Contact contact = new Contact(name, phoneNumber, email, userId);
        try {
            contactOperations.addContact(contact);
            viewContacts(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
    
    

    private synchronized void deleteContact(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("deleteid"));

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        try {
            contactOperations.deleteContact(id);
            viewContacts(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private synchronized void updateContactDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("updateid"));
        try {
            Contact contact = contactOperations.getContact(id);
            request.setAttribute("id", id);
            request.setAttribute("contact", contact);
            request.getRequestDispatcher("editcontact.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private synchronized void updateContact(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("updateid"));
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phone");
        String email = request.getParameter("email");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        Contact contactToUpdate = new Contact(id,name, phoneNumber, email,userId);
        contactToUpdate.setId(id);

       
     

        try {
            contactOperations.updateContact(contactToUpdate);
            viewContacts(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addContact(request, response);
                    break;
                case "delete":
                    deleteContact(request, response);
                    break;
                case "updatedetails":
                    updateContactDetails(request, response);
                    break;
                case "update":
                    updateContact(request, response);
                    break;
                case "viewcontacts":
                    viewContacts(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
