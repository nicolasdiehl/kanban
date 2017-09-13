package kanbanserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Pattern;

import control.ServerControl;
import model.Project;
import model.SimpleProject;
import model.SimpleUser;

/*
 * One server thread per client, handles communication.
 */
class ServerThread extends Thread {

	ServerControl serverControl;
	Boolean receivingMessages = true;
	Object currentObject = null;
	ObjectOutputStream objectOutputStream = null;
	ObjectInputStream objectInputStream = null;
	Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	/**
	 * For now, returns dummy objects. Will get a list from the xml or from projects
	 * that are created from the xml.
	 * @param userName
	 */
	public void sendSimpleProjects(String userName) {
		try {
			System.out.println("Server Message: Sending SimpleProject 's.");
			ArrayList<SimpleProject> projectList = serverControl.getProjectsForUserLogin(userName);
			serverControl.getUser().setProjects(projectList);
			objectOutputStream.writeObject(projectList);
		} catch (IOException e) {
			System.err.println("Server Error: Error sending list of simple objects to client.");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param userName
	 */
	public boolean sendSimpleUser(String userName) {
		boolean successfulLdap = false;
		try {
			System.out.println("Server Message: Sending SimpleUser.");
			SimpleUser simpleUser = serverControl.userLogin(userName);
			if (simpleUser.getFirstName() != null) {
				successfulLdap = true;
			}
			objectOutputStream.writeObject(simpleUser);
			// objectOutputStream.writeObject(serverControl.userLogin(userName));
		} catch (IOException e) {
			System.err.println("Server Error: Error sending SimpleUser object to client.");
			e.printStackTrace();
		}
		return successfulLdap;
	}

	/**
	 * 
	 * @param str
	 */
	public void sendString(String str) {
		try {
			System.out.println("Server Message: Sending string " + str + " to client.");
			objectOutputStream.writeObject(str);
		} catch (IOException e) {
			System.err.println("Server Error: Error sending string object to client.");
			e.printStackTrace();
		}
	}

	public void run() {
		serverControl = new ServerControl();
		// Setup streams
		try {
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectInputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.err.println("Server Error: IO error in server thread");
		}

		// Listen for messages
		while (receivingMessages) {
			try {
				currentObject = objectInputStream.readObject();
				if (currentObject instanceof String) {
					System.out.println("Server Message: Received String Object");
					String switchString = (String) currentObject;
					String[] switchArray = switchString.split(Pattern.quote("|"));
					System.out.println(switchArray[0]);
					if (switchArray.length == 2) {
						switchString = switchArray[0];
					}
					switch (switchString) {
					// case "SimpleProjects":
					// System.out.println("Server Message: Client is requesting SimpleProject 's");
					// sendSimpleProjects();
					case "Login":
						System.out.println("Server Message: Client is requesting SimpleProject 's");
						if(sendSimpleUser(switchArray[1])) {							
							sendSimpleProjects(switchArray[1]);
						}
						break;
					default:
						break;
					}
				} else if (currentObject instanceof Project) {
					Project project = (Project) currentObject;
					if (project.getID().equals("-1")) {
						// neues XML anlegen
						if (serverControl.createNewProjectXML((Project) currentObject)) {
							
						}
					} else {
						// XML raussuchen und updaten
					}
				} else {

					System.err.println("Server Error: Received object is not a String!?");
				}
			} catch (IOException | ClassNotFoundException | ClassCastException e) {
				// TODO: Connection lost, killing thread (kill, save data??,
				// reconnect, how to handle a broken connection?)
				System.err.println("Server Error: Lost connection to client.");
				receivingMessages = false;
			}
		}

		// Shutting down after end of receivingMessages.

		// TODO: Killing thread on lost connection as for now. When
		// reconnecting, a client will get a new thread. How to reconnect client
		// to old thread?
		try

		{
			System.out.println("Server Message: Server connection closing..");
			if (objectOutputStream != null) {
				objectOutputStream.close();
				System.out.println("Server Message: ObjectOutputStream closed!");
			}

			if (objectInputStream != null) {
				objectInputStream.close();
				System.out.println("Server Message: objectInputStream closed!");
			}

			if (socket != null) {
				socket.close();
				System.out.println("Server Message: Socket closed!");
			}

		} catch (IOException ie) {
			System.out.println("Server Error: Error closing Server socket, objectOutputStream or objectInputStream.");
		} finally {

			// Removing ServerThread from list.
			synchronized (KServer.serverThreads) {
				KServer.serverThreads.remove(this);
			}
		}
	}
}
