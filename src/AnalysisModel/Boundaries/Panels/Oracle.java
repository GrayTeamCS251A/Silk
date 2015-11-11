package AnalysisModel.Boundaries.Panels;

import java.util.*;

/**
 * 
 */
public interface Oracle {

    /**
     * @param query 
     * @return
     */
    public String ask(String query);

    /**
     * @param query 
     * @param obj 
     * @return
     */
    public Object ask(String query, Object obj);

}