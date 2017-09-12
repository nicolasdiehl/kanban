package control;

import java.util.ArrayList;
import java.util.List;

import model.*;

public class ClientControl {
	
	private static ClientControl instance = new ClientControl();
	private IProject openProject = new Project("title", "description", "me");
	private List<IProject> sleepingProjects;
	private MainApp mainApp;
	private Thread clientThread;
	private KClient kclient;
	
	public static ClientControl getInstance() {
		return instance;
	}
	
	public ClientControl() {
		// Start Client thread?
		System.out.println("starting thread of client");
		kclient = new KClient("local", 6667);
		this.clientThread = new Thread(kclient);
		this.clientThread.start();
	}
	
	/*		===================   LOGIN SECTION START ============*/
	/**
	 * logInUser gets called by LoginButton
	 * starts request to server for full User Names and users projectlist
	 * 
	 * @param 
	 */
	public void userLogIn(String name, MainApp mainApp) {
		System.out.println(name);
		kclient.requestLogIn(name);
		kclient.requestSimpleProjects(name);
		this.mainApp = mainApp;
	}	
	public void simpleUserReturnedFromLogIn(SimpleUser simpleUserObj) {
		mainApp.loginName = simpleUserObj.getFirstName() + " " + simpleUserObj.getLastName();
		System.out.println(mainApp.loginName);
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
