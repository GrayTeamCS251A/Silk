package Controllers.Tasks;

import java.util.ArrayList;

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
	
	public void executeAddTask(String taskName, Integer taskDuration, ArrayList<Task> predecessorTask, Task parentTask) {
		project.createTaskFromUI(taskName, taskDuration, predecessorTask, parentTask);
	}
}