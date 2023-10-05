package com.dkhang.contact_manager_project.controllers;

import java.io.IOException;

import com.dkhang.contact_manager_project.App;
import com.dkhang.contact_manager_project.models.Group;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class GroupDialog extends Dialog<Group>{
	private Group group;
	private TextField name;
	private TextField description;
	
	public GroupDialog(Group group) {
		Pane pane = (Pane) loadFXML("CreateNewGroup");
		getDialogPane().setContent(pane);
		getDialogPane().getStylesheets().add(App.class.getResource("/css/style.css").toExternalForm());
		setTitle("Create new group");
		this.group = group;
		name = (TextField) getDialogPane().lookup("#name");
		description = (TextField) getDialogPane().lookup("#description");
		getDialogPane().getButtonTypes().addAll(ButtonType.OK,ButtonType.CANCEL);
		Button createButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
		createButton.getStyleClass().addAll("btn","btn-success");
		createButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(! validateDialog()) {
					event.consume();
				}
			}
			
			private boolean validateDialog() {
				if(name.getText().isEmpty()) {
					return false;
				}
				return true;
			}
		});
		
		Button cancelButton = (Button) getDialogPane().lookupButton(ButtonType.CANCEL);
		cancelButton.getStyleClass().addAll("btn","btn-danger");
		setPropertyBindings();
		setResultConverter();
	}
	
	private void setResultConverter() {
		Callback<ButtonType, Group> groupResultConverter = new Callback<ButtonType, Group>() {
			@Override
			public Group call(ButtonType param) {
				if(param == ButtonType.OK) {
					return group;
				}else {
					return null;
				}
			}
		};
		setResultConverter(groupResultConverter);
	}

	private void setPropertyBindings() {
		name.textProperty().bindBidirectional(group.getNameProperty());
		description.textProperty().bindBidirectional(group.getDescriptionProperty());
	}
	
	//Helper method for load .fxml file 
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
