package Controllers.Project;

import java.util.*;

import AnalysisModel.Boundaries.Panels.Oracle;
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

	@Override
	public void execute(String command, Oracle oracle) {
		// TODO Auto-generated method stub
		
	}
	
	public void executeEditProject(String projectName, String startTime, String projectAuthor) {
		project.updateInfo(projectName, startTime, projectAuthor);
	}

}