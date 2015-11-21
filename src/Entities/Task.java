package Entities;

import java.util.*;

/**
 * 
 */
public class Task extends Observable {
	
	 private String name;
	    private String description;
	    private int duration;
	    private double startTime;
	    private double endTime;
	    private double percentCompleted;
	    private String taskID;
	    private Set<Deliverable> deliverables;
	    private HashMap<String, Resource> requiredResources;
	    private HashMap<String, Task> predecessors;
	    private HashMap<String, Task> children;
	    private HashMap<String, Task> successors;
	    private Task parent;

	//Make constructor only take 4 arguments - ID, name, description, duration
    public Task(String taskID, String name, String description, int duration) {
    	this.taskID = taskID;
    	this.name = name;
    	this.description = description;
    	this.duration = duration;
    	predecessors = new HashMap<String, Task>();
    	successors = new HashMap<String, Task>();
    	children = new HashMap<String, Task>();
    	this.parent = new Task();
    }

    //Second Constructor
    public Task(){
    	this("-1", "", "", 0);
    }

   //getters
   public String getTaskID(){
	   return taskID;
   }
   public String getTaskName(){
	   return name;
   }
   public String getDescription(){
	   return description;
   }
   public int getDuration(){
	   return duration;
   }
   public double getStartTime(){
	   return startTime;
   }
   public double getEndTime(){
	   return endTime;
   }
   
   public double getPercentCompleted(){
	   return percentCompleted;
   }
   public Set<Deliverable> getDeliverables(){
	  return deliverables;
   }
   public HashMap<String, Resource> getRequiredResources(){
	   return requiredResources;
   }
   public HashMap<String, Task> getPredecessors(){
	   return predecessors;
   }
   public Task getParent(){
	   return parent;
   }
   public void getTaskInfo(){
	   getTaskID();
	   getTaskName();
	   getDescription();
	   getDuration();
	   getStartTime();
	   getEndTime();
	   getPercentCompleted();
	   getDeliverables();
	   getRequiredResources();
	   getPredecessors();
	   getParent();
   }
   
   public void setTaskID(String taskID){
	   this.taskID = taskID;
   }
   public void setName(String name){
	   this.name= name;
   }
   public void setDescription(String description){
	   this.description = description;
   }
   public void setTaskDuration(int duration){
	   this.duration= duration;
   }
   public void setStartTime(double startTime){
	   this.startTime= startTime;
   }
   public void setEndTime(double endTime){
	   this.endTime= endTime;
   }
   
   public void setPercentCompleted(double percentCompleted){
	   this.percentCompleted = percentCompleted;
   }
   public void setDeliverables(Set<Deliverable> deliverables){
	  this.deliverables = deliverables;
   }
   public void setRequiredResources(HashMap<String, Resource> requiredResources){
	   this.requiredResources = requiredResources;
   }
   public void setPredecessorTask(HashMap<String, Task> predecessors){
	   this.predecessors = predecessors;
   }
   public void setTaskParent(Task parent){
	   this.parent = parent;
   }
    /**
     * @param info
     */
    public void updateTask(String taskID, String name, String description, int duration, double startTime, double endTime, double percentCompleted, HashMap<String, Resource> requiredResources, HashMap<String, Task> predecessors, Task parent) {
        // TODO implement here
    	setTaskID(taskID);
    	setName(name);
    	setDescription(description);
    	setTaskDuration(duration);
    	setStartTime(startTime);
    	setEndTime(endTime);
    	setPercentCompleted(percentCompleted);
    	//setDeliverables(deliverables);
    	setRequiredResources(requiredResources);
    	setPredecessorTask(predecessors);
    	setTaskParent(parent);
    		
    }

    /**
     * Calculates the start time of this Task and any subtasks (and their subtasks). startTimes should be integer values
     * @param startTime the start time of this Task
     */
    public void calculateStartTimes(int startTime) {
    	// TODO
    }
    
    public void addPredecessor(Task predecessor)
    {
    	this.predecessors.put(predecessor.getTaskID(), predecessor);
    }
    
    public void addDeliverable(Deliverable d)
    {
    	this.deliverables.add(d);
    }
    
    public void addResource(Resource r)
    {
    	this.requiredResources.put(r.getResourceID(), r);
    }
	
	public HashMap<String, Task> getChildren() {
		return children;
	}
	public void setChildren(HashMap<String, Task> children) {
		this.children = children;
	}
	
	public String toString(){
		return name;
	}
	
	public void addSuccessor(Task successor)
	{
		this.successors.put(successor.getTaskID(), successor);
	}
	
	public void addChildren(Task child)
	{
		this.children.put(child.getTaskID(), child);
	}
}
