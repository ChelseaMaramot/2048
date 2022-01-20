
package src;

/**
		@brief An ADT for representing the tiles in the board.
  */

public class TileT{

	private int value;
	private int row;
	private int col;	

	/**
	  *		@brief A constructor for initiating a TileT object.
	  *		@param val An integer representing the value of the TileT object.
	  *		@param row An integer representing the row of the TileT object.
	  *		@param col An integer representing the column of the TileT object.
	  */ 
	public TileT(int val, int row, int col){
		this.value = val;
		this.row = row;
		this.col = col;
	}

	/**
	  *		@brief A getter for the value of the tile.
	  *		@return An integer representing the value of the TileT object.
	  */
	public int getValue(){
		return this.value;
	}

	/**
	  *		@brief A getter for the position of the tile.
	  *		@return An array of integer consisting of the row and column position.
	  */
	public int[] getPosition(){
		return new int[]{this.row, this.col};
	}

	/**
	  *		@brief Sets the value of the TileT object.
	  */
	public void setValue(int val){
		this.value = val;

	}


	/**
	  *		@brief Checks if another tile has the same value as the TileT object.
	  *		@param tile A tile of type TileT.
	  *		@return A boolean representing if two tiles have the same value.
	  */
	public boolean equal(TileT tile){
		return this.value == tile.getValue();
	}


	/**
	  *		@brief Adds the tiles when merged.
	  *		@detail The value of the tile is doubled.
	  */
	public void add(){
		this.value += this.value;
	}
}
