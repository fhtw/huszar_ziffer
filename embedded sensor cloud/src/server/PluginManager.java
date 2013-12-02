package server;

import java.io.File;

public class PluginManager {

	private String _pluginResponse = null; // will be written in a <p> in the
											// html file

	public String execPlugin(String[] paramArray) {
		String[] plugins = getFilesFromPath("./src/Plugins");		
		
		for(int i = 0 ; i < plugins.length; i++){
			if(plugins[i].equals(paramArray[0])){
				try {
					Plugin plug = (Plugin)(Class.forName("plugins." + paramArray[0]).newInstance());
					if(paramArray.length < 2){
						_pluginResponse = plug.execPlugin(null);
					}else{
						_pluginResponse = plug.execPlugin(paramArray[1]);
					}
					return _pluginResponse;
				}
				catch(InstantiationException e){
					System.err.println("Could not create instance of class.");
				}
				catch(IllegalAccessException e){
					System.err.println("Could not access.(instance,class)");
				}
				catch(ClassNotFoundException e){
					System.err.println("Class does not exist.");
				}				
			}
		}
		_pluginResponse = "<p style =\"text-align: center;\"> Falscher Parameter! </p>";
		return _pluginResponse;
	}

	public String listPlugins() {
		String[] plugins = getFilesFromPath("./src/Plugins");
		String htmlList = "<div id=\"plugin_navi\" style=\"border-bottom: 1px solid black; border-top: 1px solid black; margin-bottom: 20px;\">"
						+ "<ul style=\"list-style-type:: none; text-align: center;\">";
		
		for(int i = 0;i < plugins.length;i++){
			htmlList += "<li style=\"padding-right: 15px; display: inline;\">" 
					+ "<a style=\"text-decoration: none; font-weight: bold;\"" 
					+ "href=\"http://localhost:8080/" + plugins[i] + "\">" + plugins[i] + "</a> </li>";
		}
		htmlList += "</ul></div>";
		return htmlList;
	}
	
	private String[] getFilesFromPath(String path){
		File dir = new File(path);
		File[] files = dir.listFiles();
		String plugins[] = new String[files.length];
		
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				plugins[i]= files[i].getName().substring(0, files[i].getName().length()-5);
			}
		}
		return plugins;
	}
}
