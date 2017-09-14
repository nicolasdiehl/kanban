package kanbanserver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import model.Project;
import model.SimpleProject;
import model.Task;
import model.User;

public class XML<T>
{

	// create the directory file
	private String directoryXML;

	public XML(String directory, String fileName)
	{
		directoryXML = directory + fileName + ".xml";
	}
	
	/**
	 * creates and initializes a XStream object
	 * 
	 * @return
	 */
	private XStream createXStream()
	{
		XStream xstream = new XStream(new DomDriver());

		// use classes as template
		xstream.alias("User", User.class);
		xstream.alias("Task", Task.class);
		xstream.alias("Project", Project.class);
		xstream.alias("SimpleProject", SimpleProject.class);
		
		return xstream;
	}

	/**
	 * writes an object to a XML file
	 * 
	 * @param Object
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public boolean writeXML(T Object)
	{
		XStream xstream = createXStream();

		String xml = xstream.toXML(Object);

		PrintWriter writer;
		
		try
		{
			writer = new PrintWriter(directoryXML, "UTF-8");
			writer.println(xml);
			writer.close();
		} 
		
		// can not happen (because new file will be created)
		catch (FileNotFoundException | UnsupportedEncodingException e)
		{
			return false;
		}
		
		return true;
	}

	/**
	 * creates an object of a XML file
	 * 
	 * @return
	 * @throws IOException
	 */
	public T readXML() throws IOException
	{
		FileReader reader = new FileReader(directoryXML);
		Path path = Paths.get(directoryXML);
		String xml = new String(Files.readAllBytes(path));

		XStream xstream = createXStream();
		
		T object = (T)xstream.fromXML(xml);
		
		return object;
	}
}
