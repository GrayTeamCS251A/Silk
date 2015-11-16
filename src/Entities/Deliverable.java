package Entities;

import java.util.*;

/**
 * 
 */
public class Deliverable {

	
	private String name;
    private DeliverableType type;
    
    public Deliverable(){
    	
    }

    public Deliverable(String name, DeliverableType type) {
    	this.name = name;
    	this.type = type;
    }
    public String getDeliverableName(){
    	return this.name;
    }
    public DeliverableType getDeliverableType(){
    	return this.type;
    }
    
    public void setDeliverableName(String name){
    	this.name = name;
    	
    }
    public void setDeliverableType(DeliverableType type){
    	this.type = type;
    }
    
}
