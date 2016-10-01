package project2;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class DialogConnect extends JDialog implements ActionListener {

	private JTextField rows;
	private JTextField col;
	private JTextField players;
	private JTextField connect;
	private JTextField WhosOnFirst;

	private JButton okButton;
	private JButton cancelButton;
	
	private int closeStatus;

	static final int OK = 0;
	static final int CANCEL = 1;
	private ConnectFourGame game;

	public DialogConnect(JFrame parent, ConnectFourGame c) {

		
		super(parent, true);

		setTitle("New Connect Four Game");
		this.closeStatus = CANCEL;

		setSize(500,300);

		// centers window in the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize()
				.width/2, dim.height/2-this.getSize().height/2);

		this.game = c;
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		// Creates panel with grid layout and 
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(5,2));

		// Adds textField for each input 
		textPanel.add(new JLabel("Enter number of rows between 3-20:"));
		this.rows = new JTextField("10",2);
		textPanel.add(this.rows);

		textPanel.add(new JLabel
				("Enter number of column,between 2-20:"));
		this.col = new JTextField("10",2);
		textPanel.add(this.col);

		textPanel.add(new JLabel("number of players between 2-10:"));
		this.players = new JTextField("2",2);
		textPanel.add(this.players);

		textPanel.add(new JLabel("Enter connections to win:"));
		this.connect = new JTextField("4",2);
		textPanel.add(this.connect);

		textPanel.add(new JLabel("what player Goes First:"));
		this.WhosOnFirst = new JTextField("1",2);
		textPanel.add(this.WhosOnFirst);

		// Adds panel to center of frames borderlayout
		getContentPane().add(textPanel, BorderLayout.CENTER);

		// creates and adds on and cancel button to button panel 
		this.okButton = new JButton("OK");
		this.okButton.addActionListener(this);
		this.cancelButton = new JButton("Cancel");
		this.cancelButton.addActionListener(this);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this.okButton);
		buttonPanel.add(this.cancelButton);

		// Adds button panel to frame
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		setVisible(true);

	}


	public void actionPerformed(ActionEvent e){

		JButton button = (JButton) e.getSource();

		if(button == this.okButton){

			// Checks if each input is number 
			if(this.players.getText().matches("[0-9]+")&&
					this.rows.getText().matches("[0-9]+")&&
					this.col.getText().matches("[0-9]+")&&
					this.connect.getText().matches("[0-9]+")&&
					this.WhosOnFirst.getText().matches("[0-9]+")){

				// Checks if each input is within range
				if(Integer.parseInt(players.getText())>1&&
						Integer.parseInt(this.players.getText())<11&&
						Integer.parseInt(this.rows.getText())>0&&
						Integer.parseInt(this.rows.getText())<21&&
						Integer.parseInt(this.col.getText())>0&&
						Integer.parseInt(this.col.getText())<21&&
						Integer.parseInt(this.connect.getText())>1&&
						Integer.parseInt(this.connect.getText())<
						Integer.parseInt(this.rows.getText()+1)&&
						Integer.parseInt(this.WhosOnFirst.getText())>0&&
						Integer.parseInt(this.WhosOnFirst.getText())<
						Integer.parseInt(this.players.getText())+1
						){

					// Removes intiger value of each input
					this.game.setR
					(Integer.parseInt(this.rows.getText()));
					this.game.setC
					(Integer.parseInt(this.col.getText()));
					this.game.setP
					(Integer.parseInt(this.players.getText()));
					this.game.setCn
					(Integer.parseInt(this.connect.getText()));
					this.game.setTurn
					(Integer.parseInt(this.WhosOnFirst.getText()));
					
					this.closeStatus = OK;
					
					// Removes Frame 
					dispose();
				}
			}
		}

		if(button == this.cancelButton){
			dispose();

		}
	}

	public int getCloseStatus(){
		return this.closeStatus;
	}



}
