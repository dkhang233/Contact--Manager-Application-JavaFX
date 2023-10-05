package com.dkhang.contact_manager_project.controllers;

import java.io.IOException;

import com.dkhang.contact_manager_project.App;
import com.dkhang.contact_manager_project.daos.UserDAO;
import com.dkhang.contact_manager_project.models.User;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class AccountDialog extends Dialog<User>{
	private User user;
	private TextField firstName;
	private TextField lastName;
	private TextField phoneNumber;
	private TextField password;
	private TextField username;
	private Label registerStatus;
	private UserDAO userDAO = new UserDAO();
	
	public AccountDialog(User user) {
		this.user = user;
		Pane pane = (Pane) loadFXML("RegisterPage");
		getDialogPane().setContent(pane);
		getDialogPane().getStylesheets().add(App.class.getResource("/css/style.css").toExternalForm());
		setTitle("My Account");
		firstName = (TextField) getDialogPane().lookup("#firstName");
		lastName = (TextField) getDialogPane().lookup("#lastName");
		phoneNumber = (TextField) getDialogPane().lookup("#phoneNumber");
		password = (TextField) getDialogPane().lookup("#password");
		username = (TextField) getDialogPane().lookup("#username");
		registerStatus = (Label) getDialogPane().lookup("#registerStatus");
		getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
		Button createButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
		createButton.getStyleClass().addAll("btn","btn-success");
		createButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(!validateInput()) {
					event.consume();
				}
			}
		});
		Button cancelButton = (Button) getDialogPane().lookupButton(ButtonType.CANCEL);
		cancelButton.getStyleClass().addAll("btn","btn-danger");
		setPropertyBindings();
		setResultConverter();
	}
	
	protected boolean validateInput() {
		// Validate input informations
		if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || phoneNumber.getText().isEmpty()
				|| password.getText().isEmpty() || username.getText().isEmpty()) {
			registerStatus.getStyleClass().clear();
			registerStatus.getStyleClass().add("label-danger");
			registerStatus.setText("Please enter all informations !");
			return false;
		}

		// Validate phone number format
		if (!checkPhoneNumberFormat(phoneNumber.getText())) {
			registerStatus.getStyleClass().clear();
			registerStatus.getStyleClass().add("label-danger");
			registerStatus.setText("Phone number must be has 10 digits !");
			return false;
		}

		// Check if phone number has been used
		if (!phoneNumber.getText().equals(App.user.getPhoneNumber())) {
			User user = userDAO.retrieveUserByPhoneNumber(phoneNumber.getText());
			if (user != null) {
				registerStatus.getStyleClass().clear();
				registerStatus.getStyleClass().add("label-danger");
				registerStatus.setText("This phone number has been used !");
				return false;
			}

		}
		return true;
	}

	public boolean checkPhoneNumberFormat(String phoneNumber) {
		if(phoneNumber.length() != 10) {
			return false;
		}
		for(int i=0 ; i < 10 ; i++) {
			if( !Character.isDigit(phoneNumber.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private void setPropertyBindings() {
		firstName.textProperty().bindBidirectional(user.getFirstNameProperty());
		lastName.textProperty().bindBidirectional(user.getLastNameProperty());
		phoneNumber.textProperty().bindBidirectional(user.getPhoneNumberProperty());
		password.textProperty().bindBidirectional(user.getPasswordProperty());
		username.textProperty().bindBidirectional(user.getUsernameProperty());
	}

	private void setResultConverter() {
		Callback<ButtonType, User> userResultConverter = new Callback<ButtonType, User>() {
			
			@Override
			public User call(ButtonType param) {
				if(param == ButtonType.OK) {
					return user;
				}else {
					return null;
				}
			}
			
		};
		setResultConverter(userResultConverter);
	}
	
	// Helper method for load .fxml file
	private static Parent loadFXML(String fxml) {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		try {
			return fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
