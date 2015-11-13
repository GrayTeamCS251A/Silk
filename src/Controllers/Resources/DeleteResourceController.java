package Controllers.Resources;

import java.util.*;

import AnalysisModel.Boundaries.Panels.Oracle;
import Controllers.Controller;

/**
 * 
 */
public class DeleteResourceController implements Controller {

    /**
     * 
     */
    public DeleteResourceController() {
    }

	@Override
	public void execute(String command, Oracle oracle) {
		// TODO Auto-generated method stub
	}
	
	public void executeDeleteResource(int resourceID)
	{
		project.deleteResource(resourceID);
	}

}