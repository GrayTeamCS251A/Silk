package Controllers.Project;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import AnalysisModel.Boundaries.Panels.Oracle;
import Controllers.Controller;
import Entities.Deliverable;
import Entities.ResourceType;
import Entities.Task;

/**
 * 
 */
public class LoadProjectController implements Controller {

    /**
     * 
     */
    public LoadProjectController() {
    }

    /**
     * @param file
     */
    private void parse(File projectFile) {
		//XML grab the project info
		//project.updateInfo(projectName, startTime, projectAuthor);
		
		//XML grab the project other stuff
		//project.updateProject(resources, tasks, schedule);
    	
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			Document projectDocument = db.parse(projectFile);

			Element projectRootElement = projectDocument.getDocumentElement();
			
			//*************************
			//*   Read Project Info   *
			//*************************
			NodeList projectInfoNodeList = projectRootElement.getElementsByTagName("ProjectInfo");
			String projectName = "";
			String projectAuthor = "";
			String projectStartTime = "";
			String projectEndTime = "";
			if(projectInfoNodeList != null) {
				//Get Project Info Element
				Element projectInfo = (Element) projectInfoNodeList.item(0);
				
				NodeList projectNameInfoList = projectInfo.getElementsByTagName("ProjectName");
				if(projectNameInfoList != null && projectNameInfoList.getLength() > 0) {
					Element el = (Element)projectNameInfoList.item(0);
					projectName = el.getFirstChild().getNodeValue();
				}
				
				NodeList projectAuthorInfoList = projectInfo.getElementsByTagName("ProjectAuthor");
				if(projectAuthorInfoList != null && projectAuthorInfoList.getLength() > 0) {
					Element el = (Element)projectAuthorInfoList.item(0);
					projectAuthor = el.getFirstChild().getNodeValue();
				}
				
				NodeList projectStartTimeInfoList = projectInfo.getElementsByTagName("ProjectStartTime");
				if(projectAuthorInfoList != null && projectStartTimeInfoList.getLength() > 0) {
					Element el = (Element)projectStartTimeInfoList.item(0);
					projectStartTime = el.getFirstChild().getNodeValue();
				}
				
				NodeList projectEndTimeInfoList = projectInfo.getElementsByTagName("ProjectEndTime");
				if(projectAuthorInfoList != null && projectEndTimeInfoList.getLength() > 0) {
					Element el = (Element)projectEndTimeInfoList.item(0);
					projectEndTime = el.getFirstChild().getNodeValue();
				}
				
				project.updateInfo(projectName, projectStartTime, projectAuthor);
				project.setEndTime(new Time(Integer.parseInt(projectEndTime)));
			}
			
			//**************************
			//*   Read Resource Info   *
			//**************************
			NodeList projectResourcesNodeList = projectRootElement.getElementsByTagName("ProjectResources");
						
			if(projectResourcesNodeList != null && projectResourcesNodeList.getLength() > 0) {
					//get the Project Resource root element
				Element resourceInfoList = (Element) projectResourcesNodeList.item(0);
				
				NodeList projectResourceNodeList = resourceInfoList.getElementsByTagName("Resource");
				for(int i = 0 ; i < projectResourceNodeList.getLength(); i++) {
					String resourceName = "";
					Integer resourceID = 0;
					double dailyCost = 0.0;
					ResourceType r = null; 
					
					Element resourceInfo = (Element) projectResourceNodeList.item(i);
					
					NodeList resourceNameInfoList = resourceInfo.getElementsByTagName("ResourceName");
					if(resourceNameInfoList != null && resourceNameInfoList.getLength() > 0) {
						Element el = (Element)resourceNameInfoList.item(0);
						resourceName = el.getFirstChild().getNodeValue();
					}
					
					resourceID = Integer.parseInt(resourceInfo.getAttribute("id"));
					
					NodeList resourceDailyCostInfoList = resourceInfo.getElementsByTagName("ResourceDailyCost");
					if(resourceDailyCostInfoList != null && resourceDailyCostInfoList.getLength() > 0) {
						Element el = (Element)resourceDailyCostInfoList.item(0);
						dailyCost = Double.parseDouble(el.getFirstChild().getNodeValue());
					}
					
					NodeList resourceTypeInfoList = resourceInfo.getElementsByTagName("ResourceType");
					if(resourceTypeInfoList != null && resourceTypeInfoList.getLength() > 0) {
						Element el = (Element)resourceTypeInfoList.item(0);
						String type = el.getFirstChild().getNodeValue();
						
						switch(type) {
							case "ResourceType.labor": r = ResourceType.labor;
								break;
							case "ResourceType.equipment": r = ResourceType.equipment;
								break;
							case "ResourceType.material": r = ResourceType.material;
								break;
						}
					}
					
					project.createResource(resourceName, resourceID, dailyCost, r);
				}
			}
			
			//**********************
			//*   Read Task Info   *
			//**********************
			NodeList projectTasksNodeList = projectRootElement.getElementsByTagName("ProjectTasks");
			
			if(projectTasksNodeList != null && projectTasksNodeList.getLength() > 0) {
				//get the Project Task element
				Element taskInfoList = (Element)projectTasksNodeList.item(0);
					
				NodeList projectTaskNodeList = taskInfoList.getElementsByTagName("Task");
				
				for(int i = 0 ; i < projectTaskNodeList.getLength(); i++) {
					String taskName = "";
					Integer taskID = 0;
					String taskDescription = "";
					Integer taskDuration = 0;
					double percentCompleted = 0.0;
					String taskStartTime = "";
					String taskEndTime = "";
					String parentTaskName = "";
					List<String> listOfTaskPredecessorNames = new LinkedList<String>();
					List<Deliverable> listOfTaskDeliverables = new LinkedList<Deliverable>();
					List<String> listOfTaskResourceNames = new LinkedList<String>();
					
					Element taskInfo = (Element) projectTaskNodeList.item(i);
					
					NodeList taskNameInfoList = taskInfo.getElementsByTagName("TaskName");
					if(taskNameInfoList != null && taskNameInfoList.getLength() > 0) {
						Element el = (Element)taskNameInfoList.item(0);
						taskName = el.getFirstChild().getNodeValue();
					}
					
					taskID = Integer.parseInt(taskInfo.getAttribute("id"));
					
					NodeList taskDescriptionInfoList = taskInfo.getElementsByTagName("TaskDescription");
					if(taskDescriptionInfoList != null && taskDescriptionInfoList.getLength() > 0) {
						Element el = (Element)taskDescriptionInfoList.item(0);
						taskDescription = el.getFirstChild().getNodeValue();
					}
					
					NodeList taskDurationInfoList = taskInfo.getElementsByTagName("TaskDuration");
					if(taskDurationInfoList != null && taskDurationInfoList.getLength() > 0) {
						Element el = (Element)taskDurationInfoList.item(0);
						taskDuration = Integer.parseInt(el.getFirstChild().getNodeValue());
					}
					
					NodeList taskPercentCompletedInfoList = taskInfo.getElementsByTagName("TaskPercentCompleted");
					if(taskPercentCompletedInfoList != null && taskPercentCompletedInfoList.getLength() > 0) {
						Element el = (Element)taskPercentCompletedInfoList.item(0);
						percentCompleted = Double.parseDouble(el.getFirstChild().getNodeValue());
					}
					
					NodeList taskStarTimeInfoList = taskInfo.getElementsByTagName("TaskStartTime");
					if(taskStarTimeInfoList != null && taskStarTimeInfoList.getLength() > 0) {
						Element el = (Element)taskStarTimeInfoList.item(0);
						taskStartTime = el.getFirstChild().getNodeValue();
					}
					
					NodeList taskEndTimeInfoList = taskInfo.getElementsByTagName("TaskEndTime");
					if(taskEndTimeInfoList != null && taskEndTimeInfoList.getLength() > 0) {
						Element el = (Element)taskEndTimeInfoList.item(0);
						taskEndTime = el.getFirstChild().getNodeValue();
					}
					
					NodeList taskParentTaskInfoList = taskInfo.getElementsByTagName("TaskParentTask");
					if(taskParentTaskInfoList != null && taskParentTaskInfoList.getLength() > 0) {
						Element el = (Element)taskParentTaskInfoList.item(0);
						parentTaskName = el.getFirstChild().getNodeValue();
					}
					
					NodeList taskPredecessorsInfoList = taskInfo.getElementsByTagName("TaskPredecessors");
					if(taskPredecessorsInfoList != null && taskPredecessorsInfoList.getLength() > 0) {
						Element taskPredInfo = (Element)taskPredecessorsInfoList.item(0);
						
						NodeList taskPredInfoList = taskPredInfo.getElementsByTagName("TaskPredecessor");
						for (int y = 0; y < taskPredInfoList.getLength(); y++)
						{
							String predTaskName = "";
							Element el = (Element)taskPredInfoList.item(0);
							predTaskName = el.getFirstChild().getNodeValue();
							
							listOfTaskPredecessorNames.add(predTaskName);
						}
					}
					
					NodeList taskDeliverablesInfoList = taskInfo.getElementsByTagName("TaskDeliverables");
					if(taskDeliverablesInfoList != null && taskDeliverablesInfoList.getLength() > 0) {
						Element taskDeliverableInfo = (Element)taskDeliverablesInfoList.item(0);
						
						NodeList taskDelInfoList = taskDeliverableInfo.getElementsByTagName("TaskDeliverable");
						for (int y = 0; y < taskDelInfoList.getLength(); y++)
						{
							Element delElement = (Element) taskDelInfoList.item(y);
							
							String deliverableName = "";
							String deliverableType = "";
							
							NodeList taskDeliverableNameInfo = delElement.getElementsByTagName("TaskDeliverableName");
							if(taskParentTaskInfoList != null && taskDeliverableNameInfo.getLength() > 0) {
								Element el = (Element)taskDeliverableNameInfo.item(0);
								deliverableName = el.getFirstChild().getNodeValue();
							}
							
							NodeList taskDeliverableTypeInfo = delElement.getElementsByTagName("TaskDeliverableType");
							if(taskParentTaskInfoList != null && taskDeliverableTypeInfo.getLength() > 0) {
								Element el = (Element)taskDeliverableTypeInfo.item(0);
								deliverableType = el.getFirstChild().getNodeValue();
							}
							
							listOfTaskDeliverables.add(project.createDeliverable(deliverableName, deliverableType));	
						}
					}
					
					NodeList taskResourcesInfoList = taskInfo.getElementsByTagName("TaskResources");
					if(taskResourcesInfoList != null && taskResourcesInfoList.getLength() > 0) {
						Element taskResourceInfo = (Element)taskResourcesInfoList.item(0);
						
						NodeList taskResInfoList = taskResourceInfo.getElementsByTagName("TaskDeliverable");
						for (int y = 0; y < taskResInfoList.getLength(); y++)
						{
							Element resElement = (Element) taskResInfoList.item(y);
							
							String taskResName = "";
							NodeList taskResNameInfo = resElement.getElementsByTagName("TaskResource");
							if(taskParentTaskInfoList != null && taskResNameInfo.getLength() > 0) {
								Element el = (Element)taskResNameInfo.item(0);
								taskResName = el.getFirstChild().getNodeValue();
							}
							
							listOfTaskResourceNames.add(taskResName);	
						}
					}
					
					
					project.createTaskfromLoadProject(taskName, taskID, taskDuration);
					project.getTask(taskName).setParentTask(project.getTask(parentTaskName));
					project.getTask(taskName).setTaskDescription(taskDescription);
					project.getTask(taskName).setPercentCompleted(percentCompleted);
					project.getTask(taskName).setStartTime(taskStartTime);
					project.getTask(taskName).setEndTime(taskEndTime);
					
					//Set the list of predecessor tasks
					for (int l = 0; l < listOfTaskPredecessorNames.size(); l++)
					{
						project.getTask(taskName).addPredecessor(project.getTask(listOfTaskPredecessorNames.get(l)));
					}
					
					//Set the list of deliverables for the task
					for (int d = 0; d < listOfTaskDeliverables.size(); d++)
					{
						project.getTask(taskName).addDeliverable(listOfTaskDeliverables.get(d));
					}
					
					//Set the list of resources for the task
					for (int r = 0; r < listOfTaskResourceNames.size(); r++)
					{
						project.getTask(taskName).addResource(project.getResource(listOfTaskResourceNames.get(r)));
					}
				}
			}
			

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
    }

	@Override
	public void execute(String command, Oracle oracle) {
		// TODO Auto-generated method stub
	}
	
	public void executeLoadProject(File projectFile) {
    	project.clear();
    	
    	parse(projectFile);
	}

}