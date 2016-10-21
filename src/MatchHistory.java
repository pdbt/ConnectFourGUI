import java.util.ArrayList;

/**
 * The MatchHistory class contains ...bleh fill in later
 * (not sure if this class necessary) 
 * 
 * @author Patricia Danielle Tan
 */
public class MatchHistory {
	
	private String p1Name;
	private String p2Name;
	private String winnerName;
	
	public MatchHistory(String p1Name, String p2Name, String winnerName) {
		this.p1Name = p1Name;
		this.p2Name = p2Name;
		this.winnerName = winnerName;
	}
	
	public String getP1Name() {
		return this.p1Name;
	}
	
	public void setP1Name(String p1Name) {
		this.p1Name = p1Name;
	}
	
	public String getP2Name() {
		return this.p2Name;
	}
	
	public void setP2Name(String p2Name) {
		this.p1Name = p2Name;
	}
	
	public String getWinnerName() {
		return this.winnerName;
	}
	
	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
	}
	
	public String getMatchDetail() {
		return this.p1Name+" vs. "+this.p2Name+" - "+this.winnerName+" won!";
	}
	

//	private ArrayList<String> matchHistories;
//
//	/**
//	 * Constructs a new MatchHistories instance.
//	 * */
//	public MatchHistories() {
//		matchHistories = new ArrayList<>();
//	}
//
//	/**
//	 * Accesses a list of match histories.
//	 * @return an ArrayList of Strings
//	 * */
//	public ArrayList<String> getHistories() {
//		return this.matchHistories;
//	}

//	/**
//	 * Adds a player to the database.
//	 * @param p A Player object
//	 * */
//	public void addPlayer(Player p) {
//		players.put(p.getName(), p.getWinCount());
//	}
	
}
