package com.dkhang.contact_manager_project;

import java.io.IOException;

import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import com.dkhang.contact_manager_project.models.User;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {

	private static Scene scene;
	private static Group root = new Group();
	public static User user;
	
	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(toLoginPage());
		stage.setScene(scene);
		stage.setTitle("Social Application");
		stage.sizeToScene();
		stage.show();

	}

	public static void main(String[] args) {
		launch();
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

	public static void toRegisterPage(){
		Stage registerPage = new Stage();
		registerPage.setTitle("Register Page");
		registerPage.setScene(new Scene(loadFXML("RegisterPage")));
		registerPage.sizeToScene();
		registerPage.setResizable(false);
		registerPage.initModality(Modality.APPLICATION_MODAL);
		registerPage.show();
	}
	
	public static Group toLoginPage(){
		root.getChildren().clear();
		root.getChildren().add(loadFXML("LoginForm"));
		return root;
	}
	
	public static Group toLogoutPage(){
		root.getChildren().clear();
		root.getChildren().add(loadFXML("LoginForm"));
		
		Label loginStatus = (Label) root.lookup("#loginStatus");
		loginStatus.setText("Logout sucessfully !");
		loginStatus.setStyle("-fx-padding: 8px 20px;"
					+ "-fx-background-color: #3c763d;"
					+ " -fx-text-fill: #fcf8e3;");
		
		return root;
	}
	
	//Initialize home page
	public static void toHomePage(){
		String userInfor = "Fist Name: " + App.user.getFirstName()
						+ "\r\nLast Name: "+ App.user.getLastName()
						+ "\r\nPhone Number: "+ App.user.getPhoneNumber()
						+ "\r\nUsername: "+ App.user.getUsername();
		HBox home = (HBox) loadFXML("HomePage");
		Label userInformation = (Label)home.lookup("#userInformation");
		userInformation.setText(userInfor);
		
		root.getChildren().clear();
		root.getChildren().add(home);
	}

	public static void toUpdateUserPage() {
	
		//Get VBox from RegisterPage.fxml file
		VBox vb = (VBox) loadFXML("RegisterPage");
		
		// Change title label: Register Now ! -> Update User Account
		Label title = (Label) vb.lookup("#title");
		title.setText("Update User Account");
		
		//Get "firstName" field and set text = user.firstName
		TextField firstName = (TextField) vb.lookup("#firstName");
		firstName.setText(user.getFirstName());
		
		//Get "lastName" field and set text = user.lastName
		TextField lastName = (TextField) vb.lookup("#lastName");
		lastName.setText(user.getLastName());
		
		//Get "phoneNumber" field and set text = user.phoneNumber
		TextField phoneNumber = (TextField) vb.lookup("#phoneNumber");
		phoneNumber.setText(user.getPhoneNumber());
		
		//Get "username" field and set text = user.username
		TextField username = (TextField) vb.lookup("#username");
		username.setText(user.getUsername());
		
		// Change text of button : Register -> Update
		Button registerButton = (Button)vb.lookup("#registerButton");
		registerButton.setText("Update");
		
		//Initialize updateUserPage stage
		Stage updateUserPage = new Stage();
		updateUserPage.setTitle("Update User Page");
		updateUserPage.setScene(new Scene(vb));
		updateUserPage.sizeToScene();
		updateUserPage.setResizable(false);
		updateUserPage.initModality(Modality.APPLICATION_MODAL);
		updateUserPage.show();
	}
	
	

}