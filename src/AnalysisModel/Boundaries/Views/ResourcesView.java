package AnalysisModel.Boundaries.Views;
import Controllers.*;
import Controllers.Resources.AddResourceController;
import Controllers.Resources.DeleteResourceController;
import Controllers.Resources.EditResourceController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTree;

import Entities.Project;
import Entities.Resource;
import Entities.Task;
import GUI.MainUI;

/**
 * 
 */
public class ResourcesView implements View{
	private Project project;
	private JList resourceList =new JList();
    
	public ResourcesView(Project project, JList resourceList)
    {
		this.project=project;
		this.resourceList=resourceList;
		project.addObserver(this);   
    }
    
  
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
    	HashMap<String,Resource> list =project.getResources();
    	MainUI.displayRes(resourceList,list);	

	}

}