package Entities;

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
        // TODO implement here
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

    	// if we have a startTime then great, otherwise we can't proceed 
    	if (this.startDate != null) {
    		// create a super task
    		Task superTask = new Task();
    		
    		// assign the tasks as the children of our super task
    		superTask.setChildren(tasks);

    		// calculate the start times
    		superTask.calculateStartTimes(0);
    		
    		// generate schedule to output
    		// TODO
    		
    		return null;    		
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

    public WritableWorkbook toXLS() {
    	if (this.current) {
    		/** export the schedule **/
    	} else {
    		/** don't **/
    	}

    	return null;    	
    }

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
}