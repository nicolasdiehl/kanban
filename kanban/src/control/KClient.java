package control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import model.Project;
import model.SimpleProject;
import model.SimpleUser;
import model.Task;

/**
 * Is (will be) created by the gui-thread and handles communication with the
 * server.
 */
public class KClient implements Runnable {

	private Socket socket = null;
	private ObjectOutputStream objectOutputStream = null;
	private ObjectInputStream objectInputStream = null;
	private Integer port;
	private String url;
	private ClientControl clientControl;
	private Object message;

	public KClient(String url, Integer port) {
		this.port = port;
		this.url = url;
	}

	/**
	 * Transmits a String to the server asking for a list of SimpleProject
	 * Objects.
	 */
	public void requestSimpleProjects(String userName) {
		try {
			System.out.println("Client Message: Sending Request to get SimpleProject 's.");
			objectOutputStream.writeObject("SimpleProjects|" + userName);
		} catch (IOException e) {
			System.err.println("Client Error: sending requestSimpleProjects message.");
			e.printStackTrace();
		}finally {
			System.out.println("SimpleProject request sent");
		}
	}
	public void requestLogIn(String userName) {
		try {
			System.out.println("Client Message: Sending Request to Log in.");
			String sendstring = "Login|" + userName;
			objectOutputStream.writeObject(sendstring);
		} catch (IOException e) {
			System.err.println("Client Error: sending requestSimpleProjects message.");
			e.printStackTrace();
		}finally {
			System.out.println("Login request sent");
		}
	}
	public void sendNewProject(Project project) {
		try {
			System.out.println("Client Message: Sending new project to Server");
			objectOutputStream.writeObject(project);
		} catch (IOException e) {
			System.err.println("Client Error: sending new project message.");
			e.printStackTrace();
		}finally {
			System.out.println("New Project sent");
		}
	}

	/*
	 * Establishes a connection, tries again if fail.
	 */
	public void connectToServer(InetAddress address, int port) {
		// Connecting
		System.out.print("Connecting.");
		while (true) {
			try {
				socket = new Socket(address, port);
				objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				objectInputStream = new ObjectInputStream(socket.getInputStream());
				System.out.println("..OK");
				break;
			} catch (IOException e) {
				System.out.print(".");
			}
		}
	}

	@Override
	public void run() {

		// Setup
		Boolean receivingMessages = true;
		InetAddress address;
		Object currentObject = null;
		clientControl = ClientControl.getInstance();

		// Getting port & url
		while (port == null || url == null) {
			// wait for port and url
		}

		// Getting IP
		while (true) {
			try {
				if (url.equals("local")) {
					// InetAddress address = InetAddress.getLocalHost();
					System.out.println("Client Message: Using localhost as IP");
					address = InetAddress.getByName("127.0.0.1");
					System.out.println("Client Message: IP = " + address.toString());
					break;
				} else {
					System.out.println("Client Message: Using " + url + " as IP");
					address = InetAddress.getByName(url);
					break;
				}
			} catch (UnknownHostException e) {
				// don't print if it fails
			}
		}

		// Connect
		connectToServer(address, port);

		// Example first request
//		System.out.println("Client Message: Requesting list of Projects!");
//		requestSimpleProjects();

//		System.out.println("Client Message: Address:" + address);
//		System.out.println("Client Message: Receiving objects from server.");

		// Listening
		while (receivingMessages) {
			try {
				currentObject = objectInputStream.readObject();
				if (currentObject instanceof ArrayList<?>) {
					System.out.println("Client Message: Received Object is ArrayList.");
					if (((ArrayList<?>) currentObject).get(0) instanceof SimpleProject) {
						System.out.println("Client Message: Objects are SimpleProjects.");
						@SuppressWarnings("unchecked")
						ArrayList<SimpleProject> returnedList = (ArrayList<SimpleProject>) currentObject;
						System.out.println("received " + returnedList.size() + " SimpleObject objects.");
						clientControl.simpleProjectsReturnedFromLogin(returnedList);
					} else if (((ArrayList<?>) currentObject).get(0) instanceof Project) {
						System.out.println("Client Message: Objects are SimpleProject 's.");
						// do some other stuff
					} else if (((ArrayList<?>) currentObject).get(0) instanceof Task) {
						System.out.println("Client Message: Objects are Task 's.");
						// do some other other stuff
					}
				}
				if (currentObject instanceof SimpleUser) {
					System.out.println("Client Message: Object is SimpleUser.");
					clientControl.simpleUserReturnedFromLogIn((SimpleUser)currentObject);
				}
			} catch (IOException | ClassNotFoundException | ClassCastException e) {
				e.printStackTrace();
				System.err.println("Client Error: Error reading socket!");

				// Trying to reconnect
				connectToServer(address, port);
			}
		}

		// Shutting down after end of receivingMessages.
		try {
			System.out.println("Client Message: Connection closing..");
			if (objectOutputStream != null) {
				objectOutputStream.close();
				System.out.println("Client Message: ObjectOutputStream closed!");
			}

			if (objectInputStream != null) {
				objectInputStream.close();
				System.out.println("Client Message: objectInputStream closed!");
			}

			if (socket != null) {
				socket.close();
				System.out.println("Client Message: Socket closed!");
			}

		} catch (IOException ie) {
			System.err.println("Client Error: Error closing clients socket, objectOutputStream or objectInputStream.");
		}
	}
}