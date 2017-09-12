package control;

import java.util.ArrayList;
import java.util.List;

import model.*;

public class ClientControl {
	
	private static ClientControl instance = new ClientControl();
	private IProject openProject = new Project("title", "description", "me");
	private List<IProject> sleepingProjects;
	private ArrayList<SimpleProject> receivedSimpleProjects = new ArrayList<SimpleProject>();
	private MainApp mainApp;
	private Thread clientThread;
	private KClient kclient;
	private User user;
//	private ArrayList<SimpleProject> simpleProjects;
	
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
		user = new User(name, "", null, null);
		System.out.println(name);
		kclient.requestLogIn(name);
		kclient.requestSimpleProjects(name);
		this.mainApp = mainApp;
	}	
	public void simpleUserReturnedFromLogIn(SimpleUser simpleUserObj) {
		user.setName(simpleUserObj.getFirstName() + " " + simpleUserObj.getLastName());
		mainApp.loginName = user.getName();
		System.out.println(mainApp.loginName);
	}
	
	public void simpleProjectsReturnedFromLogin(ArrayList<SimpleProject> returnedList) {
		user.setProjects(returnedList);
	}
	
	public List<SimpleProject> getSimpleProjects() {
		return user.getProjects();
	}

	public boolean projectCreate(Project projectNew) {
		boolean isProjectCreated = false;
		//anlegen der Projekt-XML
		kclient.sendNewProject(projectNew);
		
		return isProjectCreated;
	}
	
	public User getUser() {
		return user;
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

	public ArrayList<SimpleProject> getReceivedSimpleProjects() {
		return receivedSimpleProjects;
	}

	public void setReceivedSimpleProjects(ArrayList<SimpleProject> receivedSimpleProjects) {
		this.receivedSimpleProjects = receivedSimpleProjects;
	}
}
