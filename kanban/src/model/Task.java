package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class Task implements ITask, ITaskFX {
	
	/**
	 * declaration of attributes 
	 */
	
	/** task title */
	private String title;
	/** description of the task */
	private String description;
	/** categorie of the task... should define the color of the post-it*/
	private String categorie;
	/** current status of the task*/
	private String status;
	/** creator ID*/
	private String creatorID;
	/** last call of this task*/
	private Date lastCall; 
	/** creation date*/
	private Date dateCreate;
	/** list of member, which works on the task*/
	private List<User> member;
	/** list of comments*/
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
	public String getDescription() {
		 
		return description;
	}

	@Override
	public void setDescription(String value) {
		 
		description = value;
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
	public List<String> getComment() {
		
		return comment;
	}
	
	@Override
	public void setComment(List<String> value) {
		
		comment = value;
	}


	@Override
	public SimpleStringProperty getTitleProperty() {
		return new SimpleStringProperty(title);
	}


	@Override
	public void setTitle(SimpleStringProperty value) {
		title = value.get();
		
	}


	@Override
	public SimpleStringProperty getDescriptionProperty() {
		return new SimpleStringProperty(description);
	}


	@Override
	public void setDescription(SimpleStringProperty value) {
		description = value.get();
	}


	@Override
	public SimpleStringProperty getCategorieProperty() {
		return new SimpleStringProperty(categorie);
	}


	@Override
	public void setCategorie(SimpleStringProperty value) {
		categorie = value.get();
	}


	@Override
	public SimpleStringProperty getStatusProperty() {
		return new SimpleStringProperty(status);
	}


	@Override
	public void setStatus(SimpleStringProperty value) {
		status = value.get();
	}


	@Override
	public SimpleStringProperty getCreatorIDProperty() {
		return new SimpleStringProperty(creatorID);
	}


	@Override
	public void setCreatorID(SimpleStringProperty value) {
		creatorID = value.get();
	}


	@Override
	public ObjectProperty<Date> getLastCallProperty() {
		return new SimpleObjectProperty<Date>(lastCall);
	}


	@Override
	public void setLastCall(ObjectProperty<Date> value) {
		lastCall = value.get(); 
	}


	@Override
	public ObjectProperty<Date> getCreatorDateProperty() {
		return new SimpleObjectProperty<Date>(dateCreate);
	}


	@Override
	public void setCreatorDate(ObjectProperty<Date> value) {
		dateCreate = value.get(); 
	}


	@Override
	public ObservableList<User> getMemberProperty() {
		return (ObservableList<User>)getMember();
	}


	@Override
	public void setMember(ObservableList<User> value) {
		member = value; 
		
	}


	@Override
	public ObservableList<String> getCommentProperty() {
		return (ObservableList<String>)getComment();
	}


	@Override
	public void setComment(ObservableList<String> value) {
		comment = value;
	}
	
}
