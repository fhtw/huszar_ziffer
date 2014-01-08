package plugins;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

import server.Feed;
import server.FeedMessage;
import server.FeedParser;
import server.Plugin;
import server.PluginManager;

public class Sportnews implements Plugin {

	@Override
	public String execPlugin(String param, Socket socket) {
		String response = "";
		
		response = prepareResponse();
		
		
		FeedParser parser = new FeedParser("http://feeds.reuters.com/reuters/sportsNews");
	    Feed feed = parser.readFeed();
	    for (FeedMessage message : feed.getMessages()) {
	      response += message;
	    }
		
		return response;
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
			e.printStackTrace();
		}
		
		prepResponse += myPluginManager.listPlugins();
		return prepResponse;
	}
}
