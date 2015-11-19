package Controllers.Resources;

import Controllers.Controller;
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
		
		project.createResource(resourceName, dailyCost, r);
	}
}