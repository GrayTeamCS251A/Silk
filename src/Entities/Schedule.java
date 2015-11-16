package Entities;

import java.util.*;

/**
 * 
 */
public class Schedule extends Observable {

    /**
     * 
     */
    public Schedule() {
    	this.isCurrent = false;
    }

    /**
     * 
     */
    private boolean isCurrent;

    /**
     * @param tasks 
     * @return
     */
    public Schedule generateSchedule(Set<Task> tasks) {
        // TODO implement here
        return null;
    }
    
    public boolean getIsCurrent() {
    	return isCurrent;
    }

}