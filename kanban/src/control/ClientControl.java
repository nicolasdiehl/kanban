package control;

import java.util.ArrayList;
import java.util.List;

import model.*;

public class ClientControl {
	
	private static ClientControl instance = new ClientControl();
	private IProject openProject = new Project("title", "description", "me");
	private List<IProject> sleepingProjects;
	private MainApp mainApp;
	private KClient kclient;
	public static ClientControl getInstance() {
		return instance;
	}
	/*		===================   LOGIN SECTION START ============*/
	/**
	 * logInUser gets called by LoginButton
	 * starts request to server for full User Names and users projectlist
	 * 
	 * @param 
	 */
	public void userLogIn(String name) {
		kclient = new KClient("",0);
		System.out.println(name);
		kclient.requestLogIn(name);
		kclient.requestSimpleProjects(name);
//		this.mainApp = mainApp;
	}	
	public void simpleUserReturnedFromLogIn(SimpleUser simpleUserObj) {
//		mainApp.loginName = simpleUserObj.getFirstName() + " " + simpleUserObj.getLastName();
	}
	
	public void simpleProjectsReturnedFromLogin(ArrayList<SimpleProject> returnedList) {
		
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
