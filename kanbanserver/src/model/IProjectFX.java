package model;

import java.util.Date;
import javafx.beans.property.*;
import javafx.collections.*;

/**
 * Interface for the project class for JavaFX
 * @version 1.0
 */

public interface IProjectFX {
	
	/**
	 * get project id and converts it to SimpleStringProperty
	 * @return project ID as SimpleStringProperty
	 */
	SimpleStringProperty getIDProperty();
	
	/**
	 * convert SimpleStringProperty to String and set project id
	 * @param value	project id as SimpleStringProperty
	 */
	void setID(SimpleStringProperty value);
	
	/**
	 * get project name and converts it to SimpleStringProperty
	 * @return project name as SimpleStringProperty
	 */
	SimpleStringProperty getNameProperty();
	
	/**
	 * convert SimpleStringProperty to String and set project name
	 * @param value project name as SimpleStringProperty
	 */
	void setName(SimpleStringProperty value);
	
	/**
	 * get last modified date and converts it to a ObjectProperty<Date>
	 * @return last modified date as ObjectProperty<Date>
	 */
	ObjectProperty<Date> getModifiedProperty();
	void setModified(ObjectProperty<Date> value);
	
	/**
	 * get creation date and converts it to a ObjectProperty<Date>
	 * @return creation date as ObjectProperty<Date>
	 */
	ObjectProperty<Date> getCreatedProperty();
	
	/**
	 * convert ObjectProperty<Date> to Date and set creation date
	 * @param value creation date as ObjectProperty<Date> value
	 */
	void setCreated(ObjectProperty<Date> value);
	
	/**
	 * get description and converts it to SimpleStringProperty
	 * @return description as SimpleStringProperty
	 */
	SimpleStringProperty getDescriptionProperty();
	void setDescription(SimpleStringProperty value);
	
	/**
	 * get a list of tasks and converts it to a ObservableList of tasks
	 * @return ObservableList of tasks
	 */
	ObservableList<ITask> getTasksProperty();
	
	/**
	 * convert ObservableList of tasks to list of tasks and set task list
	 * @param value ObservableList of tasks
	 */
	void setTasks(ObservableList<ITask> value);
	
	/**
	 * get a list of member ids and converts it to a ObservableList of member ids
	 * @return ObservableList of member ids
	 */
	ObservableList<Integer> getMembersProperty();
		
	/**
	 * convert ObservableList of member ids to list of member id and set member id  list
	 * @param value ObservableList of member ids
	 */
	void setMembers(ObservableList<Integer> value);

}
