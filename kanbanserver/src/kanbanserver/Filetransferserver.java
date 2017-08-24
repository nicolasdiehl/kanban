package kanbanserver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Filetransferserver {
	static ServerSocket receiver = null;
	static OutputStream out = null;
	static Socket socket = null;
	static File myFile = new File("C:\\almost_portable_libgdx\\a4.png");
	/* static int count; */
	static byte[] buffer = new byte[(int) myFile.length()];

	public static void main(String[] args) throws IOException {
		receiver = new ServerSocket(9099);
		socket = receiver.accept();
		System.out.println("Accepted connection from : " + socket);
		FileInputStream fis = new FileInputStream(myFile);
		BufferedInputStream in = new BufferedInputStream(fis);
		in.read(buffer, 0, buffer.length);
		out = socket.getOutputStream();
		System.out.println("Sending files");
		out.write(buffer, 0, buffer.length);
		out.flush();
		/*
		 * while ((count = in.read(buffer)) > 0){ out.write(buffer,0,count);
		 * out.flush(); }
		 */
		out.close();
		in.close();
		socket.close();
		System.out.println("Finished sending");

	}

}