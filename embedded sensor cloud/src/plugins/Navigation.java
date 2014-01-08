package plugins;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;

import javax.xml.parsers.ParserConfigurationException;

import server.OsmSaxParser;
import server.Plugin;
import server.PluginManager;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Navigation implements Plugin {
	
	List<String> citiesList = new ArrayList <String>();
	String utf8 = "UTF-8";
	String streetname;
	public static Hashtable<String,List <String> > cityHashtable;
	static boolean isRefreshing = false;
	@Override
	public String execPlugin(String param, Socket socket) {
		
		String response = prepareResponse();
		
		response += "<p style=\"text-align: center;\">Please type in a streetname and the program will show you in which austrian cities it extists!</p>"
				+ "<div style=\"margin: auto; text-align: center;\">"
				+ "<form name=\"input\" action=\"http://localhost:8080/Navigation/\" method=\"get\">"
				+ "Streetname: <input type=\"text\" name=\"streetname\">"
				+ "<input type=\"submit\" value=\"Submit\">"
				+ "</form>"
				+ "<a style=\"text-decoration: none; color: green;\" href=\"http://localhost:8080/Navigation/refresh\"> refresh map</a>"
				+ "</div>"
				+ "<br/>";
		if(param != null){
			if(param.equals("refresh")){
				if(!isRefreshing){
					cityHashtable = null;
					isRefreshing = true;
					getHashtable();
					isRefreshing = false;
				}else{
					response += "<p style=\"color:orange; text-align:center;\">The map is already refreshing by an other user. Please, wait a minute.</p>";
				return response;
				}
			}else{
				if(cityHashtable == null){
					System.out.print("hashtable is null");
					response += "<p style=\"color:red; text-align: center;\">You have to press \"refresh map\" before you type in a streetname!</p>";
					return response;
				}
				streetname = param.substring(12);
				try {
					streetname = URLDecoder.decode(streetname, utf8);
				} catch (UnsupportedEncodingException e) {
					streetname = null;
					System.out.println("Textinput include non supported character!");
				}
				System.out.println(streetname);
				citiesList = getCityOf(streetname);
				if(citiesList != null){
					for(int i = 0;i<citiesList.size();i++){
						response += "<p style=\"text-align: center;\">" + citiesList.get(i).toString() + "</p>";
					}
				}else{
					response += "<p style =\"text-align: center;\"> There is no street registered with that name in the openstreetmap! </p>";
				}
			}
		}
		return response;
	}
	
	private /*static synchronized*/ void getHashtable() {
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
				+ "Content-Type: text; charset=utf-8\n"
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
