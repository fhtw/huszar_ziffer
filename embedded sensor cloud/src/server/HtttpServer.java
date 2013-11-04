package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
public class HtttpServer
{
	private Socket clientSocket = null;
	private ServerSocket serverSocket = null;
	
	HtttpServer()
	{
		try 
		{
			serverSocket = new ServerSocket(8080); 
		} catch (IOException e) 
		{
        	System.err.println("Could not listen on port: 8080.");
        	System.exit(1);
        }
	}
	
	public void listenAndProcess() throws Exception {
		
			while(true)
			{ 
				try {
					clientSocket = serverSocket.accept();
					HttpRequest request = new HttpRequest( clientSocket );
					Thread thread = new Thread(request);
					thread.start();
				} catch (IOException e)
				{
				System.err.println("Accept failed.");
				}
			}
	}
	
	public void close() throws IOException
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
