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
		int rowCount = 0;
		String measureTable = "<p style=\"text-align:center;\">"	
				+ tableNavigation(param)
				+ "</p>";
		
		measureTable += "<table style=\"margin:auto; border-bottom: 1px solid black; border-top: 1px solid black;\">" 
					+ "<colgroup><col width=\"40\"><col width=\"80\"><col width=\"140\"> </colgroup>"	
					+ "<tr style=\"text-align: center;\"><th>ID</th><th>Temp.</th><th>Datum</th></tr>";
		
		Connection conn = connectToDatabse();
		Statement statement;
		try {
			statement = conn.createStatement();
			String queryString;
			if(param == null) {
				queryString = "USE EmbeddedSensorCloud"
					+ " "
					+ "SELECT * FROM TEMPERATUR "
					+ "ORDER BY MEASURENUMBER DESC";
			}else{
				int page = Integer.parseInt(param);
				int topLimit = page * 15;
				int bottomLimit = topLimit - 15;				
			    
				queryString = "USE EmbeddedSensorCloud"
						+ " "							
						+ "SELECT * FROM TEMPERATUR "
						+ "WHERE MEASURENUMBER <" + topLimit + " AND MEASURENUMBER >" + bottomLimit 
						+ "ORDER BY MEASURENUMBER DESC";
			}
		    ResultSet rs = statement.executeQuery(queryString);		   
		    
		    while (rs.next() && rowCount < 14) {
		    	measureTable += "<tr style=\"text-align: center;\"><td>"+rs.getString(1)+"</td><td>"
		    			+ rs.getString(2)+"&degC</td><td>"
		    			+ rs.getString(3)+"</td></tr>";
		    	rowCount++;
		    }		    
		   
		}
		catch (SQLException e) {
			System.err.println(e);
		}
		catch(NumberFormatException e){
			return "<p style =\"text-align: center;\"> Falscher Parameter! </p>";
		}
				
		 measureTable += "</table>"
					+ "<p style=\"text-align:center;\">"
					+ tableNavigation(param)	    	    
					+ "</p>";
		
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
		      return conn;		     
		 }
         catch (Exception e) {
        	 System.err.println(e);
        	 return null;
		 }
	}
	
	private String tableNavigation(String param) {
		String tableNavi="";
		
		if(param != null) {		    	
	    	int page = Integer.parseInt(param);
		    int nextPage = page+1;
		    int prevPage = page-1;
			
		    if(page > 1) {
		    	tableNavi += "<a style=\"text-decoration: none; padding-right: 30px;\"" 
						+ "href=\"http://localhost:8080/Temperatur/" + prevPage + "\">"
						+ "<< PREVIOUS</a>";
				
		    	tableNavi += "<a style=\"text-decoration: none; padding-left: 30px;\"" 
					+ "href=\"http://localhost:8080/Temperatur/" + nextPage + "\">"
					+ "NEXT >></a>";												
			}else {
				tableNavi += "<span style=\"color: grey; padding-right: 30px;\"><< PREVIOUS</span>";
				
				tableNavi += "<a style=\"text-decoration: none; padding-left: 30px;\"" 
						+ "href=\"http://localhost:8080/Temperatur/"+ nextPage + "\"\">"
						+ "NEXT >></a>";
			}
	    }else {
	    	tableNavi += "<span style=\"color: grey; padding-right: 30px;\"><< PREVIOUS</span>";
				
	    	tableNavi += "<a style=\"text-decoration: none; padding-left: 30px;\"" 
					+ "href=\"http://localhost:8080/Temperatur/2\"\">"
					+ "NEXT >></a>";
	    }
		
		return tableNavi;
	}

}
