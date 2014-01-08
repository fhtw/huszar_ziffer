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
		String httpHeader = "HTTP/1.1 200 OK\n"
				+ "Content-Type: image/gif\n"
				+ "\r\n";
		int read = 0;
		
		try {
			FileInputStream fileInput = new FileInputStream("./src/server/test.gif");
            DataOutputStream socketOut = new DataOutputStream(socket
                    .getOutputStream());
            
            socketOut.writeBytes(httpHeader);
            
            while ((read = fileInput.read()) != -1) {
                socketOut.writeByte(read);
            }
            socketOut.flush();
            fileInput.close();
            //socket.close();
        } catch (FileNotFoundException e) {
			System.err.println("Failed to open File in StaticData.");
		} 
		catch (IOException e) {
			System.err.println("Failed to read File in StaticData.");
		}

		return null;
	}

}
