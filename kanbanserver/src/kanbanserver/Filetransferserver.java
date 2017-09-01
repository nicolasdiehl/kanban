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
	static OutputStream outputStream = null;
	static Socket socket = null;
	static File file = new File("C:\\almost_portable_libgdx\\a4.png");
	static byte[] buffer = new byte[(int) file.length()];

	public static void main(String[] args) throws IOException {
		receiver = new ServerSocket(9099);
		socket = receiver.accept();
		System.out.println("Accepted connection from : " + socket);
		FileInputStream fileInputStream = new FileInputStream(file);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		bufferedInputStream.read(buffer, 0, buffer.length);
		outputStream = socket.getOutputStream();
		System.out.println("Sending files");
		outputStream.write(buffer, 0, buffer.length);
		outputStream.flush();
		/*
		 * while ((count = in.read(buffer)) > 0){ out.write(buffer,0,count);
		 * out.flush(); }
		 */
		outputStream.close();
		bufferedInputStream.close();
		socket.close();
		System.out.println("Finished sending");

	}

}