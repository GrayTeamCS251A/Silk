package Controllers;

import java.util.*;

import AnalysisModel.Boundaries.Panels.Oracle;
import Entities.Project;

/**
 * 
 */
public interface Controller {

    /**
     * 
     */
    public static final Project project = new Project();

    /**
     * @param command 
     * @param oracle
     */
    public void execute(String command, Oracle oracle);

}