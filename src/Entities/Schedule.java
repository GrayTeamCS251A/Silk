package Entities;

import java.lang.reflect.Array;
import java.util.*;
import jxl.*;
import jxl.write.*;
import java.util.Calendar;

/**
 * 
 */
public class Schedule extends Observable {

    public Schedule() {
    	this.current = false;
    	this.startDate = null;
    }

    public Schedule(Calendar startDate) {
    	this.current = false;
    	this.startDate = startDate;
    }

    /**
     * Denotes whether the schedule (the Tasks linked by this.tasks are current and should be used)
     */
    private boolean current;

    /**
     * References to all the tasks used to make the schedule 
     */
    private HashMap<String,Task> tasks;

    /**
     * The start date of the project, used to calculate days for the schedule
     */
    private Calendar startDate;


    /**
     * @param startDate the start date as represented by a Calendar object
     * @param tasks the collection of Task references, in no particular order
     * @return a reference to this schedule. The tasks will have been assigned startTimes
     */
    public Schedule generateSchedule(Calendar startDate, HashMap<String,Task> tasks) {
    	this.startDate = startDate;
    	
    	return this.generateSchedule(tasks);
    }

    /**
     * @param tasks the collection of Task references, in no particular order
     * @return a reference to this schedule. The tasks will have been assigned startTimes
     */
    public Schedule generateSchedule(HashMap<String,Task> tasks) {
    	this.tasks = tasks;
    	this.current = true;
    	System.out.println("starting on month: " + this.startDate.get(Calendar.MONTH));

 
    	// if we have a startTime then great, otherwise we can't proceed 
    	if (this.startDate != null) {
    		// create a super task
    		Task superTask = new Task();
    		
    		// assign the tasks as the children of our super task
    		superTask.setChildren(tasks);

    		// calculate the start times
    		superTask.calculateStartTimes(0);
    		
    		return this;
    	} else { 
    		return null;
    	}
 
    }

    
    
    
    /**
     * @return whether the Schedule is current.
     */
    public boolean isCurrent() {
    	return this.current;
    }

    /**
     * Sets the Schedule to not current
     * @return whether the Schedule is current (it won't be)
     */
    public boolean invalidate() {
    	current = false;
    	return current;
    }


	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	
	
	/**
	 * Does not include children
	 * @return a matrix of strings if the Schedule is current, otherwise returns null.
	 */
	public String[][] toMatrix() {
		return toMatrix(false);
	}

	
	/**
	 * 
	 * @param showChildren shows children if true, else if false
	 * @return a matrix of strings if the Schedule is current, otherwise returns null.
	 */
	public String[][] toMatrix(boolean showChildren)
	{
		if (!this.current || this.tasks == null) {
			return null;
		}

		Collection<Task> tasks = this.tasks.values();
		HashMap<Integer, Set<Task>> matrix = new HashMap<Integer, Set<Task>>();
		int totalDays = 0;

		// for each task, put them on all their days
		for (Task task : tasks) {
			if ((task.getParent() == null) || (task.getParent() != null && showChildren)) {
				for (int day = task.getStartTime(); day < task.getEndTime(); day ++ ) {
					
					// check if there's anything there
					if (matrix.containsKey(day)) {
						matrix.get(day).add(task);
					} else {
						Set<Task> set = new HashSet<Task>();
						set.add(task);
						matrix.put(day, set);
					}
					
					// maintain totalDays
					if (totalDays < day) {
						totalDays = day;
					}
				}
			}
		}
		
		// increment by 1 because we're actually counting total days
		totalDays ++;
	
		// we assume that a successful run of the scheduler leaves no "empty" days 
		
		// convert hash map to a String[][], and make sure it's in order
		// we know how many total days we want
		String matrixString [][] = new String[totalDays][2]; 
		
		for (int day = 0; day < totalDays && matrix.containsKey(day); day ++) {
			Calendar date = (GregorianCalendar)this.startDate.clone();
			date.add(Calendar.DAY_OF_YEAR, day);
			
			// figure out date
			String dateString = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH)+1) + "/" + date.get(Calendar.DAY_OF_MONTH);
			
			// compile tasks		
			String taskString = "";
			for (Task task : matrix.get(day)){
				taskString += task.getID() + ",";
			}
			taskString = taskString.substring(0, taskString.length()-1);
			
			matrixString [day][0] = dateString;
			matrixString [day][1] = taskString;
		}

		
		return matrixString;
		
	}

}