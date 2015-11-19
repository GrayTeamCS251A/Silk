package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.Time;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entities.Project;
import Entities.Resource;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class ProjectUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField authorField;
	private JTextField startField;
	private JTextField endField;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ProjectUI dialog = new ProjectUI("");
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ProjectUI(String x) {
		setTitle(x);
		setBounds(100, 100, 318, 207);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblProjectName = new JLabel("Project Name");
		lblProjectName.setBounds(26, 21, 77, 14);
		contentPanel.add(lblProjectName);
		
		JLabel lblProjectId = new JLabel("Project ID");
		lblProjectId.setBounds(26, 46, 77, 14);
		contentPanel.add(lblProjectId);
		
		JLabel lblStartTime = new JLabel("Start Time");
		lblStartTime.setBounds(26, 71, 64, 14);
		contentPanel.add(lblStartTime);
		
		JLabel lblEndTime = new JLabel("End Time");
		lblEndTime.setBounds(26, 96, 77, 14);
		contentPanel.add(lblEndTime);
		
		nameField = new JTextField();
		nameField.setBounds(113, 18, 131, 20);
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		authorField = new JTextField();
		authorField.setBounds(113, 43, 131, 20);
		contentPanel.add(authorField);
		authorField.setColumns(10);
		
		startField = new JTextField();
		startField.setBounds(113, 68, 131, 20);
		contentPanel.add(startField);
		startField.setColumns(10);
		
		endField = new JTextField();
		endField.setBounds(113, 93, 131, 20);
		contentPanel.add(endField);
		endField.setColumns(10);
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
	   
	   public String getEndField(){
		   return endField.getText();   
	   }
	   
	   public String getStartField(){
		   return startField.getText();   
	   }
	   
	   public String getNameField(){
		   return nameField.getText();   
	   }
	   
	   public String getAuthorField(){
		   return authorField.getText();   
	   }
	   
	   public void Reset(){
		   nameField.setText("");
		   authorField.setText("");
		   startField.setText("");
		   endField.setText("");
	   }
	   
	   public void fill(Project p){
		   nameField.setText("");
		   authorField.setText("");
		   startField.setText("");
		   endField.setText("");
	   }
	   
	   
}
