package Controllers.Schedule;


import Controllers.Controller;
import Entities.Project;

/**
 * 
 */
public abstract class ViewScheduleController implements Controller {

    /**
     * 
     */
    public ViewScheduleController() {
    }
    
    public Project getProject()
    {
    	return project.getWholeProject();
    }

}