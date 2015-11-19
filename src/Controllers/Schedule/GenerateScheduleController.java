package Controllers.Schedule;

import Controllers.Controller;

/**
 * 
 */
public class GenerateScheduleController implements Controller {

    /**
     * 
     */
    public GenerateScheduleController() {
    }

    /**
     * 
     */
    public void generateSchedule() {
        project.generateSchedule();
    }
}