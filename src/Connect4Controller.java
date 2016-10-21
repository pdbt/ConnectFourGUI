import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;

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
	
	/**
	 * Brings up a dialog showing the last 5 match histories from a database.
	 */
	public void showMatchHistory() {
		int no_of_rows = 0;
		String displayedMatches = "";
		
		try {
			// connect to database and get match records
			Connection conn = DriverManager.getConnection
					("jdbc:derby:HistoryDB;create=true","connect4","connect4");  
			
			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery("SELECT * FROM matchHistory");
			
			// get most recent 5 match records to display
			Stack<String> stack = new Stack<String>();		
			while (rs1.next()) {
				no_of_rows++;
				stack.push(rs1.getString(1));
			}
			for (int i=0;i<5;i++) {
				if (stack.empty()) {
					break;
				}
				displayedMatches+=stack.pop()+"\n";
			}
			rs1.close();
			
			// clear database table once it reaches 20 stored records in order to prevent bloat
			if (no_of_rows > 20) {
				Statement stmt2 = conn.createStatement();
				stmt2.executeUpdate("TRUNCATE TABLE matchHistory");	
			}
			
			conn.close();	// close DB connection
		} catch(SQLException e) {
			System.out.println("SQL exception occured" + e);
		}
		
		JOptionPane.showMessageDialog(view.getFrame(), displayedMatches);
	}

}
