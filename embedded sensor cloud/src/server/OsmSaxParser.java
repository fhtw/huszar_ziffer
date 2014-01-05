package server;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Parse an OSM XML file and give back a Hashtable with streets and their cities they exitst in.
 * @author Stefan, Georg
 *
 */
public class OsmSaxParser {

	
	private Hashtable<String,List <String> > city_street_ht = new Hashtable<String, List <String> >();

/**
   * Find all way names in the given OSM file.
   * @param file The file.
 * @param streetname 
 * @return 
   * @return The sorted set of way names.
   * @throws ParserConfigurationException
   * @throws SAXException
   * @throws IOException
   */
  private void prepareHashtable(File file) throws ParserConfigurationException,
      SAXException, IOException {
    SAXParserFactory parserFactory = SAXParserFactory.newInstance();
    SAXParser parser = parserFactory.newSAXParser();

    OsmNamesHandler handler = new OsmNamesHandler();
    System.out.print("parsing...");
    parser.parse(file, handler);
    System.out.print("finished parsing!");
    
    city_street_ht = handler.getHashtable();
    
    return;
  }

  private static class OsmNamesHandler extends DefaultHandler {
    private final Stack<String> nodeStack = new Stack<String>();
    private final Stack<String> wayStack = new Stack<String>();
    public Hashtable<String, List <String> > ht = new Hashtable<String, List <String> >();
    private List<String> _list = new ArrayList <String>();
    private List<String> _copyList = new ArrayList <String>();
    private String city;
    private String street;

	public Hashtable<String, List<String>> getHashtable() {
      return ht;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
        Attributes attrs) throws SAXException {
       //System.out.printf("element: uri=%s localName=%s qName=%s\n", uri, localName, qName);
      if("node".equals(qName) ){
    	  nodeStack.push(qName);
      }
      if("way".equals(qName) ){
    	  wayStack.push(qName);
      }
      if ("tag".equals(qName) && (!nodeStack.isEmpty() || !wayStack.isEmpty())) {
		    String key = attrs.getValue("k");
		    if ("addr:city".equals(key)){
		        //_list.add(attrs.getValue("v"));
		    	city = attrs.getValue("v");
		    }
		    if("addr:street".equals(key)) {
		    	//_list.add(attrs.getValue("v"));
		    	street = attrs.getValue("v");
		    	if(street.equals("Rudolf-Zeller-Gasse")){
		    		//System.out.println(street);
		    	}
		    }
		    if(street != null && city != null){
		    	_list.add(city);
		    	if(ht.containsKey(street)){
		    		_copyList = ht.get(street);
		    		if(!containsString(_copyList, _list.get(0))){
			    		ht.remove(street);
			    		//System.out.println("_copyList = " + _copyList + "\n");
			    		//System.out.println("_list = " + _list);
			    		_list.addAll(_copyList);
			    		ht.put(street, _list);
		    		}
		    	}else{
		    		ht.put(street, _list);
		    	}
		    	//System.out.print("city = " +_city + " , street = " + _street + "\n");
		        _list = new ArrayList <String>();
		        _copyList = new ArrayList <String>();
		        //city = null;
		        //street = null;
		    }
	  }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
        throws SAXException {
    	if("node".equals(qName) ){
      	  nodeStack.pop();
      	  city = null;
      	  street = null;
        }
    	if("way".equals(qName) ){
        	  wayStack.pop();
        	  city = null;
        	  street = null;
          }
    }
    
    private boolean containsString(List <String> myList, String search){
    	  for(String str: myList) {
    	      if(str.trim().contains(search))
    	         return true;
    	  }
    	  return false;
    	  }
    
  }  
  
  public Hashtable<String, List<String>> getHashtable() {
		return city_street_ht;
	}

  public OsmSaxParser() throws Exception {
	  try{
		  File osmXmlFile = new File("./austria.osm");
		  System.out.print("austria.osm found!");
		  this.prepareHashtable(osmXmlFile);//find all streets with their cities
	  }catch(FileNotFoundException e){
		  System.out.print("Could not open streetmap!");
	  }
	  catch(Exception e){
		  System.out.print("Problem with SaxParser!");
	  }
	  return;
  }

}
