package Controllers.Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import AnalysisModel.Boundaries.Panels.Oracle;
import Controllers.Controller;
import Entities.Resource;
import Entities.ResourceType;

/**
 * 
 */
public class AddResourceController implements Controller {

    /**
     * 
     */
    public AddResourceController() {
    }

	@Override
	public void execute(String command, Oracle oracle) {
		// TODO Auto-generated method stub
	}
	
	public void executeAddResource(String resourceName, int resourceID, double dailyCost, String resourceType)
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
		
		project.createResource(resourceName, resourceID, dailyCost, r);
	}
}