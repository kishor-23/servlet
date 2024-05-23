package com.chainsys.dao;

import java.sql.SQLException;
import java.util.List;
import com.chainsys.model.User;

public interface UserDAO {
	 void addUser(User user) throws SQLException, ClassNotFoundException ;
    User getUserByEmailAndPassword(String email, String password) throws SQLException, ClassNotFoundException;
    List<User> getAllUsers() throws SQLException, ClassNotFoundException;
    void updateUser(User user) throws SQLException, ClassNotFoundException;
    void deleteUser(int id) throws SQLException, ClassNotFoundException;
}

