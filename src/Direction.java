
package src;


	/**
	  *		@brief An enumerated type representing the movements of the tile.
	  */
public enum Direction{

	UP(-1, 0),
	DOWN(1, 0),
	LEFT(0, -1),
	RIGHT(0, 1);

	private final int x;
	private final int y;


	/**
			@brief Initializes the values of the enumerated types for Direction.
	  */ 
	Direction(int xVector, int yVector){
		x = xVector;
		y = yVector;
	}


	/**
	  *		@brief A getter to get the x value of the corresponding Direction 
	  *			   enumerated type.
	  */ 
	public int getX(){
		return x;
	}

	/**
	  *		@brief A getter to get the y value of the corresponding Direction 
	  *			   enumerated type.
	  */ 
	public int getY(){
		return y;
	}
}