package Entities;

import java.util.*;

/**
 * 
 */
public class Task extends Observable {
	
	 private String name;
	    private String description;
	    private double duration;
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
   public double getDuration(){
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
   public void setPredecessors(HashMap<String, Task> predecessors){
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
    	setTaskParent(parent);
    		
    }

    /**
     * Calculates the start time of this Task and any subtasks (and their subtasks). startTimes should be integer values
     * @param startTime the start time of this Task
     */
    public void calculateStartTimes(int sT) {
    	this.startTime = 0;
    	this.endTime = 0;
    	
    	//Checks if starting task
    	if (this.predecessors == null || this.predecessors.isEmpty()){
    		//assigns startTime for this task as the offset value sT
    		this.startTime = sT;

    		//Checks if this task has children
    		if(this.children != null){
    			double childEndTime = 0;

    			//Call Child function here
    			childEndTime = startTimeChild(children, this.startTime); 

    			//Update Duration
    			this.duration = Math.max(this.duration, childEndTime - this.startTime);
    			
    		}
    		
    		//calc endTime for this task
    		this.endTime = this.startTime + this.duration;
    	}
    	else{ //Else if not the starting task
    		//Gets the predecessor values
    		Collection<Task> predecessor = this.predecessors.values();
    		//If more than 1 predecessor (Join Tasks)
    		if (predecessor.size() > 1){
    			this.startTime = max(predecessor);
    		}
    		else{
    			for(Task t : predecessor){
    				this.startTime = t.endTime;
    			}
    			
    		}
    		this.endTime = this.startTime + this.duration;
    		//Then Checks if children are there
    		if(this.children !=null){
    			//calc children
    			double tempEndValue = 0;
    			tempEndValue = startTimeChild(children, this.startTime);
    			//Checks old and new value and assigns greater value for task's endTime
    			if(this.endTime < tempEndValue){
    				this.endTime = tempEndValue;
    			}
    		}
    		
    	}
    }	
    
    //To calculate the bigger endTime of the predecessor to be the startTime for the current task
    private double max(Collection<Task> predecessor) {
		double maxValue = 0;
    	for (Task t : predecessor){
			if (maxValue < t.endTime){
				maxValue = t.endTime;
			}
		}
    	return maxValue;
	}
    
    //Calculate the endTime for the children
	public double startTimeChild(HashMap<String, Task> children, double startTime){
    	//Gets collection of children tasks
		Collection<Task> childrens = children.values();
    	double returnValue=0;
		for (Task child: childrens){
			//Checks if the task has more subtasks
			if(child.children != null){
				//Recursive function
				double temp = startTimeChild(child.children, child.startTime);
				child.endTime = temp;
			}
			//If child task if first task
			else if(child.predecessors == null){
					//Assigns startTime as startTime fo main task
					child.startTime = startTime;
					child.endTime = child.startTime + child.duration;
					returnValue = child.endTime;
				}
			else{ //If not the first task
					Collection<Task> predecessor = child.predecessors.values();
		    		if (predecessor.size() > 1){
		    			child.startTime = max(predecessor);
		    		}
		    		else{
		    			for(Task t : predecessor){
		    				child.startTime = t.endTime;
		    			}
		    			
		    		}
		    		child.endTime = child.startTime + child.duration;
		    		returnValue = child.endTime;
				}
				
			}
		return returnValue;
			
		
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