package kanbanserver;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.Project;

public class ProjectXML
{
	private String projectXMLDirectory;

	public ProjectXML(String directory, String pID)
	{
		projectXMLDirectory = directory + pID + ".xml";
	}

	public Project readProjectXML(String directory, String loginName)
	{
		// create new Directory of the project xml
		String projectXMLDirectory = directory + loginName + ".xml";

		// create object of class project, returning at the end of the function
		Project projectRead = new Project(null, null, null, null, null, null, null);

		// check if xml file exists
		File file = new File(projectXMLDirectory);
		if (file.exists())
		{
			System.out.println("XML wurde gefunden");
		} else
		{
			System.out.println("XML wurde NICHT gefunden");
		}

		try
		{
			//
			File fXmlFile = new File(projectXMLDirectory);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(fXmlFile);

			// normalize the XML Structure; >>recommended<<
			doc.getDocumentElement().normalize();

		} catch (ParserConfigurationException pce)
		{
			pce.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (org.xml.sax.SAXException e)
		{
			e.printStackTrace();
		}

		return projectRead;
	}

	public void writeProjectXML(Project project)
	{
		// create new file
		File file = new File(projectXMLDirectory);

		// delete existing xml file
		if (file.exists())
		{
			file.delete();

			//Debugging output
			//System.out.println("Alte XML wurde gelöscht");
		}

		try
		{
			// create new object of type document to store the object of type Project
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			// create root Element in document
			Element rootElement = doc.createElement("data");
			doc.appendChild(rootElement);

			// add new element "project" to root Element "data"
			Element projectXML = doc.createElement("project");
			rootElement.appendChild(projectXML);

			// set attribute to project element
			projectXML.setAttribute("pID", project.getID());

			// add new element "pName" to user
			Element pName = doc.createElement("pName");
			pName.appendChild(doc.createTextNode(project.getName()));
			projectXML.appendChild(pName);

			// add new element "pName" to user
			Element projName = doc.createElement("pName");
			projName.appendChild(doc.createTextNode(project.getName()));
			projectXML.appendChild(projName);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(projectXMLDirectory));

			transformer.transform(source, result);

			System.out.println("Neue Project XMl wurde angelegt");

		} catch (ParserConfigurationException pce)
		{
			pce.printStackTrace();
			System.out.println("Schreiben NICHT erfolgreich!");
		} catch (TransformerException tfe)
		{
			tfe.printStackTrace();
			System.out.println("Schreiben NICHT erfolgreich!");
		}
	}
}
