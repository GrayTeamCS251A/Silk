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
    	deliverables=new HashSet<>();
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
   public void emptyDeliverables(){
	  this.deliverables.clear();
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
     * Calculate the starTime and endTime for the collection of tasks. Takes into account resources and does not allow two tasks with shared resources run at the same time. It's not very "smart", though, and won't optimize the order of the tasks at all.
     * @param children tasks
     * @param startTime start time for this collection of tasks to start at
     */
	public void startTimeChild(HashMap<String, Task> children, int startTime){
		Queue<Task> queue = new LinkedList<Task>();

		// make a resource list for each resource
		HashMap<String, List<Task>> resourceLists = new HashMap<String, List<Task>>();

		// loop through children
		for (Task child: children.values()){
			System.out.println("pre-processing: " + child);
			
			// add children to the resource list
			for (Resource resource : child.getRequiredResources().values()) {
				// check if the resource exists in our Resource List
				if (!resourceLists.containsKey(resource.getResourceID())) {
					// if not, add it
					resourceLists.put(resource.getResourceID(), new LinkedList<Task>());
				}
			}
	
			// if it's a starting Task, add it to the queue
			if(child.getPredecessors().isEmpty()){
				// put into queue
				queue.add(child);
				
				// set start and end times to -1
				child.setStartTime(-1);
				child.setEndTime(-1);
			}
		}
		
		// Loop through queue, as we go we'll
		// 0. calculate the startTime of the task
		// 1. add it to the resource list(s) / deal with resources
		//   1a. assign start time of task
		// 2. deal with any children
		// 3. calculate duration and endTime
		// 4. put any successors on the queue
		
		System.out.println("starting queue:");
		
		while (!queue.isEmpty()) {
			System.out.println("pop");
			Task child = queue.remove();
			
			System.out.println(child); 
			
			int idealStartTime = -1; // the startTime that would be ideal, if resources weren't an issue
			// 0. calculate the startTime of the task
			System.out.println(" 0");
			if (child.getPredecessors().isEmpty()) {
				idealStartTime = startTime;
			} else {
				idealStartTime = maxEndTime(child.getPredecessors().values());
			}

			int realisticStartTime = idealStartTime; // the startTime that must be done, because of resource conflicts
			
			// 1. add to resource list(s) / deal with resources
			System.out.println(" 1");
			for (Resource resource : child.getRequiredResources().values()) {
				// check to see if there's a task using this resource. if so, our startTime must be after their endTime
				for (Task task : resourceLists.get(resource.getResourceID())) {
					realisticStartTime = (task.getEndTime() < realisticStartTime) ? realisticStartTime : task.getEndTime();
				}
				
				// add this child to that list
				if (!resourceLists.get(resource.getResourceID()).contains(resource)) {
					resourceLists.get(resource.getResourceID()).add(child);
				}
				
				// see what else is on there..
				System.out.println(" " + resource.getname() + ": " + resourceLists.get(resource.getResourceID()));
			}
			
			// 1b. Assign the startTime of a task
			System.out.println("  1b");
			child.setStartTime(realisticStartTime);
			System.out.println(" startTime: " + realisticStartTime);

			
			// 2. deal with any children
			System.out.println(" 2");
			if (!child.getChildren().isEmpty()) {
				startTimeChild(child.getChildren(), child.getStartTime());
			}

			// 3. set this child's duration & endTime
			System.out.println(" 3");
			child.setDuration(Math.max(child.getDuration(), maxEndTime(child.getChildren().values())-child.getStartTime()));
			child.setEndTime(child.getStartTime() + child.getDuration());
			
			// 4. put their successors in the queue
			System.out.println(" 4");
			for (Task successor : child.getSuccessors().values()) {
				queue.add(successor);
			}
		}
		
		System.out.println("--->ending");
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
