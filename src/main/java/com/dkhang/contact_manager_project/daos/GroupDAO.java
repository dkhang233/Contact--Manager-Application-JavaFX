package com.dkhang.contact_manager_project.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.dkhang.contact_manager_project.models.Group;

public class GroupDAO extends BaseDAO{
	private Connection connection; // manages the connection
	private PreparedStatement selectGroupByName;
	private PreparedStatement selectAllGroup;
	private PreparedStatement selectGroupByUserId;
	private PreparedStatement insertGroup;
	private PreparedStatement updateGroup;
	private PreparedStatement deleteGroup;
	
	//Initialize connection and prepared statement
	public GroupDAO() {
		try {
			this.connection = DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
			selectGroupByName = this.connection.prepareStatement("select * from _groups where name = ?");
			selectAllGroup = this.connection.prepareStatement("select * from _groups");
			selectGroupByUserId = this.connection.prepareStatement("select _groups.id, _groups.name, _groups.description, _groups.created_at from _groups join _members"
					+ " on _groups.id = _members.group_id where user_id = ?");
			insertGroup = this.connection.prepareStatement("insert into _groups(name,description,created_at) values(?,?,?)");
			updateGroup = this.connection.prepareStatement("update _groups set name = ? , description = ? where id = ?");
			deleteGroup = this.connection.prepareStatement("delete from _groups where id = ?");
		} catch (SQLException e) {
			close();
			e.printStackTrace();
		}
	}
	
	public Group retrieveGroupByName(String name){
		Group group = new Group();
		try {
			selectGroupByName.setString(1, name);
			ResultSet resultSet = selectGroupByName.executeQuery();
			if(resultSet.next()) {
				group.setId(resultSet.getInt("id"));
				group.setName(resultSet.getString("name"));
				group.setDescription(resultSet.getString("description"));
				group.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			close();
			e.printStackTrace();
		}
		
		return group;
	}
	
	public List<Group> retrieveAllGroups() {
		List<Group> groups = new ArrayList<>();
		try {
			ResultSet resultSet = selectAllGroup.executeQuery();
			while (resultSet.next()) {
				groups.add(new Group(resultSet.getInt("id"),
							resultSet.getString("name"),
							resultSet.getString("description"),
							resultSet.getTimestamp("created_at").toLocalDateTime()
						));
			}
		} catch (SQLException e) {
			close();
			e.printStackTrace();
		}
		return groups;
	}
	
	public List<Group> retrieveGroupByUserId(int userId) {
		List<Group> groups = new ArrayList<>();
		try {
			selectGroupByUserId.setInt(1, userId);
			ResultSet resultSet = selectGroupByUserId.executeQuery();
			while (resultSet.next()) {
				groups.add(new Group(resultSet.getInt("id"),
								resultSet.getString("name"),
								resultSet.getString("description"),
								resultSet.getTimestamp("created_at").toLocalDateTime()
							));
			}
		} catch (SQLException e) {
			close();
			e.printStackTrace();
		}
		return groups;
	}
	
	public void createGroup(Group group) {
		try {
			insertGroup.setString(1, group.getName());
			insertGroup.setString(2, group.getDescription());
			insertGroup.setTimestamp(3, Timestamp.valueOf(group.getCreatedAt()));
			insertGroup.executeUpdate();
		} catch (SQLException e) {
			close();
			e.printStackTrace();
		}
	}
	
	public void updateGroup(Group group) {
		try {
			updateGroup.setString(1,group.getName());
			updateGroup.setString(2, group.getDescription());
			updateGroup.setInt(3,group.getId());
			updateGroup.executeUpdate();
		} catch (SQLException e) {
			close();
			e.printStackTrace();
		}
	}
	
	public void deleteGroup(Group group) {
		try {
			deleteGroup.setInt(1, group.getId());
			deleteGroup.executeUpdate();
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
