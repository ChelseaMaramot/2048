package src;

import java.awt.event.*;
import java.util.Scanner;


/**
  *	@brief An ADT to operate the ASCII version of 2048.
  *	@details Use keys "a", "w", "s", and "d" to move tiles.
 */
public class Controller{

	private BoardT boardT;
	private UserInterface UI;
	private Scanner keyboard;
	private String key;

	/**	
	  *	@brief	A Constructor for initiating a Controller object.
	  */
	public Controller(BoardT model, UserInterface view){
		this.boardT = model;
		this.UI = view;
		this.keyboard = new Scanner(System.in);
	}


	/**	
	  *	@brief	Reads the keyboard inputs.
	  */
	public void readKeyInput(){
		System.out.println("Enter next move:");
		do{
			this.key = (this.keyboard.nextLine()).toLowerCase();
		} while (!(this.key.equals("a") || this.key.equals("w") || this.key.equals("d") || this.key.equals("s")));
	}

	/**	
	  *	@brief	Moves the tile according to the current keyboard input.
	  */
	public void moveTile(){
		if(this.key.equals("a")){
			Move.mergeLeft(this.boardT);
		}
		else if(this.key.equals("w")){
			Move.mergeUp(this.boardT);
		}
		else if(this.key.equals("d")){
			Move.mergeRight(this.boardT);
		}
		else if(this.key.equals("s")){
			Move.mergeDown(this.boardT);
		}
		
	}

	/**	
	  *	@brief	Runs the 2048 game.
	  */
	public void runGame(){
		while(true){
			UI.printBoard(this.boardT);
			readKeyInput();
			Move.setOldBoard(this.boardT.getBoard());
			moveTile();
			UI.printScore(this.boardT);
			
			if(Move.canMove(this.boardT)){
				boardT.addTile();
			}

			if(this.boardT.checkWin()){
				boardT.setWinMessage();
				UI.printBoard(this.boardT);
				UI.printMessage(this.boardT);
				break;
			}

			if(this.boardT.checkLose()){
				boardT.setLoseMessage();
				UI.printBoard(this.boardT);
				UI.printMessage(this.boardT);
				break;
			}
		}
	}
}
