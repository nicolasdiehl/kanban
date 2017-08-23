package model;

import java.util.List;
import java.util.UUID;

public class Project implements IProject{

	//ToDo: modified, created
	private String id;
	private String name;
	private String description;
	private List<ITask> tasks;
	private List<Integer> members;
	
	public Project(String name,String description, List<ITask> tasks ,List<Integer> members, String id ) {
		this.name = name;
		this.description = description;
		this.tasks = tasks;
		this.members = members;
		this.id = id;
	}
	
	public Project(String name,String description, List<ITask> tasks ,List<Integer> members ) {
			this(name, description,tasks,members,UUID.randomUUID().toString());
	}
	
	@Override 	
	public String getID() {
		return id;
	}

	@Override
	public void setID(String value) {
		id = value;	
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String value) {
		name = value;
		
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
	public List<ITask> getTasks() {
		return tasks;
	}

	@Override
	public void setTasks(List<ITask> value) {
		tasks = value;
	}

	@Override
	public List<Integer> getMembers() {
		return members;
	}

	@Override
	public void setMembers(List<Integer> value) {
		members = value;		
	}

	@Override
	public void addTask(ITask value) {
		tasks.add(value);		
	}


}
