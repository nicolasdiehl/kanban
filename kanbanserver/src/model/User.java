package model;

import java.util.*;

import kanbanserver.SimpleProject;

public class User
{
	private String uid;
	private String name;
	private String pw;
	private List<SimpleProject> projects;
	private SimpleProject projectCurrent;

	public User(String uid, String name, String pw, List<SimpleProject> projects, SimpleProject projectCurrent)
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

	public List<SimpleProject> getProjects()
	{
		return projects;
	}

	public void setProjects(List<SimpleProject> projects)
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
