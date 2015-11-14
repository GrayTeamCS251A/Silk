package Controllers.Tasks;

import java.util.*;

import AnalysisModel.Boundaries.Panels.Oracle;
import Controllers.Controller;
import Entities.Task;

/**
 * 
 */
public class EditTaskController implements Controller {

    /**
     * 
     */
    public EditTaskController() {
    }

	@Override
	public void execute(String command, Oracle oracle) {
		// TODO Auto-generated method stub
		
	}
	
	public void executeEditTask(String taskName, Integer taskID, Integer taskDuration, Task predecessorTask, Task parentTask) {
		project.editTask(taskName, taskID, taskDuration, predecessorTask, parentTask);
	}

}