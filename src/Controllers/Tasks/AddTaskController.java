package Controllers.Tasks;

import java.util.*;

import AnalysisModel.Boundaries.Panels.Oracle;
import Controllers.Controller;
import Entities.Task;

/**
 * 
 */
public class AddTaskController implements Controller {

    /**
     * 
     */
    public AddTaskController() {
    }

	@Override
	public void execute(String command, Oracle oracle) {
		// TODO Auto-generated method stub
	}
	
	public void executeAddTask(String taskName, Integer taskID, Integer taskDuration, Task predecessorTask, Task parentTask) {
		project.createTask(taskName, taskID, taskDuration, predecessorTask, parentTask);
	}

}