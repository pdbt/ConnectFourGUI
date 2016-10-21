import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Connect4View is the View class responsible for the program's user interface.
 * 
 * @author Patricia Danielle Tan
 */
public class Connect4View implements Observer, ActionListener {

	private Connect4Game model;
	private Connect4Controller controller;
	private JFrame frame;
	private JPanel statsPanel;
	private JPanel gamePanel;
	private JPanel boardPanel;
	private JPanel buttonPanel;
	private static Color yellow = new Color(255, 215, 0);
	private static Color navy = new Color(0, 0, 128);
	
	// stats panel fields, labels & buttons
	private JLabel labelHeader = new JLabel("2-PLAYER CONNECT FOUR");
	private JLabel labelRed = new JLabel("RED");
	private JLabel labelYellow = new JLabel("YELLOW");
	private JLabel labelTurn = new JLabel("Whose turn");
	private JLabel labelScore = new JLabel("Score");
	private JTextField textPlayer1 = new JTextField();
	private JTextField textPlayer2 = new JTextField();
	private JTextField textTurn = new JTextField();
	private JTextField textScore = new JTextField();
	private JButton btnNewGame = new JButton("New game");
	private JButton btnMatchHistory = new JButton("Match history");
	private JButton btnInstructions = new JButton("How to play");
	
	// game panel labels & buttons
	private JButton[] dropButtons = new JButton[7];
	private JLabel[][] boardGrid = new JLabel[6][7];
	
	/**
	 * Constructs a new Connect4View instance.
	 */
	public Connect4View(Connect4Game model, Connect4Controller controller) {
		this.model = model;
		model.addObserver(this);
		this.controller = controller;
		createGameWindow();
		controller.setView(this);
		update(model, null);
	}

	/**
	 * Creates & displays the game window.
	 */
	private void createGameWindow() {
		frame = new JFrame("CONNECT FOUR");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createStatsPanel();
		createGamePanel();
		createButtonPanel();
		
		frame.getContentPane().add(statsPanel);
		frame.getContentPane().add(gamePanel);
		gamePanel.add(buttonPanel);
		
		frame.setBounds(100, 100, 750, 450);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		controller.setNames();
	}
	
	/**
	 * Accesses the main game frame.
	 * @return a JFrame object
	 */
	public JFrame getFrame() {
		return this.frame;
	}
	
	/**
	 * Creates the stats panel, which displays player info and game option buttons.
	 */
	private void createStatsPanel() {
		statsPanel = new JPanel();
		statsPanel.setLayout(null);
		statsPanel.setBackground(Color.WHITE);
		statsPanel.setBounds(10, 10, 250, 407);
		
		labelHeader.setBounds(36, 23, 180, 16);
		labelRed.setBounds(20, 74, 44, 16);
		textPlayer1.setBounds(120, 62, 113, 40);
		labelYellow.setBounds(20, 113, 70, 16);
		textPlayer2.setBounds(120, 101, 113, 40);
		labelTurn.setBounds(20, 152, 77, 16);
		textTurn.setBounds(120, 140, 113, 40);
		labelScore.setBounds(20, 191, 34, 16);
		textScore.setBounds(120, 179, 113, 40);
		btnNewGame.setBounds(20, 246, 214, 40);
		btnMatchHistory.setBounds(20, 298, 214, 40);
		btnInstructions.setBounds(20, 350, 214, 40);

		labelRed.setForeground(Color.RED);
		labelYellow.setForeground(yellow);

		textPlayer1.setEditable(false);
		textPlayer2.setEditable(false);
		textTurn.setEditable(false);
		textScore.setEditable(false);
		
		btnNewGame.addActionListener(this);
		btnMatchHistory.addActionListener(this);
		btnInstructions.addActionListener(this);
		
		statsPanel.add(labelHeader);
		statsPanel.add(labelRed);
		statsPanel.add(textPlayer1);
		statsPanel.add(labelYellow);
		statsPanel.add(textPlayer2);
		statsPanel.add(labelTurn);
		statsPanel.add(textTurn);
		statsPanel.add(labelScore);
		statsPanel.add(textScore);
		statsPanel.add(btnNewGame);
		statsPanel.add(btnMatchHistory);
		statsPanel.add(btnInstructions);
	}
	
