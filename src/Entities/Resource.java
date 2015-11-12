package Entities;

import java.util.*;


public class Resource extends Observable {
	
    private String name;
    private double dailyCost;
    private int resourceID;
    private ResourceType type;

    public Resource(int resourceID, String name, double dailyCost, ResourceType type){
        this.resourceID= resourceID;
        this.name = name;
        this.dailyCost = dailyCost;
        this.type = type;
    }
    
    public Resource() {
           }



    public int getResourceID(){
         return resourceID;
     }
     
     public String getname(){
         return name;
     }
     public double getDailyCost(){
         return dailyCost;
     }
     
     public ResourceType getResourceType(){
         return type;
     }
     
     public void getResourceInfo() {
        getResourceID();
        getname();
        getDailyCost();
        getResourceType();
    }

     public void setResourceID(int resourceID){
        this.resourceID = resourceID;
    }
    
     public void setname(String name){
        this.name = name;
    }
    
     public void setDailyCost(double dailyCost){
        this.dailyCost = dailyCost;
    }
    
     public void setResourceType(ResourceType type){
        this.type = type;
    }
    
     public void updateResourceInfo(int resourceID,String name,double DailyCost,ResourceType type){
        
        	setResourceID(resourceID);
        if (name != null)
        	setname
        	(name);
        
        	setDailyCost(dailyCost);
        if (type != null)
        	setResourceType(type);
    }

}
