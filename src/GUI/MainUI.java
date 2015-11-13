package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTree;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import Controllers.Resources.AddResourceController;

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
	private AddResourceController addResourceController = new AddResourceController();
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
}
