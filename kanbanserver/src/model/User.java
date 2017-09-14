package model;

import java.io.Serializable;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User implements Serializable
{
	private static final long serialVersionUID = -2398524575120480637L;
	
	/*
	 * declaration of attributes
	 */
	
	/**
	 * uid --> UserID
	 */
	private String uid;
	/**
	 * name --> ldap name
	 */
	private String name;
	/**
	 * projects --> String list of all Projects the user worked on 
	 */
	private ArrayList<SimpleProject> projects;
	
	/**
	 * 
	 */
	private ObservableList<SimpleProject> projectsProperty = FXCollections.observableArrayList();
	/**
	 * project current --> project object, the user is currently working with
	 */
	private SimpleProject projectCurrent;

	/**
	 * constructor for loading project
	 * @param name				user name
	 * @param pw				user pw
	 * @param projects			list of all projects the user worked on
	 * @param project current	project object the user is working on 
	 */
	public User(String uid, String name, ArrayList<SimpleProject> projects, SimpleProject projectCurrent)
	{
		this.uid = uid;
		this.name = name;
		this.projects = projects;
		this.setProjectCurrent(projectCurrent);
	}
	

	public String getUid()
	{
		return uid;
	}

	public void setUid(String uid)
	{
		this.uid = uid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	public ArrayList<SimpleProject> getProjects()
	{
		projects.add(new SimpleProject("hallo", UUID.randomUUID().toString()));
		projects.add(new SimpleProject("hallo2", UUID.randomUUID().toString()));
		projects.add(new SimpleProject("hallo3", UUID.randomUUID().toString()));
		projects.add(new SimpleProject("hallo4", UUID.randomUUID().toString()));
	
		return projects;
	}

	public void setProjects(ArrayList<SimpleProject> projects)
	{
		this.projects = projects;
	}
	
	public void addProjects(SimpleProject project)
	{
		this.projects.add(project);
	}

	public SimpleProject getProjectCurrent()
	{
		return projectCurrent;
	}

	public void setProjectCurrent(SimpleProject projectCurrent)
	{
		this.projectCurrent = projectCurrent;
	}
}
