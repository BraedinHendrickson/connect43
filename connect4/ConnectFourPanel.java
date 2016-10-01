package project2;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

public class ConnectFourPanel extends JPanel{
	private JLabel[][] board;
	private JLabel[] winners;

	private JButton[] selection;
	private JButton undo;

	private ConnectFourGame game;

	private ImageIcon[] icon;

	private int[][] iBoard;
	private int mrow;
	private int mcol;
	private int players;

	public ConnectFourPanel(ConnectFourGame game){

		mrow= game.getR();
		mcol= game.getC();
		players=game.getP();

		// Loop to set icon path 
		icon =new ImageIcon[(players+1)];
		for(int i=0; i<icon.length ;i++){
			String s = i+".jpg";	
			icon[i] = new ImageIcon(getClass().getResource(s));
		}

		this.game=game;
		JPanel center = new JPanel();
		JPanel bottom = new JPanel();
		center.setLayout(new GridLayout((mrow+1),mcol,3,2));
		bottom.setLayout(new GridLayout(1,1));
		board = new JLabel[mrow][mcol];

		selection = new JButton[mcol];
		ButtonListener listener = new ButtonListener();

		// loop to create buttons
		for (int col = 0; col < mcol; col++) {
			selection[col] = new JButton ("slect");
			selection[col].setSize(100,70);
			selection[col].addActionListener(listener);
			center.add(selection[col]);
		}

		// Loop to display board and set icon 
		for (int row = 0; row < mrow; row++) 
			for (int col = 0; col < mcol; col++) {
				board[row][col] = new JLabel ("", 
						SwingConstants.CENTER);
				board[row][col].setIcon(icon[0]);
				center.add(board[row][col]);
			}

		// creates and adds undo button
		undo = new JButton("undo");
		undo.addActionListener(listener);
		bottom.add(undo);

		winners = new JLabel[players+1];

		// Loop to create winners array and set text
		for (int i = 0; i < players+1; i++) {
			winners[i] = new JLabel 
					("player "+i+"="+game.getWins(i), 
							SwingConstants.CENTER);
			bottom.add(winners[i]);		
			if (i==0){
				winners[i].setText("win count: ");

			}
		}
		add(center);
		add(bottom);
	}

	private void displayBoard() {
		iBoard = game.getBoard ();
		// Gets board and repaints panel board 
		for (int r = 0; r < mrow; r++) 
			for (int c = 0; c < mcol; c++) {
				for (int i=0; i<players+1;i++){
					if (iBoard[r][c] == i)
						board[r][c].setIcon(icon[i]);
				}
			}
	}



	private void repaintWiner(){
		for (int i = 1; i < players+1; i++) {
			winners[i].setText("player "+i+"="+game.getWins(i));
		}
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			// Loop checks if action preformed was "selection" button
			for (int col =0; col< mcol; col++){
				if (selection[col] == e.getSource()){
					game.selectCol (col);
					displayBoard();
					repaintWiner();
				}
			}
			
			// checks if undo is the source of action
			if (undo == e.getSource()){

				game.goBack();
				displayBoard();
				repaintWiner();


			}
			// Every time action preformed checks games status
			if(game.getGameStatus()>0){
				for(int i=1; i<(players+1);i++){
					if (game.getGameStatus() == i){
						int input = JOptionPane.showOptionDialog
								(null , "Player "+i+" won" + 
										"\n The game will reset", 
										"The title", JOptionPane
										.OK_CANCEL_OPTION, 
										JOptionPane.INFORMATION_MESSAGE,
										null, null, null);
						if(input == JOptionPane.OK_OPTION)
						{
							game.reset();
							displayBoard();
							repaintWiner();
						}
						winners[i].setText
						("player "+i+"="+game.getWins(i));



					}
				}
			}
			
			// if the game status is -1 this means the board is full
			// and there is no winner 
			if (game.getGameStatus() == -1){
				int input = JOptionPane.showOptionDialog
						(null , "Game is a tie there is no winner" 
								+ "\n The game will reset", 
								"The title", 
								JOptionPane.OK_CANCEL_OPTION, 
								JOptionPane.INFORMATION_MESSAGE,
								null, null, null);
				if(input == JOptionPane.OK_OPTION)
				{
					game.reset();
					displayBoard();
					repaintWiner();
				}
			}

		}
	}
}
