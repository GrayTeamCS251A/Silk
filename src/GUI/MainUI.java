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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.jgraph.JGraph;

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
import Controllers.Schedule.ViewScheduleAsGraphController;
import Controllers.Schedule.ViewScheduleAsTableController;
import Controllers.Tasks.AddTaskController;
import Controllers.Tasks.DeleteTaskController;
import Controllers.Tasks.EditTaskController;
import Entities.Project;
import Entities.Resource;
import Entities.ResourceType;
import Entities.Task;
import graph.Arrow;
import graph.Graph;
import graph.GraphUtils;
import graph.Link;
import graph.Node;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
public class MainUI{

	private JFrame frame;
	private JButton btnAddResource;
	private JButton btnDeleteResource;
	private JButton btnViewResource;
	private JButton btnEditResource;
	private JButton btnAddTask;
	private JButton btnDeleteTask;
	private JButton btnEditTask;
	private JButton btnViewTask;
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
	private Project project = new Project();


	private ResUI addRes = new ResUI("Add Resource");
	private ResUI editRes = new ResUI("Edit Resource");
	
	private TaskUI addTask = new TaskUI("Add Task");
	private TaskUI editTask = new TaskUI("Edit Task");
	
	private ProjectUI newProject = new ProjectUI("New Project");
	private ProjectUI editProject = new ProjectUI("Edit Project");
	
	private AddResourceController addResourceController = new AddResourceController();
	private EditResourceController editResourceController = new EditResourceController();
	private DeleteResourceController deleteResourceController = new DeleteResourceController();
	
	private AddTaskController addTaskController = new AddTaskController();
	private EditTaskController editTaskController = new EditTaskController();
	private DeleteTaskController deleteTaskController = new DeleteTaskController();
	
	private NewProjectController newProjectController = new NewProjectController();
	private EditProjectController editProjectController = new EditProjectController();
	private SaveProjectController saveProjectController = new SaveProjectController();
	private LoadProjectController loadProjectController = new LoadProjectController();
	private JScrollPane scheduleScrollPane;
	
	private GenerateScheduleController generateScheduleController = new GenerateScheduleController();
	private ViewScheduleAsGraphController viewScheduleAsGraphController = new  ViewScheduleAsGraphController();
	private ViewScheduleAsTableController  viewScheduleAsTableController = new ViewScheduleAsTableController();
	
	private ResourcesView resourcesView =new ResourcesView(project,resourceList);
	private TasksView tasksView = new TasksView(project, taskTree);	
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
		
		
		project.getResources();
		project.getResources().put("1", new Resource("1","a",3.3,ResourceType.equipment));
		displayRes(resourceList,project.getResources());
		
		treeTest();
//		resouceTest();
//		tableTest();
		graphTest();
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
		
		btnViewResource = new JButton("View Resource");
		btnViewResource.setBounds(149, 326, 128, 23);
		Resource_panel.add(btnViewResource);
		
		btnEditResource = new JButton("Edit Resource");
		btnEditResource.setBounds(149, 360, 128, 23);
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
		
		btnViewTask = new JButton("View Task");
		btnViewTask.setBounds(172, 360, 105, 23);
		Task_panel.add(btnViewTask);
		
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
				editRes.fill(r);
//				editResourceController.executeEditResource(editRes.getTextName(), 
//						r.getResourceID(), 
//						Double.parseDouble(editRes.getTextCost()), 
//						editRes.getTextType());
//				editRes.setVisible(false);
			}
						
		});
		
		btnEditResource.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
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
					resourceList.remove(selectedIndex);
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
//				editTaskController.executeEditTask(editTask.getTaskName(), 
//						editTask.getTaskID(), 
//						Integer.parseInt(editTask.getTaskDuration()),
//						editTask.getPredecessorTask(),
//						editTask.getParentTask());
//				editTask.setVisible(false);
			}
						
		});
		
		btnEditTask.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
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
//				addTaskController.executeAddTask(addTask.getTaskName(), 
//						Integer.parseInt(addTask.getTaskID()), 
//						Integer.parseInt(addTask.getTaskDuration()),
//						addTask.getPredecessorTask(),
//						addTask.getParentTask());
//				addTask.setVisible(false);
			}
						
		});
		
		btnAddTask.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
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
				
