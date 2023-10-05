package com.dkhang.contact_manager_project.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Member {
	private IntegerProperty id;
	private IntegerProperty userId;
	private IntegerProperty groupId;
	private ObjectProperty<Role> role;
	
	public Member() {
		this.id = new SimpleIntegerProperty(this,"id",0);
		this.userId = new SimpleIntegerProperty(this,"userId",0);
		this.groupId = new SimpleIntegerProperty(this,"groupId",0);
		this.role = new SimpleObjectProperty<Role>(this,"role",Role.USER);
	}
	
	public Member(int id, int userId, int groupId, Role role) {
		this.id = new SimpleIntegerProperty(this,"id",id);
		this.userId = new SimpleIntegerProperty(this,"userId",userId);
		this.groupId = new SimpleIntegerProperty(this,"groupId",groupId);
		this.role = new SimpleObjectProperty<Role>(this,"role",role);
	}

	//Get value of property
	public int getId() {
		return this.id.get();
	}
	
	public int getUserId() {
		return this.userId.get();
	}
	
	public int getGroupId() {
		return this.groupId.get();
	}
	
	public Role getRole() {
		return this.role.get();
	}
	
	//Set value of property
	public void setId(int id) {
		this.id.set(id);
	}
	
	public void setUserId(int userId) {
		this.userId.set(userId);
	}
	
	public void setGroupId(int groupId) {
		this.groupId.set(groupId);
	}
	
	public void setRole(Role role) {
		this.role.set(role);
	}
}
