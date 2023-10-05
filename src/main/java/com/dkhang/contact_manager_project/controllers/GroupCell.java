package com.dkhang.contact_manager_project.controllers;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import com.dkhang.contact_manager_project.App;
import com.dkhang.contact_manager_project.models.Group;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

/*
 * Helper class for customize group cell
 */
public class GroupCell extends ListCell<Group>{
	private GridPane groupCell;
	private Label name;
	private Label createdDate;
	
	public GroupCell() {
		groupCell = (GridPane) loadFXML("GroupCell");
		name = (Label) groupCell.lookup("#name");
		createdDate = (Label) groupCell.lookup("#createdDate");
	}
	
	 @Override
		protected void updateItem(Group item, boolean empty) {
			super.updateItem(item, empty);
			setEditable(false);
			if (empty || item == null) {
				setGraphic(null);
			} else {
				name.setText(getItem().getName());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
				createdDate.setText(getItem().getCreatedAt().format(formatter));
				setGraphic(groupCell);
			}
		}
	        
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
	 
