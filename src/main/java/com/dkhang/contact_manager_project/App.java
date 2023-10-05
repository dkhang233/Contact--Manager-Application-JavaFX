package com.dkhang.contact_manager_project;

import java.io.IOException;
import java.util.List;

import com.dkhang.contact_manager_project.models.User;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application {

	private static Scene scene;
	private static Group root = new Group();
	public static User user = new User(); 
	public static List<com.dkhang.contact_manager_project.models.Group> groups;  
	
	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(toLoginPage());
		stage.setScene(scene);
		stage.setTitle("Social Application");
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

	// Navigate to the login page
	public static Group toLoginPage(){
		root.getChildren().clear();
		root.getChildren().add(loadFXML("LoginForm"));
		return root;
	}
	
	// Navigate to the logout page
	public static Group toLogoutPage(){
		root.getChildren().clear();
		root.getChildren().add(loadFXML("LoginForm"));
		
		Label loginStatus = (Label) root.lookup("#loginStatus");
		loginStatus.setText("Logout sucessfully !");
		loginStatus.getStyleClass().clear();
		loginStatus.getStyleClass().add("label-success");
		
		return root;
	}
	
	// Navigate to the home page
	public static void toHomePage(){
		root.getChildren().clear();
		root.getChildren().add(loadFXML("HomePage"));
	}

	// Helper method for load Parent component from .fxml file
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