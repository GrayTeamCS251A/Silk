package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JTree;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JList;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import jxl.write.WritableWorkbook;

import org.jgraph.JGraph;
import org.jgraph.graph.Edge;

import AnalysisModel.Boundaries.Views.ResourcesView;
import AnalysisModel.Boundaries.Views.TasksView;

//import org.jgraph.JGraph;


import Controllers.Project.EditProjectController;
import Controllers.Project.LoadProjectController;
import Controllers.Project.NewProjectController;
import Controllers.Project.SaveProjectController;
import Controllers.Resources.AddResourceController;
import Controllers.Resources.DeleteResourceController;
import Controllers.Resources.EditResourceController;
import Controllers.Schedule.GenerateScheduleController;
import Controllers.Schedule.SaveScheduleController;
import Controllers.Schedule.ViewScheduleAsGraphController;
import Controllers.Schedule.ViewScheduleAsTableController;
import Controllers.Tasks.AddTaskController;
import Controllers.Tasks.DeleteTaskController;
import Controllers.Tasks.EditTaskController;
import Entities.Deliverable;
import Entities.DeliverableType;
import Entities.Project;
import Entities.Resource;
import Entities.ResourceType;
import Entities.Task;
import graph.Arrow;
import graph.Graph;
import graph.GraphUtils;
import graph.Node;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
public class MainUI{

	private JFrame frame;
	private JButton btnAddResource;
	private JButton btnDeleteResource;
	private JButton btnEditResource;
	private JButton btnAddTask;
	private JButton btnDeleteTask;
	private JButton btnEditTask;
	private JButton btnGenerateSchedule;
	private JButton btnSaveSchedule;
	private JButton btnTable;
	private JButton btnGraph;
	private JMenuItem New;
	private JMenuItem save;
	private JMenuItem load;
	private JMenuItem Edit;
	private JTable table = new JTable();
	private JList resourceList = new JList();
	private JTree taskTree = new JTree();
	private Project project;
	private Graph scheduleGraph;


	private ResUI addRes = new ResUI("Add Resource");
	private ResUI editRes = new ResUI("Edit Resource");
	
	private TaskUI addTask = new TaskUI("Add Task","Use Ctrl+Left Click to highlight desired resources or predecessors");
	private TaskUI editTask = new TaskUI("Edit Task","Use Ctrl+Left Click to highlight desired resources or predecessors");
	
	private ProjectUI newProject = new ProjectUI("New Project");
	private ProjectUI editProject = new ProjectUI("Edit Project");
	
	private AddResourceController addResourceController;
	private EditResourceController editResourceController;
	private DeleteResourceController deleteResourceController;
	
	private AddTaskController addTaskController;
	private EditTaskController editTaskController;
	private DeleteTaskController deleteTaskController;
	
	private NewProjectController newProjectController;
	private EditProjectController editProjectController;
	private SaveProjectController saveProjectController;
	private LoadProjectController loadProjectController;
	private JScrollPane scheduleScrollPane;
	
	private SaveScheduleController saveScheduleController;
	private GenerateScheduleController generateScheduleController;
	private ViewScheduleAsGraphController viewScheduleAsGraphController;
	private ViewScheduleAsTableController  viewScheduleAsTableController;
	
	private ResourcesView resourcesView ;
	private TasksView tasksView;	
	/**
	 * Launch the application.
	 */
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
		initAddResAction();
		initEditResAction();
		initDeleteResAction();		
		initAddTaskAction();
		initEditTaskAction();
		initDeleteTaskAction();		
		initScheduleAction();	
		initNewProjectAction();
		initEditProjectAction();
		initSaveAndLoad();
		initControllerAndView();

		
		Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
		project.updateInfo("", localCalendar.get(Calendar.YEAR), localCalendar.get(Calendar.MONTH) + 1, localCalendar.get(Calendar.DAY_OF_MONTH), "");
		btnTable.setEnabled(false);
		btnGraph.setEnabled(false);
		scheduleScrollPane.setViewportView(new JPanel());	
		project.getSchedule().invalidate();

