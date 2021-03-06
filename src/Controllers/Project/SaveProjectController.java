package Controllers.Project;

import java.io.File;
import java.util.*;

import Controllers.*;
import Entities.Deliverable;
import Entities.Project;
import Entities.Resource;
import Entities.ResourceType;
import Entities.Task;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 */
public class SaveProjectController implements Controller  {

    /**
     * 
     */
    public SaveProjectController() {
    }

    /**
     * @param project 
     * @return
     */
    public static File convert(Project project, String fileName) throws NullPointerException {
        // Convert Project to XML format to the specified fileName
    	try {

    		DocumentBuilderFactory xmlDocument = DocumentBuilderFactory.newInstance();
    		DocumentBuilder xmlBuilder = xmlDocument.newDocumentBuilder();
    		Document doc = xmlBuilder.newDocument();
    		
    		Element mainRootProject = doc.createElement("Silk");

    		//***********************************
    		//*		Project Info XML Elements   *
    		//***********************************
    		Element projectInfoElementRoot = doc.createElement("ProjectInfo");
    		doc.appendChild(projectInfoElementRoot);

    		Element projectName = doc.createElement("ProjectName");
    		projectName.appendChild(doc.createTextNode(project.getProjectName()));
    		
    		Element projectAuthor = doc.createElement("ProjectAuthor");
    		projectAuthor.appendChild(doc.createTextNode(project.getProjectAuthor()));
    		
    		Element projectStartTime = doc.createElement("ProjectStartTime");
    		projectStartTime.appendChild(doc.createTextNode(project.getStartTime().toString()));
    		
    		Element projectEndTime = doc.createElement("ProjectEndTime");
    		projectEndTime.appendChild(doc.createTextNode(project.getEndTime().toString()));
    		
    		projectInfoElementRoot.appendChild(projectName);
    		projectInfoElementRoot.appendChild(projectAuthor);
    		projectInfoElementRoot.appendChild(projectStartTime);
    		projectInfoElementRoot.appendChild(projectEndTime);
    		
    		//***************************************
    		//*		Project Resource XML Elements   *
    		//***************************************
    		HashMap<String, Resource> projectResources = project.getResources();
    		
    		Element projectResourceElementsRoot = doc.createElement("ProjectResources");
    		
    		for (String resourceID: projectResources.keySet()) {
    			Resource r = projectResources.get(resourceID);
    			
    			Element prResRoot = doc.createElement("Resource");
    			prResRoot.setAttribute("id", resourceID);
    			
    			Element prResName = doc.createElement("ResourceName");
    			prResName.appendChild(doc.createTextNode(r.getname()));
    			
    			Element prResDailyCost = doc.createElement("ResourceDailyCost");
    			prResDailyCost.appendChild(doc.createTextNode(String.valueOf(r.getDailyCost())));
    			
    			Element prResType = doc.createElement("ResourceType");
    			String resourceType = "";
    			switch (r.getResourceType().toString())
    			{
    				case "labor": resourceType = "labor";
    					break;
    				case "equipment": resourceType = "equipment";
    					break;
    				case "material": resourceType = "material";
    					break;
    			}
    			prResType.appendChild(doc.createTextNode(resourceType));
    			
    			prResRoot.appendChild(prResName);
    			prResRoot.appendChild(prResDailyCost);
    			prResRoot.appendChild(prResType);
    			
    			projectResourceElementsRoot.appendChild(prResRoot);
    		}
    		
    		//***********************************
    		//*		Project Task XML Elements   *
    		//***********************************
    		HashMap<String, Task> projectTasks = project.getTasks();
    		
    		Element projectTaskElementRoot = doc.createElement("ProjectTasks");
    		
    		for (String taskID: projectTasks.keySet()) {
    			Task t = projectTasks.get(taskID);
    			
    			Element prTaskRoot = doc.createElement("Task");
    			prTaskRoot.setAttribute("id", taskID);
    			
    			Element prTaskName = doc.createElement("TaskName");
    			prTaskName.appendChild(doc.createTextNode(t.getName()));
    			
    			Element prTaskDescription = doc.createElement("TaskDescription");
    			prTaskDescription.appendChild(doc.createTextNode(t.getDescription()));
    			
    			Element prTaskDuration = doc.createElement("TaskDuration");
    			prTaskDuration.appendChild(doc.createTextNode(String.valueOf(t.getDuration())));
    			
    			Element prStartTime = doc.createElement("TaskStartTime");
    			prStartTime.appendChild(doc.createTextNode(String.valueOf(t.getStartTime())));
    			
    			Element prEndTime = doc.createElement("TaskEndTime");
    			prEndTime.appendChild(doc.createTextNode(String.valueOf(t.getEndTime())));
    			
    			Element prPercentCompleted = doc.createElement("TaskPercentCompleted");
    			prPercentCompleted.appendChild(doc.createTextNode(String.valueOf(t.getPercentCompleted())));
    			
    			Element prParentTask = doc.createElement("TaskParentTask");
    			if (t.getParent() != null)
    			{
        			prParentTask.appendChild(doc.createTextNode(t.getParent().getID()));
    			}

    			Element prTaskPredRoot = doc.createElement("TaskPredecessors");
    			int index = 1;
    			//Create an Element for each Predecessor
    			for (Task pred: t.getPredecessors().values())
    			{
    				Element prTaskPred = doc.createElement("TaskPredecessor");
    				prTaskPred.setAttribute("id", pred.getID());
    				prTaskPred.appendChild(doc.createTextNode(pred.getName()));
    				
    				prTaskPredRoot.appendChild(prTaskPred);
    				index++;
    			}
    			
    			Element prTaskDeliverableRoot = doc.createElement("TaskDeliverables");
    			index = 1;
    			for (Deliverable d: t.getDeliverables())
    			{
    				Element prTaskDeliverable = doc.createElement("TaskDeliverable");
    				prTaskDeliverable.setAttribute("id", String.valueOf(index));
    				
    				Element prTaskDeliverableName = doc.createElement("TaskDeliverableName");
    				prTaskDeliverableName.appendChild(doc.createTextNode(d.getDeliverableName()));
    				
    				Element prTaskDeliverableType = doc.createElement("TaskDeliverableType");
    				String deliverableType = "";
    				switch (d.getDeliverableType().toString())
    				{
    					case "file": deliverableType = "file";
    								break;
    					case "presentation": deliverableType = "presentation";
    										break;
						
    				}
    				prTaskDeliverable.appendChild(doc.createTextNode(deliverableType));
    				
    				
    				prTaskDeliverable.appendChild(prTaskDeliverableName);
    				prTaskDeliverable.appendChild(prTaskDeliverableType);
    				
    				prTaskDeliverableRoot.appendChild(prTaskDeliverable);
    				index++;
    			}
    			
    			Element prTaskResourcesRoot = doc.createElement("TaskResources");
    			index = 1;
    			for (Resource r: t.getRequiredResources().values())
    			{
    				Element prTaskResource = doc.createElement("TaskResource");
    				prTaskResource.setAttribute("id", r.getResourceID());
    				prTaskResource.appendChild(doc.createTextNode(r.getname()));
    				
    				prTaskResourcesRoot.appendChild(prTaskResource);
    				index++;
    			}
    			
    			Element prTaskChildrenRoot = doc.createElement("TaskChildren");
    			index = 1;
    			for (Task child: t.getChildren().values())
    			{
    				Element prTaskChild = doc.createElement("TaskChild");
    				prTaskChild.setAttribute("id", child.getID());
    				prTaskChild.appendChild(doc.createTextNode(child.getName()));
    				
    				prTaskChildrenRoot.appendChild(prTaskChild);
    				index++;
    			}
    			
    			prTaskRoot.appendChild(prTaskName);
    			prTaskRoot.appendChild(prTaskDescription);
    			prTaskRoot.appendChild(prTaskDuration);
    			prTaskRoot.appendChild(prStartTime);
    			prTaskRoot.appendChild(prEndTime);
    			prTaskRoot.appendChild(prPercentCompleted);
    			prTaskRoot.appendChild(prParentTask);
    			prTaskRoot.appendChild(prTaskPredRoot);
    			prTaskRoot.appendChild(prTaskDeliverableRoot);
    			prTaskRoot.appendChild(prTaskResourcesRoot);
    			prTaskRoot.appendChild(prTaskChildrenRoot);
    			
    			projectTaskElementRoot.appendChild(prTaskRoot);
    		}

    		mainRootProject.appendChild(projectInfoElementRoot);
    		mainRootProject.appendChild(projectResourceElementsRoot);
    		mainRootProject.appendChild(projectTaskElementRoot);
    		
    		doc.appendChild(mainRootProject);
    		
    		// write the content into xml file
    		TransformerFactory transformerFactory = TransformerFactory.newInstance();
    		Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
    		DOMSource source = new DOMSource(doc);
    		StreamResult result = new StreamResult(new File(fileName + ".xml"));
    		//System.out.println("File saved!");

    		
    		// Output to console for testing
    		//StreamResult result = new StreamResult(System.out);
    		transformer.transform(source, result);


    	  } catch (ParserConfigurationException pce) {
    		pce.printStackTrace();
    	  } catch (TransformerException tfe) {
    		tfe.printStackTrace();
    	  }
    	
        return null;
    }

	
	public void executeSaveProject(String fileName) {
		convert(project, fileName);
	}
/*
	public static final void main(String argv[])
	{
		convert(project, "testing");
	}
*/
}