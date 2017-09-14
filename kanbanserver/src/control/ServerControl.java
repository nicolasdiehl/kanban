package control;

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
			user = new User(userName, userSimple.getFirstName() + " " + userSimple.getLastName(), new ArrayList<SimpleProject>(), null);
		}
		return userSimple;
	}
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public ArrayList<SimpleProject> getProjectsForUserLogin(String userName) {
		
//		XML<User> userXML = new XML<User>(System.getProperty("java.class.path") + "\\User\\", userName);
		XML<User> userXML = new XML<User>("D:\\kanban\\User\\", userName);
		try
		{
			user = userXML.readXML();
			return (ArrayList<SimpleProject>) user.getProjects();
		}
		catch(IOException e)
		{
//			e.printStackTrace();
			userXML.writeXML(user);
			return new ArrayList<SimpleProject>();
		}
	}
	/**
	 * 
	 * @param newProject
	 * @return boolean for successful XML-write
	 */
	public boolean createNewProjectXML(Project newProject) {
		newProject.setID(UUID.randomUUID().toString());
		XML<Project> projectXML = new XML<Project>("D:\\kanban\\Project\\", newProject.getID());
		SimpleProject simpleNewProject = new SimpleProject(newProject.getName(), newProject.getID());
		user.setProjectCurrent(simpleNewProject);
		user.addProjects(simpleNewProject);
		XML<User> userXml = new XML<User>("D:\\kanban\\User\\", user.getUid());
		userXml.writeXML(user);
		return projectXML.writeXML(newProject);
	}
	public User getUser() {
		return user;
	}
}
