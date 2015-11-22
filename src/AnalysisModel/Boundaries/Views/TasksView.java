package AnalysisModel.Boundaries.Views;

import java.util.*;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import Entities.Project;
import Entities.Task;
import GUI.MainUI;

/**
 * 
 */
public class TasksView implements View {
	private Project project;
	private JTree tree=new JTree();

    public TasksView(Project project,JTree tree) {
    	this.project=project;
    	this.tree=tree;
    	project.addObserver(this);   	
    }
    
    @Override
    public void update(Observable o, Object arg){
    	// TODO Auto-generated method stub
    	HashMap<String,Task> list =project.getTasks();
    	MainUI.displayTree(tree,list);
    }
		
}