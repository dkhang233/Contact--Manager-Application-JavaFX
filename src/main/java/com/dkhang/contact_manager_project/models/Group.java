package com.dkhang.contact_manager_project.models;

import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Group {
	private IntegerProperty id;
	private StringProperty name;
	private StringProperty description;
	private ObjectProperty<LocalDateTime> createdAt;
	
	public Group() {
		this.id = new SimpleIntegerProperty(this,"id",0);
		this.name = new SimpleStringProperty(this,"name","");
		this.description = new SimpleStringProperty(this,"description","");
		this.createdAt = new SimpleObjectProperty<>(this,"createdAt",LocalDateTime.now());
	}
	
	public Group(int id,String name,String description, LocalDateTime createdAt) {
		this.id = new SimpleIntegerProperty(this,"id",id);
		this.name = new SimpleStringProperty(this,"name",name);
		this.description = new SimpleStringProperty(this,"description",description);
		this.createdAt = new SimpleObjectProperty<>(this,"createdAt",createdAt);
	}
	
	//Get value of property
	public int getId() {
		return this.id.get();
	}
	
	public String getName() {
		return this.name.get();
	}
	
	public String getDescription() {
		return this.description.get();
	}
	
	public LocalDateTime getCreatedAt() {
		return this.createdAt.get();
	}
	
	//Set value of property
	public void setId(int id) {
		this.id.set(id);
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public void setDescription(String description) {
		this.description.set(description);
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt.set(createdAt);
	}
	
	//Get property
	public StringProperty getNameProperty() {
		return this.name;
	}
	
	public StringProperty getDescriptionProperty() {
		return this.description;
	}
}
