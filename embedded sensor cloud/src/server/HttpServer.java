package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
public class HttpServer
{
	private Socket clientSocket = null;
	private ServerSocket serverSocket = null;
	
	public HttpServer()
	{
		try {
			serverSocket = new ServerSocket(8080); //build a socket on port 8080
			Timer timer = new Timer ();
			TimerTask hourlyTask = new TimerTask () {
			    @Override
			    public void run () {
			        simulateTemperatureSensor();
			    }
			};
			//schedule timer to execute every hour
			timer.schedule(hourlyTask, 0l, 1000*60*60);
		} 
		catch (IOException e) {
        	System.err.println("Could not listen on port: 8080.");
        	System.exit(1);
        }
	}
	
	public void listenAndProcess() {		
		while(true)
		{ 
			try {
				clientSocket = serverSocket.accept();//wait for a client
				HttpRequest request = new HttpRequest( clientSocket );
				Thread thread = new Thread(request); //build a new thread 
				thread.start();//one thread go through the run() method one waits for client
			} 
			catch (IOException e) {
				System.err.println("Accept failed.");
			}
		}
	}
	
	public void close() { //method to close server-socket
		try	{
			serverSocket.close();
		} 
		catch (IOException e) {
			System.err.println("Failed to close Server-Socket.");
		}
	}
	
	private void simulateTemperatureSensor() {
		Connection conn = connectToDatabse();
		Statement statement;
		String queryString;
		int lowestTemp = -10;
		int highestTemp = 50;
		int temp = 0;
		Date date = new Date();		
		temp = -lowestTemp + (int)(Math.random() * ((highestTemp - ( -lowestTemp)) + 1));
		
		try {
			statement = conn.createStatement();
			queryString = "USE embeddedSensorCloud"
					+ " "
					+ "INSERT INTO TEMPERATURE (VALUE, MEASUREMENTTIME)"
					+ "VALUES(" + temp + ",'" + new Timestamp(date.getTime()) + "')";
		
			statement.execute(queryString);
		} catch (SQLException e) {
			System.err.println("SQL Error in Temperatur.");
			e.printStackTrace();
		}
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
}
