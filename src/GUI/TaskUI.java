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

import Entities.Task;

public class TaskUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textName;
	private JTextField textID;
	private JComboBox comboBoxParent = new JComboBox();
	private JComboBox comboBoxPred = new JComboBox();
	private JButton okButton;
	private JButton cancelButton;

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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblTaskName = new JLabel("Task Name");
		lblTaskName.setBounds(23, 27, 57, 14);
		contentPanel.add(lblTaskName);
		
		JLabel lblTaskId = new JLabel("Task ID");
		lblTaskId.setBounds(23, 52, 46, 14);
		contentPanel.add(lblTaskId);
		
		JLabel lblChildren = new JLabel("Children");
		lblChildren.setBounds(23, 77, 46, 14);
		contentPanel.add(lblChildren);
		
		JLabel lblParent = new JLabel("Parent");
		lblParent.setBounds(23, 102, 46, 14);
		contentPanel.add(lblParent);
		
		JLabel lblSucessor = new JLabel("Sucessor");
		lblSucessor.setBounds(23, 127, 46, 14);
		contentPanel.add(lblSucessor);
		
		JLabel lblPredecessor = new JLabel("Predecessor");
		lblPredecessor.setBounds(23, 152, 71, 14);
		contentPanel.add(lblPredecessor);
		
		textName = new JTextField();
		textName.setBounds(100, 24, 86, 20);
		contentPanel.add(textName);
		textName.setColumns(10);
		
		textID = new JTextField();
		textID.setBounds(100, 49, 86, 20);
		contentPanel.add(textID);
		textID.setColumns(10);
		
		JComboBox comboBoxChildren = new JComboBox();
		comboBoxChildren.setBounds(100, 74, 28, 20);
		contentPanel.add(comboBoxChildren);
		
		comboBoxParent.setBounds(100, 99, 28, 20);
		contentPanel.add(comboBoxParent);
		
		JComboBox comboBoxSuc = new JComboBox();
		comboBoxSuc.setBounds(100, 124, 28, 20);
		contentPanel.add(comboBoxSuc);
		
		comboBoxPred.setBounds(100, 149, 28, 20);
		contentPanel.add(comboBoxPred);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(236, 50, 176, 162);
		contentPanel.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel lblResource = new JLabel("Resource");
		lblResource.setBounds(236, 27, 46, 14);
		contentPanel.add(lblResource);
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
	   
	   public String getTaskName() {
		   return textName.getText();
	   }
	   
	   public String getTaskID() {
		   return textID.getText();
	   }
	   
	   public String getTaskDuration() {
		   return textDuration.getText();
	   }
	   
	   public Task getParentTask() {
		   return (Task) comboBoxParent.getSelectedItem();
	   }
	   
	   public Task getPredecessorTask() {
		   return (Task) comboBoxPred.getSelectedItem();
	   }
	   
	
}
