package control;

import kanbanserver.UserXML;
import ldap.Ldap;
import model.SimpleUser;

public class ServerControl {
	
	private Ldap ldap;
	private UserXML userXML;
	
	
	public boolean userLogin (String userName) {
		boolean isOk = false;
		
		SimpleUser user = new SimpleUser();
		
		isOk = ldap.login(userName, user);
		//User noch irgendwie an Client zurückschicken
		if (userXML.readUserXML(System.getProperty("java.class.path") + "\\User\\", userName) == null) {
			isOk = false;
		}
		
		
		return isOk;
	}
}
