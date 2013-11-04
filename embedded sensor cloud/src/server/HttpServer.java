package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
public class HttpServer
{
	private Socket clientSocket = null;
	private ServerSocket serverSocket = null;
	
	//CONSTRUCTOR
	HttpServer()
	{
		try 
		{
			serverSocket = new ServerSocket(8080); //build a socket on port 8080
		} catch (IOException e) 
		{
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
				} catch (IOException e)
				{
				System.err.println("Accept failed.");
				}
			}
	}
	
	public void close() //method to close server-socket
	{
		try
		{
			serverSocket.close();
		} catch (IOException e)
		{
			System.err.println("Failed to close Server-Socket.");
		}
	}
}
