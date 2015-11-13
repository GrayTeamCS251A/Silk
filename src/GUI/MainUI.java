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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JList;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import Controllers.Resources.AddResourceController;
import Controllers.Resources.DeleteResourceController;
import Controllers.Resources.EditResourceController;
import Controllers.Tasks.AddTaskController;
import Controllers.Tasks.DeleteTaskController;
import Controllers.Tasks.EditTaskController;

public class MainUI {

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
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
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
		
		JTree tree = new JTree();
		scrollPane_1.setViewportView(tree);
		
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
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(10, 11, 345, 304);
		Schedule_panel.add(scrollPane_2);
		
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
				String s=addRes.getTextName();
				addResourceController.executeAddResource(addRes.getTextName(), 
						Integer.parseInt(addRes.getTextID()), 
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
                addRes.Reset();        	
            }
        });
	}

	private void initEditResAction(){
		addRes.addConfirmListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//PreEdit value  
				editRes.setVisible(false);
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
            	//PreEdit value        	
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
					//you code here
				}
			}});
	}

	private void initEditTaskAction(){
		
		editTask.addConfirmListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub      
				editTask.setVisible(false);
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
		
	}
		
	private void initAddTaskAction(){
		
		addTask.addConfirmListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub      
				addTask.setVisible(false);
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
					//you code here
				}
			}});
	}

	private void initScheduleAction(){
		btnGenerateSchedule.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
		btnSaveSchedule.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
		btnGraph.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
		btnTable.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}});
	}

	private void initNewProjectAction(){
		newProject.addConfirmListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newProject.Reset();      
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
            	newProject.Reset();        	
            }
        });
	}
	
	private void initEditProjectAction(){
		
		editProject.addConfirmListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//editProject.Reset();      
				editProject.setVisible(false);
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
	}

	private void initSaveAndLoad(){
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sb = "saved or...";
                JFileChooser saveFile = new JFileChooser();
                int returnVal = saveFile.showSaveDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                    	//...................
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
			      int returnVal = openFile.showOpenDialog(null);
	                if (returnVal == JFileChooser.APPROVE_OPTION) {
	                    try {
	                    	//....................
	                    } catch (Exception ex) {
	                        ex.printStackTrace();
	                    }
	                }
			}					
		});
				
	}
}

