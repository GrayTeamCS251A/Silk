package Controllers.Project;


import java.util.Calendar;

import Controllers.*;

/**
 * 
 */
public class EditProjectController implements Controller {

    /**
     * 
     */
    public EditProjectController() {
    }
	
	public void executeEditProject(String projectName, Integer year, Integer month, Integer day, String projectAuthor) {
		//Know that user did not input correct time
		//Get original start time
		if (year == 0)
		{
			year = project.getStartTime().get(Calendar.YEAR);
			month = project.getStartTime().get(Calendar.MONTH);
			day = project.getStartTime().get(Calendar.DAY_OF_MONTH);
			
			project.updateInfo(projectName, year, month, day, projectAuthor);
		}
		else
		{
			project.updateInfo(projectName, year, month, day, projectAuthor);
		}
	}

}