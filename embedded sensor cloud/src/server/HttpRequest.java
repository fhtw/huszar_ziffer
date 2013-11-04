package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HttpRequest implements Runnable {
	
	private Socket _socket;
	private String _param = null;

	// Constructor
    HttpRequest(Socket socket) //raus nicht nur exception immer betimmten fehler thrown
    {
            try {
            	this._socket = socket;
            } 
            catch (Exception e) {
            	System.err.println("Accept failed.");
            }
    }

    // Implement the run() method of the Runnable interface.
    public void run()
    {
    	try {
    		processRequest();
    		HttpResponse response = new HttpResponse(_socket, _param);
    		response.processResponse();    		
    	} 
    	catch (Exception e) {
    		System.err.println(e);
    	}
    }

    private void processRequest()
    {
    	BufferedReader in;
		
    	try {
			in = new BufferedReader(
			        new InputStreamReader(_socket.getInputStream()));
			
			_param = in.readLine();
	    	
	    	if(_param.length() >= 15)
	    	{
	    	   	_param = _param.substring(5, (_param.length()-9));
	    	}else
	    	{
	    		_param = null;
	    	}	    	
		} 
    	catch (IOException e) {
    		System.err.println(e);
		}
    	
    	
    }
}