		//resouceTest();
		//treeTest();
		//tableTest();
		//graphTest();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 995, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 979, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu FileMenu = new JMenu("FILE");
		menuBar.add(FileMenu);
		
		New = new JMenuItem("New");
		FileMenu.add(New);
		
		save = new JMenuItem("Save");
		FileMenu.add(save);
		
		load = new JMenuItem("Load");
		FileMenu.add(load);
		
		Edit = new JMenuItem("Edit");
		FileMenu.add(Edit);
		
		JPanel Resource_panel = new JPanel();
		Resource_panel.setBackground(Color.GRAY);
		Resource_panel.setBounds(10, 32, 287, 394);
		frame.getContentPane().add(Resource_panel);
		Resource_panel.setLayout(null);
		
		btnAddResource = new JButton("Add Resource");
		btnAddResource.setBounds(10, 326, 115, 23);
		Resource_panel.add(btnAddResource);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 267, 304);
		Resource_panel.add(scrollPane);
		
		scrollPane.setViewportView(resourceList);
		
		btnDeleteResource = new JButton("Delete Resource");
		btnDeleteResource.setBounds(10, 360, 115, 23);
		Resource_panel.add(btnDeleteResource);
		
		btnEditResource = new JButton("Edit Resource");
		btnEditResource.setBounds(149, 326, 128, 23);
		Resource_panel.add(btnEditResource);
		
		JPanel Task_panel = new JPanel();
		Task_panel.setBackground(Color.GRAY);
		Task_panel.setBounds(307, 32, 287, 394);
		frame.getContentPane().add(Task_panel);
		Task_panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 11, 267, 304);
		Task_panel.add(scrollPane_1);
		
		scrollPane_1.setViewportView(taskTree);
		
		btnAddTask = new JButton("Add Task");
		btnAddTask.setBounds(10, 326, 105, 23);
		Task_panel.add(btnAddTask);
		
		btnEditTask = new JButton("Edit Task");
		btnEditTask.setBounds(172, 326, 105, 23);
		Task_panel.add(btnEditTask);
		
		btnDeleteTask = new JButton("Delete Task");
		btnDeleteTask.setBounds(10, 360, 105, 23);
		Task_panel.add(btnDeleteTask);
		
		JPanel Schedule_panel = new JPanel();
		Schedule_panel.setBackground(Color.GRAY);
		Schedule_panel.setBounds(604, 32, 365, 394);
		frame.getContentPane().add(Schedule_panel);
		Schedule_panel.setLayout(null);
		
		scheduleScrollPane = new JScrollPane();
		scheduleScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scheduleScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scheduleScrollPane.setBounds(10, 11, 345, 304);
		Schedule_panel.add(scheduleScrollPane);
		
		btnGenerateSchedule = new JButton("Generate Schedule");
		btnGenerateSchedule.setBounds(10, 326, 166, 23);
		Schedule_panel.add(btnGenerateSchedule);
		
		btnSaveSchedule = new JButton("Save Schedule");
		btnSaveSchedule.setBounds(10, 360, 166, 23);
		Schedule_panel.add(btnSaveSchedule);
		
		btnGraph = new JButton("Graph");
		btnGraph.setBounds(270, 360, 84, 23);
		Schedule_panel.add(btnGraph);
		
		btnTable = new JButton("Table");
		btnTable.setBounds(186, 360, 84, 23);
		Schedule_panel.add(btnTable);
		
	}
	
	
	private void initAddResAction(){

		addRes.addConfirmListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addResourceController.executeAddResource(addRes.getTextName(), 
						Double.parseDouble(addRes.getTextCost()), 
						addRes.getTextType());
				addRes.Reset();      
				addRes.setVisible(false);
				btnTable.setEnabled(false);
				btnGraph.setEnabled(false);
				scheduleScrollPane.getViewport().setBackground(Color.GRAY);
				project.getSchedule().invalidate();
			}
						
		});
		
		btnAddResource.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            if (!addRes.isVisible()) {
	            	addRes.setVisible(true);
	            }
	         }
	      });
		
		addRes.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //addRes.Reset();        	
            }
        });
	
		addRes.addCancelListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub 
				addRes.setVisible(false);
			}
						
		});
	}

	private void initEditResAction(){
		editRes.addConfirmListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Resource r=(Resource) resourceList.getSelectedValue();
				editResourceController.executeEditResource(editRes.getTextName(), 
						r.getResourceID(), 
						Double.parseDouble(editRes.getTextCost()), 
						editRes.getTextType());
				//System.out.println(editRes.getTextName());
				editRes.setVisible(false);
//				btnTable.setEnabled(false);
//				btnGraph.setEnabled(false);
			}
						
		});
		
		btnEditResource.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
				Resource r=(Resource) resourceList.getSelectedValue();
	        	editRes.Reset();
	        	editRes.fill(r);  
	            if (!editRes.isVisible()) {
	            	editRes.setVisible(true);
	            }
	         }
	      });
		
		editRes.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	//editRes.Reset();       	
            }
        });
	
		editRes.addCancelListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub 
				editRes.setVisible(false);
			}
						
		});
	}
	
	private void initDeleteResAction(){
		btnDeleteResource.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int x = JOptionPane.showConfirmDialog(null, "Delete this Resource?","Alert",JOptionPane.YES_NO_OPTION);
				if(x==1){
					System.out.println("Aborted");
				}else{
					int selectedIndex = resourceList.getSelectedIndex();
					Resource r=(Resource) resourceList.getSelectedValue();
					deleteResourceController.executeDeleteResource(r.getResourceID());
//					btnTable.setEnabled(false);
//					btnGraph.setEnabled(false);
				}
			}});
	}

	private void initEditTaskAction(){
		
		editTask.addConfirmListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub  
				//need to figure out how to extract selected Task from JTree
			    DefaultMutableTreeNode node = (DefaultMutableTreeNode)
	                       taskTree.getLastSelectedPathComponent();		
			    Task t = (Task) node.getUserObject();
				editTaskController.executeEditTask(editTask.getTaskName(), 
						t.getID(), 
						Integer.parseInt(editTask.getTaskDuration()),
						editTask.getPredecessorTask(),
						editTask.getParentTask(),
						editTask.getResouces(),
						editTask.getDescription(),
						editTask.getDeliverableList());
				editTask.setVisible(false);
				btnTable.setEnabled(false);
				btnGraph.setEnabled(false);
								
				scheduleScrollPane.setViewportView(new JPanel());			
				project.getSchedule().invalidate();

			}
						
		});
		
		btnEditTask.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
								taskTree.getLastSelectedPathComponent();		
				Task t = (Task) node.getUserObject();
				editTask.Reset();
				editTask.fillEdit(t,project);
	            if (!editTask.isVisible()) {
	            	editTask.setVisible(true);
	            }
	         }
	      });
		
		editTask.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {     	
            }
        });
		
		editTask.addCancelListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub 
				editTask.setVisible(false);
			}
						
		});
	}
		
	private void initAddTaskAction(){
		
		addTask.addConfirmListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub 
				addTaskController.executeAddTask(addTask.getTaskName(), 
						Integer.parseInt(addTask.getTaskDuration()),
						addTask.getPredecessorTask(),
						addTask.getParentTask(),
						addTask.getResouces(),
						addTask.getDescription(),
						addTask.getDeliverableList()
						);
				addTask.setVisible(false);
				btnTable.setEnabled(false);
				btnGraph.setEnabled(false);
				scheduleScrollPane.setViewportView(new JPanel());
				project.getSchedule().invalidate();
			}
						
		});
		
		btnAddTask.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	        	 DefaultMutableTreeNode node = (DefaultMutableTreeNode)
							taskTree.getLastSelectedPathComponent();
	        	 Task selectedTask=new Task();
	        	 if(node.getUserObject().getClass().equals(String.class)){
	        		 selectedTask=null;
	        	 }else
	        		selectedTask = (Task) node.getUserObject();
	        	addTask.fillAdd(project,selectedTask);
	            if (!addTask.isVisible()) {
	            	addTask.setVisible(true);
	            }
	         }
	      });
		
		addTask.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {     	
            }
        });
		
		addTask.addCancelListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub 
				addTask.setVisible(false);
			}
						
		});
	}
		
	private void initDeleteTaskAction(){
		btnDeleteTask.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int x = JOptionPane.showConfirmDialog(null, "Delete this task?","Alert",JOptionPane.YES_NO_OPTION);
				if(x==1){
					System.out.println("Aborted");
				}else{
				    DefaultMutableTreeNode node = (DefaultMutableTreeNode)
		                       taskTree.getLastSelectedPathComponent();		
				    Task t = (Task) node.getUserObject();
					TreePath tp = taskTree.getSelectionPath();
					deleteTaskController.executeDeleteTask(t.getID());
					taskTree.removeSelectionPath(tp);
					btnTable.setEnabled(false);
					btnGraph.setEnabled(false);
					scheduleScrollPane.setViewportView(new JPanel());			
					project.getSchedule().invalidate();
				}
			}});
	}

	private void initScheduleAction(){
		btnGenerateSchedule.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnTable.setEnabled(true);
				btnGraph.setEnabled(true);		
				generateScheduleController.generateSchedule();
				project.getSchedule().isCurrent();
				scheduleScrollPane.getViewport().setBackground(Color.WHITE);
				displayTable();
				scheduleScrollPane.setViewportView(table);
			}});
		
		btnSaveSchedule.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                JFileChooser saveFile = new JFileChooser();
                saveFile.setAcceptAllFileFilterUsed(false);
            	FileNameExtensionFilter filter = new FileNameExtensionFilter("xls file", "xls");
            	saveFile.addChoosableFileFilter(filter);
                int returnVal = saveFile.showSaveDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                    	//String fileName = saveFile.getSelectedFile().getName();
                    	String dest = saveFile.getSelectedFile().getAbsolutePath();
                    	WritableWorkbook src = saveScheduleController.executeSaveSchedule(dest);
                    	
                    	//File src = new File("");  //File returned by execute from saveSchedule 	
                    	//File dest = new File(saveFile.getSelectedFile().getAbsolutePath());
                    	//Files.copy(src.toPath(),dest.toPath(), REPLACE_EXISTING);
                    	                    	
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
			}});
		
		btnGraph.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					
					displayGraph(scheduleGraph, scheduleScrollPane);				
			}});
		
		btnTable.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub			
				displayTable();
				scheduleScrollPane.setViewportView(table);								
			}});
	}

	private void initNewProjectAction(){
		newProject.addConfirmListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String startTimeString = newProject.getStartField();
				Integer year = 0;
				Integer month = 0;
				Integer day = 0;

				if (startTimeString.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}"))
				{
					String[] splitLine = startTimeString.split("-");
					year = Integer.parseInt(splitLine[0]);
					month = Integer.parseInt(splitLine[1]);
					day = Integer.parseInt(splitLine[2]);
				}
				
				newProjectController.executeNewProject(newProject.getNameField(), 
						year, month, day, 
						newProject.getAuthorField());
				//newProject.Reset();      
				newProject.setVisible(false);
			}
						
		});
		
		New.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	            if (!newProject.isVisible()) {
	            	newProject.setVisible(true);
	            }
	         }
	      });
		
		newProject.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	//newProject.Reset();        	
            }
        });
	
		newProject.addCancelListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newProject.setVisible(false);
			}					
		});
	}
	
	private void initEditProjectAction(){
		
		editProject.addConfirmListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//editProject.fill(project);
				
				String startTimeString = editProject.getStartField();
				
				Integer year = 0;
				Integer month = 0;
				Integer day = 0;
				
				if (startTimeString.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}"))
				{
					String[] splitLine = startTimeString.split("-");
					year = Integer.parseInt(splitLine[0]);
					month = Integer.parseInt(splitLine[1]);
					day = Integer.parseInt(splitLine[2]);
				}
				
				editProjectController.executeEditProject(editProject.getNameField(), 
						year, month, day, 
						editProject.getAuthorField());
				editProject.setVisible(false);
			}
						
		});
		
		Edit.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	        	 	editProject.Reset();
					editProject.fill(project); 
	            if (!editProject.isVisible()) {
	            	editProject.setVisible(true);
	            }
	         }
	      });
		
		editProject.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	     	
            }
        });
		
		editProject.addCancelListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				editProject.setVisible(false);
			}					
		});
	}

	private void initSaveAndLoad(){
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                JFileChooser saveFile = new JFileChooser();
                saveFile.setAcceptAllFileFilterUsed(false);
            	FileNameExtensionFilter filter = new FileNameExtensionFilter("xml file", "xml");
            	saveFile.addChoosableFileFilter(filter);
                int returnVal = saveFile.showSaveDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                    	//String fileName = saveFile.getSelectedFile().getName();
                    	String dest = saveFile.getSelectedFile().getAbsolutePath();
                    	saveProjectController.executeSaveProject(dest);
               	
                    	//File src = new File("");  //File returned by executeSaveProject      	
                    	//File dest = new File(saveFile.getSelectedFile().getAbsolutePath());
                    	//Files.copy(src.toPath(),dest.toPath(), REPLACE_EXISTING);
                    	                    	
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
			}					
		});
		
		load.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			      JFileChooser openFile = new JFileChooser();
			      openFile.setAcceptAllFileFilterUsed(false);
              	  FileNameExtensionFilter filter = new FileNameExtensionFilter("xml file", "xml");
              	  openFile.addChoosableFileFilter(filter);
			      int returnVal = openFile.showOpenDialog(null);
	                if (returnVal == JFileChooser.APPROVE_OPTION) {
	                    try {
	                    	File projectFile = openFile.getSelectedFile();
	                    	loadProjectController.executeLoadProject(projectFile);
	                    } catch (Exception ex) {
	                        ex.printStackTrace();
	                    }
	                }
			}					
		});
				
	}

	
	
	public static void displayTree(JTree tree, HashMap<String, Task> mapTask){
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");		
		helper(root,mapTask);					
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		tree.setModel(treeModel);
		
	}
		
	public static void helper(DefaultMutableTreeNode root, Map<String, Task> mapTask){
		if(mapTask.isEmpty()){
			return;
		}
	
		for (Task t : mapTask.values())
		{		
		 if(!(root.getUserObject() instanceof String) || t.getParent() ==null )
		 {
			 
			DefaultMutableTreeNode aTask = new DefaultMutableTreeNode(t);	  	  
			root.add(aTask);
			aTask.add(new DefaultMutableTreeNode("ID:"+t.getID()));
			aTask.add(new DefaultMutableTreeNode("Duration:"+t.getDuration()));
			aTask.add(new DefaultMutableTreeNode("Description:"+t.getDescription()));
			aTask.add(new DefaultMutableTreeNode("Complete:"+t.getPercentCompleted()));	

			if(!t.getDeliverables().isEmpty()){
				DefaultMutableTreeNode Res = new DefaultMutableTreeNode("Deliverables");	
				aTask.add(Res);	
				for(Deliverable d:t.getDeliverables()){
					Res.add(new DefaultMutableTreeNode(d.toString()));	
				}
			}
			
			if(!t.getRequiredResources().isEmpty()){
				DefaultMutableTreeNode Res = new DefaultMutableTreeNode("Resources");	
				aTask.add(Res);	
				for(Resource r:t.getRequiredResources().values()){
					Res.add(new DefaultMutableTreeNode(r));	
				}
			}
			
			if(!t.getPredecessors().isEmpty()){
				DefaultMutableTreeNode Pre = new DefaultMutableTreeNode("Predecessors");	
				aTask.add(Pre);	
				for(Task task:t.getPredecessors().values()){
					Pre.add(new DefaultMutableTreeNode(task.getName()));	
				}
			}	
			if(!t.getSuccessors().isEmpty()){
				DefaultMutableTreeNode Suc = new DefaultMutableTreeNode("Successors");	
				aTask.add(Suc);	
				for(Task task:t.getSuccessors().values()){
					Suc.add(new DefaultMutableTreeNode(task.getName()));	
				}
			}
			
			for (Task innerTask : t.getChildren().values())
			{
			
				DefaultMutableTreeNode newRoot = new DefaultMutableTreeNode(innerTask);
				aTask.add(newRoot);	
				newRoot.add(new DefaultMutableTreeNode("ID:"+innerTask.getID()));
				newRoot.add(new DefaultMutableTreeNode("Duration:"+innerTask.getDuration()));
				newRoot.add(new DefaultMutableTreeNode("Description:"+innerTask.getDescription()));
				newRoot.add(new DefaultMutableTreeNode("Complete:"+innerTask.getPercentCompleted()));	
				
				if(!innerTask.getDeliverables().isEmpty()){
					DefaultMutableTreeNode Res = new DefaultMutableTreeNode("Deliverables");	
					newRoot.add(Res);	
					for(Deliverable d:innerTask.getDeliverables()){
						Res.add(new DefaultMutableTreeNode(d.toString()));	
					}
				}
				
				if(!innerTask.getRequiredResources().isEmpty()){
					DefaultMutableTreeNode Res = new DefaultMutableTreeNode("Resources");	
					newRoot.add(Res);	
					for(Resource r:innerTask.getRequiredResources().values()){
						Res.add(new DefaultMutableTreeNode(r));	
					}
				}

				if(!innerTask.getPredecessors().isEmpty()){
					DefaultMutableTreeNode PreC = new DefaultMutableTreeNode("Predecessors");	
					newRoot.add(PreC);	
					for(Task task:innerTask.getPredecessors().values()){
						PreC.add(new DefaultMutableTreeNode(task.getName()));	
					}
				}
				if(!innerTask.getSuccessors().isEmpty()){
					DefaultMutableTreeNode SucC = new DefaultMutableTreeNode("Successors");	
					newRoot.add(SucC);	
					for(Task task:innerTask.getSuccessors().values()){
						SucC.add(new DefaultMutableTreeNode(task.getName()));	
					}
				}
				helper(newRoot,innerTask.getChildren());							
			}
		 }	
		}
	}
		
	public static void displayRes(JList resourceList, HashMap<String,Resource> resouces){
		
		DefaultListModel model = new DefaultListModel();
		resourceList.setModel(model);
		for (Resource r:resouces.values()){
			model.addElement(r);
		}	
	}
		
	private void displayTable(String[][] dataValues,String[] columnNames, JScrollPane scheduleScrollPane){
		JTable table = new JTable( dataValues, columnNames );
		
		scheduleScrollPane.setViewportView(table);
	}
	
	private void displayGraph(Graph scheduleGraph,JScrollPane scheduleScrollPane){				//See JGraph example
		//JGraph graphView = viewScheduleAsGraphController.execute(??);
		//scheduleScrollPane.setViewportView(graphView);
		
		scheduleGraph = new Graph();
						
		Project p = viewScheduleAsGraphController.getProject();
		
		String dataValues[][] = p.getScheduleMatrix();
		
		//***Testing***
//		for (int i = 0; i <  dataValues.length; i++)
//		{
//			String tasksString = "";
//			String taskIDString = dataValues[i][1];
//			
//			String[] tasksIDList = taskIDString.split(",");
//			
//			for (int t = 0; t < tasksIDList.length; t++)
//			{
//				tasksString += project.getTask(tasksIDList[t]).getName() + ",";
//			}
//			System.out.println(dataValues[i][0] + ": " + tasksString);
//		}
		//***Testing***
		
		// Get the Starting task(s)
		String getTasks = dataValues[0][1];
		String[] taskIDList = getTasks.split(",");
		
		//Create the starting node(s)
		ArrayList<Node> currentNodeList = new ArrayList<Node>();
		
		int xindex = 100;
		int yindex = 100;
		for (int s = 0; s < taskIDList.length; s++)
		{
			Node n = new Node(taskIDList[s]);
			currentNodeList.add(n);
			
			scheduleGraph.add(n, xindex, yindex);
			yindex += 100;
		}
		
		//convert to ArrayList
		ArrayList<String> aList = convertToArrayList(taskIDList);
		
		//Go through rest of the dataValues
		for (int i = 1; i < dataValues.length; i++)
		{
			getTasks = dataValues[i][1];
			taskIDList = getTasks.split(",");
			
			ArrayList<String> bList = convertToArrayList(taskIDList);
			ArrayList<String> tempAList = (ArrayList<String>) aList.clone();
			ArrayList<String> tempBList = (ArrayList<String>) bList.clone();
			
			// Remove the Already existing ID in the previous list
			aList.removeAll(bList);
			bList.removeAll(tempAList);
			
			if (!aList.isEmpty() && !bList.isEmpty())
			{
				//Create nodes for the Blist and then hook them with the nodes already created
				// from the AList
				xindex += 100;
				yindex = 100;
				
				for (int b = 0; b < bList.size(); b++)
				{
					Node n = new Node(bList.get(b));
					scheduleGraph.add(n, xindex, yindex);
					
					for (int y = 0; y < aList.size(); y++)
					{
						for (Node checkNode: scheduleGraph.getNodes())
						{
							if (aList.get(y).equals(checkNode.getLabel()))
							{
								Arrow a = new Arrow(checkNode, n, "");
								scheduleGraph.add(a);
								break;
							}
						}
					}
					
					yindex += 100;
				}
			}
			
			aList = tempBList;
		}
		
		for (Node n: scheduleGraph.getNodes())
		{
			if (p.getTask(n.getLabel().toString()).getChildren().isEmpty())
			{
				n.setLabel(p.getTask(n.getLabel().toString()).getName());
			}
			else
			{
				n.setLabel(p.getTask(n.getLabel().toString()).getName() + " o-o");
			}
		}
		JGraph graphView=GraphUtils.makeJGraph(scheduleGraph);
		scheduleScrollPane.setViewportView(graphView);
	}
		
	private void initControllerAndView(){
		addResourceController = new AddResourceController();
		editResourceController = new EditResourceController();
		deleteResourceController = new DeleteResourceController();
		
		addTaskController = new AddTaskController();
		editTaskController = new EditTaskController();
		deleteTaskController = new DeleteTaskController();
		
		newProjectController = new NewProjectController();
		editProjectController = new EditProjectController();
		saveProjectController = new SaveProjectController();
		loadProjectController = new LoadProjectController();
		
		generateScheduleController = new GenerateScheduleController();
    	saveScheduleController = new SaveScheduleController();
		viewScheduleAsGraphController = new  ViewScheduleAsGraphController();
		viewScheduleAsTableController = new ViewScheduleAsTableController();
		
		project=newProjectController.getProject();		
		resourcesView =new ResourcesView(project,resourceList);
		tasksView = new TasksView(project, taskTree);	
	}
		
	public ArrayList<String> convertToArrayList(String[] list)
	{
		ArrayList<String> aList = new ArrayList<String>();
		
		for (int i = 0; i < list.length; i++)
		{
			aList.add(list[i]);
		}
		
		return aList;
	}
	
	private void displayTable(){
		String columnNames[] = { "Days", "Tasks" };
		
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		String[][] dataValues = project.getScheduleMatrix();
		
		for (int i = 0; i < dataValues.length; i++)
		{
			String[] rowData = new String[2];
			rowData[0] = dataValues[i][0];
			
			String tasksString = "";
			String taskIDString = dataValues[i][1];
			
			String[] tasksIDList = taskIDString.split(",");
			
			for (int t = 0; t < tasksIDList.length; t++)
			{
				tasksString += project.getTask(tasksIDList[t]).getName() + ",";
			}
			
			rowData[1] = tasksString.substring(0, tasksString.length()-1);
			model.addRow(rowData);
		}
			
		 table = new JTable(model);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void graphTest(){
        Graph simpsons = new Graph();

        Node homer = new Node("Homer");
        Node marge = new Node("Marge");

        simpsons.add(homer, 200, 100);
        simpsons.add(marge, 400, 100);

        displayGraph(simpsons,scheduleScrollPane);
	}

	private void treeTest(){
//		HashMap<String, Task> x= new HashMap<String, Task>();				
//		
//		Task y0=new Task("11","T21kk","C",2);
//		Task y1=new Task("12","T22","C",2);
//		Task y2=new Task("13","T23","C",2);	
//		
//		Task a=new Task("1","T1","A",1);
//		Task b=new Task("2","T2","B",2);
//		Task c=new Task("3","T3","C",3);
//		Task d=new Task("4","T4","D",4);
//		
//		x.put("1",a);
//		x.put("2",b);
//		x.put("3",c);
//		x.put("4",d);		
//		y2.getChildren().put("131",new Task("131","dd","sf",3));
//		y2.getChildren().put("132",new Task("132","ddasd","sfdsf",3));	
//		
//		
//		x.get("1").getChildren().put("y0",y0);
//		x.get("1").getChildren().put("y1",y1);
//		x.get("1").getChildren().put("y2",y2);
//		
//		Task z0=new Task("31","T212","C1",2);
//		Task z1=new Task("32","T222","C1",2);
//		Task z2=new Task("33","T232","C1",2);
//		x.get("3").getChildren().put("z0",z0);
//		x.get("3").getChildren().put("z1",z1);
//		x.get("3").getChildren().put("z2",z2);	
//		
//		
//		b.addResource(project.getResource("2"));
//		b.addPredecessor(a);
//		b.addSuccessor(c);
//		b.addDeliverable(new Deliverable("F1",DeliverableType.file));
//		b.addDeliverable(new Deliverable("F2",DeliverableType.file));
//		b.addDeliverable(new Deliverable("F3",DeliverableType.file));
//		
//
//		z0.setParent(c);		
//
//		
//		project.setTasks(x);
		
		ArrayList<Deliverable> d= new ArrayList<Deliverable>();
		d.add(new Deliverable("a", DeliverableType.file));
		Task ttt = new Task(UUID.randomUUID().toString(), "test1", "testing", 2);
		Task ppp = new Task(UUID.randomUUID().toString(), "test2", "testing2", 3);
		ppp.addPredecessor(ttt);

		ttt.addSuccessor(ppp);
		project.addTask(ttt.getID(), ttt);
		project.addTask(ppp.getID(), ppp);

		
				
		project.createTaskFromUI("t1", 3, new ArrayList<Task>(), null,new ArrayList<Resource>(), 
				"....",d);
		project.createTaskFromUI("t2", 3, new ArrayList<Task>(), null,new ArrayList<Resource>(), 
				"....",d);
		project.createTaskFromUI("t3", 3, new ArrayList<Task>(), null,new ArrayList<Resource>(), 
				"....",d);
		project.createTaskFromUI("t4", 3, new ArrayList<Task>(), null,new ArrayList<Resource>(), 
				"....",d);
		
		Collection<Task> x=new ArrayList<Task>();
		x= project.getTasks().values();
		
		Task z=new Task();
		for(Task t:x){
			z=t;
		}
		
		project.createTaskFromUI("t5", 3, new ArrayList<Task>(), z,new ArrayList<Resource>(), 
				"....",d);
			
		displayTree(taskTree,project.getTasks());
	}

	private void resouceTest(){
		HashMap<String,Resource> x =new HashMap<String,Resource>();
		x.put("1",new Resource("1","a",3.3,ResourceType.equipment));
		x.put("2",new Resource("2","ad",3.3,ResourceType.equipment));
		x.put("3",new Resource("3","af",3.3,ResourceType.equipment));
		x.put("4",new Resource("4","d",3.3,ResourceType.equipment));
		project.setResources(x);
		displayRes(resourceList,x);
	}
	
	private void tableTest(){
		// Create columns names
		String columnNames[] = { "Column 1", "Column 2" };

		// Create some data
		String dataValues[][] =
		{
			{ "12", "234" },
			{ "-123", "43" },
			{ "93", "89.2"},
			{ "279", "9033"}
		};
				
		displayTable(dataValues,columnNames,scheduleScrollPane);
	}
	
	
}

