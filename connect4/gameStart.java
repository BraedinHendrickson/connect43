package project2;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class gameStart {
	public static void main (String[] args)
	{	
		// centers window in the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		JFrame frame = new JFrame ("Connect Four");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		ConnectFourGame game = new ConnectFourGame();
		DialogConnect x = new DialogConnect(frame, game);
		if (x.getCloseStatus()==DialogConnect.OK){
			game.setup();

			ConnectFourPanel panel = new ConnectFourPanel(game);
			
			frame.getContentPane().add(panel);
			frame.setSize((68*game.getC()), (55*game.getR())+140);

			frame.setLocation(dim.width/2-frame.getSize().width/2, 
					dim.height/2-frame.getSize().height/2);

			frame.setVisible(true);
		}
	}
}
