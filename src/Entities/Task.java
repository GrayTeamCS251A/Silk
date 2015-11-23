package Entities;

import java.util.*;

/**
 * 
 */
public class Task extends Observable {
	
	 private String name;
	    private String description;
	    private int duration;
	    private int startTime;
	    private int endTime;
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
    	requiredResources=new HashMap<String, Resource>();
    }

    //Second Constructor
    public Task(){
    	this("-1", "", "", 0);
    }

   //getters
   public String getID(){
	   return taskID;
   }
   public String getName(){
	   return name;
   }
   public String getDescription(){
	   return description;
   }
   public int getDuration(){
	   return duration;
   }
   public int getStartTime(){
	   return startTime;
   }
   public int getEndTime(){
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
   public HashMap<String, Task> getSuccessors(){
	   return successors;
   }
   public Task getParent(){
	   return parent;
   }
   public void getTaskInfo(){
	   getID();
	   getName();
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
   
   public void setID(String taskID){
	   this.taskID = taskID;
   }
   public void setName(String name){
	   this.name= name;
   }
   public void setDescription(String description){
	   this.description = description;
   }
   public void setDuration(int duration){
	   this.duration= duration;
   }
   public void setStartTime(int startTime){
	   this.startTime= startTime;
   }
   public void setEndTime(int endTime){
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
   public void setPredecessors(HashMap<String, Task> predecessors){
	   this.predecessors = predecessors;
   }
   public void setParent(Task parent){
	   this.parent = parent;
   }
    /**
     * @param info
     */
    public void updateTask(String taskID, String name, String description, int duration, int startTime, int endTime, double percentCompleted, HashMap<String, Resource> requiredResources, HashMap<String, Task> predecessors, Task parent) {
        // TODO implement here
    	setID(taskID);
    	setName(name);
    	setDescription(description);
    	setDuration(duration);
    	setStartTime(startTime);
    	setEndTime(endTime);
    	setPercentCompleted(percentCompleted);
    	//setDeliverables(deliverables);
    	setRequiredResources(requiredResources);
    	setPredecessors(predecessors);
    	setParent(parent);
    		
    }

    /**
     * Calculates the start time of this Task and any subtasks (and their subtasks, etc) based on the starting time sT. startTimes should be integer values. The duration of a project with children should be changed to the total duration of their children's path.
     * @param startTime the start time of this Task
     */
    public void calculateStartTimes(int sT) {
    	this.startTime = 0;
    	this.endTime = 0;

    	//no need to figure if this is a "starting task" or not, because this function will only be called if we already know what it's startTime is
		//assigns startTime for this task as the offset value sT
		this.startTime = sT;

		//Checks if this task has children
		if(this.children != null){

			//Call Child function here
			startTimeChild(children, this.startTime); 

			//Update Duration
			this.duration = Math.max(this.duration, maxEndTime(children.values()) - this.startTime);
			System.out.println("-->" + this.duration);
		}
		
		//calc endTime for this task
		this.endTime = this.startTime + this.duration;
    }	
    
    /**
     * To calculate the biggest endTime of a collection of tasks
     * @param tasks a collection of Tasks
     * @return the largest endTime
     */
    private int maxEndTime(Collection<Task> tasks) {
		int maxValue = -1;
    	for (Task t : tasks){
			if (maxValue < t.endTime){
				maxValue = t.endTime;
			}
		}
    	return maxValue;
	}
    
    
    /**
     * Calculate the starTime and endTime for the collection of tasks
     * @param children tasks
     * @param startTime start time for this collection of tasks to start at
     */
	public void startTimeChild(HashMap<String, Task> children, int startTime){
		Queue<Task> queue = new LinkedList<Task>();

		for (Task child: children.values()){
			if(child.getPredecessors().isEmpty()){
				// put into queue
				queue.add(child);
				
				// set start and end times to -1
				child.setStartTime(-1);
				child.setEndTime(-1);
			}
		}
		
		// Loop through queue, as we go we'll
		// 1. calculate the startTime of the task
		// 2. deal with any children
		// 3. calculate duration and endTime
		// 4. put any successors on the queue
		
		while (!queue.isEmpty()) {
			Task child = queue.remove();

			System.out.println("working with Task " + child);
			
			// 1. calculate the startTime of the task
			if (child.getPredecessors().isEmpty()) {
				child.setStartTime(startTime);
			} else {
				child.setStartTime(maxEndTime(child.getPredecessors().values()));
			}
			
			System.out.println("  setting start time to " + child.getStartTime());
			
			// 2. deal with any children
			if (!child.getChildren().isEmpty()) {
				startTimeChild(child.getChildren(), child.getStartTime());
			}

			// set this child's duration & endTime
			child.setDuration(Math.max(child.getDuration(), maxEndTime(child.getChildren().values())-child.getStartTime()));
			child.setEndTime(child.getStartTime() + child.getDuration());
			
			// 4. put their successors in the queue
			for (Task successor : child.getSuccessors().values()) {
				queue.add(successor);
			}
		}
    }
		
  

	public void addPredecessor(Task predecessor)
    {
    	this.predecessors.put(predecessor.getID(), predecessor);
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
		this.successors.put(successor.getID(), successor);
	}
	
	public void addChildren(HashMap<String, Task> children)
	{
		this.children = children;
	}

	public void addChild(Task child)
	{
		this.children.put(child.getID(), child);
	}
	
	
}