package Controllers.Tasks;

import java.util.*;

import AnalysisModel.Boundaries.Panels.Oracle;
import Controllers.Controller;
import Entities.Task;

/**
 * 
 */
public class DeleteTaskController implements Controller {

    /**
     * 
     */
    public DeleteTaskController() {
    }

	@Override
	public void execute(String command, Oracle oracle) {
		// TODO Auto-generated method stub
	}
	
	public void executeDeleteTask(Object taskToDelete) {
		Task t = (Task) taskToDelete;
		project.deleteTask(t.getTaskID());
	}

}