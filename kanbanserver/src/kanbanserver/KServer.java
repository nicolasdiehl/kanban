package kanbanserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class KServer {
	public static ArrayList<SimpleObject> listOfObjects;

	public static void main(String args[]) {
		listOfObjects = new ArrayList<SimpleObject>();
		listOfObjects.add(new SimpleObject("name", "id"));
		listOfObjects.add(new SimpleObject("name2", "id2"));
		listOfObjects.add(new SimpleObject("name3", "id3"));
		int port = 0;
		if (args.length == 1) {
			port = Integer.valueOf(args[0]);
		} else {
			throw new IllegalArgumentException("Argument one should be the port.");
		}
		
		Socket connectionSocket = null;
		ServerSocket serverSocket = null;
		System.out.println("Server waiting for connection on port " + port + ".");
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				try {
					connectionSocket = serverSocket.accept();
					System.out.println("Connection OK!");
					ServerThread serverThread = new ServerThread(connectionSocket);
					serverThread.start();
	
				}
				catch (Exception e) {
					e.printStackTrace();
					System.out.println("Connection error!");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Server error!");
		}
		finally {
			// Finally runs after the "endless while" for now, no need to stop the server.
			if (serverSocket != null) {
				try {
					serverSocket.close();
				    System.out.println("serverSocket closed!");
				} catch (IOException ie) {
					System.out.println("Error closing serverSocket.");
				}
			}
		}
	}
}

class ServerThread extends Thread {

    ObjectOutputStream objectOutputStream = null;
    ObjectInputStream objectInputStream = null;
	Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
		    objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		    objectInputStream = new ObjectInputStream(socket.getInputStream());

		} catch (IOException e) {
			System.out.println("IO error in server thread");
		}

		try {
			objectOutputStream.writeObject(KServer.listOfObjects);
		} catch (IOException e) {
            e.printStackTrace();
            System.err.print("IO exception server!");
		} finally {
			try {
				System.out.println("Server connection closing..");
				if (objectOutputStream != null) {
					objectOutputStream.close();
					System.out.println("objectOutputStream closed!");
				}

				if (objectInputStream != null) {
					objectInputStream.close();
					System.out.println("objectInputStream closed!");
				}

				if (socket != null) {
					socket.close();
					System.out.println("Socket closed!");
				}

			} catch (IOException ie) {
				System.out.println("Error closing servers socket, objectOutputStream or objectInputStream.");
			}
		}
	}
}