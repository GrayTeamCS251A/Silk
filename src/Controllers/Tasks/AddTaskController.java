package Controllers.Tasks;

import java.util.ArrayList;

import Controllers.Controller;
import Entities.Deliverable;
import Entities.DeliverableType;
import Entities.Resource;
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
	
	public void executeAddTask(String taskName, 
			Integer taskDuration, 
			ArrayList<Task> predecessorTask, 
			Task parentTask, 
			ArrayList<Resource> taskResources,
			String taskDescription,
			String taskDeliverable) 
	{
		Deliverable d = new Deliverable();
		
		switch (taskDeliverable)
		{
			case "file": d.setDeliverableName(taskDeliverable);
						d.setDeliverableType(DeliverableType.file);
						break;
			case "presentation": d.setDeliverableName(taskDeliverable);
								d.setDeliverableType(DeliverableType.presentation);
								break;
			case "": d = null;
					break;
		}
		
		project.createTaskFromUI(taskName, taskDuration, predecessorTask, parentTask, taskResources, taskDescription, d);
	}
}