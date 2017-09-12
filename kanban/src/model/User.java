package model;
import java.util.*;

public class User {
	private String uid;
	private String name;
	

	private List<String> projects;
	private String projectCurrent;

	/**
	 * User Constructor
	 * @param uid
	 * @param name
	 * @param projects
	 * @param projectCurrent
	 */
	public User(String uid, String name, List<String> projects, String projectCurrent) {
		this.uid = uid;
		this.name = name;
		this.projects = projects;
		this.setProjectCurrent(projectCurrent);
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<String> getProjects() {
		return projects;
	}

	public void setProjects(List<String> projects) {
		this.projects = projects;
	}

	public String getProjectCurrent() {
		return projectCurrent;
	}

	public void setProjectCurrent(String projectCurrent) {
		this.projectCurrent = projectCurrent;
	}
}




