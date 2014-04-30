package server;

import java.util.List;

public interface Plugin {
	
	public String execPlugin(String[] param, List<QueryObject> query);// what the plugin should do
}
