package plugins;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

import server.Plugin;

public class StaticData implements Plugin {
	
	public String execPlugin(String param) {return null;}

	public String execPlugin(String param, Socket socket) {
		String response = "";
		//String buffer = "";
		
		
		try {
			FileInputStream fileInput = new FileInputStream("./src/server/plugins.txt");
            DataOutputStream socketOut = new DataOutputStream(socket
                    .getOutputStream());
            int read = 0;
            while ((read = fileInput.read()) != -1) {
                socketOut.write(read);
            }
            socketOut.flush();
            fileInput.close();
            socket.close();
        } catch (FileNotFoundException e) {
			System.err.println("Failed to open File.");
		} 
		catch (IOException e) {
			System.err.println("Failed to read File.");
		}
		
		
		/*try {
			//FileReader path = new FileReader("./src/server/fatfreddy4.gif");
			//if (path != null) {
			FileInputStream fileInput = new FileInputStream("./src/server/plugins.txt");
			int data;
				//BufferedReader br = new BufferedReader(path);
				while(( data = fileInput.read()) != -1) {
					response += data;
				}
				while ((buffer = br.readLine()) != null) {
					response += buffer + "<br/>";
				}
				
				//br.close();
			//}
				fileInput.close();
		} 
		catch (FileNotFoundException e) {
			System.err.println("Failed to open File.");
		} 
		catch (IOException e) {
			System.err.println("Failed to read File.");
		}*/
		
		return response;
	}

}
