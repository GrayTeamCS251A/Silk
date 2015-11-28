package Controllers.Project;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

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
		if (String.valueOf(year).length() != 4)
		{
			year = project.getStartTime().get(Calendar.YEAR);
			month = project.getStartTime().get(Calendar.MONTH);
			day = project.getStartTime().get(Calendar.DAY_OF_MONTH);
			
			project.updateInfo(projectName, year, month, day, projectAuthor);
		}
		else
		{
			if (month >= 12)
			{
				Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
				month = localCalendar.get(Calendar.MONTH);
			}
			
			Calendar localCalendar = new GregorianCalendar(year, month, day);
						
			if (day > localCalendar.getMaximum(Calendar.DAY_OF_MONTH))
			{
				System.out.println("Days exceeded!!!");
				day = localCalendar.getMaximum(Calendar.DAY_OF_MONTH);
			}
					
			project.updateInfo(projectName, year, month, day, projectAuthor);
		}
	}

}