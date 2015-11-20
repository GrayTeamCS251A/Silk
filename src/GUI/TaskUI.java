package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;

import Entities.Project;
import Entities.Task;

public class TaskUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textName;
	private JComboBox comboBoxParent = new JComboBox();
	private JButton okButton;
	private JButton cancelButton;
	private JTextField textDuration;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			TaskUI dialog = new TaskUI("");
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public TaskUI(String x) {
		setTitle(x);
		setBounds(100, 100, 550, 329);
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
		lblPredecessor.setBounds(291, 60, 84, 14);
		contentPanel.add(lblPredecessor);
		
		textName = new JTextField();
		textName.setBounds(90, 24, 78, 20);
		contentPanel.add(textName);
		textName.setColumns(10);
		
		comboBoxParent.setBounds(358, 24, 145, 20);
		contentPanel.add(comboBoxParent);
		
		JScrollPane scrollPaneRes = new JScrollPane();
		scrollPaneRes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneRes.setBounds(65, 85, 145, 162);
		contentPanel.add(scrollPaneRes);
		
		JList listRes = new JList();
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
		
		JList list = new JList();
		scrollPanePred.setViewportView(list);
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
	   
//	   public String getTaskID() {
//		   return textID.getText();
//	   }
//	   
//	   public String getTaskDuration() {
//		   return textDuration.getText();
//	   }
//	   
//	   public Task getParentTask() {
//		   return (Task) comboBoxParent.getSelectedItem();
//	   }
//	   
//	   public Task getPredecessorTask() {
//		   return (Task) comboBoxPred.getSelectedItem();
//	   }
//	   
//	   public void Reset(){
//		   textName.setText("");
//		   textID.setText("");
//		   textDuration.setText("");
//	   }
//	   
//	   
//	   public void fill(Task t){
//		   textName.setText("");
//		   textID.setText("");
//		   textDuration.setText("");
//	   }
}
