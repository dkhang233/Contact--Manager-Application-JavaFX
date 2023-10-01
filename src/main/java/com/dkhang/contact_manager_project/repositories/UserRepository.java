package com.dkhang.contact_manager_project.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dkhang.contact_manager_project.models.User;

public class UserRepository {
	
	// Database configuration
	private static final String URL = "jdbc:mysql://localhost:3306/contact_manager_system";
	private static final String USERNAME = "springstudent";
	private static final String PASSWORD = "springstudent";
	
	private Connection connection; // manages the connection
	private PreparedStatement selectUserByPhoneNumber;
	private PreparedStatement insertUser;
	private PreparedStatement updateUser;
	
	//Initialize connection and prepared statement
	public UserRepository() {
		try {
			this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			selectUserByPhoneNumber = this.connection.prepareStatement("select * from _users where phone_number = ?");
			insertUser = this.connection.prepareStatement("insert into _users(first_name,last_name,phone_number,password,username,is_used) values(?,?,?,?,?,?)");
			updateUser = this.connection.prepareStatement("update _users set first_name=?, last_name=?,phone_number=?,password=?,username=? where id = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// retrieve user from database and map to java object  
	public User retrieveUserByPhoneNumber(String phoneNumber) {
		User user = new User();
		ResultSet resultSet = null;
		try {
			selectUserByPhoneNumber.setString(1, phoneNumber);
			resultSet = selectUserByPhoneNumber.executeQuery();
			if(!resultSet.next()) {
				return null;
			}
			user.setId(resultSet.getInt("id"))
				.setFirstName(resultSet.getString("first_name"))
				.setLastName(resultSet.getString("last_name"))
				.setPhoneNumber(resultSet.getString("phone_number"))
				.setPassword(resultSet.getString("password"))
				.setUsername(resultSet.getString("username"))
				.setUsed(resultSet.getBoolean("is_used"));
		} catch (SQLException e) {
			close();
			e.printStackTrace();
		}
		
		return user;
		
	}
	

	//insert new user 
	public void createUser(User user) {
		try {
			insertUser.setString(1, user.getFirstName());
			insertUser.setString(2, user.getLastName());
			insertUser.setString(3, user.getPhoneNumber());
			insertUser.setString(4, user.getPassword());
			insertUser.setString(5, user.getPassword());
			insertUser.setBoolean(6, false);
			
			insertUser.executeUpdate();
		} catch (SQLException e) {
			close();
			e.printStackTrace();
		}
	}
	
	//update user
	public void updateUser(User user) {
		try {
			updateUser.setString(1,  user.getFirstName());
			updateUser.setString(2,  user.getLastName());
			updateUser.setString(3, user.getPhoneNumber());
			updateUser.setString(4, user.getPassword());
			updateUser.setString(5, user.getUsername());
			updateUser.setInt(6, user.getId());
			
			updateUser.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// close database connection
	public void close() {
		try {
			connection.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}
