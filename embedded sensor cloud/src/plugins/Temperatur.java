package plugins;

import server.Plugin;
import server.PluginManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Temperatur implements Plugin {	

	@Override
	public String execPlugin(String param, Socket socket) {
		return readFromDatabase(param);
	}
	
	private String readFromDatabase(String param) {			
		int page;
		int bottomLimit;
		int topLimit;
		int sqlRows;
		String response = "";
		String error = "";
		Connection conn = connectToDatabse();
		Statement statement;
		
		response = prepareResponse();
		error = response;
		
		try {
			statement = conn.createStatement();
			String queryString;
			if(param == null) {
				page = 1;
			}else{
				page = Integer.parseInt(param);
				if(page < 1)
				{
					return error + "<p style =\"text-align: center;\"> Falscher Parameter! </p>";
				}
			}			    
			topLimit = page * 15;
			bottomLimit = topLimit - 15;
			queryString = "USE EmbeddedSensorCloud"
					+ " "
					+ "SELECT count(*) FROM TEMPERATURE";
			
			ResultSet rs = statement.executeQuery(queryString);
			rs.next();
			sqlRows = Integer.parseInt(rs.getString(1)); //number of entries in table
			
			if(sqlRows < page*15-14)
			{
				return error + "<p style =\"text-align: center;\"> Parameter zu gross! </p>";
			}
			
			queryString = "USE EmbeddedSensorCloud"
					+ " "
					+ "SELECT * FROM TEMPERATURE "
					+ "WHERE MEASUREMENTNUMBER <= " + sqlRows + " - " + bottomLimit + " AND MEASUREMENTNUMBER > " + sqlRows + " - " + topLimit 
					+ "ORDER BY MEASUREMENTNUMBER DESC";
			
		    rs = statement.executeQuery(queryString);		   
		    
		    response += "<p style=\"text-align:center;\">"	
					+ tableNavigation(param, sqlRows)
					+ "</p>";
			
		    response += "<table style=\"margin:auto; border-bottom: 1px solid black; border-top: 1px solid black;\">" 
						+ "<colgroup><col width=\"40\"><col width=\"80\"><col width=\"220\"> </colgroup>"	
						+ "<tr style=\"text-align: center;\"><th>ID</th><th>Temp.</th><th>Datum</th></tr>";
		    
		    while(rs.next()) {
		    	response += "<tr style=\"text-align: center;\"><td>"+rs.getString(1)+"</td><td>"
		    			+ rs.getString(2)+"&degC</td><td>"
		    			+ rs.getString(3)+"</td></tr>";
		    }
		    
		    response += "</table>"
					+ "<p style=\"text-align:center;\">"
					+ tableNavigation(param, sqlRows)	    	    
					+ "</p>";
		   
		}
		catch (SQLException e) {
			System.err.println("SQL Error in Temperatur.");
			return error + "<p style =\"text-align: center;\"> Fehler mit Datenbankverbindung! </p>";
		}
		catch(NumberFormatException e){
			System.err.println("NumberFormat Error in Temperatur.");
			return error + "<p style =\"text-align: center;\"> Falscher Parameter! </p>";
		}
		
		return response;
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
        	 System.err.println("Error in Temperatur.connectToDatabase().");
        	 return null;
		 }
	}
	
	private String tableNavigation(String param, int sqlEntries) {
		String tableNavi="";
		
		if(param != null) {		
			int page = Integer.parseInt(param);
		    int nextPage = page+1;
		    int prevPage = page-1;
			
		    if(page > 1) {
		    	tableNavi += "<a style=\"text-decoration: none; padding-right: 30px;\"" 
						+ "href=\"http://localhost:8080/Temperatur/" + prevPage + "\">"
						+ "<< PREVIOUS</a>";
				
		    	if(page*15 < sqlEntries) {
			    	tableNavi += "<a style=\"text-decoration: none; padding-left: 30px;\"" 
						+ "href=\"http://localhost:8080/Temperatur/" + nextPage + "\">"
						+ "NEXT >></a>";
		    	}else {
		    		tableNavi += "<span style=\"color: grey; padding-left: 30px;\">NEXT >></span>";
		    	}
			}
				
			if(page == 1)
			{
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
	
	private String prepareResponse() {
		String prepResponse = "";
		String buffer = "";
		FileReader fr;
		PluginManager myPluginManager = new PluginManager();
		
		prepResponse = "HTTP/1.1 200 OK\n"
				+ "Content-Type: text\n"
				+ "\r\n";

		try {
			fr = new FileReader("./src/server/index.html");		

			BufferedReader br = new BufferedReader(fr);
			while((buffer = br.readLine()) != null)
			{	
				prepResponse += buffer;
			}
			br.close();
		}catch (IOException e) {
			System.err.println("Failed to open File in Temperatur.");
		}
		
		prepResponse += myPluginManager.listPlugins();
		return prepResponse;
	}

}
