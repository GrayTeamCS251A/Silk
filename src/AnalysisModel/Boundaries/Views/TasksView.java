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
public abstract class TasksView implements View {
	private Project project;
	private JTree tree;

    public TasksView(Project project,JTree tree) {
    	this.project=project;
    	this.tree=tree;
    	project.addObserver(this);   	
    }

    public void update(Observable o, Object arg){
    	HashMap<String,Task> list =project.getTasks();
    	List<Task> tasks = new ArrayList<Task>(list.values());
    	MainUI.displayTree(tree,tasks);	
    }
		
}