	/**
	 * Creates the game panel, displaying the game board itself.
	 */
	private void createGamePanel() {
		gamePanel = new JPanel();
		gamePanel.setLayout(null);
		gamePanel.setBackground(Color.WHITE);
		gamePanel.setBounds(270, 10, 470, 407);
		
		boardPanel = new JPanel();
		gamePanel.add(boardPanel);
		boardPanel.setBackground(Color.WHITE);
		boardPanel.setBounds(6, 50, 458, 350);
		boardPanel.setLayout(new GridLayout(6, 7, 0, 0));
		
		for (int row=0;row<boardGrid.length;row++) {
			for (int col=0;col<boardGrid[row].length;col++) {
				boardGrid[row][col] = new JLabel();
				boardGrid[row][col].setBorder(BorderFactory.createLineBorder(navy, 4));
				
				boardGrid[row][col].setOpaque(true);
				boardPanel.add(boardGrid[row][col]);
			}
		}
	}
	
	/**
	 * Creates a panel for the column drop buttons.
	 */
	private void createButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setBounds(6, 6, 458, 40);
		buttonPanel.setLayout(new GridLayout(0, 7, 0, 0));
		
		for (int i=0;i<dropButtons.length;i++) {
			dropButtons[i] = new JButton("⬇");
			dropButtons[i].addActionListener(this);
			buttonPanel.add(dropButtons[i]);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		textPlayer1.setText(model.getPlayerX().getName());
		textPlayer2.setText(model.getPlayerO().getName());
		textTurn.setText(model.determineTurn().getName());
		textScore.setText(model.getPlayerX().getWinCount()+" - "+model.getPlayerO().getWinCount());
		
		for (int row=0;row<model.getBoard().getBoard().length;row++) {
			for (int col=0;col<model.getBoard().getBoard()[row].length;col++) {
				if (model.getBoard().getBoard()[row][col] == ' ') {
					boardGrid[row][col].setBackground(Color.WHITE);
				}
				if (model.getBoard().getBoard()[row][col] == 'X') {
					boardGrid[row][col].setBackground(Color.RED);
				}
				if (model.getBoard().getBoard()[row][col] == 'O') {
					boardGrid[row][col].setBackground(Color.yellow);
				}
			}
		}
		frame.repaint();
	}
	
	/**
	 * Contains event handlers for the various buttons.
	 * @param event An ActionEvent
	 */
	public void actionPerformed(ActionEvent event) {
		// column drop button press
		for (int i=0;i<dropButtons.length;i++) {
			if (event.getSource() == dropButtons[i]) {
				if (model.moveIsMade((i+1), model.determineTurn().getToken())) {
					if (model.getWinner().findWinner(model.getBoard())!=' ') {
						controller.endGame();
					}
					textTurn.setText(model.determineTurn().getName());
				}
			}
		}
		// 'New game' button press
		if (event.getSource() == btnNewGame) {
			controller.setNames();
			model.resetGame();
		}
		// 'How to play' button press
		if (event.getSource() == btnInstructions) {
			controller.showInstructions();
		}
		// 'Match history' button press
		if (event.getSource() == btnMatchHistory) {
			controller.showMatchHistory();
		}
	}
	
	// ------------ access methods for unit testing purposes only ------------
	
	/**
	 * Accesses the player 1 name field.
	 * @return a JTextField object
	 */
	public JTextField getPlayer1NameField() {
		return this.textPlayer1;
	}

	/**
	 * Accesses the bottom left cell of the graphical game board.
	 * @return a JLabel object
	 */
	public JLabel getBottomLeftBoardCell() {
		return this.boardGrid[5][0];
	}
	
	/**
	 * Accesses the first column drop button.
	 * @return a JButton object
	 */
	public JButton getColDropBtn() {
		return this.dropButtons[0];
	}
	
}
