package plugins;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

import javax.xml.parsers.ParserConfigurationException;

import server.OsmSaxParser;
import server.Plugin;
import server.PluginManager;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Navigation implements Plugin {
	
	List<String> citiesList = new ArrayList <String>();
	String streetname;
	public static Hashtable<String,List <String> > cityHashtable;
	@Override
	public String execPlugin(String param, Socket socket) {
		
		String response = prepareResponse();
		
		response += "<form name=\"input\" action=\"http://localhost:8080/Navigation/\" method=\"get\">" + 
					"Username: <input type=\"text\" name=\"streetname\">" + 
					"<input type=\"submit\" value=\"Submit\">" +
					"</form>";
		
		if(param == null){
			try {
				 OsmSaxParser saxParser = new OsmSaxParser();
				 cityHashtable = new Hashtable<String, List <String> >();
				 cityHashtable = saxParser.getHashtable();
				 
			} catch (ParserConfigurationException e) {
				System.out.print("ParserConfigurationException thrown!");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.print("Exception thrown in saxParser!");
			}
		}else{
			if(cityHashtable == null){
				System.out.print("hashtable is null");
			}
			streetname = param.substring(12);
			System.out.println(streetname);
			citiesList = getCityOf(streetname);
			if(citiesList != null){
				for(int i = 0;i<citiesList.size();i++){
					response += "<p>" + citiesList.get(i).toString() + "</p>";
				}
			}else{
				response += "<p>" + "There is no street registered with that name in the openstreetmap!" + "</p>";
			}
		}
		return response;
	}
	
	public List<String> getCityOf(String streetname){
		  
		  List<String> list = new ArrayList <String>();
		  list = cityHashtable.get(streetname);
		  return list;  
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
			System.err.println("Failed to open File in Navigation.");
		}
		
		prepResponse += myPluginManager.listPlugins();
		return prepResponse;
	}
}
