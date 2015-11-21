package Controllers.Resources;

import Controllers.Controller;
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


	public void executeEditResource(String resourceName, String resourceID, double dailyCost, String resourceType)
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