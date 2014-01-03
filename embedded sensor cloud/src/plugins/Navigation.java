package plugins;

import java.net.Socket;

import javax.xml.parsers.ParserConfigurationException;

import server.OsmSaxParser;
import server.Plugin;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Navigation implements Plugin {
	
	List<String> citiesList = new ArrayList <String>();
	String streetname;
	public static Hashtable<String,List <String> > cityHashtable;
	@Override
	public String execPlugin(String param, Socket socket) {
		
		String response = "<form name=\"input\" action=\"http://localhost:8080/Navigation/\" method=\"get\">" + 
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
}
