package com.chainsys.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.dao.ContactDAO;
import com.chainsys.model.Contact;
import com.chainsys.util.DbConnection;

public class ContactImpl implements ContactDAO {
    private Connection con;

    public ContactImpl() throws ClassNotFoundException, SQLException {
        this.con = DbConnection.getConnection();
    }

    @Override
    public void addContact(Contact contact) throws ClassNotFoundException, SQLException {
        String insert = "INSERT INTO contacts (name, phoneNumber, email, userId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(insert)) {
            pstmt.setString(1, contact.getName());
            pstmt.setString(2, contact.getPhoneNumber());
            pstmt.setString(3, contact.getEmail());
            pstmt.setInt(4, contact.getUserId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Contact getContact(int id) throws ClassNotFoundException, SQLException {
        String select = "SELECT * FROM contacts WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(select)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Contact contact = new Contact(
                    rs.getString("name"),
                    rs.getString("phoneNumber"),
                    rs.getString("email")
                );
                contact.setId(rs.getInt("id"));
                contact.setUserId(rs.getInt("userId"));
                return contact;
            }
        }
        return null;
    }

    @Override
    public List<Contact> getAllContacts(int userid) throws ClassNotFoundException, SQLException {
        List<Contact> contacts = new ArrayList<>();
        String selectAll = "SELECT * FROM contacts WHERE userId=?";
        try (PreparedStatement pstmt = con.prepareStatement(selectAll)) {
            pstmt.setInt(1, userid);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Contact contact = new Contact(
                        rs.getString("name"),
                        rs.getString("phoneNumber"),
                        rs.getString("email")
                    );
                    contact.setId(rs.getInt("id"));
                    contact.setUserId(rs.getInt("userId"));
                    contacts.add(contact);
                }
            }
        }
        return contacts;
    }


    @Override
    public void updateContact(Contact contact) throws ClassNotFoundException, SQLException {
        String update = "UPDATE contacts SET name = ?, phoneNumber = ?, email = ?, userId = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(update)) {
            pstmt.setString(1, contact.getName());
            pstmt.setString(2, contact.getPhoneNumber());
            pstmt.setString(3, contact.getEmail());
            pstmt.setInt(4, contact.getUserId());
            pstmt.setInt(5, contact.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteContact(int id) throws ClassNotFoundException, SQLException {
        String delete = "DELETE FROM contacts WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(delete)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
