package Entities;

import java.util.*;
import jxl.*;
import jxl.write.*;

/**
 * 
 */
public class Schedule extends Observable {

    /**
     * 
     */
    public Schedule() {
    	this.current = false;
    }

    /**
     * Denotes whether the schedule (the Tasks linked by this.tasks are current and should be used)
     */
    private boolean current;

    /**
     * References to all the tasks used to make the schedule 
     */
    private Set<Task> tasks;

    /**
     * @param tasks the collection of Task references, in no particular order
     * @return a reference to this schedule. The tasks will have been assigned starTimes
     */
    public Schedule generateSchedule(Set<Task> tasks) {
        // TODO implement here
    	this.tasks = tasks;
    	this.current = true;
        return null;
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
}