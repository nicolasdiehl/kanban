package control;

import java.util.List;

import model.*;

public class ClientControl {
	
	private static ClientControl instance = new ClientControl();
	private IProject openProject = new Project("name", "description", 1);
	private List<IProject> sleepingProjects;
	
	
	public static ClientControl getInstance() {
		return instance;
	}
	
	/**
	 * logInUser gets called by LoginButton
	 * 
	 * @return
	 */
	public boolean userLogIn(String name) {
		boolean isLoggedIn = false;
		//Kommunikation mit Server, der dann 
		//LDAP call and log in
		
		//funktion to get list of Projects from XML for that user
		return isLoggedIn;
	}
	
	public boolean projectCreate() {
		boolean isProjectCreated = false;
		//auslesen der ganzen textboxen
		//anlegen der Projekt-XML
		
		return isProjectCreated;
	}
	
	public boolean projectNewTask() {
		boolean isNewTaskCreated = false;
		
		
		
		return isNewTaskCreated;
	}

	public IProject getOpenProject() {
	    return openProject;
	}

	public void setOpenProject(IProject openProject) {
	    this.openProject = openProject;
	}
}
