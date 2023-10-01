package com.dkhang.contact_manager_project.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberRepository {
	// Database configuration
		private static final String URL = "jdbc:mysql://localhost:3306/contact_manager_system";
		private static final String USERNAME = "springstudent";
		private static final String PASSWORD = "springstudent";
		
		private Connection connection; // manages the connection
		private PreparedStatement selectGroupByName;
		private PreparedStatement insertGroup;
		private PreparedStatement updateGroup;
		
		public MemberRepository() {
			try {
				this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				selectGroupByName = this.connection.prepareStatement("select * from _groups where name = ?");
				insertGroup = this.connection.prepareStatement("insert into _groups(name) values(?)");
				updateGroup = this.connection.prepareStatement("update _groups set name = ? where id = ?");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
