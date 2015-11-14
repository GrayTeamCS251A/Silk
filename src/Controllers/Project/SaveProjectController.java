package Controllers.Project;

import java.io.File;
import java.util.*;

import AnalysisModel.Boundaries.Panels.Oracle;
import Controllers.*;
import Entities.Project;

/**
 * 
 */
public class SaveProjectController implements Controller {

    /**
     * 
     */
    public SaveProjectController() {
    }

    /**
     * @param project 
     * @return
     */
    private File convert(Project project, String fileName) {
        // Convert to XML format to the specified fileName
        return null;
    }

	@Override
	public void execute(String command, Oracle oracle) {
		// TODO Auto-generated method stub
		
	}
	
	public void executeSaveProject(String fileName) {
		convert(project, fileName);
	}

}