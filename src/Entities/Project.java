package Entities;

import java.sql.Time;
import java.util.*;
/**
 * 
 */
public class Project extends Observable{
    private Time startTime;
    private Time endTime;
    private Set<Resource> resources;
    private Set<Task> tasks;
    private Schedule schedule;
    private String projectName;
    private String projectAuthor;
    
    public Project() 
    {
    	projectName = "";
    	projectAuthor = "";
    	startTime = new Time(0);
    	endTime = new Time(0);
    	resources = new HashSet<Resource>();
    	tasks = new HashSet<Task>();
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
    	startTime = new Time(0);
    	resources = new HashSet<Resource>();
    	tasks = new HashSet<Task>();
    	schedule = new Schedule();
    }

    /**
     * @return
     */
    public Object getProjectInfo() {
    	HashMap<String, String> projectInfo = new HashMap<String, String>();
    	projectInfo.put("Project Name", this.projectName);
    	projectInfo.put("Project Author", this.projectAuthor);
    	projectInfo.put("Project Start Time", this.startTime.toString());
    	return projectInfo;
    }

    /**
     * @param info
     */
    public void updateInfo(String projectName, String startTime, String projectAuthor) {
        this.projectName = projectName;
        this.projectAuthor = projectAuthor;
        
        this.startTime = new Time (Integer.parseInt(startTime));
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
    public void createTaskFromUI(String taskName, Integer taskID, Integer taskDuration, Task taskPredecessor, Task taskParent) {
        Task taskToAdd = new Task();
        taskToAdd.setName(taskName);
        taskToAdd.setTaskID(taskID);
        taskToAdd.setTaskDuration(taskDuration);
        if (taskPredecessor != null){
            taskToAdd.setPredecessorTask(taskPredecessor);
        }
        if (taskParent != null) {
            taskToAdd.setTaskParent(taskParent);
        }
        
        tasks.add(taskToAdd);
    }
    
    public void createTaskfromLoadProject(String taskName, Integer taskID, Integer taskDuration) {
        Task taskToAdd = new Task();
        taskToAdd.setName(taskName);
        taskToAdd.setTaskID(taskID);
        taskToAdd.setTaskDuration(taskDuration);
        
        tasks.add(taskToAdd);
    }

    /**
     * 
     */
    public void createResource(String resourceName, int resourceID, double dailyCost, ResourceType r) {
        Resource resourceToCreate = new Resource();
        resourceToCreate.setname(resourceName);
        resourceToCreate.setResourceID(resourceID);
        resourceToCreate.setDailyCost(dailyCost);
        resourceToCreate.setResourceType(r);
        
        resources.add(resourceToCreate);
        setChanged();
        notifyObservers();
    }

    /**
     * @return
     */
    public Set<Resource> getResources() {
        return this.resources;
    }
    
    public Resource getResource(String resourceName) {
    	for (Resource r: resources)
    	{
    		if (r.getname().equals(resourceName))
    		{
    			return r;
    		}
    	}
    	return null;
    }

    /**
     * @param info
     */
    public void editResource(String resourceName, int resourceID, double dailyCost, ResourceType r) {
        // Search for the Resource to Edit using resource ID,
    	for (Resource resource: resources) {
    		if (resource.getResourceID() == resourceID && resource.getname().equals(resourceName)){
    			resource.setDailyCost(dailyCost);
    			resource.setResourceType(r);
    	        setChanged();
    	        notifyObservers();
    			break;
    		}
    	}
    }

    /**
     * 
     */
    public void deleteResource(int resourceID) {
    	for (Resource resource: resources) {
    		if (resource.getResourceID() == resourceID) {
    			resources.remove(resource);
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
    public Task getTask(String taskName) {
        for (Task task: tasks) {
        	if (task.getTaskName().equals(taskName))
        	{
        		return task;
        	}
        }
        
        return null;
    }

    /**
     * @param taskID 
     * @param info
     */
    public void editTask(String taskName, Integer taskID, Integer taskDuration, Task predecessorTask, Task parentTask) {
        // TODO implement here
    	for (Task taskToEdit: tasks) {
    		if (taskToEdit.getTaskName().equals(taskName) && taskToEdit.getTaskID() == taskID)
    		{
    			taskToEdit.setTaskDuration(taskDuration);
    			if (predecessorTask != null){
        			taskToEdit.setPredecessorTask(predecessorTask);
    			}
    			if (parentTask != null) {
        			taskToEdit.setTaskParent(parentTask);
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
    public Set<Task> getTasks() {
        return this.tasks;
    }

    /**
     * 
     */
    public void deleteTask(int taskID) {
    	for (Task taskToDelete: tasks) {
    		if (taskToDelete.getTaskID() == taskID) {
    			resources.remove(taskToDelete);
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
    	schedule.generateSchedule(tasks);
    }

    /**
     * @return
     */
    private List<Task> calculateCriticalPath() {
        // TODO implement here
        return null;
    }

    /**
     * @param schedule
     */
    public void saveSchedule() {
        schedule.toXLS();
    }
    
    public String getProjectName()
    {
    	return this.projectName;
    }
    
    public String getProjectAuthor()
    {
    	return this.projectAuthor;
    }
    
    public Time getStartTime()
    {
    	return this.startTime;
    }
    
    public Time getEndTime()
    {
    	return this.endTime;
    }
    
    public void setEndTime(Time endtime)
    {
    	this.endTime = endtime;
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
}
