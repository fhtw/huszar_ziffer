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
	private String contentHtml = "Content-Type: text";
	private String pathOfFrontpage = "./src/server/index.html";
	private String[] _paramArray;
	private String _pluginResponse = null;
	
	HttpResponse(Socket s, String[] paramArray) //clientsocket and parameter are saved as private variable
	{
		_socket = s;
		_paramArray = paramArray;
	}
	
	public void processResponse() //write the index.html in PrintWriter and check Plugins
    {												
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
	   			BufferedReader br = new BufferedReader(fr);
	   			while((buffer = br.readLine()) != null)
	   			{	
	   				out.println(buffer);
    			}
	    		br.close();

	    		out.println(myPluginManager.listPlugins());
		   		out.println("</div>");
		   		out.println("</body>");
		   		out.println("</html>");
		   		out.flush();
	    		out.close();
	    		_socket.close();
	    	} catch (FileNotFoundException e){
	   			System.err.println("File not found in HttpResponse.");
	   		} catch (IOException e) {
				System.err.println("Failed to open File in HttpResponse.");
			}
		}
		else
		{
			PrintWriter out;
			_pluginResponse = myPluginManager.execPlugin(_paramArray, _socket);
			
			try {
				out = new PrintWriter(_socket.getOutputStream());
		   		
		   		out.println(_pluginResponse);
		   		out.println("</div>");
		   		out.println("</body>");
		   		out.println("</html>");
		   		out.flush();
	    		out.close();
	    		_socket.close();
	   		} catch (IOException e) {
				System.err.println("Failed to close socket in HttpResponse.");
			}
		}
    }
}
