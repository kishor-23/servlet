<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.chainsys.model.Contact" %>
<%@ page import="com.chainsys.model.User" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    User user = (User) session.getAttribute("user");
    List<Contact> contacts = (List<Contact>) request.getAttribute("contacts");

    // Default values for sortField, sortOrder, and searchQuery
    String sortField = (String) request.getAttribute("sortField");
    if (sortField == null) {
        sortField = "name";
    }
    String sortOrder = (String) request.getAttribute("sortOrder");
    if (sortOrder == null) {
        sortOrder = "asc";
    }
    String searchQuery = (String) request.getAttribute("searchQuery");
    if (searchQuery == null) {
        searchQuery = "";
    }

    String nameSortOrder = "asc".equals(sortOrder) && "name".equals(sortField) ? "desc" : "asc";
%>

<html>
<head>
    <title>View Contacts</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2><%= user.getName() %> Contacts</h2>
        <a href='LogoutServlet' class="logout">Logout</a>
        <a href='addcontacts.jsp' class="button">Add Contact</a>
        <form method="get" action="PhonebookServlet">
            <input type="hidden" name="action" value="viewcontacts">
            <input type="text" name="searchQuery" placeholder="Search by name..." value="<%= searchQuery %>">
            <input type="submit" value="Search">
        </form>
        <table>
            <tr>
                <th><a href="PhonebookServlet?action=viewcontacts&sortField=name&sortOrder=<%= nameSortOrder %>&searchQuery=<%= searchQuery %>">Name</a></th>
                <th>Phone Number</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
            <%
                if (contacts != null && !contacts.isEmpty()) {
                    for (Contact contact : contacts) {
            %>
            <tr>
                <td><%= contact.getName() %></td>
                <td><%= contact.getPhoneNumber() %></td>
                <td><%= contact.getEmail() %></td>
                <td>
                    <a href="PhonebookServlet?action=updatedetails&updateid=<%= contact.getId() %>" class="button">Edit</a>
                    <a href="PhonebookServlet?action=delete&deleteid=<%= contact.getId() %>" class="logout">Delete</a>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="4">No contacts available.</td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>
