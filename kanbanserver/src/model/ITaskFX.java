package model;

import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public interface ITaskFX {
	
	/**
	 * get title of task and converts it to SimpleStringProperty
	 * @return title of task as SimpleStringProperty
	 */	
	SimpleStringProperty getTitleProperty();
	
	/**
	 * convert SimpleStringProperty to String and set title of task
	 * @param value	title of task as SimpleStringProperty
	 */
	void setTitle(SimpleStringProperty value);

	/**
	 * get task description and converts it to SimpleStringProperty
	 * @return task description as SimpleStringProperty
	 */	
	SimpleStringProperty getDescriptionProperty();
	
	/**
	 * convert SimpleStringProperty to String and set task description
	 * @param value	task description as SimpleStringProperty
	 */
	void setDescription(SimpleStringProperty value);

	/**
	 * get task categorie and converts it to SimpleStringProperty
	 * @return task categorie as SimpleStringProperty
	 */	
	SimpleStringProperty getCategorieProperty();
	
	/**
	 * convert SimpleStringProperty to String and set task categorie
	 * @param value	task categorie as SimpleStringProperty
	 */
	void setCategorie(SimpleStringProperty value);
	
	/**
	 * get status of task and converts it to SimpleStringProperty
	 * @return status of task as SimpleStringProperty
	 */	
	SimpleStringProperty getStatusProperty();
	
	/**
	 * convert SimpleStringProperty to String and set status of task
	 * @param value	status of task as SimpleStringProperty
	 */
	void setStatus(SimpleStringProperty value); 
	
	/**
	 * get the ID of the creator and converts it to SimpleStringProperty
	 * @return ID of the creator as SimpleStringProperty
	 */	
	SimpleStringProperty getCreatorIDProperty();
	
	/**
	 * convert SimpleStringProperty to String and set ID of creator
	 * @param value	ID of creator as SimpleStringProperty
	 */
	void setCreatorID(SimpleStringProperty value); 
	
	/**
	 * get the last call as Date and converts it to a ObjectProperty<Date>
	 * @return creation last call (Date) as ObjectProperty<Date>
	 */
	ObjectProperty<Date> getLastCallProperty();
	
	/**
	 * convert ObjectProperty<Date> to Date and set last call as date
	 * @param value last call (Date) as ObjectProperty<Date> value
	 */
	void setLastCall(ObjectProperty<Date> value); 
	
	/**
	 * get creation date and converts it to a ObjectProperty<Date>
	 * @return creation date as ObjectProperty<Date>
	 */
	ObjectProperty<Date> getCreatorDateProperty();
	
	/**
	 * convert ObjectProperty<Date> to Date and set creation date
	 * @param value creation date as ObjectProperty<Date> value
	 */
	void setCreatorDate(ObjectProperty<Date> value); 
	
	/**
	 * get a list of user and converts it to a ObservableList of User
	 * @return ObservableList of tasks
	 */
	ObservableList<User> getMemberProperty();
	
	/**
	 * convert ObservableList of user to list of user and set user list
	 * @param value ObservableList of user
	 */
	void setMember(ObservableList<User> value); 
	
	/**
	 * get a list of comments(strings) and converts it to a ObservableList of comments
	 * @return ObservableList of comments
	 */
	ObservableList<String> getCommentProperty();
	
	/**
	 * convert ObservableList of comments to list of comments and set comment list
	 * @param value ObservableList of comments
	 */
	void setComment(ObservableList<String> value);

}
