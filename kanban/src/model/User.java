package model;

import java.util.*;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class User {

	private String uid;
	private String name;
	private List<SimpleProject> projects;
	private Project projectCurrent;

	/**
	 * User Constructor
	 * 
	 * @param uid
	 * @param name
	 * @param projects
	 * @param projectCurrent
	 */
	public User(String uid, String name, List<SimpleProject> projects, Project projectCurrent) {
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

	public List<SimpleProject> getProjects() {
		return projects;
	}

	public void setProjects(List<SimpleProject> projects) {
		this.projects = projects;
	}

	public Project getProjectCurrent() {
		return projectCurrent;
	}

	public ObjectProperty<Project> getObservableProjectCurrent() {
		return new SimpleObjectProperty<Project>(projectCurrent);
	}
	
	public void setProjectCurrent(Project projectCurrent) {
		this.projectCurrent = projectCurrent;
	}
}
