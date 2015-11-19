package Controllers.Project;


import Controllers.*;

/**
 * 
 */
public class EditProjectController implements Controller {

    /**
     * 
     */
    public EditProjectController() {
    }
	
	public void executeEditProject(String projectName, Integer year, Integer month, Integer day, String projectAuthor) {
		project.updateInfo(projectName, year, month, day, projectAuthor);
	}

}