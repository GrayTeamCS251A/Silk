package Controllers.Tasks;

import java.util.ArrayList;

import Controllers.Controller;
import Entities.Resource;
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
	
	public void executeEditTask(String taskName, 
			String taskID, 
			Integer taskDuration, 
			ArrayList<Task> predecessorTask, 
			Task parentTask, 
			ArrayList<Resource> taskResources,
			String taskDescription) {
		project.editTask(taskName, taskID, taskDuration, predecessorTask, parentTask, taskResources, taskDescription);
	}

}