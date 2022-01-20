/**
 * Author: Chelsea Maramot
 * Revised: April 4, 2021
 * 
 * Description: Testing all of the modules
 */

package src;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   TestTileT.class,
   TestBoardT.class,
   TestMove.class
})

public class AllTests
{
}
