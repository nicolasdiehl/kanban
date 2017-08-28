package control;

//for reading
import org.w3c.dom.*;
import javax.xml.parsers.*;

// for writing
import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import model.Project;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Class XML (read and write a xml file)
 * 
 * @author Norman Dettmer
 * @version 1.0
 */
public class XML
{
	/**
	 * function to read a xml file
	 * 
	 * @param projectID
	 *             => neccesary to find the right xml file
	 */
	public Project readProject(String directory, String ID)
	{
		String pryID = ID;
		String pryDirectory = directory + pryID + ".xml";

		// create object of class Project, returning at the end of the function
		Project project = null;

		try
		{
			// create the DocumentBuilder object
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// create a Document from a file
			Document document = builder.parse(new File(pryDirectory));

			// normalize the XML Structure; important!
			document.getDocumentElement().normalize();

			// get the root Element from XML document
			Element root = document.getDocumentElement();

		} catch (ParserConfigurationException pce)
		{
			pce.printStackTrace();
		} catch (SAXException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		// return object of class Propject
		return project;

	}

	/*
	 * function to create a xml file
	 */
	public void createProjectXML(Project project, String directory, String ID)
	{
		// filename of the xml file (consisting of the ID and the file type)
		String fileName = ID + ".xml";

		// directory of the xml file
		String pryDirectory = directory + fileName; //"C:\\Projects\\123456789.xml"

		// delete existing xml file
		File file = new File(pryDirectory);
		if (file.exists())
		{
			file.delete();
		}

		try
		{
			//
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// create new document
			Document doc = docBuilder.newDocument();

			// create root element
			Element rootElement = doc.createElement("project");
			doc.appendChild(rootElement);
			// set Attribute of root element
			//rootElement.setAttribute("id", "1");

			// add new child "general" to root element
			Element general = doc.createElement("general");
			rootElement.appendChild(general);

			// add project id to "general"
			Element prjID = doc.createElement("prjID");
			prjID.appendChild(doc.createTextNode(project.getID()));
			general.appendChild(prjID);

			// add project titel to "general"
			Element prjTitel = doc.createElement("prjTitel");
			prjTitel.appendChild(doc.createTextNode(project.getName()));
			general.appendChild(prjTitel);

			// add project description to "general"
			Element prjDescription = doc.createElement("prjDescription");
			prjDescription.appendChild(doc.createTextNode(project.getDescription()));
			general.appendChild(prjDescription);

			// add date of creation to "general"
			Element prjDateOfCreation = doc.createElement("prjDateCreation");
			// Convert Date to String
			Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String s = formatter.format(project.getCreated());
			// 
			prjDateOfCreation.appendChild(doc.createTextNode(s));
			general.appendChild(prjDateOfCreation);

			// add date of last editing to "general"
			Element prjDateOfEditing = doc.createElement("prjDateEditing");
			// Convert Date to String
			Format formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String s2 = formatter2.format(project.getCreated());
			//
			prjDateOfEditing.appendChild(doc.createTextNode(s2));
			general.appendChild(prjDateOfEditing);

			// add new child "tasks" to root element
			Element tasks = doc.createElement("tasks");
			rootElement.appendChild(tasks);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(pryDirectory));

		} catch (ParserConfigurationException pce)
		{
			pce.printStackTrace();
		} catch (TransformerException tfe)
		{
			tfe.printStackTrace();
		}
	}

	/**
	 * function to add tasks to xml file
	 */
	public void addTasksToXML()
	{

	}
}
