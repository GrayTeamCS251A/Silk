package Entities;

import java.sql.Time;
import java.util.*;

/**
 * 
 */
public class Project {
    private Time startTime;
    private Set<Resource> resources;
    private Set<Task> tasks;
    private Schedule schedule;
    
    public Project() 
    {
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
        // TODO implement here
    }

    /**
     * @return
     */
    public Object getProjectInfo() {
        // TODO implement here
        return null;
    }

    /**
     * @param info
     */
    public void updateInfo(Object info) {
        // TODO implement here
    }

    /**
     * @param resources 
     * @param tasks 
     * @param schedule
     */
    public void updateProject(List<Resource> resources, List<Task> tasks, Schedule schedule) {
        // TODO implement here
    }

    /**
     * 
     */
    public void getWholeProject() {
        // TODO implement here
    }

    /**
     * @param info
     */
    public void createTask(Object info) {
        // TODO implement here
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
        //for (Resource resource: resources) {
        //    System.out.println(resource.getname());
        //}
    }

    /**
     * @return
     */
    public Set<Resource> getResources() {
        return this.resources;
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
    			break;
    		}
    	}
    }

    /**
     * @param taskName 
     * @return
     */
    public Task getTask(String taskName) {
        // TODO implement here
        return null;
    }

    /**
     * @param taskID 
     * @param info
     */
    public void editTask(Integer taskID, String info) {
        // TODO implement here
    }

    /**
     * 
     */
    public void getTasks() {
        // TODO implement here
    }

    /**
     * 
     */
    public void deleteTask() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getSchedule() {
        // TODO implement here
    }

    /**
     * 
     */
    public void generateSchedule() {
        // TODO implement here
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
    public void saveSchedule(Schedule schedule) {
        // TODO implement here
    }

}