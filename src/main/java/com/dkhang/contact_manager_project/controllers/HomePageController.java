package com.dkhang.contact_manager_project.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dkhang.contact_manager_project.App;
import com.dkhang.contact_manager_project.daos.GroupDAO;
import com.dkhang.contact_manager_project.daos.MemberDAO;
import com.dkhang.contact_manager_project.models.Group;
import com.dkhang.contact_manager_project.models.Role;
import com.dkhang.contact_manager_project.models.User;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class HomePageController {
	
	@FXML public BorderPane homePage; 
	@FXML public VBox centerContent;
	@FXML public ListView<Group> mainGroupList;
	@FXML public Label description;
	@FXML public Button joinGroupButton;
	@FXML public TextField groupName;
	
	private ObservableList<Group> groups = FXCollections.observableArrayList();
	private GroupDAO groupRepository = new GroupDAO();
	private MemberDAO memberRepository = new MemberDAO();
	
	/*Initialize HomePage
	 * Set all group informations to groupList
	 */
	public void initialize() {
		joinGroupButton.setDisable(true);
		groups.addAll(groupRepository.retrieveAllGroups());
		if( ! groups.isEmpty()) {
			mainGroupList.setItems(groups);
			mainGroupList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Group>() {
				@Override
				public void changed(ObservableValue<? extends Group> observable, Group oldValue, Group newValue) {
					if(newValue != null) {
						if (App.groups.stream().anyMatch((group) -> group.getId() == newValue.getId())) {
							joinGroupButton.setDisable(true);
						} else {
							joinGroupButton.setDisable(false);
						}
						description.setText(newValue.getDescription());
					}
				}
			});
			mainGroupList.setCellFactory((listview) -> new GroupCell());
		}
		homePage.setCenter(centerContent);
	}
	
	@FXML
	public void logout() {
		Alert confirmDelete = new Alert(AlertType.CONFIRMATION);
		confirmDelete.setHeaderText("Are you sure you want to logout?");
		Optional<ButtonType> result = confirmDelete.showAndWait();
		if(result.isPresent() && result.get()==ButtonType.OK) {
			App.user = new User();
			App.groups = new ArrayList<>();
			App.toLogoutPage();
		}
	}
	
	@FXML
	public void toExplore() {
		groups.clear();
		groups.addAll(groupRepository.retrieveAllGroups());
		if( ! groups.isEmpty()) {
			mainGroupList.setItems(groups);
		}
		mainGroupList.getSelectionModel().clearSelection();
		homePage.setCenter(centerContent);
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
		 GridPane group = (GridPane) loadFXML("MyGroup");
		 homePage.setCenter(group);
	}
	
	@FXML
	public void findGroupByName() {
		joinGroupButton.setDisable(true);
		mainGroupList.getSelectionModel().clearSelection();
		String name = groupName.getText();
		if(!name.isEmpty()) {
			ObservableList<Group> result =groups.stream().filter((group) -> group.getName().equals(name)).collect(Collectors.toCollection(FXCollections::observableArrayList));
			mainGroupList.setItems(result);
		}
		else{
			mainGroupList.setItems(groups);
		}
	}
	
	@FXML
	public void joinGroup() {
		Group group = mainGroupList.getSelectionModel().getSelectedItem();
		memberRepository.createMember(App.user.getId(), group.getId(), Role.USER);
		App.groups.add(group);
		joinGroupButton.setDisable(true);
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
