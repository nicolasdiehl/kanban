package kanbanserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import model.Project;
import model.Task;

/**
 * Is (will be) created by the gui-thread and handles communication with the
 * server.
 */
public class KClient {

	static Socket socket = null;
	static ObjectOutputStream objectOutputStream = null;
	static ObjectInputStream objectInputStream = null;

	/**
	 * Transmits a String to the server asking for a list of SimpleProject
	 * Objects.
	 */
	public static void requestSimpleProjects() {
		try {
			System.out.println("Client Message: Sending Request to get SimpleProject 's.");
			objectOutputStream.writeObject("SimpleProjects");
		} catch (IOException e) {
			System.err.println("Client Error: sending requestSimpleProjects message.");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws IOException {

		// Setup
		Boolean receivingMessages = true;
		String url = "";
		InetAddress address;
		Object currentObject = null;

		// Getting CMD arguments & port
		int port = 0;
		if (args.length == 2) {
			url = args[0];
			port = Integer.valueOf(args[1]);
		} else {
			throw new IllegalArgumentException(
					"Client Error: Argument one should be ip/url and argument two should be port.");
		}

		// Getting IP
		if (url.equals("local")) {
			// InetAddress address = InetAddress.getLocalHost();
			System.out.println("Client Message: Using localhost as IP");
			address = InetAddress.getByName("127.0.0.1");
			System.out.println("Client Message: IP = " + address.toString());
		} else {
			System.out.println("Client Message: Using " + url + " as IP");
			address = InetAddress.getByName(url);
		}

		// Connecting
		try {
			socket = new Socket(address, port);
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectInputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Client Message: IO exception at client connecting to server!");
			System.exit(1);
		}

		// Example first request
		System.out.println("Client Message: Requesting list of Projects!");
		requestSimpleProjects();

		System.out.println("Client Message: Address:" + address);
		System.out.println("Client Message: Receiving objects from server.");

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
					} else if (((ArrayList<?>) currentObject).get(0) instanceof Project) {
						System.out.println("Client Message: Objects are SimpleProject 's.");
						// do some other stuff
					} else if (((ArrayList<?>) currentObject).get(0) instanceof Task) {
						System.out.println("Client Message: Objects are Task 's.");
						// do some other other stuff
					}
				}
			} catch (IOException | ClassNotFoundException | ClassCastException e) {
				e.printStackTrace();
				System.err.println("Client Error: Error reading socket!");
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