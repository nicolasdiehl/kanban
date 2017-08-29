package kanbanserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class KServer {
	public static void main(String args[]) {

		int port = 0;
		if (args.length == 1) {
			port = Integer.valueOf(args[0]);
		} else {
			throw new IllegalArgumentException("Argument one should be the port.");
		}

		Socket socket = null;
		ServerSocket serverSocket = null;
		System.out.println("Server waiting for connection on port " + port + ".");
		try {
			serverSocket = new ServerSocket(port);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Server error!");
		}

		while (true) {
			try {
				socket = serverSocket.accept();
				System.out.println("Connection OK!");
				ServerThread serverThread = new ServerThread(socket);
				serverThread.start();

			}

			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Connection error!");

			}
		}

	}

}

class ServerThread extends Thread {

	String line = null;
	BufferedReader inputStreamReader = null;
	PrintWriter outputStream = null;
	Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			inputStreamReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outputStream = new PrintWriter(socket.getOutputStream());

		} catch (IOException e) {
			System.out.println("IO error in server thread");
		}

		try {
			line = inputStreamReader.readLine();
			while (line.compareTo("QUIT") != 0) {

				outputStream.println("lol echo: " + line);
				outputStream.flush();
				System.out.println("Response to Client is: " + line);
				line = inputStreamReader.readLine();
			}
		} catch (IOException e) {

			line = this.getName();
			System.out.println("IO error, client " + line + " terminated too early");
		} catch (NullPointerException e) {
			line = this.getName();
			System.out.println("Client " + line + " closed");
		}

		finally {
			try {
				System.out.println("Connection Closing..");
				if (inputStreamReader != null) {
					inputStreamReader.close();
					System.out.println("Socket input stream reader closed!");
				}

				if (outputStream != null) {
					outputStream.close();
					System.out.println("Socket output stream closed!");
				}

				if (socket != null) {
					socket.close();
					System.out.println("Socket closed!");
				}

			} catch (IOException ie) {
				System.out.println("Error closing socket, input stream reader or output stream.");
			}
		}
	}
}