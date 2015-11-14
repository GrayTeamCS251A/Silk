package Controllers.Project;

import java.io.File;
import java.util.*;

import AnalysisModel.Boundaries.Panels.Oracle;
import Controllers.Controller;

/**
 * 
 */
public class LoadProjectController implements Controller {

    /**
     * 
     */
    public LoadProjectController() {
    }

    /**
     * @param file
     */
    private void parse(File file) {
		//XML grab the project info
		//project.updateInfo(projectName, startTime, projectAuthor);
		
		//XML grab the project other stuff
		//project.updateProject(resources, tasks, schedule);
    }

	@Override
	public void execute(String command, Oracle oracle) {
		// TODO Auto-generated method stub
	}
	
	public void executeLoadProject(File projectFile) {
    	project.clear();
    	
    	parse(projectFile);
	}

}