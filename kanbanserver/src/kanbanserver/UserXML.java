package kanbanserver;

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

		try
		{
			//
			File fXmlFile = new File(userXMLDirectory);

			// check if xml file exists
			if (fXmlFile.exists())
			{
				System.out.println("XML wurde gefunden");

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(fXmlFile);

				// normalize the XML Structure; >>recommended<<
				doc.getDocumentElement().normalize();

				NodeList nList = doc.getElementsByTagName("data");
				
				// get user id
				NodeList userData = doc.getElementsByTagName("user");
				Element uData = (Element) userData.item(0);
				String uID = uData.getAttribute("uID");

				for (int nodeAtPos = 0; nodeAtPos < nList.getLength(); nodeAtPos++)
				{
					Element e = (Element) nList.item(nodeAtPos);
	
					// get name
					String name = e.getElementsByTagName("name").item(0).getTextContent().trim();
	
					// get currentProject
					SimpleProject currentProject = new SimpleProject(e.getElementsByTagName("currentProject").item(0).getTextContent().trim(), "");

					NodeList projectList = doc.getElementsByTagName("project");
					List<SimpleProject> projectArrayList = new ArrayList<SimpleProject>();
					for (int i = 0; i < projectList.getLength(); i++)
					{
						// get project id
						NodeList pList = doc.getElementsByTagName("project");
						Node proj = pList.item(i);
						Element pro = (Element) proj;
						String projectID = pro.getAttribute("pID");
						projectArrayList.add(new SimpleProject(projectID, ""));
					}
					
					return new User (uID, name, "", projectArrayList, currentProject);
				}
			} else
			{
				System.out.println("XML wurde NICHT gefunden");
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
		return null;
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
			currentProject.appendChild(doc.createTextNode(user.getProjectCurrent().getId()));
			userXML.appendChild(currentProject);

			// store all authorized projects 
			List<SimpleProject> aProjects = user.getProjects();

			// loop to store every authorized project in xml
			for (int i = 0; i < aProjects.size(); i++)
			{
				// add new element "projectX" to "authorizedProjects"
				Element aProject = doc.createElement("project");
				// set attribute to user element
				aProject.setAttribute("pID", aProjects.get(i).getId());
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
