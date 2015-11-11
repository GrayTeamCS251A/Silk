package Entities;

import java.util.*;

/**
 * 
 */
public class Project {

    /**
     * 
     */
    public Project() {
    }

    /**
     * 
     */
    private Time startTime;

    /**
     * 
     */
    private Set<Resource> resources;

    /**
     * 
     */
    private Set<Task> tasks;

    /**
     * 
     */
    private Schedule schedule;

    /**
     * @param info
     */
    public void Project(Object info) {
        // TODO implement here
    }

    /**
     * @param info 
     * @param resources 
     * @param tasks 
     * @param schedule
     */
    public void Project(Object info, Object resources, Object tasks, Schedule schedule) {
        // TODO implement here
    }

    /**
     * 
     */
    public void clear() {
        // TODO implement here
    }

    /**
     * @return
     */
    public Object getProjectInfo() {
        // TODO implement here
        return null;
    }

    /**
     * @param info
     */
    public void updateInfo(Object info) {
        // TODO implement here
    }

    /**
     * @param resources 
     * @param tasks 
     * @param schedule
     */
    public void updateProject(List<Resource> resources, List<Task> tasks, Schedule schedule) {
        // TODO implement here
    }

    /**
     * 
     */
    public void getWholeProject() {
        // TODO implement here
    }

    /**
     * @param info
     */
    public void createTask(Object info) {
        // TODO implement here
    }

    /**
     * 
     */
    public void createResource() {
        // TODO implement here
    }

    /**
     * @return
     */
    public List<Resource> getResources() {
        // TODO implement here
        return null;
    }

    /**
     * @param info
     */
    public void editResource(Resource info) {
        // TODO implement here
    }

    /**
     * 
     */
    public void deleteResource() {
        // TODO implement here
    }

    /**
     * @param taskName 
     * @return
     */
    public Task getTask(String taskName) {
        // TODO implement here
        return null;
    }

    /**
     * @param taskID 
     * @param info
     */
    public void editTask(Integer taskID, String info) {
        // TODO implement here
    }

    /**
     * 
     */
    public void getTasks() {
        // TODO implement here
    }

    /**
     * 
     */
    public void deleteTask() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getSchedule() {
        // TODO implement here
    }

    /**
     * 
     */
    public void generateSchedule() {
        // TODO implement here
    }

    /**
     * @return
     */
    private List<Task> calculateCriticalPath() {
        // TODO implement here
        return null;
    }

    /**
     * @param schedule
     */
    public void saveSchedule(Schedule schedule) {
        // TODO implement here
    }

}