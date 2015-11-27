package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Calendar;

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
	private JTextField startField;
	private JTextField authorField;
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
		
		JLabel lblStartTime = new JLabel("Start Time");
		lblStartTime.setBounds(26, 46, 64, 14);
		contentPanel.add(lblStartTime);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(26, 94, 77, 14);
		contentPanel.add(lblAuthor);
		
		nameField = new JTextField();
		nameField.setBounds(113, 18, 131, 20);
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		startField = new JTextField();
		startField.setBounds(113, 43, 131, 20);
		contentPanel.add(startField);
		startField.setColumns(10);
		
		authorField = new JTextField();
		authorField.setBounds(113, 91, 131, 20);
		contentPanel.add(authorField);
		authorField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Example: 2010-03-12");
		lblNewLabel.setBounds(113, 66, 131, 14);
		contentPanel.add(lblNewLabel);
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
		   return authorField.getText();   
	   }
	   
	   public String getStartField(){
		   System.out.println(startField.getText());
		   return startField.getText();   
	   }
	   
	   public String getNameField(){
		   return nameField.getText();   
	   }
	   
	   public String getAuthorField(){
		   return authorField.getText();   
	   }

	public void Reset() {
		// TODO Auto-generated method stub
		nameField.setText("");
		startField.setText("");
		authorField.setText("");		
	}

	public void fill(Project p) {
		// TODO Auto-generated method stub
		nameField.setText(p.getProjectName());
		startField.setText(p.getStartTime().get(Calendar.YEAR) + "-" + p.getStartTime().get(Calendar.MONTH) + "-" + p.getStartTime().get(Calendar.DAY_OF_MONTH));
		authorField.setText(p.getProjectAuthor());				
	}
}
