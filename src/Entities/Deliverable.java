package Entities;

import java.util.*;

/**
 * 
 */
public class Deliverable {

	public enum DeliverableType {
	    file,
	    presentation
	}
	private String name;
    private DeliverableType type;

    public Deliverable(String name, DeliverableType type) {
    	this.name = name;
    	this.type = type;
    }
    public String getName(){
    	return this.name;
    }
    public DeliverableType getType(){
    	return this.type;
    }
    
    public void setName(String name){
    	this.name = name;
    	
    }
    public void setType(DeliverableType type){
    	this.type = type;
    }
    
}
