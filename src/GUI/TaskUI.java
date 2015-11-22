package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;

import Entities.Project;
import Entities.Resource;
import Entities.Task;

public class TaskUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textName;
	private JComboBox comboBoxParent = new JComboBox();
	private JButton okButton;
	private JButton cancelButton;
	private JTextField textDuration;
	private JList listRes;
	private JList listPred;

	/**
	 * Create the dialog.
	 */
	public TaskUI(String x,String instr) {
		setTitle(x);
		setBounds(100, 100, 550, 348);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTaskName = new JLabel("Task Name");
		lblTaskName.setBounds(23, 27, 84, 14);
		contentPanel.add(lblTaskName);
		
		JLabel lblParent = new JLabel("Parent");
		lblParent.setBounds(305, 27, 84, 14);
		contentPanel.add(lblParent);
		
		JLabel lblPredecessor = new JLabel("Predecessor");
		lblPredecessor.setBounds(283, 60, 84, 14);
		contentPanel.add(lblPredecessor);
		
		textName = new JTextField();
		textName.setBounds(90, 24, 78, 20);
		contentPanel.add(textName);
		textName.setColumns(10);
		
		comboBoxParent.setBounds(358, 24, 145, 20);
		contentPanel.add(comboBoxParent);
		
		JScrollPane scrollPaneRes = new JScrollPane();
		scrollPaneRes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneRes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneRes.setBounds(65, 85, 145, 162);
		contentPanel.add(scrollPaneRes);
		
		listRes = new JList();
		scrollPaneRes.setViewportView(listRes);
		
		JLabel lblResource = new JLabel("Resource");
		lblResource.setBounds(65, 60, 94, 14);
		contentPanel.add(lblResource);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(184, 27, 84, 14);
		contentPanel.add(lblDuration);
		
		textDuration = new JTextField();
		textDuration.setBounds(236, 24, 42, 20);
		contentPanel.add(textDuration);
		textDuration.setColumns(10);
		
		JScrollPane scrollPanePred = new JScrollPane();
		scrollPanePred.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanePred.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPanePred.setBounds(284, 85, 145, 162);
		contentPanel.add(scrollPanePred);
		
		listPred = new JList();
		scrollPanePred.setViewportView(listPred);
		
		JLabel lblInstr = new JLabel(instr);
		lblInstr.setBounds(65, 258, 376, 14);
		contentPanel.add(lblInstr);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

	}
	
	   public void addConfirmListener(ActionListener listener) {
	   		okButton.addActionListener(listener);
	   }
	   
	   public void addCancelListener(ActionListener listener) {
		   cancelButton.addActionListener(listener);
	   }
	   
	   public String getTaskName() {
		   return textName.getText();
	   }
	   
 
	   public String getTaskDuration() {
		   return textDuration.getText();
	   }
  
	   public Task getParentTask() {
		   return (Task) comboBoxParent.getSelectedItem();
	   }
 
	   public ArrayList<Task> getPredecessorTask() {
		   int[] r=listPred.getSelectedIndices();
		   ArrayList<Task> x=new ArrayList<Task>();
		   for(int i=0;i<r.length;i++){
			   Task t= (Task) listPred.getModel().getElementAt(r[i]);
			   x.add(t);
		   }	
		   //System.out.println(x);
		   return x;
	   }
	   
	   public ArrayList<Resource> getResouces() {
		   int[] r=listRes.getSelectedIndices();
		   ArrayList<Resource> x=new ArrayList<Resource>();
		   for(int i=0;i<r.length;i++){
			   Resource t= (Resource) listRes.getModel().getElementAt(r[i]);
			   x.add(t);
		   }
		   return x;
	   }
	   
	   public void Reset(){
		   textName.setText("");
		   textDuration.setText("");
		   listRes.removeAll();
		   listPred.removeAll();
		   comboBoxParent.removeAll();
	   }
	   
	   
	   public void fillEdit(Task t,Project p){
		   textName.setText(t.getName());
		   textDuration.setText(String.valueOf(t.getDuration()));
		   fillResAndPre(t,p);  		   
		   Task z =t.getParent();
		   DefaultComboBoxModel model3 = new DefaultComboBoxModel();
		   comboBoxParent.setModel( model3);
		   model3.addElement(z);
	   }
	   
	   public void fillAdd(Project p){
		   DefaultListModel model = new DefaultListModel();
		   listPred.setModel(model);
		   for(Task task: p.getTasks().values()){
			 	model.addElement(task);
		   }
		   DefaultListModel model2 = new DefaultListModel();
		   listRes.setModel(model2);		  
		   for(Resource resource: p.getResources().values()){
			 	model2.addElement(resource);
		   }  
	   }
	   
	   private void fillResAndPre(Task t,Project p){
		
		   Collection<Task> x =t.getPredecessors().values();			
		   DefaultListModel model = new DefaultListModel();
		   listPred.setModel(model);
		   for(Task task: p.getTasks().values()){
			 	model.addElement(task);
		   }  
		  
		   ArrayList<Integer> n = new  ArrayList<Integer>();
		  
		   for(int i=0;i<p.getTasks().values().size();i++){
			   Task projectTask= (Task)model.get(i);
			   for(Task task:x){
				   if(projectTask.getID().equals(task.getID())){				
					   n.add(i);
				   }
			   }
		   }
		   
		  int[] IntArray = new int[n.size()];
		  for(int i=0;i<n.size();i++){
			  IntArray[i]=n.get(i);
		  }		  
		  listPred.setSelectedIndices(IntArray);
		   
		  
		  ArrayList<Integer> n2 = new  ArrayList<Integer>();
		   Collection<Resource> y =t.getRequiredResources().values();
		   DefaultListModel model2 = new DefaultListModel();
		   listRes.setModel(model2);		  
		   for(Resource resource: p.getResources().values()){
			 	model2.addElement(resource);
		   }  
		   for(int i=0;i<p.getResources().values().size();i++){
			   Resource r= (Resource)model2.get(i);
			   for(Resource resource:y){
				   if(r.getResourceID().equals(resource.getResourceID())){
					   //listRes.setSelectedIndex(i);
					   n2.add(i);
				   }
			   }
		   }
		   
		 int[] IntArray2 = new int[n2.size()];
			for(int i=0;i<n2.size();i++){
			 IntArray2[i]=n2.get(i);
			}		  
		 listRes.setSelectedIndices(IntArray2);
	   }
}
