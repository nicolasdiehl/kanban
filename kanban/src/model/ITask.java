package model;

import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;

public interface ITask extends ITaskFX{
	
	
	/**
	 * get title of task
	 * @return title of task
	 */	
	String getTitle();

	/**
	 * set title of task
	 * @param value	title of task
	 */
	void setTitle(String value);
	
	/**
	 * get task describtion
	 * @return task describtion
	 */	
	String getDescribtion();
	
	/**
	 * set task describtion
	 * @param value task describtion
	 */	
	void setDescribtion(String value);
	
	/**
	 * get task categorie
	 * @return task categorie
	 */	
	String getCategorie();
	
	/**
	 * set task categorie
	 * @param value categorie
	 */	
	void setCategorie(String value);
	
	/**
	 * get current status of task
	 * @return current status of task
	 */	
	String getStatus();
	
	/**
	 * set current status of task
	 * @param value for current status of task
	 */	
	void setStatus(String value); 
	
	/**
	 * get creatorID
	 * @return creatorID
	 */	
	String getCreatorID();
	
	/**
	 * set creatorID
	 * @param value creatorID
	 */	
	void setCreatorID(String value); 
	
	/**
	 * get date of last call
	 * @return date of last call
	 */	
	Date getLastCall();
	
	/**
	 * set date of last call
	 * @param value of last call
	 */	
	void setLastCall(Date value); 
	
	/**
	 * get date of task creation 
	 * @return date of task creation
	 */	
	Date getCreatorDate();
	
	/**
	 * set date of the task creation
	 * @param date of the task creation
	 */	
	void setCreatorDate(Date value); 
	
	/**
	 * get List of User
	 * @return List of User
	 */	
	List<User> getMember();
	
	/**
	 * set List of User 
	 * @param List of User
	 */	
	void setMember(List<User> value); 
	
	/**
	 * get list of comments in the task
	 * @return list of comments in the task
	 */	
	List<String> getComment();
	
	/**
	 * set list of comments
	 * @param list of comments in task
	 */	
	void setComment(List<String> value);
	
}
