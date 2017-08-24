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
	static int maxsize = 999999999;
	static int byteread;
	static int current = 0;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		byte[] buffer = new byte[maxsize]; // create buffer with big size
		Socket socket = new Socket("localhost", 9099);
		InputStream is = socket.getInputStream();
		File test = new File("C:\\almost_portable_libgdx\\a5.png");
		test.createNewFile();
		FileOutputStream fos = new FileOutputStream(test);
		BufferedOutputStream out = new BufferedOutputStream(fos);

		current = byteread;
		while ((byteread = is.read(buffer, 0, buffer.length)) != -1) {
			out.write(buffer, 0, byteread);
		}

		out.flush();

		socket.close();
		fos.close();
		is.close();

	}
}