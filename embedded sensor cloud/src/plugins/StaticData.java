package plugins;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

import server.Plugin;

public class StaticData implements Plugin {
	
	@Override
	public String execPlugin(String param) {return null;}

	public String execPlugin(String param, Socket s) {
		String response = "";
		//String buffer = "";
		
		try {
			//FileReader path = new FileReader("./src/server/fatfreddy4.gif");
			//if (path != null) {
			FileInputStream fileInput = new FileInputStream("./src/server/plugins.txt");
			int data;
				//BufferedReader br = new BufferedReader(path);
				while(( data = fileInput.read()) != -1) {
					response += data;
				}
				/*while ((buffer = br.readLine()) != null) {
					response += buffer + "<br/>";
				}*/
				
				//br.close();
			//}
				fileInput.close();
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
