package Entities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

import jxl.*;
import jxl.write.*;
/**
 * 
 */
public class Project extends Observable{
    private Calendar startTime;
    private Calendar endTime;
    private HashMap<String, Resource> resources;
    private HashMap<String, Task> tasks;
    private Schedule schedule;
    private String projectName;
    private String projectAuthor;
    
    public Project() 
    {
    	projectName = "";
    	projectAuthor = "";
    	startTime = new GregorianCalendar();
    	endTime = new GregorianCalendar();
    	resources = new HashMap<String, Resource>();
    	tasks = new HashMap<String, Task>();
    	schedule = new Schedule();
    }

    /**
     * @param info
     */
    public void Project(Object info) {
        // TODO implement here
    }

    /**
     * @param info 
     * @param resources 
     * @param tasks 
     * @param schedule
     */
    public void Project(Object info, Object resources, Object tasks, Schedule schedule) {
        // TODO implement here
    }

    /**
     * 
     */
    public void clear() {
    	projectName = "";
    	projectAuthor = "";
    	startTime = new GregorianCalendar();
    	endTime = new GregorianCalendar();
    	resources = new HashMap<String, Resource>();
    	tasks = new HashMap<String, Task>();
    	schedule = new Schedule();
    }

    /**
     * @return
     */
    public Object getProjectInfo() {
    	HashMap<String, Object> projectInfo = new HashMap<String, Object>();
    	projectInfo.put("Project Name", this.projectName);
    	projectInfo.put("Project Author", this.projectAuthor);
    	projectInfo.put("Project Start Time", this.startTime);
    	return projectInfo;
    }

    /**
     * @param info
     */
    public void updateInfo(String projectName, Integer year, Integer month, Integer day, String projectAuthor) {
        this.projectName = projectName;
        this.projectAuthor = projectAuthor;
        
        this.startTime = new GregorianCalendar(year, month, day);
        setChanged();
        notifyObservers();
    }

    /**
     * @param resources 
     * @param tasks 
     * @param schedule
     */
    public void updateProject(Set<Resource> resources, Set<Task> tasks, Schedule schedule) {

    }

    /**
     * 
     */
    public Project getWholeProject() {
        return this;
    }

    /**
     * @param info
     */
    public void createTaskFromUI(String taskName, 
    		Integer taskDuration, 
    		ArrayList<Task> taskPredecessor, 
    		Task taskParent, 
    		ArrayList<Resource> taskResources,
    		String taskDescription)
    {
        Task taskToAdd = new Task();
        taskToAdd.setName(taskName);
        
        //Generate Task ID
        String uniqueID = UUID.randomUUID().toString();
        
        taskToAdd.setID(uniqueID);
        taskToAdd.setDuration(taskDuration);
        taskToAdd.setDescription(taskDescription);
        
        if (!taskPredecessor.isEmpty()){
        	for (int p = 0; p < taskPredecessor.size(); p++)
        	{
                taskToAdd.addPredecessor(taskPredecessor.get(p));
                taskPredecessor.get(p).addSuccessor(taskToAdd);
        	}
        }
        if (taskParent != null) {
            taskToAdd.setParent(taskParent);
            taskParent.addChild(taskToAdd);
        }
        
        if (!taskResources.isEmpty())
        {
        	for (int i = 0; i < taskResources.size(); i++)
        	{
        		taskToAdd.addResource(taskResources.get(i));
        	}
        }
        if(taskParent == null){
        tasks.put(uniqueID, taskToAdd);}
        setChanged();
        notifyObservers();
    }
    
    public void createTaskfromLoadProject(String taskName, String taskID, Integer taskDuration) {
        Task taskToAdd = new Task();
        taskToAdd.setName(taskName);
        taskToAdd.setID(taskID);
        taskToAdd.setDuration(taskDuration);
        
        tasks.put(taskID, taskToAdd);
        setChanged();
        notifyObservers();
    }

    /**
     * 
     */
    public void createResource(String resourceName, double dailyCost, ResourceType r) {
        Resource resourceToCreate = new Resource();
        resourceToCreate.setname(resourceName);
        String uniqueID = UUID.randomUUID().toString();

        resourceToCreate.setResourceID(uniqueID);
        resourceToCreate.setDailyCost(dailyCost);
        
        if (r != null)
        {
            resourceToCreate.setResourceType(r);
        }
        
        resources.put(uniqueID, resourceToCreate);
        setChanged();
        notifyObservers();
    }
    
    public void createResourcefromLoadProject(String resourceName, String resourceID, double dailyCost, ResourceType r) {
        Resource resourceToCreate = new Resource();
        resourceToCreate.setname(resourceName);

        resourceToCreate.setResourceID(resourceID);
        resourceToCreate.setDailyCost(dailyCost);
        
        if (r != null)
        {
            resourceToCreate.setResourceType(r);
        }
        
        resources.put(resourceID, resourceToCreate);
        setChanged();
        notifyObservers();
    }


    /**
     * @return
     */
    public HashMap<String, Resource> getResources() {
        return this.resources;
    }
    
    public Resource getResource(String rID) {
    	for (String resourceID: resources.keySet())
    	{
    		if (resourceID.equals(rID))
    		{
    			return resources.get(resourceID);
    		}
    	}
    	return null;
    }

