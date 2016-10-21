/**
 * Connect Four
 * <p>
 * This program allows 2 human players to play a computer version of the classic board game Connect Four.
 * The program is written following the MVC pattern.
 * 
 * TODO:
 * JUnit tests - column drop button
 * Javadocs
 * Derby database component
 * 
 * @author Patricia Danielle Tan
 * @version 1.0 - 17.10.2016: Created
 */
public class RunConnect4 {

	/**
	 * Implements Connect4Game functionality and takes user input via GUI.
	 * @param args
	 */
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        createAndShowGUI();
                    }
                }
        );
		
    }
	
    public static void createAndShowGUI() {
    	
    	Connect4Game model = new Connect4Game();
    	Connect4Controller controller = new Connect4Controller(model);
    	Connect4View view = new Connect4View(model, controller);
    	
    }
		
}
