package model;

import java.util.Date;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

class Task implements ITask {
	
	/*
	 * declaration of attributes 
	 */
	private SimpleStringProperty title;
	private SimpleStringProperty description;
	private SimpleStringProperty categorie;
	private SimpleStringProperty status;
	private SimpleStringProperty creatorID; 
	private ObjectProperty<Date> lastCall; 
	private ObjectProperty<Date> dateCreate; 
	private ObservableList<User> member;
	private ObservableList<String> comment;
	
	
	/*
	 * Constructor
	 */
	
	public Task(){
		
	}
	
	
	/*
	 *   Functions
	 */

	@Override
	public String getTitleProperty() {
		
		return title.get();
	}

	@Override
	public void setTitle(String value) {
		this.title = new SimpleStringProperty();
		this.title.set(value);
	}

	@Override
	public String getDescribtionProperty() {
		 
		return description.get();
	}

	@Override
	public void setDescribtion(String value) {
		 
		this.description = new SimpleStringProperty();
		this.description.set(value);
	}

	@Override
	public String getCategorieProperty() {
		 
		return categorie.get();
	}

	@Override
	public void setCategorie(String value) {
		 
		this.categorie = new SimpleStringProperty();
		this.categorie.set(value);
	}

	@Override
	public String getStatusProperty() {
		 
		return status.get();
	}

	@Override
	public void setStatus(String value) {
		 
		this.status = new SimpleStringProperty();
		this.status.set(value);
	}

	@Override
	public String getCreatorIDProperty() {
		 
		return creatorID.get();
	}

	@Override
	public void setCreatorID(String value) {
		
		this.creatorID = new SimpleStringProperty();
		this.creatorID.set(value);
	}

	@Override
	public Date getLastCallProperty() {
		
		return lastCall.get();
	}

	@Override
	public void setLastCall(Date value) {
		
		this.lastCall = new SimpleObjectProperty<>();
		this.lastCall.set(value);
	}

	@Override
	public Date getCreatorDateProperty() {
		
		return dateCreate.get();
	}

	@Override
	public void setCreatorDate(Date value) {
		this.dateCreate = new SimpleObjectProperty<>();
		this.dateCreate.set(value);
	}

	@Override
	public List<User> getMemberProperty() {
		
		return this.member;
	}

	@Override
	public void setMember(List<User> value) {
		
		this.member = (ObservableList<String>)value;
	}
	

	@Override
	public List<String> getCommentProperty() {
		
		return this.comment;
	}
	
	@Override
	public void setComment(List<String> value) {
		
		this.comment = (ObservableList<String>) value;
	}
	
}
