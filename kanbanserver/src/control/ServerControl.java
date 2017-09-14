package control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import kanbanserver.XML;
import ldap.Ldap;
import model.Project;
import model.SimpleProject;
import model.SimpleUser;
import model.User;

public class ServerControl {


	private User user;

	/**
	 * Constructor
	 * @param userName
	 * @return
	 */
	public SimpleUser userLogin(String userName) {
		
		Ldap ldap = new Ldap("ldaps://10.16.1.1:636","ou=accounts,dc=linuxmuster-net,dc=lokal");
		SimpleUser userSimple = new SimpleUser();

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
		
		XML<User> userXML = new XML<User>(System.getProperty("java.class.path") + "\\User\\", userName);
		try
		{
			user = userXML.readXML();
			return (ArrayList<SimpleProject>) user.getProjects();
		}
		catch(IOException e)
		{
			userXML.writeXML(user);
			return null;
		}

//		File f = new File(userXML.getUserXMLDirectory());
//		
//		if(f.exists() && !f.isDirectory())
//		{
//			user = userXML.readUserXML();
//		}else {
//			userXML.writeUserXML(user);
//		}
//		if (user != null) {
//			return (ArrayList<SimpleProject>) user.getProjects();
//		}else {
//			return null;
//		}
	}
	/**
	 * 
	 * @param newProject
	 * @return boolean for successful XML-write
	 */
	public boolean createNewProjectXML(Project newProject) {
		newProject.setID(UUID.randomUUID().toString());
		XML<Project> projectXML = new XML<Project>(System.getProperty("java.class.path") + "\\User\\", newProject.getID());
		return projectXML.writeXML(newProject);
	}
	public User getUser() {
		return user;
	}
}
