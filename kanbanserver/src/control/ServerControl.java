package control;

import kanbanserver.UserXML;
import ldap.Ldap;
import model.SimpleUser;
import model.User;

public class ServerControl {
	
	private Ldap ldap;
	private UserXML userXML;
	
	
	public boolean userLogin (String userName) {
		boolean isOk = false;
		
		SimpleUser userSimple = new SimpleUser();
		User user;
		isOk = ldap.login(userName, userSimple);
		//User noch irgendwie an Client zurückschicken
		if (!isOk) {
			return isOk;
		}
		userXML = new UserXML(System.getProperty("java.class.path") + "\\User\\", userName);
		user = userXML.readUserXML();
		if (user == null) {
			isOk = false;
		}else {
			
		}
				
		return isOk;
	}
}
