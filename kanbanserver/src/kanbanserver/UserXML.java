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

import model.SimpleProject;
import model.User;

public class UserXML
{
	/**
	 * UserXML class to read and write a XML
	 * 
	 * An object of this class needs a directory and the project id to generate a file (to find and to store the XML)
	 */
	
	// create the directory file
	private String userXMLDirectory;
	
	public UserXML(String directory, String login)
	{
		userXMLDirectory = directory + login + ".xml";
	}
	
	public String getDirectory()
	{
		return userXMLDirectory;
	}
	

	public User readUserXML()
	{
		/**
		 * Function to read a user XML
		 * @return an object of class User
		 */
		
		try
		{
			// create new file for import
			File file = new File(userXMLDirectory);

			// check if xml file exists
			if (file.exists())
			{
				// Debugging output
				System.out.println("XML wurde gefunden");

				// create new object of type document with the data of the xml
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(file);

				// normalize the xml structure in the doc object; >>recommended<<
				doc.getDocumentElement().normalize();

				// Nodelist to store every element in root element
				NodeList nList = doc.getElementsByTagName("data");

				// get user id and store it as a string
				NodeList userData = doc.getElementsByTagName("user");
				Element uData = (Element) userData.item(0);
				String uID = uData.getAttribute("uID");

				// get elements 
				Element e = (Element) nList.item(0);

				// get user name
				String uName = e.getElementsByTagName("uName").item(0).getTextContent().trim();

				// get current project and store name and id in object of type SimpleProject
				NodeList cProjectData = doc.getElementsByTagName("cProject");
				Element cpData = (Element) cProjectData.item(0);
				SimpleProject currentProject = new SimpleProject(e.getElementsByTagName("cProject").item(0).getTextContent().trim(), cpData.getAttribute("pID"));

				// get Elements of type authorizised projects
				NodeList projectList = doc.getElementsByTagName("aProject");

				// create new ArrayList of type SimpleProject to store every single authorizised project
				ArrayList<SimpleProject> authorizisedProjects = new ArrayList<SimpleProject>();

				// loop to store objects of type SimpleProject
				for (int i = 0; i < projectList.getLength(); i++)
				{
					// get one project
					NodeList pList = doc.getElementsByTagName("aProject");
					Node proj = pList.item(i);
					Element pro = (Element) proj;

					// store data in new object of type SimpleProject in ArrayList authorizisedProjects
					authorizisedProjects.add(new SimpleProject(pro.getTextContent(), pro.getAttribute("pID")));
				}

				// return object of type User with user id, user name, ArrayList containing objects(SimpleObject) of authorizised projects, and an object(SimpleObject) of the current project
				return new User(uID, uName,  authorizisedProjects, currentProject);

			} else
			{
				// Debugging output
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

		// return null if file wasn't found
		return null;
	}

	public boolean writeUserXML(User user)
	{
		/**
		 *  Function to write auser XML
		 * @return boolean indicating if writing was succesfull
		 */
		
		// indicator
		boolean isWritten = false;
		
		// create new file
		File file = new File(userXMLDirectory);
		
		// delete existing xml file
		if (file.exists())
		{
			// delete
			file.delete();

			//Debugging output
			System.out.println("Alte XML wurde gelöscht");
		}
		try
		{
			// create new object of type document to store the object of type User
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			// create root Element in document
			Element rootElement = doc.createElement("data");
			doc.appendChild(rootElement);

			// add new element "user" to root Element "data"
			Element userXML = doc.createElement("user");
			rootElement.appendChild(userXML);

			// set attribute to user element
			userXML.setAttribute("uID", user.getUid());

			// add new element "uName" to user
			Element uName = doc.createElement("uName");
			uName.appendChild(doc.createTextNode(user.getName()));
			userXML.appendChild(uName);

			// add new element "cProject" to user
			Element currentProject = doc.createElement("cProject");
			
			// set attribute to currentProject element
			currentProject.setAttribute("pID", user.getProjectCurrent().getId());
			userXML.appendChild(currentProject);

			// add new element "pName" to currentProject
			Element cpName = doc.createElement("pName");
			cpName.appendChild(doc.createTextNode(user.getProjectCurrent().getName()));
			currentProject.appendChild(cpName);

			// store all authorized projects in a list of type SimpleProject
			List<SimpleProject> aProjects = user.getProjects();

			// loop to store every authorized project in xml
			for (int i = 0; i < aProjects.size(); i++)
			{
				// add new element "aProject" to user
				Element aProject = doc.createElement("aProject");
				// set attribute to user element
				aProject.setAttribute("pID", aProjects.get(i).getId());
				userXML.appendChild(aProject);

				// add new element "pName" to aProject
				Element apName = doc.createElement("pName");
				apName.appendChild(doc.createTextNode(aProjects.get(i).getName()));
				aProject.appendChild(apName);
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(userXMLDirectory));
			
			// change indicator because writing was succesfull
			isWritten = true;

			transformer.transform(source, result);

			// Debugging output
			System.out.println("Neue User XMl wurde angelegt");

		} catch (ParserConfigurationException pce)
		{
			pce.printStackTrace();
			
			// Debugging output
			System.out.println("Schreiben NICHT erfolgreich!");
		} catch (TransformerException tfe)
		{
			tfe.printStackTrace();
			
			// Debugging output
			System.out.println("Schreiben NICHT erfolgreich!");
		}
		
		// return indicator
		return isWritten;
	}
}
