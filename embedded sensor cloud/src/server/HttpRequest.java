package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HttpRequest implements Runnable {
	
	private Socket _socket;
	private String[] _paramArray;
	private List<QueryObject> _queryList = null;

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
    		HttpResponse response = new HttpResponse(_socket, _paramArray,_queryList);    		
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
			System.out.println("Request = " + param);

	    	if(param != null && param.length() >= 15)//größer 15 weil diese länge bedeutet das ein parameter existiert
	    											//-> GET / HTTP/1.1 wäre länge 14; ab 15 steht etwas nach dem slash
	    	{
	    	   	param = param.substring(5, (param.length()-9));
	    	   	_paramArray = param.split("/");
	    	   String[] query =_paramArray[_paramArray.length - 1].split("\\?");//[0] = letzer param; [1] - [unendlich] alle queries
	    	   _paramArray[_paramArray.length-1] = query[0];//letzten param speichern in paramArray
	    	   if(query.length > 1){
		    	   try{
			    		   query =query[query.length - 1].split("&");
				    	   String[] keyValue;
				    	   QueryObject queryObject;
				    	   _queryList = new ArrayList<QueryObject>();
				    	   for(int i=0; i < query.length; i++){
				    	   		//System.out.println("Query " + i +" = " + query[i]);
				    		   //zum ausgeben der queries
				    	   		keyValue = query[i].split("=");
				    	   		queryObject = new QueryObject(keyValue[0],keyValue[1]);
				    	   		_queryList.add(queryObject);
				    	   }
			    	   }catch(ArrayIndexOutOfBoundsException e){
			    		   System.out.println("ArrayIndexOutOfBoundsException : Wrong parameter! It has to be url?key=value!");
			    		   QueryObject queryObject;
			    		   queryObject = new QueryObject("Error","Error");
			    	   		_queryList.add(queryObject);
			    	   }
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
