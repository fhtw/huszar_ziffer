package server.test;

import java.net.Socket;

import org.junit.Before;
import org.junit.Test;

import server.HttpResponse;

public class HttpResponseTest {
	
	Socket _socket;
	String[] _paramArray = new String[1];
	
	@Before public void instantiate(){
		
		_socket = new Socket(); 
		_paramArray[0] = "Sportnews";
		return;
	}

	@Test
	public void testProcessResponse() {
		new HttpResponse(_socket, _paramArray);
		return;
	}

}
