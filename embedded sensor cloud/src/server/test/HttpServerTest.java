package server.test;

import static org.junit.Assert.*;

import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;

import server.HttpServer;

public class HttpServerTest {

	
	@Before public void instantiate(){

		ServerSocket serverSocket = null;
	}
	
	@Test
	public void testHttpServer() {
		HttpServer server = new HttpServer();
		return;
	}

}
