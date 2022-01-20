package src;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Arrays;

public class TestTileT
{

	private TileT t1;
	private TileT t2;
	private TileT t3;

	@Before
	public void setUp(){
		t1 = new TileT(0,0,0);
		t2 = new TileT(4,1,1);
		t3 = new TileT(2,4,3);
	}

	@After 
	public void tearDown(){
		t1 = null;
		t2 = null;
		t3 = null;
	}

    @Test
    public void testGetValue()
    {
        assertTrue(t1.getValue() == 0);
    }

    @Test
    public void testGetPosition(){
    	assertTrue(Arrays.equals(t3.getPosition(),new int[]{4,3}));
    }

    @Test
    public void testSetValue(){
    	t2.setValue(2);

    	assertTrue(t2.getValue() == 2);
    }

    @Test
    public void testEqualSelf(){
    	assertTrue(t1.equal(t1));
    }

    @Test
    public void testEqualSimilar(){
    	assertTrue(t1.equal(new TileT(0,0,0)));
    }

    @Test
    public void testAddZero(){
    	t1.add();
    	assertTrue(t1.getValue() == 0);
    }

    @Test
    public void testAddBasic(){
    	t2.add();
    	assertTrue(t2.getValue() == 8);
    }


}
