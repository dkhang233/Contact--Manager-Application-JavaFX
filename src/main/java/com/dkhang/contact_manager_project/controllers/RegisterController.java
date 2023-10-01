package com.dkhang.contact_manager_project.controllers;

import com.dkhang.contact_manager_project.App;
import com.dkhang.contact_manager_project.models.User;
import com.dkhang.contact_manager_project.repositories.UserRepository;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {
	
	public TextField firstName;
	public TextField lastName;
	public TextField phoneNumber;
	public TextField username;
	public PasswordField password;
	public Label registerStatus;
	public Button registerButton;
	private UserRepository userRepository = new UserRepository();
	
	/* This method can be used for register or update user account.
	 * If text of "registerButton" button contains "register" then register the user.
	 *  otherwise, update the user.
	 */
	@FXML
	public void register() {
		
		//Validate input informations
		if(firstName.getText().isEmpty() ||
				lastName.getText().isEmpty() ||
				phoneNumber.getText().isEmpty() ||
				password.getText().isEmpty() ||
				username.getText().isEmpty()
		) {
			registerStatus.setStyle("-fx-padding: 8px 20px;"
					+ "-fx-background-color: #f8d7da;"
					+ " -fx-text-fill: #721c24;" );
			
			registerStatus.setText("Please enter all informations !");
		}
		
		else if (! checkPhoneNumberFormat(phoneNumber.getText())) {
			registerStatus.setStyle("-fx-padding: 8px 20px;"
					+ "-fx-background-color: #f8d7da;"
					+ " -fx-text-fill: #721c24;" );
			
			registerStatus.setText("Phone number must be has 10 digits !");
		}
		
		else {
			// Register user account
			if(registerButton.getText().contains("Register")) {
				//Check if phone number has been used 
				User user = userRepository.retrieveUserByPhoneNumber(phoneNumber.getText());
				if(user != null ) {
					registerStatus.setStyle("-fx-padding: 8px 20px;"
							+ "-fx-background-color: #f8d7da;"
							+ " -fx-text-fill: #721c24;" );
					registerStatus.setText("This phone number has been used !");
				}
				
				else {
					user = User.builder()
							.firstName(firstName.getText())
							.lastName(lastName.getText())
							.phoneNumber(phoneNumber.getText())
							.password(password.getText())
							.username(username.getText())
							.isUsed(false)
							.build();
					userRepository.createUser(user);
					registerStatus.setStyle("-fx-padding: 8px 20px;"
							+ "-fx-background-color: #3c763d;"
							+ " -fx-text-fill: #fcf8e3;");
					
					registerStatus.setText("Register Successfully !");
				}
			}
			
			//Update user account
			else {
				
				// Check if phone number change, then check if phone number has been used
				if(! phoneNumber.getText().equals(App.user.getPhoneNumber())) {
					User user = userRepository.retrieveUserByPhoneNumber(phoneNumber.getText());
					if(user != null ) {
						registerStatus.setStyle("-fx-padding: 8px 20px;"
								+ "-fx-background-color: #f8d7da;"
								+ " -fx-text-fill: #721c24;" );
						registerStatus.setText("This phone number has been used !");
					}
				}
				
				User user = User.builder()
						.id(App.user.getId())
						.firstName(firstName.getText())
						.lastName(lastName.getText())
						.phoneNumber(phoneNumber.getText())
						.password(password.getText())
						.username(username.getText())
						.isUsed(false)
						.build();
				userRepository.updateUser(user);
				App.user = user;
				registerStatus.setStyle("-fx-padding: 8px 20px;"
						+ "-fx-background-color: #3c763d;"
						+ " -fx-text-fill: #fcf8e3;");
					
				registerStatus.setText("Update Successfully !");
				
			}
		
		}
	}
	
	//Helper method for validate phone number
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
}
