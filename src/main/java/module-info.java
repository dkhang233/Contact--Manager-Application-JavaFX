module com.dkhang.contact_manager_project {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;

	opens com.dkhang.contact_manager_project to javafx.fxml;

	exports com.dkhang.contact_manager_project;
	exports com.dkhang.contact_manager_project.controllers;
	exports com.dkhang.contact_manager_project.models;
	exports com.dkhang.contact_manager_project.daos;

}
