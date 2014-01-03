package server;

import java.net.Socket;

public interface Plugin {
	
	public String execPlugin(String param, Socket socket);// what the plugin should do
}
