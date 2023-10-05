package com.dkhang.contact_manager_project.controllers;


import java.util.Optional;

import com.dkhang.contact_manager_project.App;
import com.dkhang.contact_manager_project.daos.MemberDAO;
import com.dkhang.contact_manager_project.daos.UserDAO;
import com.dkhang.contact_manager_project.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AccountController {
	
	@FXML public TextField firstName;
	@FXML public TextField lastName;
	@FXML public TextField phoneNumber;
	@FXML public TextField username;
	
	private UserDAO userDAO = new UserDAO();
	private MemberDAO memberDAO = new MemberDAO();
	
	public void initialize() {
		firstName.textProperty().bindBidirectional(App.user.getFirstNameProperty());
		lastName.textProperty().bindBidirectional(App.user.getLastNameProperty());
		phoneNumber.textProperty().bindBidirectional(App.user.getPhoneNumberProperty());
		username.textProperty().bindBidirectional(App.user.getUsernameProperty());
	}
	
	@FXML
	public void updateUser() {
		Dialog<User> dialog = new AccountDialog(App.user);
		Optional<User> result = dialog.showAndWait();
		if(result.isPresent()) {
			User user = result.get();
			userDAO.updateUser(user);
		}
	}

	@FXML
	public void deleteUser() {
		Alert confirmDelete = new Alert(AlertType.CONFIRMATION);
		confirmDelete.setHeaderText("Are you sure you want to delete this account?");
		Optional<ButtonType> result = confirmDelete.showAndWait();
		if(result.isPresent() && result.get()==ButtonType.OK) {
			memberDAO.deleteAllMemberByUserId(App.user.getId());
			userDAO.deleteUser();
			App.user = new User();
			App.toLoginPage();
		}
	}
}
