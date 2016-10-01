package project2;
/*****************************************************************
Connect Four Game
CIS 163 Project 2
Connect Four program 

Connect Four game that has resizable board by hight and by width 
along with totla user and also what user is to go first along with 
the number of connections needed to win the game asked for at the 
beginning of the program in one single panel compleet with undo 
button and a display of players and how many wins each players has
 
@author Braedin Hendrickson
@version Fall 2015
 *****************************************************************/
public class ConnectFourGame {
	/** private 2D array of board*/
	private int[][] board;

	/** private int of status if game is won*/
	private int status;

	/** private int of players turn*/
	private int turn;

	/** private int of total rows*/
	private int rows;

	/** private int of total columns*/
	private int cols;

	/** private int of total connections*/
	private int connections;

	/** private int of total turns taken*/
	private int turnCount;

	/** private int of total players*/
	private int players;

	/** private int player beginning player*/
	private int masterTurn;

	/** private int array of rows that hold past turns taken*/
	private int[] undoRow;

	/** private int array of columns that hold past turns taken*/
	private int[] undoCol; 

	/** private int array of wins that hold past wins by player*/
	private int[] wins;

	/*****************************************************************
    Default constructor creates an empty ConnectFourGame
	 *****************************************************************/

	public ConnectFourGame(){

	}

	/*****************************************************************
    Default constructor creates ConnectFourGame with int rows, int 
    columns, int players, int connections 
    @param rows
    @param columns
    @param players
    @param connections
	 *****************************************************************/

	public ConnectFourGame(int rs, int cs,int play,int connect){
		connections=connect;
		players= play;
		rows= rs;
		cols=cs;
		status = 0;
		board = new int[rows][cols];
		turn = 1;
		turnCount = 0;
		undoRow = new int[(rows*cols)];
		undoCol = new int[(rows*cols)];
	}
	/*****************************************************************
    Method creates the rest of the needed internal 
    based on the users input

	 *****************************************************************/
	public void setup (){
		status = 0;
		board = new int[rows][cols];
		turn = masterTurn;
		turnCount = 0;
		undoRow = new int[(rows*cols)];
		undoCol = new int[(rows*cols)];
		wins = new int[players+1];
		//sets array to "0"
		for(int i=1; i<players+1; i++){
			wins[i]=0;
		}
	}
	/*****************************************************************
    Method returns the board array
    @return board

	 *****************************************************************/
	public int[][] getBoard() {
		return board;
	}

	/*****************************************************************
    Method that decides if a column is full and if it isint it decides 
    where the piece will be placed 
    @param column 
	 *****************************************************************/
	public void selectCol(int col){
		
		//forloop rows checks to see if its empty 
		for(int i = (rows - 1); i > -1; i--){
			if ((board[i][col] == 0) && 
					(status == 0)) {
				board[i][col] = turn;

				//memorizes turn location
				undoRow[turnCount]=i;
				undoCol[turnCount]=col;
				turnCount++;

				if (turn == players){
					turn = 1;
				}else{
					turn++;
				}
				//checks for winner if win adds win to players win count
				if(getGameStatus()>0){
					wins[status]++;
				}
				break;
			}
		}
	}
	/*****************************************************************
    Method that resets the board[][] to all "0"
    and sets the turn back to the first player
	 *****************************************************************/
	public void reset(){
		//resets bord to"00"
		for (int r = 0; r < rows; r++) 
			for (int c = 0; c < cols; c++) 
				board[r][c] = 0;
		status = 0;
		turnCount = 0;
		//returns turn to original player
		turn = masterTurn;
	}
	/*****************************************************************
    Method that removes sets the turn back one 
    and sets that board space to "0"
	 *****************************************************************/
	public void goBack(){
		if(turnCount>0){
			turnCount--;
			if (turn == 1){
				turn = players;
			}else{
				turn--;}
		}
		if (status>0){
			wins[status]--;

		}
		//sets board location of last turn to "0"
		board[undoRow[turnCount]][undoCol[turnCount]]=0;
		getGameStatus();

	}
	/*****************************************************************
    Method that checks if a player has won and returns the 
    player in form of int value 0 being game in progress 
    and -1 for stalemate 1 and beond for who won the game
	 *****************************************************************/
	public int getGameStatus() {

		status=-1;
		//forloop of rows
		for (int r = 0; r < rows; r++) 
			//forloop of columns
			for (int c = 0; c < cols; c++){
				//checks if board has unused slots
				if (board[r][c] == 0){
					status=0;
					break;
				}
			}
		//checks for each player 
		for (int i = 1; i <(players+1);i++){
			//checks each row
			for (int r = 0; r < rows; r++){
				//checks each column
				for (int c = 0; c < cols; c++){ 
					if (board[r][c] == i){
						//creats individual count for each win direction
						for ( int ia=0,count[] = 
							{0, 0, 0, 0}; ia< connections;ia++){

							//checks horizontal
							if(board[r][(c+ia)%cols] == i){
								count[0]++;
								if (count[0]== connections){
									status= i;

									return status;
								}
							}
							//checks virtical
							if(board[(r+ia)%rows][c] == i){
								count[1]++;
								if (count[1]== connections){
									status= i;

									return status;
								}
							}
							//ckecks diagonal 
							if(board[(r+ia)%rows][(c+ia)%cols] == i){
								count[2]++;
								if (count[2]== connections){
									status= i;

									return status;
								}

							}
						}
					}
				}
				// forloop for offset columns 
				for (int c = connections; c < cols; c++){ 
					if (board[r][c] == i)
						for ( int ia=0,count=0; ia< connections;ia++){
							if(board[(r+ia)%rows][(c-ia)%cols] == i){
								count++;
								//checks for alternate horizontal  
								if (count== connections){
									status= i;

									return status;
								}
							}
						}
				}
			}

		}
		return status;
	}
	/*****************************************************************
    Method that gets rows
    @return rows returns integer of rows
	 *****************************************************************/
	public int getR() {
		return rows;
	}
	/*****************************************************************
    Method that sets rows
    @param rows integer of rows
	 *****************************************************************/
	public void setR(int rows) {
		this.rows = rows;
	}
	/*****************************************************************
    Method that gets columns
    @return columns returns integer of columns
	 *****************************************************************/
	public int getC() {
		return cols;
	}
	/*****************************************************************
    Method that sets columns 
    @param columns integer of columns
	 *****************************************************************/
	public void setC(int cols) {
		this.cols = cols;
	}
	/*****************************************************************
    Method that gets connections
    @return connections returns integer of connects to win
	 *****************************************************************/
	public int getCn() {
		return connections;
	}
	/*****************************************************************
    Method that sets connections
    @param connections integer of connects to win
	 *****************************************************************/
	public void setCn(int connections) {
		this.connections = connections;
	}
	/*****************************************************************
    Method that gets players
    @return players integer amount of players
	 *****************************************************************/
	public int getP() {
		return players;
	}
	/*****************************************************************
    Method that sets players
    @param players integer amount of players
	 *****************************************************************/
	public void setP(int players) {
		this.players = players;
	}
	/*****************************************************************
    Method that gets wins
    @return wins integer amount of wins for a player
	 *****************************************************************/
	public int getWins(int i){
		return wins[i];
	}
	/*****************************************************************
    Method that sets master turn
    @param masterTurns
	 *****************************************************************/
	public void setTurn(int t){
		masterTurn =t;
	}

}
