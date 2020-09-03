import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.math.*;


public class PasswordGenerator implements ActionListener
{
	
	//Generator components
	JButton genButton;
	JTextField pw;
	JLabel charConstraintLabel;
	JTextField charConstraint;
	
	//Window components
	JPanel panel;
	JFrame frame;
	
	public PasswordGenerator(int width, int height)
	{

		
		createComponents();
		setupPanel();
		setupFrame(width, height);
	}

	public static void main(String[] args) 
	{
		new PasswordGenerator(500,200);
	}
	
	private void createComponents()
	{
		//create button
		genButton = new JButton("Generate random password");
		
		genButton.addActionListener(this);
		
		//create char amount text box, default to 8 characters
		charConstraintLabel = new JLabel("Password size:");
		charConstraintLabel.setMaximumSize(new Dimension(40,20));
		charConstraint = new JTextField("8");
		charConstraint.setMaximumSize(new Dimension(40,20));
		
		//create pw label
		pw = new JTextField("");
		pw.setEditable(false);
		
	}
	
	private void setupPanel()
	{
		//create panel
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(2,2, 10, 5));

		//set which way the components are added
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		//add components to panel
		panel.add(charConstraintLabel);
		panel.add(charConstraint);

		panel.add(genButton);
		panel.add(pw);	
	}
	
	private void setupFrame(int width, int height)
	{
		//create frame
		frame = new JFrame();
		frame.setSize(width, height);
		//add everything to frame
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Random Password Generator");
		//frame.pack();
		frame.setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		pw.setText(genPassword()); 
	}
	
	private String genPassword()
	{
		String result = "";
		
		//obtain character limit
		int charLimit = Integer.parseInt(charConstraint.getText());
		
		//add random characters to result
		for(int i = 0; i < charLimit; i++)
		{
			int temp = (int)(Math.random() * (126 - 33) + 33);
			result += (char) temp;
		}
		
		return result;
	}

}
