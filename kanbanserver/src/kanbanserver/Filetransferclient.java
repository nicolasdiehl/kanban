package kanbanserver;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Filetransferclient {
	static Socket socket = null;
	// static int maxsize = 999999999;
	static int maxsize = 1;
	static int byteread;
	static int current = 0;

	public static void main(String[] args) throws FileNotFoundException, IOException {

		// create buffer with big size
		byte[] buffer = new byte[maxsize];

		// create socket with ip&port
		Socket socket = new Socket("localhost", 9099);

		// create inputstream
		InputStream inputStream = socket.getInputStream();

		// set file target
		File test = new File("C:\\almost_portable_libgdx\\a5.png");
		test.createNewFile();

		// create buffered output stream
		FileOutputStream fileOutputStream = new FileOutputStream(test);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

		// as long as there are bytes to read
		while ((byteread = inputStream.read(buffer, 0, buffer.length)) != -1) {
			// write bytes to output
			bufferedOutputStream.write(buffer, 0, byteread);
		}

		bufferedOutputStream.flush();

		socket.close();
		fileOutputStream.close();
		inputStream.close();

	}
}