package plugins;

import server.Plugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Temperatur implements Plugin {
	

	@Override
	public String execPlugin(String param) {
		return readFromDatabase(param);
	}
	
	private String readFromDatabase(String param) {	
		
		String measureTable = "<table style='margin:auto;'>" + 
				"<tr><th>ID</th><th>Temperatur</th><th>Datum</th></tr>";
		int rowCount = 0;
		
		Connection conn = connectToDatabse();
		Statement statement;
		try {
			statement = conn.createStatement();
			String queryString;
			if(param == null) {
				queryString = "use EmbeddedSensorCloud"
					+ " "
					+ "SELECT * FROM TEMPERATURE "
					+ "ORDER BY MEASUREMENTNUMBER DESC";
			}else{
				int page = Integer.parseInt(param);
				int topLimit = page * 15;
				int bottomLimit = topLimit - 15;;
				queryString = "use EmbeddedSensorCloud"
						+ " "							
						+ "SELECT * FROM TEMPERATURE "
						+ "WHERE MEASUREMENTNUMBER <"+topLimit+" AND MEASUREMENTNUMBER >"+bottomLimit 
						+ "ORDER BY MEASUREMENTNUMBER DESC";
			}
		    ResultSet rs = statement.executeQuery(queryString);
		    
		    while (rs.next() && rowCount < 14) {
		    	measureTable += "<tr><td>"+rs.getString(1)+"</td><td>"
		    			+rs.getString(2)+"</td><td>"
		    			+rs.getString(3)+"</td><td></tr>";
		    	rowCount++;
		    }
		}
		catch (SQLException e) {
			System.err.println(e);
		}
		catch(NumberFormatException e){
			return "<p style =\"text-align: center;\"> Falscher Parameter! </p>";
		}
	    
		measureTable += "</table>";
		return measureTable;
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
