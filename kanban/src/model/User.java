package model;
import java.util.List;

public class User {
	private String uid;
	private String name;
	private String pw;
	
	private List<String> projects;
	private String lastProject;
	
	public User(String uid, String name, String pw, List<String> projects, String lastProject) {
		this.uid = uid;
		this.name = name;
		this.pw = pw;
		this.setProjects(projects);
		this.setLastProject(lastProject);
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
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getLastProject() {
		return lastProject;
	}

	public void setLastProject(String lastProject) {
		this.lastProject = lastProject;
	}

	public List<String> getProjects() {
		return projects;
	}

	public void setProjects(List<String> projects) {
		this.projects = projects;
	}

	

}




