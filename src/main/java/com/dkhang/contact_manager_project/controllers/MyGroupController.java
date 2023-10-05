package com.dkhang.contact_manager_project.controllers;

import java.util.Optional;

import com.dkhang.contact_manager_project.App;
import com.dkhang.contact_manager_project.daos.GroupDAO;
import com.dkhang.contact_manager_project.daos.MemberDAO;
import com.dkhang.contact_manager_project.models.Group;
import com.dkhang.contact_manager_project.models.Member;
import com.dkhang.contact_manager_project.models.Role;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

public class MyGroupController {
	
	@FXML public ListView<Group> groupList;
	@FXML public Button leaveGroupButton;
	@FXML public Button updateGroupButton;
	@FXML public Button deleteGroupButton;
	
	private ObservableList<Group> groups = FXCollections.observableArrayList();
	private MemberDAO memberRepository = new MemberDAO();
	private GroupDAO groupRepository = new GroupDAO();
	
	public void initialize() {
		leaveGroupButton.setDisable(true);
		updateGroupButton.setDisable(true);
		deleteGroupButton.setDisable(true);
		groups.addAll(App.groups);
		if( ! groups.isEmpty()) {
			groupList.setItems(groups);
		}
		groupList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Group>() {
			@Override
			public void changed(ObservableValue<? extends Group> observable, Group oldValue, Group newValue) {
				if(newValue != null) {
					leaveGroupButton.setDisable(false);
					Member member = memberRepository.retrieveMemberByUserIdAndGroupId( App.user.getId(),newValue.getId());
					if(member.getRole() != Role.USER) {
						updateGroupButton.setDisable(false);
						deleteGroupButton.setDisable(false);
					}else {
						updateGroupButton.setDisable(true);
						deleteGroupButton.setDisable(true);
					}
				}
			}
		});
		groupList.setCellFactory((listview) -> new GroupCell());
	}
	
	@FXML
	public void createGroup() {
		GroupDialog groupDialog = new GroupDialog(new Group());
		Optional<Group> input = groupDialog.showAndWait();
		if(input.isPresent()) {
			Group result = input.get();
			
			if(groupRepository.retrieveGroupByName(result.getName()) != null){
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Group name has been existed");
				error.showAndWait();
				return;
			}
			
			groupRepository.createGroup(result);
			result  = groupRepository.retrieveGroupByName(result.getName());
			memberRepository.createMember(App.user.getId(),result.getId(),Role.ADMIN);
			App.groups.add(result);
			groupList.getItems().add(result);
		}
	}
	
	@FXML
	public void leaveGroup() {
		Group group = groupList.getSelectionModel().getSelectedItem();
		if(group != null) {
			memberRepository.deleteMember(App.user.getId(), group.getId());
			App.groups.remove(group);
			groupList.getItems().remove(group);
		}
	}
	
	@FXML
	public void updateGroup() {
		Group selectedGroup = groupList.getSelectionModel().getSelectedItem();
		GroupDialog groupDialog = new GroupDialog(selectedGroup);
		Optional<Group> input = groupDialog.showAndWait();
		if(input.isPresent()) {
			Group result = input.get();
			
			if(groupRepository.retrieveGroupByName(result.getName()) != null){
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Group name has been existed");
				error.showAndWait();
				return;
			}
			
			groupRepository.updateGroup(result);
			App.groups.remove(selectedGroup);
			App.groups.add(result);
			groupList.getItems().remove(selectedGroup);
			groupList.getItems().add(result);
		}
	}
	
	@FXML
	public void deleteGroup() {
		Alert confirmDelete = new Alert(AlertType.CONFIRMATION);
		confirmDelete.setHeaderText("Are you sure you want to delete this group?");
		Optional<ButtonType> result = confirmDelete.showAndWait();
		if(result.isPresent() && result.get()==ButtonType.OK) {
			Group selectedGroup = groupList.getSelectionModel().getSelectedItem();
			memberRepository.deleteAllMemberByGroupId(selectedGroup.getId());
			groupRepository.deleteGroup(selectedGroup);
			App.groups.remove(selectedGroup);
			groupList.getItems().remove(selectedGroup);
		}
	}
}
