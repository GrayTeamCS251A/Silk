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
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;

public class TaskUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textName;
	private JComboBox comboBoxParent = new JComboBox();
	private JButton okButton;
	private JButton cancelButton;
	private JTextField textDuration;
	private JList listRes;
	private JList listPred;
	private JRadioButton rdbtnNoParent;
	private JTextPane txtpnDescription;

	/**
	 * Create the dialog.
	 */
	public TaskUI(String x,String instr) {
		setTitle(x);
		setBounds(100, 100, 639, 373);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTaskName = new JLabel("Task Name");
		lblTaskName.setBounds(22, 51, 84, 14);
		contentPanel.add(lblTaskName);
		
		JLabel lblParent = new JLabel("Current Parent");
		lblParent.setBounds(338, 51, 84, 14);
		contentPanel.add(lblParent);
		
		JLabel lblPredecessor = new JLabel("Predecessor");
		lblPredecessor.setBounds(246, 79, 84, 14);
		contentPanel.add(lblPredecessor);
		
		textName = new JTextField();
		textName.setBounds(91, 48, 78, 20);
		contentPanel.add(textName);
		textName.setColumns(10);
		
		comboBoxParent.setBounds(423, 48, 145, 20);
		contentPanel.add(comboBoxParent);
		
		JScrollPane scrollPaneRes = new JScrollPane();
		scrollPaneRes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneRes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneRes.setBounds(65, 104, 145, 162);
		contentPanel.add(scrollPaneRes);
		
		listRes = new JList();
		scrollPaneRes.setViewportView(listRes);
		
		JLabel lblResource = new JLabel("Resource");
		lblResource.setBounds(65, 79, 94, 14);
		contentPanel.add(lblResource);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(179, 51, 84, 14);
		contentPanel.add(lblDuration);
		
		textDuration = new JTextField();
		textDuration.setBounds(236, 48, 42, 20);
		contentPanel.add(textDuration);
		textDuration.setColumns(10);
		
		JScrollPane scrollPanePred = new JScrollPane();
		scrollPanePred.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanePred.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPanePred.setBounds(246, 104, 145, 162);
		contentPanel.add(scrollPanePred);
		
		listPred = new JList();
		scrollPanePred.setViewportView(listPred);
		
		JLabel lblInstr = new JLabel(instr);
		lblInstr.setBounds(65, 277, 376, 14);
		contentPanel.add(lblInstr);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(423, 79, 84, 14);
		contentPanel.add(lblDescription);
		
		JScrollPane scrollPaneDescription = new JScrollPane();
		scrollPaneDescription.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneDescription.setBounds(424, 104, 144, 162);
		contentPanel.add(scrollPaneDescription);
		
		txtpnDescription = new JTextPane();
		scrollPaneDescription.setViewportView(txtpnDescription);
		
		rdbtnNoParent = new JRadioButton("Set no parent");
		rdbtnNoParent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnNoParent.isSelected()){
					comboBoxParent.setEnabled(false);		
				}else{
					comboBoxParent.setEnabled(true);
				}
			}
		});
		rdbtnNoParent.setBounds(422, 18, 146, 23);
		contentPanel.add(rdbtnNoParent);
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
			 if(comboBoxParent.isEnabled()){
				  return (Task) comboBoxParent.getSelectedItem();
			  }		   
			 return  null;
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
	   
	   public String getDescription()
	   {
		   return txtpnDescription.getText();
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
		   Task parent =t.getParent();

		   if(parent==null){
			   rdbtnNoParent.setSelected(true);
			   comboBoxParent.setEnabled(false);
		   }else {
			   rdbtnNoParent.setSelected(false);
			   comboBoxParent.setEnabled(true);
		   }
		  
		   DefaultComboBoxModel model3 = new DefaultComboBoxModel();
		   comboBoxParent.setModel( model3);
			   for(Task task: p.getTasks().values()){
				 	model3.addElement(task);
			   }
		  
			   
		   if(parent!=null){
			rdbtnNoParent.setSelected(false);   
			for(int i=0;i<p.getTasks().values().size();i++){
				   Task projectTask= (Task)model3.getElementAt(i);
				   for(Task task:p.getTasks().values()){
					   if(projectTask.getID().equals(task.getID())){				
						   comboBoxParent.setSelectedIndex(i);
					   }
				   }
			   }
			}
		  
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
		   DefaultComboBoxModel model3 = new DefaultComboBoxModel();
		   comboBoxParent.setModel( model3);
		   for(Task task: p.getTasks().values()){
			 	model3.addElement(task);
		   }
	   }
	   
	   private void fillResAndPre(Task t,Project p){
		   listPred.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		   
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
		   
		  int[] num = new int[n.size()];
		  for (int i=0; i <num.length; i++)
		  {
		     num[i] = n.get(i).intValue();
		  }	  
		  listPred.setSelectedIndices( num);
		   
		  
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
		   
		int[] num2 = new int[n2.size()];
		for (int i=0; i <num2.length; i++)
			  {
			     num2[i] = n2.get(i).intValue();
			  }	  
		 listRes.setSelectedIndices(num2);	  
	   }
}
