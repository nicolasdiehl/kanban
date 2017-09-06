package kanbanserver;
/**
 * 
 */

import org.w3c.dom.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.User;

/**
 * @author Norman Dettmer
 */
public class UserXML
{
	/**
	 * function to read a user xml file
	 * see:https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
	 */
	public static User readUserXML(String directory, String loginName)
	{
		// create new Directory of the user xml
		String userXMLDirectory = directory + loginName + ".xml";

		// create object of class User, returning at the end of the function
		User userRead = new User(null, null, null, null, null);

		// check if xml file exists
		File file = new File(userXMLDirectory);
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
			File fXmlFile = new File(userXMLDirectory);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(fXmlFile);

			// normalize the XML Structure; >>recommended<<
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("data");

			for (int nodeAtPos = 0; nodeAtPos < nList.getLength(); nodeAtPos++)
			{
				Node data = nList.item(nodeAtPos);
				Element e = (Element) data;

				// get user id
				NodeList userData = doc.getElementsByTagName("user");
				Node uD = userData.item(0);
				Element uData = (Element) uD;
				String uID = uData.getAttribute("uID");
				// set uID in object userRead
				userRead.setUid(uID);

				
				// get name
				String name = e.getElementsByTagName("name").item(0).getTextContent().trim();
				// set name in object userRead
				userRead.setName(name);

				
				// get currentProject
				String currentProject = e.getElementsByTagName("currentProject").item(0).getTextContent().trim();
				// set current project in object userRead
				userRead.setProjectCurrent(currentProject);

				NodeList projectList = doc.getElementsByTagName("project");
				List<String> projectArrayList = new ArrayList<String>();
				for (int i = 0; i < projectList.getLength(); i++)
				{					
					// get project id
					NodeList pList = doc.getElementsByTagName("project");
					Node proj = pList.item(i);
					Element pro = (Element) proj;
					String projectID = pro.getAttribute("pID");
					projectArrayList.add(projectID);
					
				}
				
				userRead.setProjects(projectArrayList);

			}

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

		// return object of class Project
		return userRead;
	}

	/**
	 * function to write a user xml file
	 * see:https://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
	 */
	public static void writeUserXML(User user, String directory, String loginName)
	{

		// directory of the xml file
		String userXMLDirectory = directory + loginName + ".xml"; //"C:\\Projects\\123456789.xml"

		// delete existing xml file
		File file = new File(userXMLDirectory);
		if (file.exists())
		{
			file.delete();
			System.out.println("Alte XML wurde gelöscht");
		}

		try
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root element "data"
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("data");
			doc.appendChild(rootElement);

			// add new element "user" to rootElement "data"
			Element userXML = doc.createElement("user");
			rootElement.appendChild(userXML);

			// set attribute to user element
			userXML.setAttribute("uID", user.getUid());

			// add new element "name" to user
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(user.getName()));
			userXML.appendChild(name);

			// add new element "currentProject" to user
			Element currentProject = doc.createElement("currentProject");
			currentProject.appendChild(doc.createTextNode(user.getProjectCurrent()));
			userXML.appendChild(currentProject);

			// store all authorized projects 
			List<String> aProjects = new ArrayList<String>();
			aProjects = user.getProjects();

			// loop to store every authorized project in xml
			for (int i = 0; i < aProjects.size(); i++)
			{
				// add new element "projectX" to "authorizedProjects"
				Element aProject = doc.createElement("project");
				// set attribute to user element
				aProject.setAttribute("pID", aProjects.get(i));
				userXML.appendChild(aProject);
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(userXMLDirectory));

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
