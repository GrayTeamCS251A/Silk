package Controllers.Resources;

import java.util.*;

import AnalysisModel.Boundaries.Panels.Oracle;
import Controllers.Controller;
import Entities.Resource;
import Entities.ResourceType;

/**
 * 
 */
public class EditResourceController implements Controller {

    /**
     * 
     */
    public EditResourceController() {
    }

	@Override
	public void execute(String command, Oracle oracle) {
	}

	public void executeEditResource(String resourceName, int resourceID, double dailyCost, String resourceType)
	{	
		//Convert resourceType to a ResourceType Entity
		ResourceType r = null;
		
		switch(resourceType) {
			case "labor": r = ResourceType.labor;
				break;
			case "equipment": r = ResourceType.equipment;
				break;
			case "material": r = ResourceType.material;
				break;
		}
		
		project.editResource(resourceName, resourceID, dailyCost, r);
	}
}