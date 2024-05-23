package com.chainsys.dao;

import com.chainsys.model.Contact;

import java.sql.SQLException;
import java.util.List;

public interface ContactDAO {
	void addContact(Contact contact) throws  ClassNotFoundException, SQLException;
    Contact getContact(int id) throws  ClassNotFoundException, SQLException;
    List<Contact> getAllContacts(int userId) throws  ClassNotFoundException, SQLException;
    void updateContact(Contact contact) throws  ClassNotFoundException, SQLException;
    void deleteContact(int id) throws  ClassNotFoundException, SQLException;
}