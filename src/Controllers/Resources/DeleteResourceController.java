package Controllers.Resources;


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
	
	public void executeDeleteResource(String resourceID)
	{
		project.deleteResource(resourceID);
	}

}