package model;

import java.util.*;

public interface IProject {
	String getID();
	void setID(String value);
	
	String getName();
	void setName(String value);
	
	Date getModified();
	void setModified(Date value);
	
	Date getCreated();
	void setCreated(Date value);
	
	String getDescription();
	void setDescription(String value);
	
	List<ITask> getTasks();
	void setTasks(List<ITask> value);
	
	List<Integer> getMembers();
	void setMembers(List<Integer> value);
	
	void addTask(ITask value); 
}
