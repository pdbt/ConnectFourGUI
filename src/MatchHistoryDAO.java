import java.util.List;

/**
 * not sure if needed we shall see
 * 
 * @author patriciadanielle
 */
public interface MatchHistoryDAO {

	public List<MatchHistory> getAll();
	public boolean insertMatch(MatchHistory match);
	public boolean deleteMatch(MatchHistory match);
	
}
