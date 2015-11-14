package Controllers.Schedule;

import java.util.*;

import AnalysisModel.Boundaries.Panels.Oracle;
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

	@Override
	public void execute(String command, Oracle oracle) {
		// TODO Auto-generated method stub
		
	}

}