module com.dkhang.contact_manager_project {
	requires static lombok;
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires org.kordamp.bootstrapfx.core;

	opens com.dkhang.contact_manager_project to javafx.fxml;

	exports com.dkhang.contact_manager_project;
	exports com.dkhang.contact_manager_project.controllers;
	exports com.dkhang.contact_manager_project.models;
	exports com.dkhang.contact_manager_project.repositories;

}
