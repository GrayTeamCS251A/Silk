package Entities;

import java.util.*;

/**
 * 
 */
public class Task extends Observable {

    /**
     * 
     */
    public Task() {
    }

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private int duration;

    /**
     * 
     */
    private double startTime;

    /**
     * 
     */
    private double endTime;

    /**
     * 
     */
    private double percentCompleted;

    /**
     * 
     */
    private Integer taskID;

    /**
     * 
     */
    private Set<Deliverable> deliverables;

    /**
     * 
     */
    private Set<Resource> requiredResources;

    /**
     * 
     */
    private Set<Task> predecessors;

    /**
     * 
     */
    private Task parent;

    /**
     * @param info
     */
    public void updateTask(String info) {
        // TODO implement here
    }

    /**
     * @return
     */
    public List<Task> calculateCriticalPath() {
        // TODO implement here
        return null;
    }

}