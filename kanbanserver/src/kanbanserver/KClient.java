package kanbanserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

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
        InetAddress address;
        if (url.equals("local")) {
            // InetAddress address = InetAddress.getLocalHost();
            address = InetAddress.getByName("127.0.0.1");
            System.out.println(address.toString());
        } else {
            address = InetAddress.getByName(url);
        }
        Socket socket = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        
        try {
            socket = new Socket(address, port);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.print("IO exception at client!");
        }

        System.out.println("Client address:" + address);
        System.out.println("Receiving objects from server.");

        try {
            @SuppressWarnings("unchecked")
			ArrayList<SimpleObject> returnedList = (ArrayList<SimpleObject>) objectInputStream.readObject();
            System.out.println("received " + returnedList.size() + " SimpleObject objects.");
            socket.close();
        } catch(IOException | ClassNotFoundException | ClassCastException e) {
        	e.printStackTrace();
            System.out.println("Error reading socket!");
        } finally {
    			try {
    				System.out.println("Client connection closing..");
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
    				System.out.println("Error closing clients socket, objectOutputStream or objectInputStream.");
    			}
    		}
    }
}