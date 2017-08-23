package model;

import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

class Task {
	
	/*
	 * declaration of attributes 
	 */
	private SimpleStringProperty title;
	private SimpleStringProperty description;
	private SimpleStringProperty categorie;
	private SimpleStringProperty status;
	private SimpleStringProperty creatorID;
	//private SimpleStringProperty comment; 
	private ObjectProperty<Date> lastCall; 
	private ObjectProperty<Date> dateCreate; 
	private ObservableList<User> member;
	private ObservableList<String> comment;
	
	public Task(){
		
	}
	
}