//					TreePath tp = taskTree.getSelectionPath();
//					deleteTaskController.executeDeleteTask(tp.getLastPathComponent());
//					taskTree.removeSelectionPath(tp);
				}
			}});
	}

	private void initScheduleAction(){
		btnGenerateSchedule.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				generateScheduleController.generateSchedule();
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
                    	String fileName = saveFile.getSelectedFile().getName();
                    	saveProjectController.executeSaveProject(fileName);
               	
                    	File src = new File("");  //File returned by execute from saveSchedule    	
                    	File dest = new File(saveFile.getSelectedFile().getAbsolutePath());
                    	Files.copy(src.toPath(),dest.toPath(), REPLACE_EXISTING);
                    	                    	
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
			}});
		
		btnGraph.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//See JGraph example
				//JGraph graphView = viewScheduleAsGraphController.execute(??);
				//scheduleScrollPane.setViewportView(graphView);
			}});
		
		btnTable.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//JTable table = viewScheduleAsTableController.execute(?);
				//scheduleScrollPane.setViewportView(table);
			}});
	}

	private void initNewProjectAction(){
		newProject.addConfirmListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				newProjectController.executeNewProject(newProject.getNameField(), 
//						newProject.getStartField(), 
//						newProject.getAuthorField());
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
//				editProject.fill(project);
//				editProjectController.executeEditProject(editProject.getNameField(), 
//						editProject.getStartField(), 
//						editProject.getAuthorField());
//				editProject.setVisible(false);
			}
						
		});
		
		Edit.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
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
                    	String fileName = saveFile.getSelectedFile().getName();
                    	saveProjectController.executeSaveProject(fileName);
               	
                    	File src = new File("");  //File returned by executeSaveProject      	
                    	File dest = new File(saveFile.getSelectedFile().getAbsolutePath());
                    	Files.copy(src.toPath(),dest.toPath(), REPLACE_EXISTING);
                    	                    	
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
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tasks");
		
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
			DefaultMutableTreeNode aTask = new DefaultMutableTreeNode(t);	  	  
			root.add(aTask);
			aTask.add(new DefaultMutableTreeNode("ID:"+t.getTaskID()));
			aTask.add(new DefaultMutableTreeNode("Duration:"+t.getDuration()));
			aTask.add(new DefaultMutableTreeNode("Description:"+t.getDescription()));	
			
			for (Task innerTask : t.getChildren().values())
			{
				DefaultMutableTreeNode newRoot = new DefaultMutableTreeNode(innerTask);
				aTask.add(newRoot);	
				newRoot.add(new DefaultMutableTreeNode("ID:"+t.getTaskID()));
				newRoot.add(new DefaultMutableTreeNode("Duration:"+t.getDuration()));
				newRoot.add(new DefaultMutableTreeNode("Description:"+t.getDescription()));	
				helper(newRoot,innerTask.getChildren());							
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
		
	public static void displayTable(String columnNames[], String dataValues[][],JScrollPane scheduleScrollPane){
		JTable table = new JTable( dataValues, columnNames );
		scheduleScrollPane.setViewportView(table);
	}
	
	public static void displayGraph(Graph graph,JScrollPane scheduleScrollPane){
		JGraph graphView=GraphUtils.makeJGraph(graph);
		scheduleScrollPane.setViewportView(graphView);
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
		HashMap<String, Task> x= new HashMap<String, Task>();				
		Task y0=new Task("2","T21","C",2);
		Task y1=new Task("2","T22","C",2);
		Task y2=new Task("2","T23","C",2);			
		x.put("1",new Task("1","T1","A",1));
		x.put("2",new Task("2","T2","B",2));
		x.get("1").getChildren().put("y0",y0);
		x.get("1").getChildren().put("y1",y1);
		x.get("1").getChildren().put("y2",y2);
		x.put("3",new Task("2","T3","C",3));
		x.put("4",new Task("3","T4","D",4));		
		y2.getChildren().put("3",new Task("3","dd","sf",3));
		y2.getChildren().put("2",new Task("2","ddasd","sfdsf",3));					
		x.get("3").getChildren().put("y0",y0);
		x.get("3").getChildren().put("y1",y1);
		x.get("3").getChildren().put("y2",y2);	
		displayTree(taskTree,x);
	
	}

	private void resouceTest(){
		HashMap<String,Resource> x =new HashMap<String,Resource>();
		x.put("1",new Resource("1","a",3.3,ResourceType.equipment));
		x.put("2",new Resource("2","ad",3.3,ResourceType.equipment));
		x.put("3",new Resource("3","af",3.3,ResourceType.equipment));
		x.put("4",new Resource("4","d",3.3,ResourceType.equipment));
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
		displayTable(columnNames,dataValues,scheduleScrollPane);
	}
	
	
}

