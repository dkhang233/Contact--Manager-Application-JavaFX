package com.dkhang.contact_manager_project.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.dkhang.contact_manager_project.App;
import com.dkhang.contact_manager_project.models.Group;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class HomePageController {
	
	public BorderPane homePage; 
	public VBox centerContent;
	
	public ListView<Group> listGroupsAndUsers;
	
	 private ObservableList<Group> groups = FXCollections.observableArrayList();
	
	public void initialize() {
		groups.add(new Group(1, "a"));
		groups.add(new Group(1, "a"));
		groups.add(new Group(1, "a"));
		listGroupsAndUsers.setItems(groups);
		listGroupsAndUsers.getSelectionModel().select(0);
	}
	
	@FXML
	public void logout() {
		Alert confirmDelete = new Alert(AlertType.CONFIRMATION);
		confirmDelete.setHeaderText("Are you sure you want to logout?");
		Optional<ButtonType> result = confirmDelete.showAndWait();
		if(result.isPresent() && result.get()==ButtonType.OK) {
			App.user = null;
			App.toLogoutPage();
		}
	}
	
	@FXML
	public void toExplore() {
		App.toHomePage();
	}
	
	@FXML
	public void toAccount() {
	        GridPane account = (GridPane) loadFXML("MyAccount");
	        TextField firstName = (TextField) account.lookup("#firstName");
	        TextField lastName = (TextField) account.lookup("#lastName");
	        TextField phoneNumber = (TextField) account.lookup("#phoneNumber");
	        TextField username = (TextField) account.lookup("#username");
	        firstName.setText(App.user.getFirstName());
	        lastName.setText(App.user.getLastName());
	        phoneNumber.setText(App.user.getPhoneNumber());
	        username.setText(App.user.getUsername());
	        homePage.setCenter(account);
	}
	
	@FXML
	public void toGroup() {
		
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
		Alert confirmDelete = new Alert(AlertType.CONFIRMATION);
		confirmDelete.setHeaderText("Are you sure you want to delete this group?");
		Optional<ButtonType> result = confirmDelete.showAndWait();
		if(result.isPresent() && result.get()==ButtonType.OK) {
			System.out.println("deleteGroup");
		}
		
	}
	
	private static Parent loadFXML(String fxml){
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		try {
			return fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
