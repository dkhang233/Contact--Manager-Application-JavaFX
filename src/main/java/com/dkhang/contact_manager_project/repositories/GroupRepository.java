package com.dkhang.contact_manager_project.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dkhang.contact_manager_project.models.Group;

public class GroupRepository {
	
	// Database configuration
	private static final String URL = "jdbc:mysql://localhost:3306/contact_manager_system";
	private static final String USERNAME = "springstudent";
	private static final String PASSWORD = "springstudent";
	
	private Connection connection; // manages the connection
	private PreparedStatement selectGroupByName;
	private PreparedStatement insertGroup;
	private PreparedStatement updateGroup;
	
	public GroupRepository() {
		try {
			this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			selectGroupByName = this.connection.prepareStatement("select * from _groups where name = ?");
			insertGroup = this.connection.prepareStatement("insert into _groups(name) values(?)");
			updateGroup = this.connection.prepareStatement("update _groups set name = ? where id = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Group retrieveGroupByName(String name){
		Group group = new Group();
		try {
			selectGroupByName.setString(1, name);
			ResultSet resultSet = selectGroupByName.executeQuery();
			if(resultSet.next()) {
				group.setId(resultSet.getInt("id"))
					.setName(resultSet.getString("name"));
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return group;
	}
	
	public void createGroup(Group group) {
		try {
			insertGroup.setString(1, group.getName());
			insertGroup.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateGroup(Group group) {
		try {
			updateGroup.setString(1,group.getName());
			updateGroup.setInt(2,group.getId());
			updateGroup.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
