package com.dkhang.contact_manager_project.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dkhang.contact_manager_project.App;
import com.dkhang.contact_manager_project.models.User;

public class UserDAO extends BaseDAO{
	private Connection connection; // manages the connection
	private PreparedStatement selectUserByPhoneNumber;
	private PreparedStatement insertUser;
	private PreparedStatement updateUser;
	private PreparedStatement deleteUser;
	
	//Initialize connection and prepared statement
	public UserDAO() {
		try {
			this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			selectUserByPhoneNumber = this.connection.prepareStatement("select * from _users where phone_number = ?");
			insertUser = this.connection.prepareStatement("insert into _users(first_name,last_name,phone_number,password,username,is_used) values(?,?,?,?,?,?)");
			updateUser = this.connection.prepareStatement("update _users set first_name=?, last_name=?,phone_number=?,password=?,username=? where id = ?");
			deleteUser = this.connection.prepareStatement("delete from _users where id = ?");
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
			user.setId(resultSet.getInt("id"));
			user.setFirstName(resultSet.getString("first_name"));
			user.setLastName(resultSet.getString("last_name"));
			user.setPhoneNumber(resultSet.getString("phone_number"));
			user.setPassword(resultSet.getString("password"));
			user.setUsername(resultSet.getString("username"));
			user.setIsUsed(resultSet.getBoolean("is_used"));
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
			close();
			e.printStackTrace();
		}
	}
	
	public void deleteUser() {
		try {
			deleteUser.setInt(1, App.user.getId());
			deleteUser.executeUpdate();
		} catch (SQLException e) {
			close();
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
