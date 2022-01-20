
package src;

public class A4Example{

	public static void main(String[] args){

		BoardT board = new BoardT();
		UserInterface UI = new UserInterface();
		Controller c = new Controller(board, UI);

		c.runGame();

	}
}
