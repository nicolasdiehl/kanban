package kanbanserver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.w3c.dom.NodeList;

import model.ITask;
import model.Project;

public class ProjectXML
{
	/**
	 * ProjectXML class to read and write a XML
	 * 
	 * An object of this class needs a directory and the project id to generate a file (to find and to store the XML)
	 */
	
	// create the directory file
	private String projectXMLDirectory;

	public ProjectXML(String directory, String pID)
	{
		projectXMLDirectory = directory + pID + ".xml";
	}

	public Project readProjectXML()
	{
		/**
		 * Function to read a project XML
		 * @return an object of class Project
		 */
		
		try
		{
			// essentiall to cast a string into a date
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			// create new file for import
			File file = new File(projectXMLDirectory);
			
			// check if xml file exists
			if (file.exists())
			{
				// Debugging output
				System.out.println("XML wurde gefunden");

				// create new object of type document with the data of the xml
				File fXmlFile = new File(projectXMLDirectory);
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(fXmlFile);

				// normalize the xml structure in the doc object; >>recommended<<
				doc.getDocumentElement().normalize();

				// Nodelist to store every element in root element
				NodeList nList = doc.getElementsByTagName("data");

				// get user id and store it as a string
				NodeList projectData = doc.getElementsByTagName("project");
				Element pData = (Element) projectData.item(0);
				String pID = pData.getAttribute("pID");

				// get elements 
				Element o = (Element) nList.item(0);

				// get project name
				String pName = o.getElementsByTagName("pName").item(0).getTextContent().trim();
				
				// get project description
				String pDescription = o.getElementsByTagName("pDescription").item(0).getTextContent().trim();
						
				// get modified date
				String pMDate = o.getElementsByTagName("mDate").item(0).getTextContent().trim();
				Date mDate = df.parse(pMDate);
				
				// get date of creation
				String pCDate = o.getElementsByTagName("cDate").item(0).getTextContent().trim();
				Date cDate = df.parse(pCDate);

				// create object of class project, returning at the end of the function
				return new Project(pName, pDescription, mDate, cDate, null, null, pID);
				
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
		} catch (ParseException e)
		{
			e.printStackTrace();
		}

		// return null if file wasn't found
		return null;
	}

	public boolean writeProjectXML(Project project)
	{
		/**
		 *  Function to write a project XML
		 * @return boolean indicating if writing was succesfull
		 */
		
		// indicator
		boolean isWritten = false;
		
		// essentiall to cast a date into a string
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		// create new file
		File file = new File(projectXMLDirectory);

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

			// add new element "pName" to project
			Element pName = doc.createElement("pName");
			pName.appendChild(doc.createTextNode(project.getName()));
			projectXML.appendChild(pName);

			// add new element "pDescription" to project
			Element pDescription = doc.createElement("pDescription");
			pDescription.appendChild(doc.createTextNode(project.getDescription()));
			projectXML.appendChild(pDescription);

			// add new element "mDate" to project
			Element mDate = doc.createElement("mDate");
			// cast Date to String
			String reportMDate = df.format(project.getModified());
			// 
			mDate.appendChild(doc.createTextNode(reportMDate));
			projectXML.appendChild(mDate);

			// add new element "cDate" to project
			Element cDate = doc.createElement("cDate");
			// cast Date to String
			String reportCDate = df.format(project.getCreated());
			cDate.appendChild(doc.createTextNode(reportCDate));
			projectXML.appendChild(cDate);

			// store all authorized projects in a list of type SimpleProject
			List<ITask> taskList = project.getTasks();

			// loop to store every task in xml file
			for (int i = 0; i < taskList.size(); i++)
			{
				// add new element "task" to project
				Element task = doc.createElement("task");
				projectXML.appendChild(task);
				
				// add attribute title to task
				task.setAttribute("tName", taskList.get(i).getTitle());
				
				// add new element "description" to task
				Element tDescription = doc.createElement("tDescription");
				tDescription.appendChild(doc.createTextNode(taskList.get(i).getDescription()));
				task.appendChild(tDescription);
				
				// add new element "categorie" to task
				Element tCategorie = doc.createElement("tCategorie");
				tCategorie.appendChild(doc.createTextNode(taskList.get(i).getCategorie()));
				task.appendChild(tCategorie);
				
				// add new element "status" to task
				Element tStatus = doc.createElement("tStatus");
				tStatus.appendChild(doc.createTextNode(taskList.get(i).getStatus()));
				task.appendChild(tStatus);
				
				// add new element "creatorID" to task
				Element tCreatorID = doc.createElement("tCreatorID");
				tCreatorID.appendChild(doc.createTextNode(taskList.get(i).getCreatorID()));
				task.appendChild(tCreatorID);
				
				// add new element "lastCall" to task
				Element tLastCall = doc.createElement("tLastCall");
				
				// cast Date to String
				String reportLastCall = df.format(taskList.get(i).getLastCall());
				tLastCall.appendChild(doc.createTextNode(reportLastCall));
				task.appendChild(tLastCall);
				
				// add new element "dateCreate" to task
				Element tDateCreate = doc.createElement("tDateCreate");
				
				// cast Date to String
				String reportDateCreate = df.format(taskList.get(i).getCreatorDate());
				tDateCreate.appendChild(doc.createTextNode(reportDateCreate));
				task.appendChild(tDateCreate);				
			}
			
//			// store all members in a list of type SimpleProject
//			List<String> memberList = project.getMembersProperty();
//			
//			Element pMembers = doc.createElement("pMembers");
//			projectXML.appendChild(pMembers);
//			
//			// loop to store the memberList in project xml
//			for (int i = 0; i < memberList.size(); i++)
//			{
//				Element mID = doc.createElement("memberID");
//				mID.appendChild(doc.createTextNode(memberList.get(i)));
//				pMembers.appendChild(mID);
//			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(projectXMLDirectory));

			transformer.transform(source, result);

			// change indicator because writing was succesfull
			isWritten = true;

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
