package model;

import java.io.Serializable;
import java.util.*;

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
	 * pw --> ldap passwort
	 */
	private String pw;
	/**
	 * projects --> String list of all Projects the user worked on 
	 */
	private ObservableList<SimpleProject> projects;
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
	public User(String uid, String name, String pw, ObservableList<SimpleProject> projects, SimpleProject projectCurrent)
	{
		this.uid = uid;
		this.name = name;
		this.pw = pw;
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

	public String getPw()
	{
		return pw;
	}

	public void setPw(String pw)
	{
		this.pw = pw;
	}

	public ObservableList<SimpleProject> getProjects()
	{
		return projects;
	}

	public void setProjects(ObservableList<SimpleProject> projects)
	{
		this.projects = projects;
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
