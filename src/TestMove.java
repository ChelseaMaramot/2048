/**
 * @file: TestBoardT.java
 * @Author: Chelsea Maramot
 * @Date: April 9, 2021
 * @Description: Tests Move Module
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class TestMove
{

	private BoardT b1;
	private BoardT b2;
	private UserInterface UI;

	@Before 
	public void SetUp(){
		b1 = new BoardT();
		b2 = new BoardT();
		int val = 0;
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				b2.getBoard()[i][j].setValue(val);
				val++;
			}
		}
	}

	@After
	public void TearDown(){
		b1 = null;
	}


	@Test
	public void testSetOldBoard(){
		Move.setOldBoard(b1.getBoard());
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				if (b1.getBoard()[i][j].getValue() != Move.getOldBoard()[i][j]){
					assertTrue(false);
				}
			}
		}
		assertTrue(true);
	}

	@Test 
	public void testCanMoveTrue(){
		for(int i=0; i<4; i++){
			b1.getBoard()[0][i].setValue(2);
		}
		assertTrue(Move.canMove(b1));
	}

	@Test 
	public void testCanMoveFalse(){
		assertTrue(Move.canMove(b2));
	}
	


	// less than equal to two
	// moved tile + new tile added after merge
	@Test
	public void testMoveRightOne(){
		int num = 0;

		for(int i=0; i<3; i++){
			b1.getBoard()[0][i].setValue(0);
		}
		b1.getBoard()[0][0].setValue(2);
		Move.mergeRight(b1);


		// tests to see if the moved tile is there
		// and if a tile was added
		int[] row = b1.getRowValue(0);
		for(int val : row){
			if(val == 2 || val == 4){
				num++;
		
			}
		}
		assertTrue(num <= 2);
	}


	// Tests if the tile is in the proper position
	@Test
	public void testMoveRightOnePosition(){
		int num = 0;
		UserInterface UI = new UserInterface();
		for(int i=0; i<4; i++){
			b1.getBoard()[0][i].setValue(0);
		}
		b1.getBoard()[0][0].setValue(2);
		Move.mergeRight(b1);
		int[] row = b1.getRowValue(0);
		assertEquals(row[3], 2);
	}

	@Test
	public void testMoveRightTwoSameValue(){
		int num = 0;
		for(int i=0; i<3; i++){
			b1.getBoard()[0][i].setValue(0);
		}
		
		b1.getBoard()[0][0].setValue(2);
		b1.getBoard()[0][2].setValue(2);
		Move.mergeRight(b1);
		
		int[] row = b1.getRowValue(0);
		for(int val : row){
			if(val == 2 || val == 4){
				num++;
			}
		}
		assertTrue(num <= 2);
	}

	@Test
	public void testMoveRightSameValuePosition(){
		int num = 0;
		for(int i=0; i<3; i++){
			b1.getBoard()[0][i].setValue(0);
		}
		b1.getBoard()[0][0].setValue(2);
		b1.getBoard()[0][2].setValue(2);
		Move.mergeRight(b1);
		int[] row = b1.getRowValue(0);
		assertTrue(row[3] == 4);
	}

	@Test
	public void testMoveRightAllSame(){
		int num = 0;
		for(int i=0; i<4; i++){
			b1.getBoard()[3][i].setValue(2);
		}
		Move.mergeRight(b1);
		int[] row = b1.getRowValue(3);
		for(int val : row){
			if(val == 2 || val == 4){
				num++;
			}
		}
		assertTrue(num <= 3);
	}

	@Test
	public void testMoveRightAllSamePosition(){
		for(int i=0; i<4; i++){
			b1.getBoard()[3][i].setValue(2);
		}
		UserInterface UI = new UserInterface();
		Move.mergeRight(b1);
		int[] row = b1.getRowValue(3);
		assertTrue(row[3] == 4 && row[3] == 4);
	}

	@Test
	public void testMoveRightDifferentValues(){
		int num = 0;
		for(int i=0; i<4; i++){
			b1.getBoard()[1][i].setValue(0);
		}
		b1.getBoard()[1][0].setValue(2);
		b1.getBoard()[1][2].setValue(4);
		Move.mergeRight(b1);
		int[] row = b1.getRowValue(1);
		for(int val : row){
			if(val == 2 || val == 4){
				num++;
			}
		}
		assertTrue(num <= 3);
	}

	@Test
	public void testMoveRightDifferentValuesPosition(){
		int num = 0;
		for(int i=0; i<4; i++){
			b1.getBoard()[1][i].setValue(0);
		}
		b1.getBoard()[1][0].setValue(2);
		b1.getBoard()[1][2].setValue(4);
		Move.mergeRight(b1);
		int[] row = b1.getRowValue(1);
		assert(row[3] == 4 && row[2] == 2);
	}

	@Test
	public void testMoveRightMixedValues(){
		int num = 0;
		b1.getBoard()[1][0].setValue(2);
		b1.getBoard()[1][2].setValue(2);
		b1.getBoard()[1][2].setValue(4);
		b1.getBoard()[1][2].setValue(4);
		Move.mergeRight(b1);
		int[] row = b1.getRowValue(1);
		for(int val : row){
			if(val == 2 || val == 4){
				num++;
			}
		}
		assertTrue(num <= 3);
	}


	@Test
	public void testMoveRightMixedValuesPosition(){
		b1.getBoard()[1][0].setValue(2);
		b1.getBoard()[1][1].setValue(2);
		b1.getBoard()[1][2].setValue(4);
		b1.getBoard()[1][3].setValue(4);
		//UI.printBoard(b1);
		Move.mergeRight(b1);
		int[] row = b1.getRowValue(1);
		//UI.printBoard(b1);
		assertTrue(row[3] == 8 && row[2] == 4);
	}
	//------------------------------MoveRight-----------------------------

		@Test
	public void testMoveLeftOne(){
		int num = 0;

		for(int i=0; i<3; i++){
			b1.getBoard()[0][i].setValue(0);
		}
		b1.getBoard()[0][0].setValue(2);
		Move.mergeLeft(b1);


		// tests to see if the moved tile is there
		// and if a tile was added
		int[] row = b1.getRowValue(0);
		for(int val : row){
			if(val == 2 || val == 4){
				num++;
		
			}
		}
		assertTrue(num <= 2);
	}

	

	// Tests if the tile is in the proper position
	@Test
	public void testMoveLeftOnePosition(){
		for(int i=0; i<3; i++){
			b1.getBoard()[0][i].setValue(0);
		}
		b1.getBoard()[0][3].setValue(2);
		Move.mergeLeft(b1);
		int[] row = b1.getRowValue(0);
		assertEquals(row[0],2);
	}



	@Test
	public void testMoveLeftTwoSameValue(){
		UserInterface UI = new UserInterface();
		int num = 0;
		for(int i=0; i<3; i++){
			b1.getBoard()[0][i].setValue(0);
		}
		
		b1.getBoard()[0][1].setValue(2);
		b1.getBoard()[0][2].setValue(2);
		Move.mergeLeft(b1);
		int[] row = b1.getRowValue(0);
		for(int val : row){
			if(val == 2 || val == 4){
				num++;
			}
		}
		assertTrue(num <= 2);
	}



	@Test
	public void testMoveLeftSameValuePosition(){
		int num = 0;
		for(int i=0; i<3; i++){
			b1.getBoard()[0][i].setValue(0);
		}
		UserInterface UI = new UserInterface();
		b1.getBoard()[0][0].setValue(2);
		b1.getBoard()[0][2].setValue(2);
		//UI.printBoard(b1);
		Move.mergeLeft(b1);
		//UI.printBoard(b1);
		int[] row = b1.getRowValue(0);
		assertTrue(row[0] == 4);
	}

	@Test
	public void testMoveLeftAllSame(){
		int num = 0;
		for(int i=0; i<4; i++){
			b1.getBoard()[3][i].setValue(2);
		}
		Move.mergeLeft(b1);
		int[] row = b1.getRowValue(3);
		for(int val : row){
			if(val == 2 || val == 4){
				num++;
			}
		}
		assertTrue(num <= 3);
	}

	@Test
	public void testMoveLeftAllSamePosition(){
		UserInterface UI = new UserInterface();
		for(int i=0; i<4; i++){
			b1.getBoard()[3][i].setValue(2);
		}
		//UI.printBoard(b1);
		Move.mergeLeft(b1);
		//UI.printBoard(b1);
		int[] row = b1.getRowValue(3);
		assertTrue(row[0] == 4 && row[1] == 4);
	}

	@Test
	public void testMoveLeftDifferentValues(){
		int num = 0;
		for(int i=0; i<4; i++){
			b1.getBoard()[1][i].setValue(0);
		}
		b1.getBoard()[1][0].setValue(2);
		b1.getBoard()[1][2].setValue(4);
		Move.mergeLeft(b1);
		int[] row = b1.getRowValue(1);
		for(int val : row){
			if(val == 2 || val == 4){
				num++;
			}
		}
		assertTrue(num <= 3);
	}

	@Test
	public void testMoveLeftDifferentValuesPosition(){
		int num = 0;
		for(int i=0; i<4; i++){
			b1.getBoard()[1][i].setValue(0);
		}
		b1.getBoard()[1][3].setValue(2);
		b1.getBoard()[1][2].setValue(4);
		Move.mergeLeft(b1);
		int[] row = b1.getRowValue(1);
		assert(row[0] == 4 && row[1] == 2);
	}

	@Test
	public void testMoveLeftMixedValues(){
		int num = 0;
		b1.getBoard()[1][0].setValue(2);
		b1.getBoard()[1][2].setValue(2);
		b1.getBoard()[1][2].setValue(4);
		b1.getBoard()[1][2].setValue(4);
		Move.mergeLeft(b1);
		int[] row = b1.getRowValue(1);
		for(int val : row){
			if(val == 2 || val == 4){
				num++;
			}
		}
		assertTrue(num <= 3);
	}

	@Test
	public void testMoveLeftMixedValuesPosition(){
		b1.getBoard()[1][0].setValue(2);
		b1.getBoard()[1][1].setValue(2);
		b1.getBoard()[1][2].setValue(4);
		b1.getBoard()[1][3].setValue(4);
		Move.mergeLeft(b1);
		int[] row = b1.getRowValue(1);
		assertTrue(row[0] == 4 && row[1] == 8);
	}
	//-------------------------------------------TestMergeUp------------------


		@Test
	public void testMoveUpOne(){
		int num = 0;

		for(int i=0; i<3; i++){
			b1.getBoard()[i][2].setValue(0);
		}
		b1.getBoard()[2][2].setValue(2);
		Move.mergeUp(b1);

		// tests to see if the moved tile is there
		// and if a tile was added
		int[] col = b1.getColValue(2);
		for(int val : col){
			if(val == 2 || val == 4){
				num++;
		
			}
		}
		assertTrue(num <= 2);
	}

	

	// Tests if the tile is in the proper position
	@Test
	public void testMoveUpOnePosition(){
		UserInterface UI = new UserInterface();
		for(int i=0; i<3; i++){
			b1.getBoard()[i][2].setValue(0);
		}
		b1.getBoard()[2][2].setValue(2);
		Move.mergeUp(b1);
		int[] col = b1.getColValue(2);
		assertEquals(col[0],2);
	}



	@Test
	public void testMoveUpTwoSameValue(){
		UserInterface UI = new UserInterface();
		int num = 0;
		for(int i=0; i<3; i++){
			b1.getBoard()[i][0].setValue(0);
		}
		
		b1.getBoard()[2][0].setValue(2);
		b1.getBoard()[3][0].setValue(2);
		Move.mergeUp(b1);
		int[] col = b1.getColValue(0);
		for(int val : col){
			if(val == 2 || val == 4){
				num++;
			}
		}
		assertTrue(num <= 2);
	}



	@Test
	public void testMoveUpSameValuePosition(){
		int num = 0;
		for(int i=0; i<3; i++){
			b1.getBoard()[0][i].setValue(0);
		}
		UserInterface UI = new UserInterface();
		b1.getBoard()[2][0].setValue(2);
		b1.getBoard()[3][0].setValue(2);
		Move.mergeUp(b1);		
		int[] col = b1.getColValue(0);
		assertTrue(col[0] == 4);
	}

	@Test
	public void testMoveUpAllSame(){
		int num = 0;
		for(int i=0; i<4; i++){
			b1.getBoard()[i][3].setValue(2);
		}
		Move.mergeUp(b1);
		int[] col = b1.getColValue(3);
		for(int val : col){
			if(val == 2 || val == 4){
				num++;
			}
		}
		assertTrue(num <= 3);
	}

	@Test
	public void testMoveUpAllSamePosition(){
		for(int i=0; i<4; i++){
			b1.getBoard()[i][3].setValue(2);
		}
		Move.mergeUp(b1);
		int[] col = b1.getColValue(3);
		assertTrue(col[0] == 4 && col[1] == 4);
	}

	@Test
	public void testMoveUpDifferentValues(){
		int num = 0;
		for(int i=0; i<4; i++){
			b1.getBoard()[i][1].setValue(0);
		}
		b1.getBoard()[0][1].setValue(2);
		b1.getBoard()[2][1].setValue(4);
		Move.mergeUp(b1);
		int[] col = b1.getColValue(1);
		for(int val : col){
			if(val == 2 || val == 4){
				num++;
			}
		}
		assertTrue(num <= 3);
	}

	@Test
	public void testMoveUpDifferentValuesPosition(){
		int num = 0;
		for(int i=0; i<4; i++){
			b1.getBoard()[i][1].setValue(0);
		}
		b1.getBoard()[3][1].setValue(2);
		b1.getBoard()[2][1].setValue(4);
		Move.mergeUp(b1);
		int[] col = b1.getColValue(1);
		assert(col[0] == 4 && col[1] == 2);
	}

	@Test
	public void testMoveUpMixedValues(){
		int num = 0;
		b1.getBoard()[0][1].setValue(2);
		b1.getBoard()[2][1].setValue(2);
		b1.getBoard()[2][1].setValue(4);
		b1.getBoard()[2][1].setValue(4);
		Move.mergeUp(b1);
		int[] col = b1.getColValue(1);
		for(int val : col){
			if(val == 2 || val == 4){
				num++;
			}
		}
		assertTrue(num <= 3);
	}


	@Test
	public void testMoveUpMixedValuesPosition(){
		b1.getBoard()[0][1].setValue(2);
		b1.getBoard()[1][1].setValue(2);
		b1.getBoard()[2][1].setValue(4);
		b1.getBoard()[3][1].setValue(4);
		Move.mergeUp(b1);
		int[] col = b1.getColValue(1);
		assertTrue(col[0] == 4 && col[1] == 8);
	}


//---------------------------------TestMergeDown---------------	
	@Test
	public void testMoveDownOne(){
		int num = 0;
		for(int i=0; i<3; i++){
			b1.getBoard()[2][i].setValue(0);
		}
		b1.getBoard()[2][3].setValue(2);
		Move.mergeUp(b1);

		// tests to see if the moved tile is there
		// and if a tile was added
		int[] col = b1.getColValue(2);
		for(int val : col){
			if(val == 2 || val == 4){
				num++;
		
			}
		}
		assertTrue(num <= 2);
	}

	

	// Tests if the tile is in the proper position
	@Test
	public void testMoveDownOnePosition(){
		for(int i=0; i<3; i++){
			b1.getBoard()[i][3].setValue(0);
		}
		b1.getBoard()[2][3].setValue(2);
		Move.mergeDown(b1);
		int[] col = b1.getColValue(3);
		assertEquals(col[3],2);
	}



	@Test
	public void testMovedDownTwoSameValue(){
		int num = 0;
		for(int i=0; i<3; i++){
			b1.getBoard()[i][0].setValue(0);
		}
		
		b1.getBoard()[0][0].setValue(2);
		b1.getBoard()[2][0].setValue(2);
		Move.mergeDown(b1);
		int[] col = b1.getColValue(0);
		for(int val : col){
			if(val == 2 || val == 4){
				num++;
			}
		}
		assertTrue(num <= 2);
	}



	@Test
	public void testMoveDownSameValuePosition(){
		for(int i=0; i<3; i++){
			b1.getBoard()[0][i].setValue(0);
		}
		b1.getBoard()[0][0].setValue(2);
		b1.getBoard()[2][0].setValue(2);
		Move.mergeDown(b1);		
		int[] col = b1.getColValue(0);
		assertTrue(col[3] == 4);
	}

	@Test
	public void testMoveDownAllSame(){
		int num = 0;
		for(int i=0; i<4; i++){
			b1.getBoard()[i][3].setValue(2);
		}
		Move.mergeDown(b1);
		int[] col = b1.getColValue(3);
		for(int val : col){
			if(val == 2 || val == 4){
				num++;
			}
		}
		assertTrue(num <= 3);
	}

	@Test
	public void testMoveDownAllSamePosition(){
		for(int i=0; i<4; i++){
			b1.getBoard()[i][3].setValue(2);
		}
		Move.mergeDown(b1);
		int[] col = b1.getColValue(3);
		assertTrue(col[3] == 4 && col[2] == 4);
	}

	@Test
	public void testMoveDownDifferentValues(){
		int num = 0;
		for(int i=0; i<4; i++){
			b1.getBoard()[i][1].setValue(0);
		}
		b1.getBoard()[0][1].setValue(2);
		b1.getBoard()[2][1].setValue(4);
		Move.mergeUp(b1);
		int[] col = b1.getColValue(1);
		for(int val : col){
			if(val == 2 || val == 4){
				num++;
			}
		}
		assertTrue(num <= 3);
	}

	@Test
	public void testMoveDownDifferentValuesPosition(){
		int num = 0;
		for(int i=0; i<4; i++){
			b1.getBoard()[i][1].setValue(0);
		}
		b1.getBoard()[0][1].setValue(2);
		b1.getBoard()[2][1].setValue(4);
		Move.mergeDown(b1);
		int[] col = b1.getColValue(1);
		assert(col[3] == 4 && col[2] == 2);
	}

	@Test
	public void testMoveDownMixedValues(){
		int num = 0;
		b1.getBoard()[0][1].setValue(2);
		b1.getBoard()[2][1].setValue(2);
		b1.getBoard()[2][1].setValue(4);
		b1.getBoard()[2][1].setValue(4);
		Move.mergeDown(b1);
		int[] col = b1.getColValue(1);
		for(int val : col){
			if(val == 2 || val == 4){
				num++;
			}
		}
		assertTrue(num <= 3);
	}


	@Test
	public void testMoveDownMixedValuesPosition(){
		b1.getBoard()[0][1].setValue(2);
		b1.getBoard()[1][1].setValue(2);
		b1.getBoard()[2][1].setValue(4);
		b1.getBoard()[3][1].setValue(4);
		Move.mergeDown(b1);
		int[] col = b1.getColValue(1);
		assertTrue(col[2] == 4 && col[3] == 8);
	}
}

