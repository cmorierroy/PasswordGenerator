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
	
	//Last row
	JButton genButton;
	JTextField pw;
	
	//row 1
	JLabel upperCaseConstraintLabel;
	JTextField upperCaseConstraint;
	
	//row 2
	JLabel lowerCaseConstraintLabel;
	JTextField lowerCaseConstraint;
	
	//row 3
	JLabel numberConstraintLabel;
	JTextField numberConstraint;
	
	//row 4
	JLabel specialCharacterConstraintLabel;
	JTextField specialCharacterConstraint;
	
	//Window components
	JPanel panel;
	JFrame frame;
	
	public static void main(String[] args) 
	{
		new PasswordGenerator(700,200);
	}
	
	public PasswordGenerator(int width, int height)
	{
		createComponents();
		setupPanel();
		setupFrame(width, height);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		pw.setText(genPassword()); 
	}
	
	private void createComponents()
	{
		//create button
		genButton = new JButton("Generate random password");
		
		//add action listener for click
		genButton.addActionListener(this);
		
		//create uppercase char amount text box, default to 4 characters
		upperCaseConstraintLabel = new JLabel("Amount of uppercase characters in password:");
		upperCaseConstraint = new JTextField("2");
		
		//create lowercase char amount text box, default to 4 characters
		lowerCaseConstraintLabel = new JLabel("Amount of lowercase characters in password:");
		lowerCaseConstraint = new JTextField("2");
		
		//create char amount text box, default to 8 characters
		numberConstraintLabel = new JLabel("Amount of numbers in password:");
		numberConstraint = new JTextField("2");
		
		//create char amount text box, default to 8 characters
		specialCharacterConstraintLabel = new JLabel("Amount of special characters in password:");
		specialCharacterConstraint = new JTextField("2");
		
		//create pw label
		pw = new JTextField("");
		pw.setEditable(false);	
	}
	
	private void setupPanel()
	{
		//create panel
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(5,2, 10, 5));

		//set which way the components are added
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		//add components to panel
		panel.add(upperCaseConstraintLabel);
		panel.add(upperCaseConstraint);
		panel.add(lowerCaseConstraintLabel);
		panel.add(lowerCaseConstraint);
		panel.add(numberConstraintLabel);
		panel.add(numberConstraint);
		panel.add(specialCharacterConstraintLabel);
		panel.add(specialCharacterConstraint);

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
	
	private String genPassword()
	{
		String result = "";
		
		//obtain limits
		int upperCaseLimit = Integer.parseInt(upperCaseConstraint.getText());
		int lowerCaseLimit = Integer.parseInt(lowerCaseConstraint.getText());
		int numberLimit = Integer.parseInt(numberConstraint.getText());
		int specialCharacterLimit = Integer.parseInt(specialCharacterConstraint.getText());
		
		//Generate characters of each category
		String uppercase = genRandString(upperCaseLimit,'A','Z');	
		String lowercase = genRandString(lowerCaseLimit,'a','z');
		String numbers = genRandString(numberLimit,'0','9');
		String specialCharacters = "";
		
		//special case for sporadic special character ranges
		
		//ranges: 33-47(15),58-64(7),91-96(6),123-126(4)
		//total = 32
		int rand;
		for(int i = 0; i < specialCharacterLimit; i++)
		{
			rand = (int) (Math.random() * (33 - 1) + 1);
			
			if(rand < 15)
			{
				specialCharacters += genRandString(1,(char)33,(char)47);
			}
			else if(rand >= 15 && rand < 23)
			{
				specialCharacters += genRandString(1, (char)58, (char)64);
			}
			else if(rand >= 22 && rand < 28)
			{
				specialCharacters += genRandString(1, (char)91, (char)96);
			}
			else if(rand >= 28)
			{
				specialCharacters += genRandString(1, (char)123, (char)126);	
			}
		}
		
		//Randomly pick characters from each category until all are picked
		while(numbers.length() != 0 || lowercase.length() != 0 || uppercase.length() != 0 || specialCharacters.length() != 0)
		{
			//pick a category
			rand = (int) (Math.random() * (5 - 1) + 1);
			
			switch(rand)
			{
			case 1:
				if(numbers.length() != 0)
				{
					//remove last (random) character from numbers, add to result, shorten numbers by 1
					result += numbers.charAt(numbers.length()-1);
					numbers = numbers.substring(0, numbers.length()-1);
				}
				else
				{
					continue;
				}
				break;
			case 2:
				if(uppercase.length() != 0)
				{
					result += uppercase.charAt(uppercase.length()-1);
					uppercase = uppercase.substring(0, uppercase.length()-1);
				}
				else
				{
					continue;
				}
				break;
			case 3:
				if(lowercase.length() != 0)
				{
					result += lowercase.charAt(lowercase.length()-1);
					lowercase = lowercase.substring(0, lowercase.length()-1);
				}
				else
				{
					continue;
				}
				break;
			case 4:
				if(specialCharacters.length() != 0)
				{
					result += specialCharacters.charAt(specialCharacters.length()-1);
					specialCharacters = specialCharacters.substring(0, specialCharacters.length()-1);
				}
				else
				{
					continue;
				}
				break;
			default:
				System.out.println("Error.");
				break;
					
			}
		}
		
		return result;
	}
	
	private String genRandString(int length, char lowerBound, char upperBound)
	{
		String result = "";
		
		for(int i = 0; i < length; i++)
		{
			char temp = (char)(Math.random() * (upperBound + 1 - lowerBound) + lowerBound);
			if(length == 1)
			{
				System.out.println(temp);
			}
			result += temp;
		}
		
		return result;
	}

}
