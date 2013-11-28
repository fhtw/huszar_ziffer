package plugins;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import server.Plugin;

public class StaticData implements Plugin {

	@Override
	public String execPlugin(String param) {
		String response = "", buffer;
		
		try {
			FileReader path = new FileReader("./src/server/plugins.txt");
			if (path != null) {
				BufferedReader br = new BufferedReader(path);
				
				while ((buffer = br.readLine()) != null) {
					response += buffer + "<br/>";
				}
				
				br.close();
			}
		} 
		catch (FileNotFoundException e) {
			System.err.println("Failed to open File.");
		} 
		catch (IOException e) {
			System.err.println("Failed to read File.");
		}
		
		return response;
	}

}
