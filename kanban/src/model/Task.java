package model;

import java.util.Date;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

class Task implements ITask, ITaskFX {
	
	/**
	 * declaration of attributes 
	 */
	private String title;
	private String describtion;
	private String categorie;
	private String status;
	private String creatorID; 
	private Date lastCall; 
	private Date dateCreate; 
	private List<User> member;
	private List<String> comment;
	
	
	/**
	 * Constructor
	 */
	
	public Task(){
		
	}
	
	
	/*
	 *   Functions
	 */

	@Override
	public String getTitle() {
		
		return title;
	}

	@Override
	public void setTitle(String value) {
		title = value;
	}

	@Override
	public String getDescribtion() {
		 
		return describtion;
	}

	@Override
	public void setDescribtion(String value) {
		 
		describtion = value;
	}

	@Override
	public String getCategorie() {
		 
		return categorie;
	}

	@Override
	public void setCategorie(String value) {
		 
		categorie = value;
	}

	@Override
	public String getStatus() {
		 
		return status;
	}

	@Override
	public void setStatus(String value) {
		 
		status = value; 
	}

	@Override
	public String getCreatorID() {
		 
		return creatorID;
	}

	@Override
	public void setCreatorID(String value) {
		
		creatorID = value; 
	}

	@Override
	public Date getLastCall() {
		
		return lastCall;
	}

	@Override
	public void setLastCall(Date value) {
		
		lastCall = value; 
	}

	@Override
	public Date getCreatorDate() {
		
		return dateCreate;
	}

	@Override
	public void setCreatorDate(Date value) {

		dateCreate = value;
	}

	@Override
	public List<User> getMember() {
		
		return member;
	}

	@Override
	public void setMember(List<User> value) {
		
		member = value;
	}
	

	@Override
	public List<String> getCommentProperty() {
		
		return comment;
	}
	
	@Override
	public void setComment(List<String> value) {
		
		comment = value;
	}
	
}
