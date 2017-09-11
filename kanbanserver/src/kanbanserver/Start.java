package kanbanserver;

import java.io.IOException;

public class Start {
    /* Start the project with Start.java,
     * providing one argument (port) to start as server
     * or two arguments (url and port) to start as client.
     * Use the url local to connect to testserver on same machine.
     */
	public static void main(String[] args) {
		System.out.println("Starting kanbanserver project.");
		if (args.length == 1) {
			System.out.println("Running as server");
			KServer.main(args);
		} else if (args.length == 2) {
			System.out.println("Running as client");
//			try {
//				KClient.main(args);
//			} catch (IOException e) {
//				System.out.println("Start Caught KClient's Exception!");
//				e.printStackTrace();
//			}
		} else
			System.out.println("Exiting, no argments given!");
	}

}
