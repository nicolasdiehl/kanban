package model;
import java.util.*;

/**
 * interface of the project class
 * @version 1.0
 */
public interface IProject extends IProjectFX{

	
	/**
	 * get project id
	 * @return project id
	 */
	String getID();

	/**
	 * set project id
	 * @param value	project id
	 */
	void setID(String value);
	
	/**
	 * get project name
	 * @return project name
	 */
	String getName();
	
	/**
	 * set project name
	 * @param value project name
	 */
	void setName(String value);
	
	/**
	 * get date last modified
	 * @return date last modified
	 */
	Date getModified();
	
	/**
	 * set date last modified
	 * @param value	date last modified
	 */
	void setModified(Date value);
	
	/**
	 * get creation date
	 * @return creation date
	 */
	Date getCreated();
	
	/**
	 * set creation date
	 * @param value	creation date
	 */
	void setCreated(Date value);
	
	/**
	 * get project description
	 * @return description	project description
	 */
	String getDescription();
	
	/**
	 *  set project description
	 * @param value	project description
	 */
	void setDescription(String value);
	
	/**
	 * get list of tasks
	 * @return list of tasks
	 */
	List<ITask> getTasks();
	
	/**
	 * set list of tasks
	 * @param value	list of tasks
	 */
	void setTasks(List<ITask> value);
	
	/**
	 * get list of member ids
	 * @return integer list of member ids
	 */
	List<Integer> getMembers();
	
	/**
	 * set list of member ids
	 * @param value	list of member ids
	 */
	void setMembers(List<Integer> value);
	
	/**
	 * add task to list
	 * @param value	task
	 */
	void addTask(ITask value); 
	
	/**
	 * add member id to list
	 * @param value	member id
	 */
	void addMember(int value);
}
