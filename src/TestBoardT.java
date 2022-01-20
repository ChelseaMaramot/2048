/**
 * @file: TestBoardT.java
 * @Author: Chelsea Maramot
 * @Date: April 9, 2021
 * @Description: Tests BoardT module
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class TestBoardT
{

	private BoardT b1;
	private BoardT b2;
	private BoardT b3;

	@Before 
	public void SetUp(){
		b1 = new BoardT();
		b2 = new BoardT();

		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				b2.getBoard()[i][j].setValue(2);
			}
		}

		b3 = new BoardT();
		int val = 1;
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				b3.getBoard()[i][j].setValue(val);
				val++;
			}
		}
		b3.setLoseMessage();

	}

	@After
	public void TearDown(){
		b1 = null;
		b2 = null;
	}

	//---------------------------------------------Testing getBoard--------------
	@Test
	public void testTwoRandomValuesGenerated(){
		int random = 0;
		int zero = 0;
		TileT[][] board = b1.getBoard(); 
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if (board[i][j].getValue() == 2){
					random++;
				}
				else if(board[i][j].getValue() ==4){
					random++;
				}
				else if(board[i][j].getValue() ==0){
					zero++;
				}
			}
		}

		assertTrue(random == 2 && zero == 14);
	}
		//---------------------------------------------Testing getScore--------------

	@Test
	public void testGetZeroScore(){
		assertEquals(b1.getScore(), 0);
	}

	@Test
	public void testGetZeroScoreAddAll(){
		Move.mergeUp(b2);
		Move.mergeUp(b2);
		Move.mergeRight(b2);
		Move.mergeRight(b2);
		assertEquals(b2.getScore(), 128);
	}


	//---------------------------------------------Testing getTile--------------

	@Test
	public void testGetTileBasic1(){
		assertTrue(b2.getTile(2,3).getValue() == 2);
	}

	//---------------------------------------------Testing getHighest--------------

	@Test
	public void testGetHighest(){
		b1.getBoard()[1][1].setValue(2048);
		assertTrue(b1.getHighest().getValue() == 2048);
	}

	//---------------------------------------------Testing getMessage--------------

	@Test
	public void testGetMessageLose(){
		assertTrue(b3.getMessage().equals("You Lost!"));
	}

	@Test
	public void testGetMessageWin(){
		b3.setWinMessage();
		assertTrue(b3.getMessage().equals("Congratulations!"));
	}

	//---------------------------------------------Testing getRow--------------

	@Test
	public void testGetRowValue(){
		assertTrue(Arrays.equals(b3.getRowValue(0), new int[]{1,2,3,4}));
	}

	//---------------------------------------------Testing getCol--------------

	@Test
	public void testGetColValue(){
		assertTrue(Arrays.equals(b3.getColValue(0), new int[]{1,5,9,13}));
	}

	//---------------------------------------------Testing setLoseMessage-------------

	//Do not need to test further as it was already tested in getter


	//---------------------------------------------Testing setWinMessage--------------

	//Do not need to test further as it was already tested in getter

	//---------------------------------------------Testing addTile--------------

	@Test
	public void testAddTileThreeTiles(){
		int random = 0;
		int zero = 0;
		b1.addTile();
		TileT[][] board = b1.getBoard(); 
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if (board[i][j].getValue() == 2){
					random++;
				}
				else if(board[i][j].getValue() ==4){
					random++;
				}
				else if(board[i][j].getValue() ==0){
					zero++;
				}
			}
		}

		assertTrue(random == 3 && zero == 13);
	}


/*

		@Test
	public void testAddTileUntilFull(){
		int random = 0;
		int zero = 0;

		for(int i=0; i<15; i++){
			b1.addTile();
		}
		
		TileT[][] board = b1.getBoard(); 
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if (board[i][j].getValue() == 2){
					random++;
				}
				else if(board[i][j].getValue() ==4){
					random++;
				}
				else if(board[i][j].getValue() ==0){
					zero++;
				}
			}
		}

		assertTrue(random == 16 && zero == 0);
	}
*/ 


	//---------------------------------------------Testing addScore-------------
	@Test
	public void testAddScore(){
		b1.addScore(2);
		assertTrue(b1.getScore() == 2);
	}

	//---------------------------------------------Testing checkWin--------------
	@Test //Moves left
	public void testCheckWinFalseMovesLeft(){
		assertFalse(b1.checkWin());
	}

	@Test //No moves left
	public void testCheckWinFalseNoMoves(){
		assertFalse(b3.checkWin());
	}


	@Test //Win
	public void testCheckWinTrue(){
		b1.getBoard()[1][1].setValue(2048);
		assertTrue(b1.checkWin());
	}

	//---------------------------------------------Testing checkLose--------------

	@Test //One free move
	public void testCheckLoseOneZero(){
		b3.getBoard()[0][0].setValue(0);
		assertFalse(b3.checkLose());
	}

	@Test //A lot of moves left and free space
	public void testCheckLoseFalse(){
		assertFalse(b1.checkLose());
	}

	@Test // lost
	public void testCheckLoseTrue(){
		assertTrue(b3.checkLose());
	}

	@Test //Won
	public void testCheckLoseWon(){
		b3.getBoard()[1][1].setValue(2048);
		assertFalse(b3.checkLose());
	}

}



