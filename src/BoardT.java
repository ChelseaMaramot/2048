
package src;

/**
  *	@detail An ADT for representing the model of the board.
  */
public class BoardT{

	private final static int size = 4;
	private TileT highest;
	private int score;
	private TileT[][] board;
	private String message;

	/**
	  *	@brief A Constructor for initializing a BoardT object.
	  */
	public BoardT(){
		this.highest = new TileT(0,0,0);
		this.score = 0;
		this.board = new TileT[size][size];
		for(int row=0; row<size; row++){
			for(int col=0; col<size; col++){
				this.board[row][col] = new TileT(0, row, col);
			}
		}
		this.addTile();
		this.addTile();
	}

	/**
	  *	@brief A getter to get the board array.
	  *	@return The board, a two dimensional TileT array.
	  */
	public TileT[][] getBoard(){
		return this.board;
	}
	
	/**
	  *	@brief A getter to get the current score.
	  *	@return An integer representing the score.
	  */
	public int getScore(){
		return this.score;
	}

	/**
	  *	@brief A getter to get the tile at the indicated position.
	  *	@param row An integer representing the row.
	  *	@param col An integer representing the column.
	  *	@return A TileT object positioned in the specified row 
	  *			and column in the board.
	  */
	public TileT getTile(int row, int col){
		return this.board[row][col];
	}

	/**
	  *	@brief A getter to get the highest tile in the board.
	  *	@return A TileT object representing the highest valued tile.
	  */
	public TileT getHighest(){
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if (this.board[i][j].getValue() > this.highest.getValue()){
					this.highest = board[i][j];
				}
			}
		}
		return this.highest;
	}

	/**
	  *	@brief A getter for the losing or winning message.
	  * @return A String representing the message.
	  */
	public String getMessage(){
		// Add a error if message is empty
		return message;
	}


	/**
	  *	@brief Gets the value of each tile in the indicated row of the board.
	  *	@param row An integer representing the row.
	  *	@return An array of four integers representing the values of each tile in the row.
	  */
	public int[] getRowValue(int row){
		int[] row_board = new int[4];
		for(int col=0; col<4; col++){
			row_board[col] = board[row][col].getValue();
		}
		return row_board;
	}

	/**
	  *	@brief Gets the value of each tile in the indicated column of the board.
	  *	@param col An integer representing the row.
	  *	@return An array of four integers representing the values of each tile in the column.
	  */
	public int[] getColValue(int col){
		int[] col_board = new int[4];
		for(int row=0; row<4; row++){
			col_board[row] = board[row][col].getValue();
		}
		return col_board;
	}

	
	/**
	  *	@brief Sets the message to the losing message.
	  */
	public void setLoseMessage(){
		this.message = "You Lost!";
	}

	/**
	  *	@brief Sets the message to the winning message.
	  */
	public void setWinMessage(){
		this.message = "Congratulations!";
	}
	/**
	  *	@brief Checks if the winning condition is met.
	  *	@details To win 2048, the highest tile value should be 2048.
	  *	@return A boolean, true, if the player won.
	  */
	public boolean checkWin(){

		if (this.getHighest().getValue() == 2048){
			return true;
		}
		return false;
	}

	/**
	  *	@brief Checks if the losing condition is met.
	  *	@details To lose 2048, no tile is able to move in the board.
	  *	@return A boolean, true, if the player lost.
	  */
	public boolean checkLose(){
		for(int row=0; row<4; row++){
			for(int col=0; col<4; col++){

				if(this.board[row][col].getValue() == 2048){
					return false;
				}
				if(this.board[row][col].getValue() == 0){
					return false;
				}
				if(col < 3 && this.board[row][col].equal(this.board[row][col+1])){
					return false;
				}
				if(row < 3 && this.board[row][col].equal(this.board[row+1][col])){
					return false;
				}
			}
		}
		return true;
	}

	/**
	  *	@brief Adds a random tile to the board.
	  */
	public void addTile(){
		int value = generateRandomTile();
		int[] position = generateRandomPosition();
		this.board[position[0]][position[1]].setValue(value);
	}

	/**
	  *	@brief Updates the score in the board.
	  */
	public void addScore(int points){
		this.score += points;
	}

	/**
	  *	@brief A helper function that generates a random tile value.
	  *	@details There is a 90% chance to get a value of 2 and 
	  *		 	 a 10% chance to get the value 4.
	  *	@return An integer, 2 or 4, which represents the value of the new tile.
	  */
	private int generateRandomTile(){
		if (Math.random() < 0.9){
			return 2;
		}else{
			return 4;
		}
	}

	/**
	  *	@brief A helper function that generates a random position in the board
	  *	@details Uses a random function that generates a value between 1 and 0.
	  *			 To convert that into an integer between 0 and 3, multiply by 4.
	  *	@return An array of integer consisting of the row and column.
	  */
	private int[] generateRandomPosition(){
		int row;
		int col;
		do{
			row = (int) (Math.random() * 4);
			col = (int) (Math.random() * 4); 
		}while (this.getTile(row, col).getValue() != 0); 
		return new int[]{row, col};
	}
}


