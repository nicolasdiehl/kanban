package model;

import java.util.*;

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
		projects.add(new SimpleProject("hallo", UUID.randomUUID().toString()));
		projects.add(new SimpleProject("hallo2", UUID.randomUUID().toString()));
		projects.add(new SimpleProject("hallo3", UUID.randomUUID().toString()));
		projects.add(new SimpleProject("hallo4", UUID.randomUUID().toString()));
	
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
