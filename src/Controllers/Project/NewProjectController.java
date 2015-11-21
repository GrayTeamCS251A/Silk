package Controllers.Project;

import Controllers.Controller;
import Entities.Project;

/**
 * 
 */
public class NewProjectController implements Controller {

    /**
     * 
     */
    public NewProjectController() {
    }

	public void executeNewProject(String projectName, Integer year, Integer month, Integer day, String projectAuthor){
		project.updateInfo(projectName, year, month, day, projectAuthor);
	}
	
	public Project getProject(){
		return project;
	}
}