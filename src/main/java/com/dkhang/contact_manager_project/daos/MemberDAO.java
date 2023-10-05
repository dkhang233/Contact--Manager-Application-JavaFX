package com.dkhang.contact_manager_project.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dkhang.contact_manager_project.models.Member;
import com.dkhang.contact_manager_project.models.Role;

public class MemberDAO extends BaseDAO{
		private Connection connection; // manages the connection
		private PreparedStatement insertMember;
		private PreparedStatement selectMember;
		private PreparedStatement selectMemberNumber;
		private PreparedStatement deleteMember;
		private PreparedStatement deleteAllMemberByGroupId;
		private PreparedStatement deleteAllMemberByUserId;
		
		//Initialize connection and prepared statement
		public MemberDAO() {
			try {
				this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				insertMember = this.connection.prepareStatement("insert into _members(user_id,group_id,role) values(?,?,?)");
				selectMember = this.connection.prepareStatement("select * from _members where user_id = ? and group_id = ?");
				deleteMember = this.connection.prepareStatement("delete from _members where user_id = ? and group_id =?");
				selectMemberNumber = this.connection.prepareStatement("select count(*) as member_number from _members where group_id = ?");
				deleteAllMemberByGroupId = this.connection.prepareStatement("delete from _members where group_id = ?");
				deleteAllMemberByUserId = this.connection.prepareStatement("delete from _members where user_id = ?");
			} catch (SQLException e) {
				close();
				e.printStackTrace();
			}
		}
		
		public void createMember(int userId, int groupId, Role role) {
			try {
				insertMember.setInt(1, userId);
				insertMember.setInt(2, groupId);
				insertMember.setString(3, role.toString());
				insertMember.executeUpdate();
			} catch (SQLException e) {
				close();
				e.printStackTrace();
			}
		}
		
		public Member retrieveMemberByUserIdAndGroupId(int userId, int groupId) {
			try {
				selectMember.setInt(1, userId);
				selectMember.setInt(2, groupId);
				ResultSet resultSet = selectMember.executeQuery();
				if(resultSet.next()) {
					int id = resultSet.getInt("id");
					Role role = Role.valueOf(resultSet.getString("role"));
					return new Member(id,userId,groupId,role);
				}
			} catch (SQLException e) {
				close();
				e.printStackTrace();
			}
			return null;
		}
		
		public int retrieveMemberNumber(int groupId) {
			try {
				selectMemberNumber.setInt(1, groupId);
				ResultSet resultSet = selectMemberNumber.executeQuery();
				if(resultSet.next()) {
					return resultSet.getInt("member_number");
				}
			} catch (SQLException e) {
				close();
				e.printStackTrace();
			}
			return -1;
		}

		public void deleteMember(int userId, int groupId) {
			try {
				deleteMember.setInt(1, userId);
				deleteMember.setInt(2, groupId);
				deleteMember.executeUpdate();
			} catch (SQLException e) {
				close();
				e.printStackTrace();
			}
		}
		
		public void deleteAllMemberByGroupId(int groupId) {
			try {
				deleteAllMemberByGroupId.setInt(1, groupId);
				deleteAllMemberByGroupId.executeUpdate();
			} catch (SQLException e) {
				close();
				e.printStackTrace();
			}
		}
		
		public void deleteAllMemberByUserId(int userId) {
			try {
				deleteAllMemberByUserId.setInt(1, userId);
				deleteAllMemberByUserId.executeUpdate();
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
