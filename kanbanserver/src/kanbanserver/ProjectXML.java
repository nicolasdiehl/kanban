package kanbanserver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Project;

public class ProjectXML
{
	public static Project readProjectXML(String directory, String loginName)
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
	
	public static void writeProjectXML(Project project, String directory, String loginName)
	{

		// directory of the xml file
		String projectXMLDirectory = directory + loginName + ".xml"; //"C:\\Projects\\123456789.xml"

		// delete existing xml file
		File file = new File(projectXMLDirectory);
		if (file.exists())
		{
			file.delete();
			System.out.println("Alte XML wurde gelöscht");
		}

		try
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			
//			Element rootElement = doc.createElement("data");
//			doc.appendChild(rootElement);
//
//			// add new element "user" to rootElement "data"
//			Element userXML = doc.createElement("user");
//			rootElement.appendChild(userXML);
//
//			// set attribute to user element
//			userXML.setAttribute("uID", user.getUid());
//
//			// add new element "name" to user
//			Element name = doc.createElement("name");
//			name.appendChild(doc.createTextNode(user.getName()));
//			userXML.appendChild(name);
//
//			// add new element "currentProject" to user
//			Element currentProject = doc.createElement("currentProject");
//			currentProject.appendChild(doc.createTextNode(user.getProjectCurrent()));
//			userXML.appendChild(currentProject);
//
//			// store all authorized projects 
//			List<String> aProjects = new ArrayList<String>();
//			aProjects = user.getProjects();
//
//			// loop to store every authorized project in xml
//			for (int i = 0; i < aProjects.size(); i++)
//			{
//				// add new element "projectX" to "authorizedProjects"
//				Element aProject = doc.createElement("project");
//				// set attribute to user element
//				aProject.setAttribute("pID", aProjects.get(i));
//				userXML.appendChild(aProject);
//			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(projectXMLDirectory));

			transformer.transform(source, result);

			System.out.println("Neue User XMl wurde angelegt");

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
