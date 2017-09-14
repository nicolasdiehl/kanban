package kanbanserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Main thread of the server. Handles xml and storage of data.
 */
public class KServer {

	static List<ServerThread> serverThreads = Collections.synchronizedList(new ArrayList<ServerThread>());

	public static void main(String args[]) {

		// Getting port
		int port = 0;
		if (args.length == 1) {
			port = Integer.valueOf(args[0]);
		} else {
			throw new IllegalArgumentException("Server Error: Argument one should be the port.");
		}

		// Waiting for clients
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
					synchronized (serverThreads) {
						serverThreads.add(serverThread);
					}

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
			// the servers main thread.
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