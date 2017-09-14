package control;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import kanbanserver.ProjectXML;
import kanbanserver.UserXML;
import ldap.Ldap;
import model.Project;
import model.SimpleProject;
import model.SimpleUser;
import model.User;

public class ServerControl {


	private UserXML userXML;
	private ProjectXML projectXML;
	private User user;

	/**
	 * Called to log in a User by UID name. Calls LDAP function and returns a SimpleUser Object.
	 * @param userName
	 * @return logged in SimpleUser Object.
	 */
	public SimpleUser userLogin(String userName) {
		
		Ldap ldap = new Ldap("ldaps://10.16.1.1:636","ou=accounts,dc=linuxmuster-net,dc=lokal");
		SimpleUser userSimple = new SimpleUser();
		/*
		 * userSimple gets altered to store user first and last name, set in LDAP function.
		 */
		if(ldap.login(userName, userSimple)) {
			user = new User(userName, userSimple.getFirstName() + " " + userSimple.getLastName(), null, null);
		}
		return userSimple;
	}
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public ArrayList<SimpleProject> getProjectsForUserLogin(String userName) {
		
		userXML = new UserXML(System.getProperty("java.class.path") + "\\User\\", userName);
		File f = new File(userXML.getUserXMLDirectory());
		if(f.exists() && !f.isDirectory())
		{
			user = userXML.readUserXML();
		}else {
			userXML.writeUserXML(user);
		}
		if (user != null) {
			//returns dummy list of simpleProjects, dummy is in user.getProjects()
			return (ArrayList<SimpleProject>) user.getProjects();
		}else {
			return null;
		}
	}
	/**
	 * 
	 * @param newProject
	 * @return boolean for successful XML-write
	 */
	public boolean createNewProjectXML(Project newProject) {
		boolean successfulCreate = false;
		newProject.setID(UUID.randomUUID().toString());
		projectXML = new ProjectXML(System.getProperty("java.class.path") + "\\Project\\", newProject.getID());
		successfulCreate = projectXML.writeProjectXML(newProject);
		user.setProjectCurrent(new SimpleProject( newProject.getName(), newProject.getID()));
		user.addProjects(new SimpleProject( newProject.getName(), newProject.getID()));
		userXML.writeUserXML(user);
		return successfulCreate;
	}
	public User getUser() {
		return user;
	}
}
