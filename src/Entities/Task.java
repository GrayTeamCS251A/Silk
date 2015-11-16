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
	    private Set<Deiverable> deliverables;
	    private Set<Resource> requiredResources;
	    private Set<Task> predecessors;

	    private Task parent;
	//Make constructor only take 4 arguments - ID, name, description, duration
    public Task(int taskID, String name, String description, int duration) {
    	this.taskID = taskID;
    	this.name = name;
    	this.description= description;
    	this.duration = duration;
    }
    //Second Constructor
   public Task(){
	   
   }
   //getters
   public int getTaskID(){
	   return taskID;
   }
   public String getTaskname(){
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
   public Set<Deiverable> getDeliverables(){
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
	   getTaskname();
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
   public void setTaskname(String name){
	   this.name= name;
   }
   public void setDescription(String description){
	   this.description = description;
   }
   public void setDuration(int duration){
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
   public void setDeliverables(Set<Deiverable> deliverables){
	   this.deliverables = deliverables;
   }
   public void setRequiredResources(Set<Resource> requiredResources){
	   this.requiredResources = requiredResources;
   }
   public void setPredecessors(Set<Task> predecessors){
	   this.predecessors = predecessors;
   }
   public void setParent(Task parent){
	   this.parent = parent;
   }
    /**
     * @param info
     */
    public void updateTask(int taskID, String name, String description, int duration, double startTime, double endTime, double percentCompleted, Set<Deiverable> deliverables, Set<Resource> requiredResources, Set<Task> predecessors, Task parent) {
        // TODO implement here
    	setTaskID(taskID);
    	setTaskname(name);
    	setDescription(description);
    	setDuration(duration);
    	setStartTime(startTime);
    	setEndTime(endTime);
    	setPercentCompleted(percentCompleted);
    	setDeliverables(deliverables);
    	setRequiredResources(requiredResources);
    	setPredecessors(predecessors);
    	setParent(parent);
    		
    }

    /**
     * @return
     */
    public List<Task> calculateCriticalPath() {
        // TODO implement here
        return null;
    }

}
