package Controllers.Resources;

import java.util.*;

import AnalysisModel.Boundaries.Panels.Oracle;
import Controllers.Controller;
import Entities.Resource;

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
	
	public void executeDeleteResource(Object resourceToDelete)
	{
		Resource r = (Resource) resourceToDelete;
		project.deleteResource(r.getResourceID());
	}

}