package Controllers.Tasks;

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
	
	public void executeEditTask(String taskID, Integer taskDuration, Task predecessorTask, Task parentTask) {
		project.editTask(taskID, taskDuration, predecessorTask, parentTask);
	}

}