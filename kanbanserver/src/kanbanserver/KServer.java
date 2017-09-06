package kanbanserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class KServer {
	public static ArrayList<SimpleProject> listOfObjects;

	public static void requestSimpleProjects() {

	}

	public static void main(String args[]) {
		listOfObjects = new ArrayList<SimpleProject>();
		listOfObjects.add(new SimpleProject("name", "id"));
		listOfObjects.add(new SimpleProject("name2", "id2"));
		listOfObjects.add(new SimpleProject("name3", "id3"));
		int port = 0;
		if (args.length == 1) {
			port = Integer.valueOf(args[0]);
		} else {
			throw new IllegalArgumentException("Server Error: Argument one should be the port.");
		}

		Socket connectionSocket = null;
		ServerSocket serverSocket = null;
		System.out.println("Server Message: Waiting for connection on port " + port + ".");
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				try {
					connectionSocket = serverSocket.accept();
					System.out.println("Server Message: Connection OK!");
					ServerThread serverThread = new ServerThread(connectionSocket);
					serverThread.start();

				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Server Error: Connection accept error!");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Server Error: New Server socket Error!");
		} finally {
			// Finally runs after the "endless while" for now, no need to stop
			// the server.
			if (serverSocket != null) {
				try {
					serverSocket.close();
					System.out.println("Server Message: serverSocket closed!");
				} catch (IOException ie) {
					System.out.println("Server Error: Error closing serverSocket.");
				}
			}
		}
	}
}

class ServerThread extends Thread {

	Boolean receivingMessages = true;
	Object currentObject = null;
	ObjectOutputStream objectOutputStream = null;
	ObjectInputStream objectInputStream = null;
	Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void sendSimpleProjects() {
		try {
			System.out.println("Server Message: Sending SimpleProject 's.");
			objectOutputStream.writeObject(KServer.listOfObjects);
		} catch (IOException e) {
			System.err.println("Server Error: Error sending list of simple objects to client.");
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectInputStream = new ObjectInputStream(socket.getInputStream());

		} catch (IOException e) {
			System.err.println("Server Error: IO error in server thread");
		}

		while (receivingMessages) {
			try {
				currentObject = objectInputStream.readObject();
				if (currentObject instanceof String) {
					System.out.println("Server Message: Received String Object");
					if (currentObject.equals("SimpleProjects")) {
						System.out.println("Server Message: Client is requesting SimpleProject 's");
						sendSimpleProjects();
					}
				} else {
					System.err.println("Server Error: Received object is not a String!?");
				}
			} catch (IOException | ClassNotFoundException | ClassCastException e) {
				// e.printStackTrace();
				// System.err.println("Error reading socket!");
			}
		}

		try {
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
		}
	}
}