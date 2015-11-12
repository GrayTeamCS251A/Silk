package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TaskUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textName;
	private JTextField textID;
	private JTextField textParent;

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
		
		textParent = new JTextField();
		textParent.setBounds(100, 99, 86, 20);
		contentPanel.add(textParent);
		textParent.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
