package src;

import java.util.Arrays;

/**
  *		@brief	An abstract object for merging the tiles in the board.
  */
public class Move{

	private static int[][] old_board = new int[4][4];

	/**
	  *		@brief Gets the old board of the BoardT object.
	  *		@param board Represents a two dimentional array of TileT.
	  *		@return A two dimentional integer array representing a copy of the old board.
	  */
	public static int[][] getOldBoard(){
		return old_board;
	}

	/**
	  *		@brief Copies the current board of the BoardT object.
	  *		@param board Represents a two dimentional array of TileT.
	  */
	public static void setOldBoard(TileT[][] board){
		for (int i=0; i<4; i++){
			for (int j=0; j<4; j++){
				old_board[i][j] = board[i][j].getValue();
			}
		}
	}


	/**
	  *		@brief Returns if the tiles are able to move in the board.
	  *		@param boardT Represents a boardT object.
	  * 	@return A boolean representing if the tiles can move.
	  */
	public static boolean canMove(BoardT boardT){
		TileT[][] new_board = boardT.getBoard();
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if(old_board[i][j] != new_board[i][j].getValue()){
					return true;
				}
			}
		}
		return false;
	}

	/**
	  *		@brief Merges the tiles to the left.
	  *		@details Loops through the tiles and slides left if the adjacent
	  *				 value is zero, then combines tiles of similar values. 
	  *				 Lastly, it slides the tiles to the left again if the
	  *				 adjacent value is zero.
	  *		@param boardT The game board object of type BoardT.
	  */
	public static void mergeLeft(BoardT boardT){
		TileT[][] board = boardT.getBoard();
		for(int row=0; row<4; row++){
			slideZero(row, Direction.LEFT, boardT);
			for(int col=1; col<4; col++){
				combine(row, col, Direction.LEFT, boardT);
			}
			slideZero(row, Direction.LEFT, boardT);
		}
	}

	/**
	  *		@brief Merges the tiles to the right.
	  *		@details Loops through the tiles and slides right if the adjacent
	  *				 value is zero, then combines tiles of similar values. 
	  *				 Lastly, it slides the tiles to the right again if the
	  *				 adjacent value is zero.
	  *		@param boardT The game board object of type BoardT.
	  */
	public static void mergeRight(BoardT boardT){
		TileT[][] board = boardT.getBoard();
		for(int row=0; row<4; row++){
			slideZero(row, Direction.RIGHT, boardT);
			for(int col=2; col>-1; col--){
				combine(row, col, Direction.RIGHT, boardT);
			}
			slideZero(row, Direction.RIGHT, boardT);
		}
	}

	/**
	  *		@brief Merges the tiles to down.
	  *		@details Loops through the tiles and slides down if the adjacent
	  *				 value is zero, then combines tiles of similar values. 
	  *				 Lastly, it slides the tiles down again if the
	  *				 adjacent value is zero.
	  *		@param boardT The game board object of type BoardT.
	  */
	public static void mergeDown(BoardT boardT){
		TileT[][] board = boardT.getBoard();

		for(int col=0; col<4; col++){
			slideZero(col, Direction.DOWN, boardT);
			for(int row=2; row>-1; row--){
				combine(row,col, Direction.DOWN, boardT);
			}
			slideZero(col, Direction.DOWN, boardT);
		}	
	}

	/**
	  *		@brief Merges the tiles to up.
	  *		@details Loops through the tiles and slides up if the adjacent
	  *				 value is zero, then combines tiles of similar values. 
	  *				 Lastly, it slides the tiles up again if the
	  *				 adjacent value is zero.
	  *		@param boardT The game board object of type BoardT.
	  */
	public static void mergeUp(BoardT boardT){
		TileT[][] board = boardT.getBoard();

		for(int col=0; col<4; col++){
			slideZero(col, Direction.UP, boardT);
			for(int row=1; row<4; row++){
				combine(row,col, Direction.UP, boardT);
			}
			slideZero(col, Direction.UP, boardT);
			
		}
	}

	/**
	  *		@brief A helper function that slides the tiles through zeroes.
	  *		@param pos An integer representing the row or column.
	  *		@param dir A direction of an enumerated type Direction.
	  *		@param boardT The game board object of type BoardT.
	  */
	private static void slideZero(int pos, Direction dir, BoardT boardT){
		TileT[][] board = boardT.getBoard();
		int index;
		int[] cells = new int[]{0,0,0,0};
		
		if(dir == Direction.RIGHT){
			index = 3;
			for(int i=3; i>=0; i--){
				if(board[pos][i].getValue() == 0) continue;
				cells[index] = board[pos][i].getValue();
				index--;
			}

			for(int i=0; i<4; i++){
				board[pos][i].setValue(cells[i]);
			}
		}

		else if(dir == Direction.LEFT){
			UserInterface UI = new UserInterface();
			index = 0;
			for(int val : boardT.getRowValue(pos)){
				if(val == 0) continue;
				cells[index] = val;
				index++;
			
			}
			for(int i=0; i<4; i++){
				board[pos][i].setValue(cells[i]);
			}
		}

		else if(dir == Direction.DOWN){
			index = 3;
			for(int i=3; i>=0; i--){
				if(board[i][pos].getValue() == 0) continue;
				cells[index] = board[i][pos].getValue();
				index--;
			}

			for(int i=0; i<4; i++){
				board[i][pos].setValue(cells[i]);
			}

		}else{
			index = 0;
			for(int val : boardT.getColValue(pos)){
				if(val == 0) continue;
				cells[index] = val;
				index++;
			}

			for(int i=0; i<4; i++){
				board[i][pos].setValue(cells[i]);
			}
		}

	}

	/**
	  *		@brief A helper function to combine adjacent tiles of the same value.
	  *		@param row An integer representing the row.
	  *		@param col An integer representing the column.
	  *		@param dir A direction of an enumerated type Direction.
	  *		@param boardT The game board object of type BoardT.
	  */
	private static void combine(int row, int col, Direction dir, BoardT boardT){
		TileT[][] board = boardT.getBoard();

		int x = dir.getX();
		int y = dir.getY();; 

		if(board[row][col].getValue() != 0 && board[row+x][col+y].equal(board[row][col])){
			board[row+x][col+y].add();
			boardT.addScore(board[row+x][col+y].getValue());
			board[row][col].setValue(0);
		}

	}


}