package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HttpRequest implements Runnable {
	
	private Socket _socket;
	private String[] _paramArray;

	// Constructor
    HttpRequest(Socket socket)
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
    		HttpResponse response = new HttpResponse(_socket, _paramArray);    		
    		response.processResponse();
    	} 
    	catch (Exception e) {
    		System.err.println(e);
    		e.printStackTrace(System.out);
    	}
    }

    private void processRequest()
    {
    	BufferedReader in;
		
    	try {
			in = new BufferedReader(
			        new InputStreamReader(_socket.getInputStream()));
			
			String param = in.readLine();
			
			System.out.println(param);

	    	if(param != null && param.length() >= 15)
	    	{
	    	   	param = param.substring(5, (param.length()-9));
	    	   	_paramArray = param.split("/");
	    	   	for(int i=0; i < _paramArray.length; i++){
	    	   		System.out.println("Param " + i + _paramArray[i]);
	    	   	}
	    	}else
	    	{
	    		_paramArray = new String[1];
	    		_paramArray[0] = null;
	    	}
		} 
    	catch (IOException e) {
    		System.err.println(e);
		}    	
    }
}
