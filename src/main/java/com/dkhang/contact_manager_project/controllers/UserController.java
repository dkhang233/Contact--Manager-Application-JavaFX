package com.dkhang.contact_manager_project.controllers;

import java.io.IOException;

import com.dkhang.contact_manager_project.App;
import com.dkhang.contact_manager_project.models.User;
import com.dkhang.contact_manager_project.repositories.UserRepository;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserController {

	public TextField phoneNumber;
	public PasswordField password;
	public Label loginStatus;
	private UserRepository userRepository = new UserRepository();
	
	//Validate user information
	@FXML
	public void login(){
		User user = userRepository.retrieveUserByPhoneNumber(phoneNumber.getText());
		if (user != null && user.getPassword().equals(password.getText())) {
			App.user = user;
			App.toHomePage();
		} else {
			
			loginStatus.setStyle("-fx-padding: 8px 20px;"
					+ "-fx-background-color: #f8d7da;"
					+ " -fx-text-fill: #721c24;" );
			
			loginStatus.setText("Login failed !");
		}

	}
	
	//Go to register page
	@FXML
	public void register() {
			App.toRegisterPage();
	}
	
}
