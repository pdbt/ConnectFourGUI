import javax.swing.JOptionPane;

/**
 * Connect4Controller is the Controller class which facilitates interaction between the Model and View.
 * 
 * @author Patricia Danielle Tan
 */
public class Connect4Controller {
	
	private Connect4Game model;
	private Connect4View view;
	
	/**
	 * Constructs a new Connect4Controller instance.
	 * @param model A Connect4Game object instance
	 */
	public Connect4Controller(Connect4Game model) {
		this.model = model;
	}
	
	/**
	 * Specifies the View instance.
	 * @param view A Connect4View object
	 */
	public void setView(Connect4View view) {
        this.view = view;
    }
	
	/**
	 * Brings up dialogs asking for players to input their names and updates the Model accordingly.
	 */
	public void setNames() {
		String p1Name = JOptionPane.showInputDialog("PLAYER 1 - Enter a nickname:");
		if (p1Name != null) {
			model.getPlayerX().setName(p1Name);
		} else {
			model.getPlayerX().setName("Player 1");
		}
		
		String p2Name = JOptionPane.showInputDialog("PLAYER 2 - Enter a nickname:");
		if (p2Name != null) {
			model.getPlayerO().setName(p2Name);
		} else {
			model.getPlayerO().setName("Player 2");
		}
	}

	/**
	 * Brings up a dialog when the game is over and resets the game on dialog exit.
	 */
	public void endGame() {
		if (model.getWinner().getWinnerToken()=='X') {
			model.incrementWinCount(model.getPlayerX());
			int input = JOptionPane.showOptionDialog(view.getFrame(), model.getPlayerX().getName()+" wins!", "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
			if (input == JOptionPane.DEFAULT_OPTION || input == JOptionPane.OK_OPTION) {
				model.newMatch();
			}
		}
		if (model.getWinner().getWinnerToken()=='O') {
			model.incrementWinCount(model.getPlayerO());
			int input = JOptionPane.showOptionDialog(view.getFrame(), model.getPlayerO().getName()+" wins!", "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
			if (input == JOptionPane.DEFAULT_OPTION || input == JOptionPane.OK_OPTION) {
				model.newMatch();
			}
		}
		if (model.getWinner().getWinnerToken()=='T') {
			int input = JOptionPane.showOptionDialog(view.getFrame(), "It's a tie!", "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
			if (input == JOptionPane.DEFAULT_OPTION || input == JOptionPane.OK_OPTION) {
				model.newMatch();
			}
		}
	}
	
	/**
	 * Brings up a dialog with game instructions.
	 */
	public void showInstructions() {
		JOptionPane.showOptionDialog(view.getFrame(), "Click on one of the buttons above the game board to drop a token into the column below it.\nGet four same-colour tokens in a row vertically, horizontally or diagonally to win!"
				, "How to Play", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
	}

}