    /**
     * @param info
     */
    public void editResource(String rName, String rID, double dailyCost, ResourceType r) {
        // Search for the Resource to Edit using resource ID,
    	for (String resourceID: resources.keySet()) {
    		if (resourceID.equals(rID)){
    			if (!rName.equals(""))
    			{
    				resources.get(resourceID).setname(rName);
        			resources.get(resourceID).setDailyCost(dailyCost);
        			resources.get(resourceID).setResourceType(r);
    			}

    	        setChanged();
    	        notifyObservers();
    			break;
    		}
    	}

    }

    /**
     * 
     */
    public void deleteResource(String rID) {
    	for (String resourceID: resources.keySet()) {
    		if (resourceID.equals(rID)) {
    			resources.remove(resourceID);
    	        setChanged();
    	        notifyObservers();
    			break;
    		}
    	}
    }

    /**
     * @param taskName 
     * @return
     */
    public Task getTask(String tID) {
        for (String taskID: tasks.keySet()) {
        	if (taskID.equals(tID))
        	{
        		return tasks.get(taskID);
        	}
        }
        
        return null;
    }

    /**
     * @param taskID 
     * @param info
     */
    public void editTask(String taskName, 
    		String tID, 
    		Integer taskDuration, 
    		ArrayList<Task> predecessorTask, 
    		Task parentTask, 
    		ArrayList<Resource> taskResources,
    		String taskDescription)
    {
        // TODO implement here
    	for (String taskID: tasks.keySet()) {
    		if (taskID.equals(tID))
    		{
    			tasks.get(taskID).setName(taskName);
    			
    			tasks.get(taskID).setDuration(taskDuration);
    			
    			if (!predecessorTask.isEmpty()){
    				for (int p = 0; p < predecessorTask.size(); p++)
    				{
        				tasks.get(taskID).addPredecessor(predecessorTask.get(p));
    				}
    			}
    			if (parentTask != null) {
    				tasks.get(taskID).setParent(parentTask);
    			}
    			
    			if (!taskResources.isEmpty())
    			{
    				for (int i = 0; i < taskResources.size(); i++)
    				{
    					tasks.get(taskID).addResource(taskResources.get(i));
    				}
    			}
    			tasks.get(taskID).setDescription(taskDescription);
    			
    			setChanged();
    			notifyObservers();
    			break;
    		}
    	}
    }
    private void findTask(int taskID, Task task){
    	if(task.getChildren().isEmpty()) return;
    	
    	
    	
    }
    /**
     * 
     */
    public HashMap<String, Task> getTasks() {
        return this.tasks;
    }

    /**
     * 
     */
    public void deleteTask(String tID) {
    	for (String taskID: tasks.keySet()) {
    		if (taskID.equals(tID)) {
    			tasks.remove(taskID);
    	        setChanged();
    	        notifyObservers();
    			break;
    		}
    	}
    }

    /**
     * 
     */
    public Schedule getSchedule() {
        return this.schedule;
    }

    /**
     * 
     */
    public void generateSchedule() {
    	schedule.generateSchedule(this.startTime, this.tasks);
    }
    
    /**
     * @param schedule
     * @throws IOException 
     * @throws WriteException 
     */
    public WritableWorkbook saveSchedule(String dest) throws IOException, WriteException {
        String[][] dataValue = this.getScheduleMatrix();
        
        WritableWorkbook workbook = Workbook.createWorkbook(new File(Paths.get(dest) + ".xls"));
        
        WritableSheet scheduleSheet = workbook.createSheet("Schedule", 0);
        
        for (int i = 0; i < dataValue.length; i++)
        {
            Label dateLabel = new Label(i, 0, dataValue[i][0]);
            
            String[] s = dataValue[i][1].split(",");
            String taskNames = "";
            for (int t = 0; t < s.length; t++)
            {
            	taskNames += getTask(s[t]) + ",";
            }
                        
            Label taskLabel = new Label(i, 1, taskNames.substring(0, taskNames.length() - 1));
            
            scheduleSheet.addCell(dateLabel);
            scheduleSheet.addCell(taskLabel);
        }

        workbook.write(); 
        workbook.close();
        
        return workbook;
    }
    
    public String getProjectName()
    {
    	return this.projectName;
    }
    
    public String getProjectAuthor()
    {
    	return this.projectAuthor;
    }
    
    public Calendar getStartTime()
    {
    	return this.startTime;
    }
    
    public Calendar getEndTime()
    {
    	return this.endTime;
    }
    
    public void setEndTime(Integer year, Integer month, Integer day)
    {
    	this.endTime = new GregorianCalendar(year, month, day);
    }
    
    public Deliverable createDeliverable(String deliverableName, String deliverableType)
    {
    	Deliverable d = new Deliverable();
    	DeliverableType dt = null;
    	
    	switch (deliverableType){
    		case "DeliverableType.file": dt = DeliverableType.file;
    									break;
    		case "DeliverableType.presentation": dt = DeliverableType.presentation;
    											break;
    	}
    	
    	d.setDeliverableName(deliverableName);
    	d.setDeliverableType(dt);
    	
    	return d;
    }

    public void setTasks(HashMap<String, Task> tasks){
    	this.tasks=tasks;
    }
    
    public void setResources(HashMap<String, Resource> resources){
    	this.resources=resources;
    }
    
    public String[][] getScheduleMatrix()
    {
    	// careful, this could be null (if schedule isn't current)
    	return schedule.toMatrix();
    }
}
