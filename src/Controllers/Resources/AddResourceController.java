package Controllers.Resources;

import java.util.*;

import AnalysisModel.Boundaries.Panels.Oracle;
import Controllers.Controller;

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
		//project.addResource();
	}

	
	public void execute(String command) {
		System.out.println("Resouce Name:"+ command);
	}
}