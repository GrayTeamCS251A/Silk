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
	    private int taskID;
	    private Set<Deliverable> deliverables;
	    private Set<Resource> requiredResources;
	    private Set<Task> predecessors;
	    private List<Task> children;
	    private Task parent;

	//Make constructor only take 4 arguments - ID, name, description, duration
    public Task(int taskID, String name, String description, int duration) {
    	this.taskID = taskID;
    	this.name = name;
    	this.description = description;
    	this.duration = duration;
    	children=new ArrayList<Task>();
    }

    //Second Constructor
    public Task(){
    	this(-1, "", "", 0);
    }

   //getters
   public int getTaskID(){
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
   public Set<Resource> getRequiredResources(){
	   return requiredResources;
   }
   public Set<Task> getPredecessors(){
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
   
   public void setTaskID(int taskID){
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
   public void setRequiredResources(Set<Resource> requiredResources){
	   this.requiredResources = requiredResources;
   }
   public void setPredecessorTask(Set<Task> predecessors){
	   this.predecessors = predecessors;
   }
   public void setTaskParent(Task parent){
	   this.parent = parent;
   }
    /**
     * @param info
     */
    public void updateTask(int taskID, String name, String description, int duration, double startTime, double endTime, double percentCompleted, Set<Resource> requiredResources, Set<Task> predecessors, Task parent) {
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
    
    public void addPredecessor(Task predTask)
    {
    	this.predecessors.add(predTask);
    }
    
    public void addDeliverable(Deliverable d)
    {
    	this.deliverables.add(d);
    }
    
    public void addResource(Resource r)
    {
    	this.requiredResources.add(r);
    }
	public List<Task> getChildren() {
		return children;
	}
	public void setChildren(List<Task> children) {
		this.children = children;
	}
	
	public String toString(){
		return name;
	}
}
