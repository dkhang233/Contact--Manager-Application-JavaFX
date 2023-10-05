package com.dkhang.contact_manager_project.controllers;

import java.util.Optional;

import com.dkhang.contact_manager_project.App;
import com.dkhang.contact_manager_project.daos.GroupDAO;
import com.dkhang.contact_manager_project.daos.UserDAO;
import com.dkhang.contact_manager_project.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML public TextField phoneNumber;
	@FXML public PasswordField password;
	@FXML public Label loginStatus;
	
	private UserDAO userDAO = new UserDAO();
	private GroupDAO groupDAO = new GroupDAO();
	
	@FXML
	public void login(){
		User user = userDAO.retrieveUserByPhoneNumber(phoneNumber.getText());
		if (user != null && user.getPassword().equals(password.getText())) {
			App.user = user;
			App.groups = groupDAO.retrieveGroupByUserId(App.user.getId());
			App.toHomePage();
		} else {
			loginStatus.getStyleClass().clear();
			loginStatus.getStyleClass().add("label-danger");
			loginStatus.setText("Login failed !");
		}
	}
	
	public void register() {
		Dialog<User> dialog = new AccountDialog(new User());
		Optional<User> result = dialog.showAndWait();
		if(result.isPresent()) {
			User user = result.get();
			userDAO.createUser(user);
		}
	}
}
