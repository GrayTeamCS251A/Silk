package Controllers.Tasks;

import Controllers.Controller;

/**
 * 
 */
public class DeleteTaskController implements Controller {

    /**
     * 
     */
    public DeleteTaskController() {
    }
	
	public void executeDeleteTask(String taskID) {
		project.deleteTask(taskID);
	}

}