package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpResponse {
	
	private Socket _socket;
	private String statusline = "HTTP/1.1 200 OK";
	private String crlf = "\r\n";
	private String contentHtml = "Content-Type: text/html";
	private String pathOfFrontpage = "./src/server/index.html";
	private String[] _paramArray = null;
	private String _pluginResponse = null;
	
	HttpResponse(Socket s, String[] paramArray)//clientsocket and parameter are saved as private var.
	{
		_socket = s;
		_paramArray = paramArray;
	}
	
	public void processResponse()//write the index.html in PrintWriter
    {												//and check Plugins
		PluginManager myPluginManager = new PluginManager();
		if(_paramArray[0] == null)
		{
			String buffer;	    		
			PrintWriter out;
			try {
				out = new PrintWriter(_socket.getOutputStream());
				out.println(statusline);
		   		out.println(contentHtml);
		   		out.println(crlf); 	
	   		
    			FileReader fr = new FileReader(pathOfFrontpage);
    			
	    		if(fr != null)
	    		{
	   				BufferedReader br = new BufferedReader(fr);
	   				while((buffer = br.readLine()) != null)
	   				{	
	   					out.println(buffer);
    				}
	    			br.close();
	    		}
	    		out.println(myPluginManager.listPlugins());
		   		out.println("</div>");
		   		out.println("</body>");
		   		out.println("</html>");
		   		out.flush();
	    		out.close();
	    		_socket.close();
	    	} catch (FileNotFoundException e)
	    	{
	   			System.err.println("File not found.");
	   		} catch (IOException e) {
				System.err.println("Failed to open File.");
			}
		}
		else
		{
			String buffer;						    		
			PrintWriter out;
			try {
				out = new PrintWriter(_socket.getOutputStream());
				out.println(statusline);
		   		out.println(contentHtml);
		   		out.println(crlf);
		   		
    			FileReader fr = new FileReader(pathOfFrontpage);
    			
	    		if(fr != null)
	    		{
	   				BufferedReader br = new BufferedReader(fr);//write frontpage to Printwriter
	   				while((buffer = br.readLine()) != null)
	   				{	
	   					out.println(buffer);
    				}
	    			br.close();
	    		}
	    		_pluginResponse = myPluginManager.execPlugin(_paramArray);
		   		//give parameter to Pluginmanager and write response String into Printwriter
		   		out.println(myPluginManager.listPlugins());
		   		out.println(_pluginResponse);
		   		out.println("</div>");
		   		out.println("</body>");
		   		out.println("</html>");
		   		out.flush();// printwriter is sent to browser
	    		out.close();
	    	} catch (FileNotFoundException e)
	    	{
	   			System.err.println("File not found.");
	   			System.exit(1);
	   		} catch (IOException e) {
				System.err.println("Failed to open File.");
			}
			finally {
				try {
					_socket.close();
				} catch (IOException e) {
					System.err.println("Failed to close socket.");
				}
			}
		}
    }
}
