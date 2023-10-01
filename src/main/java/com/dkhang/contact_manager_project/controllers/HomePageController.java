package com.dkhang.contact_manager_project.controllers;

import com.dkhang.contact_manager_project.App;
import com.dkhang.contact_manager_project.repositories.UserRepository;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HomePageController {
	
	public Label userInformation;
	public TextField nameGroupOrUser;
	
	@FXML
	public void logout() {
			App.user = null;
			App.toLogoutPage();
	}
	
	@FXML
	public void updateUser() {
		App.toUpdateUserPage();
	}
	
	@FXML
	public void deleteUser() {
		System.out.println("deleteUser");
	}
	
	
	@FXML
	public void findGroupOrUser() {
		System.out.println("findGroupOrUser");
	}
	
	@FXML
	public void updateGroup() {
		System.out.println("updateGroup");
	}
	
	@FXML
	public void deleteGroup() {
		System.out.println("deleteGroup");
	}
}
