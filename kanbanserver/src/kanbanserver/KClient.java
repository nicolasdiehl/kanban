package kanbanserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class KClient {

	public static void main(String args[]) throws IOException {

		String url = "";
		int port = 0;
		if (args.length == 2) {
			url = args[0];
			port = Integer.valueOf(args[1]);
		} else {
			throw new IllegalArgumentException("Argument one should be ip/url and argument two should be port.");
		}

		// InetAddress address = InetAddress.getLocalHost();
		InetAddress address = InetAddress.getByName(url);
		// InetAddress address =
		// InetAddress.getByName("http://www.ekamer.ddns.net");
		// InetAddress address = InetAddress.getByName(new
		// URL("http://www.ekamer.ddns.net").getHost());
		Socket socket = null;
		String line = null;
		BufferedReader bufferedReader = null;
		BufferedReader inputStream = null;
		PrintWriter outputStream = null;

		try {
			socket = new Socket(address, port);
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outputStream = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.err.print("IO exception!");
		}

		System.out.println("Client address:" + address);
		System.out.println("Enter data to echo Server (enter quit to exit):");

		String response = null;
		try {
			line = bufferedReader.readLine();
			while (line.compareTo("quit") != 0) {
				outputStream.println(line);
				outputStream.flush();
				response = inputStream.readLine();
				System.out.println("Server response:" + response);
				line = bufferedReader.readLine();

			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error reading socket!");
		} finally {

			inputStream.close();
			outputStream.close();
			bufferedReader.close();
			socket.close();
			System.out.println("Connection closed!");

		}

	}
}