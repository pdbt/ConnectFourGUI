import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.AfterClass;

/**
 * Contains all program unit tests.
 * 
 * @author Patricia Danielle Tan
 */
public class JUnitTests {

	private static Connect4Game testMdl;
	private static Connect4Controller testCtrl;
	private static Connect4View testView;
	
	/**
	 * Sets up all required test objects.
	 * */
	@BeforeClass
	public static void before() {
		testMdl = new Connect4Game();
    	testCtrl = new Connect4Controller(testMdl);
    	testView = new Connect4View(testMdl, testCtrl);
	}
	
	/**
	 * Tests whether the GUI displays game stats correctly (using Player 1 name as an example).
	 * */
	@Test
	public void displayStatsTest() {
    	String expected = testMdl.getPlayerX().getName();
    	String actual = testView.getPlayer1NameField().getText();
    	assertEquals(expected, actual);
	}
	
	/**
	 * Tests whether clicking a column drop button updates the Model board correctly.
	 * */
	@Test
	public void colDropBtnTest() {
		testView.getColDropBtn().doClick();
		char expected = 'X';
		char actual = testMdl.getBoard().getBoard()[5][0];
		assertEquals(expected, actual);
	}
	
	/**
	 * Tests whether the GUI fills the correct cell of the graphical board with the correct colour according to the Model.
	 * */
	@Test
	public void boardFillTest() {
		testMdl.moveIsMade(1, 'X');
		Color expected = Color.RED;
		Color actual = testView.getBottomLeftBoardCell().getBackground();
		assertEquals(expected, actual);
	}

	/**
	 * Tears down test objects after finishing tests.
	 * */
	@AfterClass
	public static void after() {
		testMdl = null;
		testCtrl = null;
		testView = null;
		assertNull(testMdl);
		assertNull(testCtrl);
		assertNull(testView);
	}
	
}
