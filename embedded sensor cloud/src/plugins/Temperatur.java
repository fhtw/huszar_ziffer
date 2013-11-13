package plugins;

import server.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Temperatur implements Plugin {
	

	@Override
	public String execPlugin() {
		return readFromDatabase(10);
	}
	
	private String readFromDatabase(int startDate) {		  
		
		Connection conn = connectToDatabse();
		Statement statement;
		try {
			statement = conn.createStatement();
			String queryString = "use Fahrrad"
					+ " "
					+ "select * from Angestellte";
		    ResultSet rs = statement.executeQuery(queryString);
		    
		    while (rs.next()) {
		         System.out.println(rs.getString(1));
		    }
		} 
		catch (SQLException e) {
			System.err.println(e);
		}
	    
		return "read";
	}
	
	private Connection connectToDatabse() {
         String db_userid = "SteGe";
         String db_password = "123";
         String db_connect = "jdbc:sqlserver://127.0.0.1:1433";
         
         try {
		      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		      Connection conn = DriverManager.getConnection(db_connect,
		               db_userid, db_password);
		      System.out.println("connected");
		      return conn;		     
		 }
         catch (Exception e) {
        	 System.err.println(e);
        	 return null;
		 }
	}

}
