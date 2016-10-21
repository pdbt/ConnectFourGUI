import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * test - dl33t l8r
 * 
 * @author patriciadanielle
 */

public class DBtest {
	
	 String url = "jdbc:derby:HistoryDB;create=true"; 
	    //String url="jdbc:derby://localhost:1527/HistoryDB;create=true";

	    String usernameDerby="connect4";
	    String passwordDerby="connect4";
	    Connection conn;
	    
	    public void establishConnection() {
	        try {
	            conn=DriverManager.getConnection(url, usernameDerby, passwordDerby);
	            System.out.println(url+"   connected....");
	        
	        } catch (SQLException ex) {
	            Logger.getLogger(DBtest.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    
	    }
	    
}
