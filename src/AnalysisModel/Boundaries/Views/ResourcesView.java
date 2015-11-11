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

import Entities.Resource;

/**
 * 
 */
public class ResourcesView implements View, ActionListener {

    /**
     * 
     */
	AddResourceController arc = new AddResourceController();
	EditResourceController edit = new EditResourceController();
	DeleteResourceController delete = new DeleteResourceController();
	

    public ResourcesView()
    {
    	JButton b1 = new JButton("Disable middle button");
        b1.setVerticalTextPosition(AbstractButton.CENTER);
        b1.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        b1.setMnemonic(KeyEvent.VK_D);
    }
    
    public void actionPerformed(ActionEvent e) {
//    	if (e.getActionCommand() == "addResource")
//    	{
//        	arc.execute();
//    	}
//    	elif{
//    		edit.execute();
//    	}
//    	elif 
//    	{
//    		delete.execute();
//    	}

    }

    /**
     * 
     */
    private Set<Resource> resources;

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}