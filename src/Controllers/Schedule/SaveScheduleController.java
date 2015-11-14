package Controllers.Schedule;

import java.util.*;

import AnalysisModel.Boundaries.Panels.Oracle;
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

	@Override
	public void execute(String command, Oracle oracle) {
		// TODO Auto-generated method stub
	}
	
	public void executeSaveSchedule() {
		project.saveSchedule();
	}

}