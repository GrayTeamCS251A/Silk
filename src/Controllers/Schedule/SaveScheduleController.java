package Controllers.Schedule;

import java.io.IOException;

import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
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
	
	public WritableWorkbook executeSaveSchedule(String dest) throws IOException, WriteException {
		return project.saveSchedule(dest);
	}

}