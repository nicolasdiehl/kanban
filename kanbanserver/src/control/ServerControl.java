package control;

import java.util.ArrayList;

import kanbanserver.UserXML;
import ldap.Ldap;
import model.SimpleProject;
import model.SimpleUser;
import model.User;

public class ServerControl {


	private UserXML userXML;
	
	

	public SimpleUser userLogin(String userName) {
		
		Ldap ldap = new Ldap("","");
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
}
