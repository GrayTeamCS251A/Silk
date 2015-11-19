package Controllers.Schedule;

import Controllers.Controller;

/**
 * 
 */
public class SaveScheduleController implements Controller {

    /**
     * 
     */
    public SaveScheduleController() {
    }
	
	public void executeSaveSchedule() {
		project.saveSchedule();
	}

}