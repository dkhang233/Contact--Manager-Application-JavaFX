package com.dkhang.contact_manager_project.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	private IntegerProperty id;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty phoneNumber;
	private StringProperty password;
	private StringProperty username;
	private BooleanProperty isUsed;
	
	public User() {
		super();
		this.id = new SimpleIntegerProperty(this,"id",0);
		this.firstName = new SimpleStringProperty(this,"firstName","");
		this.lastName = new SimpleStringProperty(this,"lastName","");;
		this.phoneNumber = new SimpleStringProperty(this,"phoneNumber","");;
		this.password = new SimpleStringProperty(this,"password","");;
		this.username = new SimpleStringProperty(this,"username","");;
		this.isUsed = new SimpleBooleanProperty(this,"isUsed",false);
	}
	
	//Get value of property
	public int getId() {
		return this.id.get();
	}
	
	public String getFirstName() {
		return this.firstName.get();
	}
	
	public String getLastName() {
		return this.lastName.get();
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber.get();
	}
	
	public String getPassword() {
		return this.password.get();
	}
	
	public String getUsername() {
		return this.username.get();
	}
	
	public boolean getIsUsed() {
		return this.isUsed.get();
	}
	
	// Set value of property
	public void setId(int id) {
		this.id.set(id);
	}
	
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}
	
	public void setPassword(String password) {
		this.password.set(password);
	}
	
	public void setUsername(String username) {
		this.username.set(username);
	}
	
	public void setIsUsed(boolean isUsed) {
		this.isUsed.set(isUsed);
	}

	//Get property
	public StringProperty getFirstNameProperty() {
		return this.firstName;
	}
	
	public StringProperty getLastNameProperty() {
		return this.lastName;
	}
	
	public StringProperty getPhoneNumberProperty() {
		return this.phoneNumber;
	}
	
	public StringProperty getPasswordProperty() {
		return this.password;
	}
	
	public StringProperty getUsernameProperty() {
		return this.username;
	}
	
	public BooleanProperty getIsUsedProperty() {
		return this.isUsed;
	}
	
}
