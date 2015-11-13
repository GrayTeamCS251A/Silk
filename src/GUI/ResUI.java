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

public class ResUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textName;
	private JTextField textID;
	private JTextField textCost;
	private JTextField textType;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AddResUI dialog = new AddResUI();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ResUI(String x) {
		setTitle(x);
		setBounds(100, 100, 270, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(27, 26, 46, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblResourceId = new JLabel("Resource ID");
		lblResourceId.setBounds(27, 50, 69, 14);
		contentPanel.add(lblResourceId);
		{
			JLabel lblCost = new JLabel("Cost");
			lblCost.setBounds(27, 75, 46, 14);
			contentPanel.add(lblCost);
		}
		{
			JLabel lblType = new JLabel("Type");
			lblType.setBounds(27, 100, 46, 14);
			contentPanel.add(lblType);
		}
		{
			textName = new JTextField();
			textName.setBounds(103, 23, 86, 20);
			contentPanel.add(textName);
			textName.setColumns(10);
		}
		{
			textID = new JTextField();
			textID.setBounds(103, 47, 86, 20);
			contentPanel.add(textID);
			textID.setColumns(10);
		}
		{
			textCost = new JTextField();
			textCost.setBounds(103, 72, 86, 20);
			contentPanel.add(textCost);
			textCost.setColumns(10);
		}
		{
			textType = new JTextField();
			textType.setBounds(103, 97, 86, 20);
			contentPanel.add(textType);
			textType.setColumns(10);
		}
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
	   
	   public String getTextName(){
		   return textName.getText();		   
	   }
	   
	   public String getTextID() {
		   return textID.getText();
	   }
	   
	   public String getTextCost() {
		   return textCost.getText();
	   }
	   
	   public String getTextType() {
		   return textType.getText();
	   }
}
