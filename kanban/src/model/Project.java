package model;
import java.util.*;

/**
 * project class containing id, name, description, date last modified, creation date
 * @version 1.0
 * @author André Adolf, Steffen Aaland 
 */

public class Project implements IProject{

	/**project id*/
	private String id;	
	/**project name */
	private String name;
	/**description of the project */
	private String description;	
	/**date the project was last modified */
	private Date modified;	
	/**date the project was created */
	private Date created;					
	//TODO delete tasks/members?
	/**list of tasks */
	private List<ITask> tasks;		
	/**list of project member ids */
	private List<Integer> members;	
	
	/**
	 * constructor for loading project
	 * @param name				project name
	 * @param description		project description
	 * @param modified			datelast modified date
	 * @param created			date creation date
	 * @param tasks 				list of tasks
	 * @param members		list of member ids
	 * @param id						project id
	 */
	public Project(String name,String description,Date modified, Date created, List<ITask> tasks ,List<Integer> members, String id ) {
		this.name = name;
		this.description = description;
		this.modified = modified;
		this.created = created;
		this.tasks = tasks;
		this.members = members;
		this.id = id;
	}
	
	/**
	 *  constructor for creating new project, 
	 *  creates unique id for the project, 
	 *  sets current date&time to modified/created, 
	 *  creates members list and adds creator id to the list
	 * @param name 				project name
	 * @param description		project description
	 * @param creator			creator id
	 */
	public Project(String name,String description ,int creator ) {
			this(name, description,null,null,new ArrayList<ITask>(),null,UUID.randomUUID().toString());
			Date current = new Date();
			modified = current;
			created = current;		
			members = new ArrayList<Integer>();
			members.add(creator);
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
	public Date getModified() {
		return modified;
	}

	@Override
	public void setModified(Date value) {
		modified = value;
		
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(Date value) {
		created = value;		
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

	@Override
	public void addMember(int value) {
		members.add(value);	
	}
}
