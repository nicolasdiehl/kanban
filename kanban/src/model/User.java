package model;
import java.util.List;

public class User {
	private String uid;
	private String name;
	private String pw;
	
	List<Project> project = new List<Project>();
	Project lastProject;
	
	public User(String uid, String name, String pw) {
		this.uid = uid;
		this.name = name;
		this.pw = pw;
		
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

	

}




