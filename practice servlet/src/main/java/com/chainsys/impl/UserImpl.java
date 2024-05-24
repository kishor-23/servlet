package com.chainsys.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.chainsys.dao.UserDAO;
import com.chainsys.model.User;
import com.chainsys.util.DbConnection;

public class UserImpl implements UserDAO {
    private Connection con;

    public UserImpl() throws ClassNotFoundException, SQLException {
        this.con = DbConnection.getConnection();
    }

    @Override
    public void addUser(User user) throws SQLException, ClassNotFoundException {
    	String selectQuery = "SELECT mailId,password FROM users WHERE mailId = ? ";
		PreparedStatement selectStatement = con.prepareStatement(selectQuery);
		selectStatement.setString(1, user.getMailid());
		ResultSet resultSet = selectStatement.executeQuery();

		if (!resultSet.next()) {
			String insertQuery = "INSERT INTO users (mailId, name, password) VALUES (?, ?, ?)";
			PreparedStatement insertStatement = con.prepareStatement(insertQuery);
			insertStatement.setString(1, user.getMailid());
			insertStatement.setString(2, user.getName());
			insertStatement.setString(3, user.getPassword());
			insertStatement.executeUpdate();
			System.out.println("User registered successfully.");
		} else {
			System.out.println("User already exists with this email ID. use another email id to sign up or sign in ");
		}        
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) throws SQLException, ClassNotFoundException {
        String selectQuery = "SELECT * FROM users WHERE mailid = ? AND password = ?";
        try (PreparedStatement pstmt = con.prepareStatement(selectQuery)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setMailid(rs.getString("mailid"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
            }
        }
        return null;
    }
    
    public void registerUser(String mailId, String name, String password) throws ClassNotFoundException, SQLException {
		String selectQuery = "SELECT mailId,password FROM users WHERE mailId = ? ";
		PreparedStatement selectStatement = con.prepareStatement(selectQuery);
		selectStatement.setString(1, mailId);
		ResultSet resultSet = selectStatement.executeQuery();

		if (!resultSet.next()) {
			String insertQuery = "INSERT INTO users (mailId, name, password) VALUES (?, ?, ?)";
			PreparedStatement insertStatement = con.prepareStatement(insertQuery);
			insertStatement.setString(1, mailId);
			insertStatement.setString(2, name);
			insertStatement.setString(3, password);
			insertStatement.executeUpdate();
			System.out.println("User registered successfully.");
		} else {
			System.out.println("User already exists with this email ID. use another email id to sign up or sign in ");
		}

	}

	@Override
	public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User user) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

    // Implement other methods such as getAllUsers, updateUser, deleteUser
}

