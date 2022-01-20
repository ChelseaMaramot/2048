
package src;

/**
  *	@brief	Implements the view aspect of MVC.
  */

public class UserInterface{

	/**	
	  *	@brief	Prints the board on the computer window.
	  */
	public void printBoard(BoardT boardT){
		TileT[][] board = boardT.getBoard();
		System.out.println("-----------------");
		System.out.println("| " +board[0][0].getValue()+ " | " + board[0][1].getValue() + " | " + board[0][2].getValue() + " | " + board[0][3].getValue()+ " |");
		System.out.println("-----------------");
		System.out.println("| " +board[1][0].getValue()+ " | " + board[1][1].getValue() + " | " + board[1][2].getValue() + " | " + board[1][3].getValue()+ " |");
		System.out.println("-----------------");
		System.out.println("| " +board[2][0].getValue()+ " | " + board[2][1].getValue() + " | " + board[2][2].getValue() + " | " + board[2][3].getValue()+ " |");
		System.out.println("-----------------");
		System.out.println("| " +board[3][0].getValue()+ " | " + board[3][1].getValue() + " | " + board[3][2].getValue() + " | " + board[3][3].getValue()+ " |");
		System.out.println("-----------------");
	}

	/**	
	  *	@brief	Prints the score on the computer window.
	  */
	public void printScore(BoardT boardT){
		System.out.println("Score: " + boardT.getScore());
	}

	/**	
	  *	@brief	Prints the winning or losing message on the computer window.
	  */
	public void printMessage(BoardT boardT){
		System.out.println(boardT.getMessage());
	}
}

