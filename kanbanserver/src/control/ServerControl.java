package control;

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
	

	public SimpleUser userLogin(String userName) {
		
		Ldap ldap = new Ldap("ldaps://10.16.1.1:636","ou=accounts,dc=linuxmuster-net,dc=lokal");
		SimpleUser userSimple = new SimpleUser();

		ldap.login(userName, userSimple);

		return userSimple;
	}

	public ArrayList<SimpleProject> getProjectsForUserLogin(String userName) {
		User user;
		
		userXML = new UserXML(System.getProperty("java.class.path") + "\\User\\", userName);
		user = userXML.readUserXML();
		if (user != null) {
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
		projectXML = new ProjectXML(System.getProperty("java.class.path") + "\\User\\", newProject.getID());
		successfulCreate = projectXML.writeProjectXML(newProject);
		return successfulCreate;
	}
}
