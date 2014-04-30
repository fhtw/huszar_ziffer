package server;

public class QueryObject {
	
	private String _key,_value;
	

	
	public QueryObject(String key, String value){
		
		_key = key;
		_value = value;
		
	}

	public String get_key() {
		return _key;
	}



	public String get_value() {
		return _value;
	}
}
