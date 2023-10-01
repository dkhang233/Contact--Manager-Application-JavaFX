package com.dkhang.contact_manager_project.controllers;

import java.util.Optional;

import com.dkhang.contact_manager_project.App;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AccountController {
	
	public TextField firstName;
	public TextField lastName;
	public TextField phoneNumber;
	public TextField username;
	
	@FXML
	public void updateUser() {
		App.toUpdateUserPage();
		firstName.setText(App.user.getFirstName());
		lastName.setText(App.user.getLastName());
		phoneNumber.setText(App.user.getPhoneNumber());
		username.setText(App.user.getUsername());
	}

	@FXML
	public void deleteUser() {
		Alert confirmDelete = new Alert(AlertType.CONFIRMATION);
		confirmDelete.setHeaderText("Are you sure you want to delete this account?");
		Optional<ButtonType> result = confirmDelete.showAndWait();
		if(result.isPresent() && result.get()==ButtonType.OK) {
			System.out.println("deleteUser");
		}
	}
}
